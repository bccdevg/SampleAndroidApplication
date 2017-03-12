package com.example.iwaki.mysampleapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iwaki.mysampleapplication.sample.asynctask.SampleAsyncTaskActivity;
import com.example.iwaki.mysampleapplication.sample.intentservice.SampleIntentServiceActivity;
import com.example.iwaki.mysampleapplication.sample.listview.ListViewSampleActivity;
import com.example.iwaki.mysampleapplication.sample.location.LocationSample1Activity;
import com.example.iwaki.mysampleapplication.sample.methodtime.MethodTimeCheckActivity;
import com.example.iwaki.mysampleapplication.sample.network.HttpURLConnectionSample1Activity;
import com.example.iwaki.mysampleapplication.sample.notification.NotificationSample1Activity;
import com.example.iwaki.mysampleapplication.sample.sqlite.SqliteSampleActivity;
import com.example.iwaki.mysampleapplication.sample.wifi.WiFiSampleActivity;

public class MainActivity extends AppCompatActivity {

    static private final int LOCATION_PERMISSION_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 画面上のボタン。一時的な動作確認画面へ遷移する
    public void onGoAoTempTestTapped(View view) {
        startActivity(new Intent(this, TempTestActivity.class));
    }

    // 画面上のボタン。AsyncTaskのサンプル画面へ遷移する
    public void onGoToAsyncTaskSampleTapped(View view) {
        startActivity(new Intent(this, SampleAsyncTaskActivity.class));
    }

    // 画面上のボタン。IntentServiceのサンプル画面へ遷移する
    public void onGoToIntentServiceSampleTapped(View view) {
        startActivity(new Intent(this, SampleIntentServiceActivity.class));
    }

    // 画面上のボタン。MethodTimeCheckのサンプル画面へ遷移する
    public void onGoToMethodTimeCheckTapped(View view) {
        startActivity(new Intent(this, MethodTimeCheckActivity.class));
    }

    // 画面上のボタン。SQLiteSampleのサンプル画面へ遷移する
    public void onGoToSQLiteSampleTapped(View view) {
        startActivity(new Intent(this, SqliteSampleActivity.class));
    }

    // 画面上のボタン。ListViewのサンプル画面へ遷移する
    public void onGoToListViewSampleTapped(View view) {
        startActivity(new Intent(this, ListViewSampleActivity.class));
    }

    // 画面上のボタン。Notificationのサンプル1画面へ遷移する
    public void onNotificationSample1Tapped(View view) {
        startActivity(new Intent(this, NotificationSample1Activity.class));
    }

    // 画面上のボタン。WiFiのサンプル1画面へ遷移する
    public void onWiFiSampleTapped(View view) {
        startActivity(new Intent(this, WiFiSampleActivity.class));
    }

    // 画面上のボタン。HttpURLConnectionのサンプル1画面へ遷移する
    public void onHttpURLConnectionSample1Tapped(View view) {
        startActivity(new Intent(this, HttpURLConnectionSample1Activity.class));
    }


    // 画面上のボタン。LocationSample1のサンプル画面へ遷移する
    public void onGoToLocationSample1Tapped(View view) {

        // 位置情報のパーミッションが許可されている場合だけ画面遷移する

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST);
        }else{
            startActivity(new Intent(this, LocationSample1Activity.class));
        }

    }

    // パーミッション許諾画面表示後の処理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch ((requestCode)) {
            case LOCATION_PERMISSION_REQUEST:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this, LocationSample1Activity.class));
                }
                break;
            default:
                break;
        }
    }
}
