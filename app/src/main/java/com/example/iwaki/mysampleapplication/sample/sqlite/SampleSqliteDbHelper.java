package com.example.iwaki.mysampleapplication.sample.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

import static android.R.attr.version;

/**
 * Created by Iwaki on 2017/02/23.
 */

public class SampleSqliteDbHelper extends SQLiteOpenHelper {

    /** データベース名 */
    private static final String DB_NAME = "SampleSqliteDbHelper";

    /** データベースバージョン */
    private static final int DB_VERSION = 1;

    // コンストラクタは基本的にはこのような実装にする
    public SampleSqliteDbHelper(Context context) {
        // arg1 : Context
        // arg2 : データベース名を指定。任意
        // arg3 : nullでOK
        // arg4 : データベースのバージョン。スキーマ変更時は参照している定数値を変えることで
        //        onUpgrade()が呼び出されるようになり、onUpgrade()にスキーマ変更の実装を定義する
        super(context, DB_NAME, null, DB_VERSION);
    }

    // バージョン2以降になる場合に実装を行う
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion <= 1){
            // バージョン1 -> 2 への移行分の更新処理を実装する
        }
        if (oldVersion <= 2) {
            // バージョン2 -> 3 への移行分の更新処理を実装する
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SIMPLE(_id INTEGER PRIMARY KEY AUTOINCREMENT, name, kind, updateTime);");

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "チョコレート");
        contentValues.put("kind", "お菓子");
        Date date = new Date();
        contentValues.put("updateTime", date.getTime());

        db.insert("SIMPLE", null, contentValues);
    }


}
