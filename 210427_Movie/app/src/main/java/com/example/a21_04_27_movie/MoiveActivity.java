package com.example.a21_04_27_movie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.file.Files;

public class MoiveActivity extends AppCompatActivity {

    MovieDBHelper helper;
    SQLiteDatabase database;
    EditText edTitle,edDirector,edActor,edPoint;
    TextView tvInfo;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moive);

        edTitle = findViewById(R.id.edTitle);
        edDirector = findViewById(R.id.edDirector);
        edActor = findViewById(R.id.edActor);
        edPoint = findViewById(R.id.edPoint);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        helper = new MovieDBHelper(this);
        tvInfo = findViewById(R.id.tvInfo);

        if (id > 0) {
            database = helper.getReadableDatabase();
            String sql = "SELECT * FROM " + helper.TABLE_NAME + " WHERE _id= " + id;
            Cursor cursor = database.rawQuery(sql,null);
            cursor.moveToNext();
            edTitle.setText(cursor.getString(2));
            edDirector.setText(cursor.getString(3));
            edActor.setText(cursor.getString(4));
            edPoint.setText(cursor.getDouble(5)+"");
        }
    }

    public void mOnClicked(View view) {
        switch (view.getId()) {
            case R.id.btnInsert:
                dataInsert();
                init();
                break;

            case R.id.btnSearch:
                dataSearch();
                break;

            case R.id.btnUpdate:
                dataUpdate();
                break;

            case R.id.btnDelete:
                dataDelete();
                break;

            case R.id.btnBack:
                finish();
                break;
        }
    }

    private void dataInsert() {
        println("dataInsert() 호출");

        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("resId",Data.resIds[id]);
        values.put("title",edTitle.getText().toString());
        values.put("director",edDirector.getText().toString());
        values.put("actor",edActor.getText().toString());
        values.put("point",Double.parseDouble(edPoint.getText().toString()));
        database.insert(helper.TABLE_NAME,null,values);
    }

    private void dataSearch() {
        println("dataSelect() 호출");

        database = helper.getReadableDatabase();
        String sql = "select * from " + helper.TABLE_NAME;
        Cursor cursor = database.rawQuery(sql,null);
        for (int i =0; i < cursor.getCount(); i++ ) {
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String title = cursor.getString(2);
            String director = cursor.getString(3);
            String actor = cursor.getString(4);
            double point = cursor.getDouble(5);
            println(id + ", " + title + ", " + director + ", " + actor + ", " + point);
        }
    }

    private void dataUpdate() {
    }

    private void dataDelete() {
    }

    private void init() {
    }

    private void println(String str) {
        tvInfo.append(str + "\n");
    }
}