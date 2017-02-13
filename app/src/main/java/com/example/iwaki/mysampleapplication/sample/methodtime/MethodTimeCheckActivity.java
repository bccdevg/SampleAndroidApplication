package com.example.iwaki.mysampleapplication.sample.methodtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.iwaki.mysampleapplication.R;

import static java.lang.System.nanoTime;

public class MethodTimeCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_time_check);
    }

    public void onNoneStaticMethodCheckTapped(View view) {
        Toast.makeText(this, "経過時間(ミリ秒) = " + noneStaticMethod(), Toast.LENGTH_SHORT).show();
    }

    public void onStaticMethodCheckTapped(View view) {
        Toast.makeText(this, "経過時間(ミリ秒) = " + staticMethod(), Toast.LENGTH_SHORT).show();
    }

    // 非staticメソッド。処理時間(ナノ秒単位)を返す
    public long noneStaticMethod(){
        long startTime = nanoTime();

        int result = 0;
        for(int i = 0; i < 100; i++){
            result++;
        }

        return System.nanoTime() - startTime;
    }

    // staticメソッド。処理時間(ナノ秒単位)を返す
    public static long staticMethod(){
        long startTime = nanoTime();

        int result = 0;
        for(int i = 0; i < 100; i++){
            result++;
        }

        return System.nanoTime() - startTime;
    }

}
