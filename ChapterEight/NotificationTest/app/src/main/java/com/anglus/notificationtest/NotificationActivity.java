package com.anglus.notificationtest;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;

/**
 * Created by Anglus on 2016/10/18.
 */
public class NotificationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(100);
    }
}
