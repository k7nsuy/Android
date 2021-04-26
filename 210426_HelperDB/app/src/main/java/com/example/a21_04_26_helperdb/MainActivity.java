package com.example.a21_04_26_helperdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edName,edAge,edPhone;
    TextView tvResult;
    DatabaseHelper dbHelper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        edAge = findViewById(R.id.edAge);
        edPhone = findViewById(R.id.edPhone);

        tvResult = findViewById(R.id.tvResult);
        dbHelper = new DatabaseHelper(this);
    }

    public void mOnClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsert:
                dataInsert();
                break;

            case R.id.btnSelect:
                dataSelect();
                break;

            case R.id.btnSearch:
                dataSearch();
                break;

            case R.id.btnUpgrade:
                dataUpgrade();
                break;

            case R.id.btnDelete:
                dataDelete();
                break;
        }
    }

    private void dataInsert() {
        println("dataInsert() 호출");
        database = dbHelper.getWritableDatabase();
        String name = edName.getText().toString();
        int age = Integer.parseInt(edAge.getText().toString());
        String phone = edPhone.getText().toString();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        values.put("phone",phone);
        database.insert(dbHelper.TABLE_NAME,null,values);
//        String sql = "insert into " +dbHelper.TABLE_NAME+"(name,age,phone) " +
//                "values('"+name+"' , " + age + ", '" + phone + "')";
//        database.execSQL(sql);
        println("레코드 추가");
    }

    private void dataSelect() {
        println("dataSelect() 호출");
        database = dbHelper.getReadableDatabase();
        String sql = "select * from " + dbHelper.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql,null);
        for (int i =0; i < cursor.getCount(); i++ ) {
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String phone = cursor.getString(3);
            println(id + ", " + name + ", " + age + ", " + phone);
        }
    }

    private void dataSearch() {

        database = dbHelper.getReadableDatabase();
        String name = edName.getText().toString();
        String sql = "select * from " + dbHelper.TABLE_NAME +
                " where name='" + name + "'";
        Cursor cursor = database.rawQuery(sql,null);
        cursor.moveToNext();
        edName.setText(cursor.getString(1));
        edAge.setText(cursor.getInt(2));
        edPhone.setText(cursor.getString(3));

    }

    private void dataUpgrade() {

        database = dbHelper.getWritableDatabase();
        String name = edName.getText().toString();
        int age = Integer.parseInt(edAge.getText().toString());
        String phone = edPhone.getText().toString();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        values.put("phone",phone);
        database.update(dbHelper.TABLE_NAME,values,
                "name=?",new String[]{name});
    }
    private void dataDelete() {

        database = dbHelper.getWritableDatabase();
        String name = edName.getText().toString();
        database.delete(dbHelper.TABLE_NAME,"name=?",new String[]{name});
    }

    private void println(String str) {
        tvResult.append(str + "\n");
    }
}