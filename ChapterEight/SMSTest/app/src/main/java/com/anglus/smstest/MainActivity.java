package com.anglus.smstest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView sender;
    private TextView content;

    private IntentFilter receiverFilter;
    private MessageReceiver messageReceiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);

        receiverFilter = new IntentFilter();
        receiverFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        messageReceiver  = new MessageReceiver();
        registerReceiver(messageReceiver , receiverFilter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i("MessageReceiver", "收到短信消息了");

            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus");// 接收短信消息
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            String address = messages[0].getOriginatingAddress();// 获取发送方号码
            String fullMessage = "";
            for (SmsMessage message : messages) {
                fullMessage += message.getMessageBody();// 获取短信内容
            }
            sender.setText(address);
            content.setText(fullMessage);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
    }
}