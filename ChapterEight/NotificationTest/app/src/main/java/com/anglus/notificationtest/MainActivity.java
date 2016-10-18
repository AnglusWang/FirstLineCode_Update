package com.anglus.notificationtest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends Activity {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 书上代码已过时，不能用 更新为以下方法

                Notification.Builder builder = new Notification.Builder(getApplicationContext());
                // 设置通知的基本信息：icon、标题、内容
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("My notification");
                builder.setContentText("Hello World!");

                // 控制通知的声音
                Uri soundUri = Uri.fromFile(
                        new File("/system/media/audio/ringtones/basic_tone.ogg"));
                builder.setSound(soundUri);

                // 控制通知的振动 --- 下标偶数表示静止时长，奇数表示振动时长
                long[] vibrates = {0, 1000, 1000, 1000};
                builder.setVibrate(vibrates);

                // 控制 LED 灯
                builder.setLights(Color.RED, 1000, 1000);
                builder.build().flags = Notification.FLAG_SHOW_LIGHTS;

                // 设置系统默认效果
                builder.setDefaults(Notification.DEFAULT_ALL);

                // 设置通知的点击行为：这里启动一个 Activity
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        getApplicationContext(), 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                // 发送通知 id 需要在应用内唯一
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(100, builder.build());
            }
        });
    }
}
