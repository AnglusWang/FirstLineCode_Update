package com.anglus.smstest;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView sender;
    private TextView content;

    private EditText to, msgInput;
    private Button send;

    private IntentFilter receiverFilter, sendFilter;
    private MessageReceiver messageReceiver;
    private SendStatusReceiver sendStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        receiverFilter = new IntentFilter();
        receiverFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        receiverFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        messageReceiver = new MessageReceiver();
        registerReceiver(messageReceiver, receiverFilter);
    }

    private void initView() {
        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);

        to = (EditText) findViewById(R.id.to);
        msgInput = (EditText) findViewById(R.id.msg_input);
        send = (Button) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                Intent sendIntent = new Intent("SENT_SMS_ACTION");
                PendingIntent pi = PendingIntent.getBroadcast(
                        MainActivity.this, 0, sendIntent, 0);
                smsManager.sendTextMessage(to.getText().toString(), null,
                        msgInput.getText().toString(), null, null);
            }
        });
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

    private class SendStatusReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == RESULT_OK) {
                // 短信发送成功
                Toast.makeText(context, "Send succeeded",
                        Toast.LENGTH_SHORT).show();
            } else {
                // 短信发送失败
                Toast.makeText(context, "Send failed",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
        unregisterReceiver(sendStatusReceiver);
    }
}