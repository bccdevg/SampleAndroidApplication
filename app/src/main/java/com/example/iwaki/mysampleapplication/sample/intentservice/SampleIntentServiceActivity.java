package com.example.iwaki.mysampleapplication.sample.intentservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iwaki.mysampleapplication.R;

public class SampleIntentServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_intent_service);
    }

    // 画面上のボタン。サービスをスタートさせる
    public void onStartIntentServiceTapped(View view) {
        Intent intent = new Intent(this, SampleIntentService.class);
        intent.putExtra("start_time", System.currentTimeMillis());
        startService(intent);
    }

    public void voidFunc(){

    }

}
