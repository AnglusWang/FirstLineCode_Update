package com.angluswang.raiseweapon.activitytest;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;

/**
 * Created by Jeson on 2016/7/7.
 * 第二个活动
 */

public class ThirdActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_third);

        Log.d("ThirdActivity", "Task id is " + getTaskId());
    }
}
