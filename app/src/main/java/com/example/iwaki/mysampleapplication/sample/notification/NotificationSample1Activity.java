package com.example.iwaki.mysampleapplication.sample.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iwaki.mysampleapplication.MainActivity;
import com.example.iwaki.mysampleapplication.R;

public class NotificationSample1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sample1);
    }

    public void onNotifyTapped(View view) {

        // 押すとMainActivityを開く通知
        // Intentには特にフラグを設定していないので、MainActivityは単純に今開いているこの画面の上に置かれる

        // 通知のプロパティを設定
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                // ステータスバー上に表示される小さいアイコン
                .setSmallIcon(android.R.drawable.ic_notification_overlay)
                // 通知のタイトル
                .setContentTitle("My Notification")
                // 通知の本文
                .setContentText("Details")
                // 通知がタップされた時に通知を削除する
                .setAutoCancel(true);

        // 通知がタップされたときに実行する処理を設定
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // 通知を発行
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
