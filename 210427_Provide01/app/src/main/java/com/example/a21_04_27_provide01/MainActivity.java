package com.example.a21_04_27_provide01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tvResult);
    }

    public void mOnClicked(View view) {
        switch (view.getId()) {
            case R.id.btnInsert:
                insertPerson();
                break;
            case R.id.btnQuery:
                queryPerson();
                break;
            case R.id.btnUpdate:
                updatePerson();
                break;
            case R.id.btnDelete:
                deletePerson();
                break;
        }
    }

    private void insertPerson() {
        String uriString ="content://com.example.a21_04_27_provide01/person";
        Uri uri = new Uri.Builder().build().parse(uriString);
        Cursor cursor = getContentResolver().query(uri,null,null,null,
                null);
        String[] columns = cursor.getColumnNames();
        println("columns count => " + columns.length);
        for (int i = 0; i < columns.length; i++) {
            println("#" + i + " : " + columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name","홍길동");
        values.put("age",20);
        values.put("mobile","010-0000-0001");
        uri = getContentResolver().insert(uri,values);
        println("insert 결과 =>" + uri.toString());
    }

    private void queryPerson() {
        try {
            String uriString ="content://com.example.a21_04_27_provide01/person";
            Uri uri = new Uri.Builder().build().parse(uriString);
            String[] columns = new String[]{"name","age","mobile"};
            Cursor cursor = getContentResolver().query(uri,columns,null,null,
                    "name ASC");
            println("query 결과 : " + cursor.getCount());
            int index = 0;
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));
                println("#" + index + " => " + name + ", " + age + " ," + mobile);
                index++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePerson() {
        String uriString ="content://com.example.a21_04_27_provide01/person";
        Uri uri = new Uri.Builder().build().parse(uriString);
        String selection = "name=?";
        String [] selectionArgs = new String[]{"홍길동"};
        ContentValues values = new ContentValues();
        values.put("name","홍길동");
        values.put("mobile","010-0000-0002");
        int count = getContentResolver().update(uri,values,selection,selectionArgs);
        println("update 결과 : " + count);
    }

    private void deletePerson() {
        String uriString ="content://com.example.a21_04_27_provide01/person";
        Uri uri = new Uri.Builder().build().parse(uriString);
        String selection = "name=?";
        String[] selectionArgs = new String[]{"홍길동"};
        int count = getContentResolver().delete(uri,selection,selectionArgs);
        println("delete 결과 : " + count);
    }

    private void println(String str) {
        tv.append(str + "\n");
    }
}