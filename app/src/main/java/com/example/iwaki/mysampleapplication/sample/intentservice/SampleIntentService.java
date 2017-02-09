package com.example.iwaki.mysampleapplication.sample.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class SampleIntentService extends IntentService {


    private static final String TAG = "SampleIntentService";
    private int startedCount;

    // IntentService はデフォルトコンストラクタをもっていないので、デフォルトコンストラクタ
    // を定義する場合は、IntentServiceのデフォルトではないコンストラクタを呼び出すように
    // する必要がある。引数は文字列で何を渡してもかまわない。
    public SampleIntentService() {
        super(SampleIntentServiceActivity.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "★onCreate: サービスが初期化されました");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        // Killされたときの動作を指定
        // return START_STICKY;
        return START_REDELIVER_INTENT;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        startedCount++;


        if (intent == null) {
            Log.i(TAG, "★onHandleIntent: intentにnullがわたってきました。startedCount = " + startedCount);
        } else {
            Log.i(TAG, "★onHandleIntent: time = " + intent.getLongExtra("start_time", 0));
        }
        Log.i(TAG, "★onHandleIntent: startedCount = " + startedCount);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "★onDestroy: サービスが終了しました");
    }
}
