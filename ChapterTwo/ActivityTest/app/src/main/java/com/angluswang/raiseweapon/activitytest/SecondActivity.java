package com.angluswang.raiseweapon.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Jeson on 2016/7/7.
 * 第二个活动
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_second);

//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity", data);

        Button btn2 = (Button) findViewById(R.id.btn_2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return", "Hello FirstActivity from SecondActivity");
                /** Standard activity result: operation canceled. */
//                public static final int RESULT_CANCELED    = 0;
                /** Standard activity result: operation succeeded. */
//                public static final int RESULT_OK           = -1;
                /** Start of user-defined activity results. */
//                public static final int RESULT_FIRST_USER   = 1;
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /**
     * 在用户按 Back 键返回上一个活动的情况下
     * 进行相关处理，同样也能返回数据给上一个活动
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstActivity from SecondActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}
