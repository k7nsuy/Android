package com.example.a21_04_27_movie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MovieDBHelper extends SQLiteOpenHelper {

    public static String DBName = "movie5.db";
    public String TABLE_NAME = "Movie_Table";
    public static int VERSION = 1;
    String TAG = "MovieDBHelper TAG";

    public MovieDBHelper(@Nullable Context context) {
        super(context, DBName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"onCreate() 호출");
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                " _id integer PRIMARY KEY autoincrement," +
                " resId integer," +
                " title text," +
                " director text," +
                " actor text," +
                " point double)";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i(TAG,"onOpen() 호출");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"onUpgrade() 호출");
    }
}
