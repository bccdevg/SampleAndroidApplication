package com.example.iwaki.mysampleapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TempTestActivity extends AppCompatActivity {

    private TextView resultMessage1;
    private TextView resultMessage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_test);

        resultMessage1 = (TextView) findViewById(R.id.resultMessage1);
        resultMessage2 = (TextView) findViewById(R.id.resultMessage2);

    }

    public void onButtonATapped(View view) {
        Log.d("★", "onButtonATapped: ");

    }

    public void onButtonBTapped(View view) {
        Log.d("★", "onButtonBTapped: ");
    }

    public void onButtonCTapped(View view) {
        Log.d("★", "onButtonCTapped: ");
    }
}
