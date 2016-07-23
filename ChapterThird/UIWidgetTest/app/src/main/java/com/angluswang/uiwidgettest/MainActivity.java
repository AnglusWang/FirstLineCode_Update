package com.angluswang.uiwidgettest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

        Button btnAlertDialog = (Button) findViewById(R.id.alert_dialog);
        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("AlertDialog...This is Title");
                ad.setMessage("Something important");
                ad.setCancelable(true);
                ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Toast.makeText(MainActivity.this, "Click onCancel...",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Click Ok...",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Click Cancel...",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                ad.show();
            }
        });

        Button btnProgressDialog = (Button) findViewById(R.id.progress_dialog);
        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setTitle("ProgressDialog ... This is Title");
                pd.setMessage("Please waiting .... This is Meg");
                pd.setCancelable(true);
                pd.show();
            }
        });
    }
}