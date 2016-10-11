package com.anglus.broadcastbestpractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private EditText et_account, et_password;
    private Button btn_login;
    private CheckBox cb_remember_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        cb_remember_pass = (CheckBox) findViewById(R.id.remember_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账户和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            et_account.setText(account);
            et_password.setText(password);
            cb_remember_pass.setChecked(true);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = et_account.getText().toString();
                String password = et_password.getText().toString();
                if (account.equals("admin") && password.equals("123456")) {
                    editor = pref.edit();
                    if (cb_remember_pass.isChecked()) {// 检查复选框是否被选中
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", account);
                        editor.putString("password", password);
                    } else {
                        editor.clear();// 将 pref 文件中的数据全部清除掉
                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
