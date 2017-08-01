package com.zsh.learn.music;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zsh on 2017/7/11.
 */

public class MusicDBHelper extends SQLiteOpenHelper{

    public MusicDBHelper(Context context) {
        super(context, "my", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table music(id integer PRIMARY KEY autoincrement,name text,author text,url text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
