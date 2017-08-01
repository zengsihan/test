package com.zsh.learn.music;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsh on 2017/7/11.
 */

public class MusicDB {
    Context context;
    SQLiteDatabase db;
    public MusicDB(Context context){
        this.context=context;
        MusicDBHelper musicDBHelper=new MusicDBHelper(context);
        db= musicDBHelper.getReadableDatabase();
    }

    /**
     * 添加单首音乐信息
     * @param
     */
    public void addSingleMusicInfo(MusicInfo mi){
        ContentValues cv=new ContentValues();
        cv.put("name",mi.getName());
        cv.put("author",mi.getAuthor());
        cv.put("url",mi.getUrl());
        db.insert("music",null,cv);
    }

    /**
     * 查询一个
     * @param
     * @return
     */
    public MusicInfo findSingleMusicInfo(String url1){
        Cursor cursor=null;
        MusicInfo hi=null;
        cursor=db.rawQuery("select * from music where url=?",new String[]{url1});
        if(cursor!=null){
            if (cursor.moveToNext()){
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                String url=cursor.getString(cursor.getColumnIndex("url"));
                hi=new MusicInfo(name,author,url);
            }
        }
        return hi;
    }

    /**
     * 模糊查询
     * @return
     */
    public List<MusicInfo> mohuCheck(String name1){
        List<MusicInfo> list=new ArrayList<MusicInfo>();
        Cursor cursor=null;

        //select  * from 表名 where 列名 like ？ ,new String []{'%'+传递进来查询的值+'%'};注意用rawQuery方法只能用单引号
        //Cursor cursor=db.rawQuery("select * from LLL where name like ?",new String[]{'%'+name+'%'});//Like代表通配符

        cursor=db.rawQuery("select * from music where name like '%"+name1+"%'",null);
        if(cursor!=null){
            while (cursor.moveToNext()){
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String author=cursor.getString(cursor.getColumnIndex("author"));
                String url=cursor.getString(cursor.getColumnIndex("url"));
                MusicInfo mi=new MusicInfo(name,author,url);
                list.add(mi);
            }
        }
        return list;
    }

}
