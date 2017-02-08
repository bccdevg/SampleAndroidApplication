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
        // これでonStartCommand()のデフォルトの戻り値を変更している
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        startedCount++;

        if (intent == null) {
            Log.i(TAG, "★onHandleIntent: intentにnullがわたってきました。startedCount = " + startedCount);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "★onHandleIntent: startedCount = " + startedCount);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "★onDestroy: サービスが終了しました");
    }
}
