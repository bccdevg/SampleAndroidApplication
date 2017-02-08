package com.example.iwaki.mysampleapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iwaki.mysampleapplication.sample.asynctask.SampleAsyncTaskActivity;
import com.example.iwaki.mysampleapplication.sample.intentservice.SampleIntentServiceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 画面上のボタン。AsyncTaskのサンプル画面へ遷移する
    public void onGoToAsyncTaskSampleTapped(View view) {
        startActivity(new Intent(this, SampleAsyncTaskActivity.class));
    }

    // 画面上のボタン。IntentServiceのサンプル画面へ遷移する
    public void onGoToIntentServiceSampleTapped(View view) {
        startActivity(new Intent(this, SampleIntentServiceActivity.class));
    }
}