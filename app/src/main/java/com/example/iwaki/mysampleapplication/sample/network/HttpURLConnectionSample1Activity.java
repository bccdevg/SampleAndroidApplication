package com.example.iwaki.mysampleapplication.sample.network;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.iwaki.mysampleapplication.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class HttpURLConnectionSample1Activity extends AppCompatActivity {

    private Handler handler;
    private TextView getRequestResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection_sample1);

        handler = new Handler();

        getRequestResult = (TextView) findViewById(R.id.getRequestResult);
    }

    // livedoor の天気予報取得APIへアクセス
    public void onGetRequestTapped(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                BufferedReader bufferedReader = null;
                HttpURLConnection httpURLConnection = null;
                StringBuilder stringBuilder = null;
                try {
                    // 石川県金沢市の天気を取得する
                    URL url = new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=170010");
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line);
                        }

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }

                // 取得結果を画面に表示
                if (stringBuilder != null) {
                    final String output = stringBuilder.toString();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            getRequestResult.setText(output);
                        }
                    });

                }

            }
        }).start();
    }
}
