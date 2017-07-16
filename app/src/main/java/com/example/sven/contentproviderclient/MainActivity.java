package com.example.sven.contentproviderclient;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String AUTHORITY = "com.example.sven.contentproviderdemo.provider";
    private static final String TAG = "CPClient";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt1:
                //添加数据
                Random random = new Random();
                Uri uriInsert = Uri.parse("content://" + AUTHORITY + "/users");
                ContentValues values = new ContentValues();
                values.put("name", "sven" + random.nextInt(30));
                values.put("age", random.nextInt(20));
                values.put("address", "guangzhou");
                Uri newUri = getContentResolver().insert(uriInsert, values);
                String newId = newUri.getPathSegments().get(1);
                Log.i(TAG, "newId = " + newId);
                break;
            case R.id.bt2:

                break;
            case R.id.bt3:

                break;
            case R.id.bt4:
                //查询数据
                Uri uriQuery = Uri.parse("content://" + AUTHORITY + "/users");
                Cursor cursor = getContentResolver().query(uriQuery, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        String address = cursor.getString(cursor.getColumnIndex("address"));
                        Log.i(TAG, "name = " + name + ", age = " + age + ", address = " + address);
                    }
                    cursor.close();
                }
                break;
            default:
                break;
        }
    }

}
