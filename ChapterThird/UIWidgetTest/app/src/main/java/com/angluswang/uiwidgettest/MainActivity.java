package com.angluswang.uiwidgettest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button btn;
    private EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn_button);
        etText = (EditText) findViewById(R.id.et_text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = etText.getText().toString();
                Toast.makeText(MainActivity.this, "EditText 文本框内容为： " + inputText,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
