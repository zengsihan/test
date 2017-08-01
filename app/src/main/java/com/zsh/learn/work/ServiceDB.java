package com.zsh.learn.work;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by legend on 2017/3/13.
 */

public class ServiceDB {
    Context context;
    SQLiteDatabase db;
    public ServiceDB(Context context){
        this.context=context;
        MyDBhelper myDBhelper=new MyDBhelper(context);
        db=myDBhelper.getReadableDatabase();
    }

    //判断是否有这个ID了
    public boolean isHaveThisId(String id){
        boolean f=false;
        Cursor cursor=null;
        cursor=db.rawQuery("select * from info where id=?",new String[]{id});
        if(cursor!=null){
            if(cursor.moveToNext()){
                f=true;
            }
        }
        return f;
    }

    //添加数据
    public void Add(ServiceInfo serviceInfo){
        ContentValues cv=new ContentValues();
        cv.put("id",serviceInfo.getId());
        cv.put("content",serviceInfo.getContent());
        db.insert("info",null,cv);
    }

    //得到这个id的内容
    public ServiceInfo getServiceInfo(String sid){
        Cursor cursor=null;
        ServiceInfo serviceInfo=null;
        cursor=db.rawQuery("select * from info where id=?",new String[]{sid});
        if(cursor!=null){
            if(cursor.moveToNext()){
                String id=cursor.getString(cursor.getColumnIndex("id"));
                String content=cursor.getString(cursor.getColumnIndex("content"));
                serviceInfo=new ServiceInfo(id,content);
            }
        }
        return serviceInfo;
    }

    //得到所有
    public List<ServiceInfo> getAll(){
        List<ServiceInfo> list=new ArrayList<ServiceInfo>();
        Cursor cursor=null;
        cursor=db.rawQuery("select * from info where id=?",null);
        if (cursor != null) {
            while(cursor.moveToNext()){
                String id=cursor.getString(cursor.getColumnIndex("id"));
                String content=cursor.getString(cursor.getColumnIndex("content"));
                ServiceInfo serviceInfo=new ServiceInfo(id,content);
                list.add(serviceInfo);
            }
        }
        return list;
    }

    //删除这个id
    public void deleteId(String id){
        db.delete("info","id=?",new String[]{id});
    }

    //删除全部
    public void deleteAll(){
        db.delete("info",null,null);
    }

}
