package com.example.iwaki.mysampleapplication.sample.asynctask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.iwaki.mysampleapplication.R;

public class SampleAsyncTaskActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private boolean asyncTaskRunning = false;
    private AsyncTask<String, Integer, String> asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_async_task);
    }

    // 画面上のボタン。AsyncTaskの処理を起動させる
    public void onAsyncTaskRunButtonTapped(View view) {
        if (asyncTaskRunning) {
            Toast.makeText(this, "AsyncTaskの処理が実行中です。しばらくお待ちください", Toast.LENGTH_SHORT).show();
        } else {
            asyncTask = new SampleAsyncTask();
            asyncTask.execute("パラメータ");
        }
    }

    public class SampleAsyncTask extends AsyncTask<String, Integer, String> {

        // メインスレッドで実行される初期化処理
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            asyncTaskRunning = true;

            // プログレスダイアログを表示させる
            progressDialog = new ProgressDialog(SampleAsyncTaskActivity.this);

            // プログレスダイアログへプロパティをセット
            progressDialog.setTitle("タイトル");
            progressDialog.setMessage("メッセージ");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setCancelable(true);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                // バックキー押下でプログレスダイアログがキャンセルされた時に実行される処理
                @Override
                public void onCancel(DialogInterface dialog) {
                    // SampleAsyncTaskの処理をキャンセルする
                    asyncTask.cancel(true);
                }
            });

            // プログレスダイアログ表示
            progressDialog.show();
        }

        // 別スレッドで実行される非同期処理
        @Override
        protected String doInBackground(String... params) {
            // 10秒ごとにプログレスダイアログのバーを更新する
            for(int i = 1; i <= 10; i++) {
                // キャンセルされたかどうか確認
                if (isCancelled()) {
                    return "キャンセルされました";
                }

                // 進捗状況をメインスレッドへ報告
                publishProgress(i * 10);

                // 1秒待機
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "処理が完了しました";
        }

        // メインスレッドで実行される、doInBackground内でpublishProgress()が呼び出された時に呼び出される処理。
        // 進捗状況などを受け取ってメインスレッドで処理する
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            // 進捗は増分ではなく、バーのゲージの大きさ(setMax()で指定したのが最大値)に対してどれだけ表示させたいかで指定する
            progressDialog.setProgress(values[0]);
        }

        // メインスレッドで実行される、doInBackgroundの処理がキャンセルされずに完了した際に呼びされる
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(SampleAsyncTaskActivity.this, result, Toast.LENGTH_SHORT).show();
            // プログレスダイアログを閉じる
            progressDialog.dismiss();
            asyncTaskRunning = false;
        }

        // メインスレッドで実行される、doInBackgroundの処理がキャンセルされて完了(または途中でreturn)した際に呼び出される
        @Override
        protected void onCancelled(String result) {
            super.onCancelled(result);
            Toast.makeText(SampleAsyncTaskActivity.this, result, Toast.LENGTH_SHORT).show();
            asyncTaskRunning = false;
        }
    }
}

