package com.example.a21_04_26_database01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edDB,edTable;
    TextView tv;
    String tableName;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edDB = findViewById(R.id.edDB);
        edTable = findViewById(R.id.edTable);
        tv = findViewById(R.id.textView2);
    }

    public void mOnClicked(View view) {
        switch (view.getId()) {
            case R.id.btnDB:
                createDB(edDB.getText().toString());
                break;

            case R.id.btnTable:
                createTable(edTable.getText().toString());
                break;

            case R.id.btnInsert:
                insertData();
                break;

            case R.id.btnSelect:
                selectData();
                break;
        }
    }

    private void insertData() {
        println("insertData() 호출");
        if (database == null) {
            println("데이터베이스를 먼저 생성하세요");
            return;
        }
        if (tableName == null) {
            println("테이블을 먼저 생성하세요");
            return;
        }
        String sql = "insert into " + tableName + "(name,age,mobile)" +
                "values('홍길동',20,'010-0000-0001')";
        database.execSQL(sql);
        println("레코드 추가함");
    }

    private void selectData() {
        println("insertData() 호출");
        if (database == null) {
            println("데이터베이스를 먼저 생성하세요");
            return;
        }
        if (tableName == null) {
            println("테이블을 먼저 생성하세요");
            return;
        }
        String sql = "select * from " + tableName;
        Cursor cursor = database.rawQuery(sql,null);

        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);

            println(id + ", " + name + ", " + age + ", " + mobile);
        }
    }

    private void createTable(String name) {
        tableName = name;
        println("createTable() 호출");
        if (database == null) {
            println("데이터 베이스를 먼저 생성하세요");
            return;
        }
        String sql = "create table if not exists " + name + "(" +
                " _id integer primary key autoincrement," + " name text," +
                " age integer," + " mobile text)";
        database.execSQL(sql);
        println("테이블 생성함 : " + name);
    }

    private void createDB(String name) {
        println("createDB() 호출");
        database = openOrCreateDatabase(name,MODE_PRIVATE,null);
        println("데이터베이스 " + name + " 생성됨");
    }

    private void println(String str) {
        tv.append(str+"\n");
    }
}