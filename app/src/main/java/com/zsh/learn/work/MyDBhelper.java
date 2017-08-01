package com.zsh.learn.work;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by legend on 2017/3/13.
 * sqlite数据库创建
 */

public class MyDBhelper extends SQLiteOpenHelper {
    public MyDBhelper(Context context){
        super(context,"workservice.db",null,1);//创建数据库

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(id text,content text)");//创建表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
