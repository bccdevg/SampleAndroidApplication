package com.example.iwaki.mysampleapplication.sample.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.iwaki.mysampleapplication.R;

public class SqliteSampleActivity extends AppCompatActivity {

    private static final String TAG = "SqliteSampleActivity";
    private SampleSqliteDbHelper sampleSqliteDbHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_sample);

        // データベースオブジェクトを取得
        sampleSqliteDbHelper = new SampleSqliteDbHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // データベースへ接続
        sqLiteDatabase = sampleSqliteDbHelper.getWritableDatabase();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // データベースを切断
        sqLiteDatabase.close();
    }

    public void onSelectButtonTapped(View view) {

        // SAMPLEテーブルのレコードを全件取得
        Cursor cursor = sqLiteDatabase.query("SIMPLE", null, null, null, null, null, null, null);

        // カーソルを先頭にセットする。レコードがない場合はfalseが返る
        if (cursor.moveToFirst()) {
            do {
                // カラムに対応する値を取得
                long id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String kind = cursor.getString(cursor.getColumnIndex("kind"));
                long time = cursor.getLong(cursor.getColumnIndex("updateTime"));

                Log.d(TAG, "★onSelectButtonTapped: _id = " + id + ", name = " + name + ", kind = " + kind + ", time = " + time);

            } while (cursor.moveToNext());

        }

        // 使い終わったカーソルはクローズする
        cursor.close();

    }
}
