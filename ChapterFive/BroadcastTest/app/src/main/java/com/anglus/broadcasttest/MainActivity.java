package com.anglus.broadcasttest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private IntentFilter mIntentFilter;
    private NetworkChangerReceiver mNetworkChangerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("android.net.com.CONNECTIVITY_CHANGE");
        mNetworkChangerReceiver = new NetworkChangerReceiver();
        registerReceiver(mNetworkChangerReceiver, mIntentFilter);
    }

    private class NetworkChangerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(MainActivity.this, "network is available",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "network is unavailable",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class BootCompleteReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(MainActivity.this, "Boot complete",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
