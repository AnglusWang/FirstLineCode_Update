package com.anglus.providertest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = (Button) findViewById(R.id.add_data);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加数据
                Uri uri = Uri.parse("content://com.anglus.databasetest.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 22.85);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });

        Button delete = (Button) findViewById(R.id.delete_data);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除数据
                Uri uri = Uri.parse(
                        "content://com.anglus.databasetest.provider/book/" + newId);
                getContentResolver().delete(uri, null, null);
            }
        });

        Button update = (Button) findViewById(R.id.update_data);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 更改数据
                Uri uri = Uri.parse(
                        "content://com.anglus.databasetest.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 24.05);
                getContentResolver().update(uri, values, null, null);
            }
        });

        Button query = (Button) findViewById(R.id.query_data);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查询数据
                Uri uri = Uri.parse("content://com.anglus.databasetest.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG, "book name is " + name);
                        Log.d(TAG, "author name is " + author);
                        Log.d(TAG, "pages name is " + pages);
                        Log.d(TAG, "price name is " + price);
                    }
                    cursor.close();
                }
            }
        });
    }
}
