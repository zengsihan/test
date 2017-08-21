package com.zsh.learn.music;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zsh on 2017/8/8.
 */

public class Tools {
    /**
     * 创建db文件
     * @param context
     * @param file
     * @return File
     */
    public static File createFile(Context context, File file){
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){//判断是否有sd卡
            file=new File("data"+File.separator+"data"+File.separator+Environment.getExternalStorageDirectory()
                    +File.separator+"myFIle"+File.separator+"copy.db");
        }else{
            file=new File("data"+File.separator+"data"+File.separator+context.getPackageName()
                    +File.separator+"myFIle"+File.separator+"copy.db");
        }
        if(!file.getParentFile().exists()){//判断父级目录是否存在
            file.getParentFile().mkdirs();//创建父级目录
        }
        if(!file.exists()){//判断文件是否存在
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 从assets复制数据库文件到指定目录文件
     * @param context
     * @param path
     * @param file
     */
    public static int copyBD(Context context,String path,File file){
        AssetManager am=context.getAssets();//获得管理器
        BufferedInputStream bis=null;//输入流
        BufferedOutputStream bos=null;//输出流

        try {
            bis=new BufferedInputStream(am.open(path));//把db路径传进去，用管理器的open方法。
            bos=new BufferedOutputStream(new FileOutputStream(file));//把指定的要复制到的文件放进去。
            int len=0;//长度
            byte[] b=new byte[1024];//一次读的长度
            while((len=bis.read(b))!=-1){//返回值不是-1就接着读，读到-1就不读了，
                bos.write(b,0,len);//写进去
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 1;
    }

    public static MusicInfo getSingleMusicInfo(File file,int id){
        SQLiteDatabase sd = null;
        Cursor cursor = null;
        sd = SQLiteDatabase.openOrCreateDatabase(file,null);
        cursor=sd.rawQuery("select * from music where id="+id,null);
        String name=cursor.getString(cursor.getColumnIndex("name"));
        String author=cursor.getString(cursor.getColumnIndex("author"));
        String url=cursor.getString(cursor.getColumnIndex("url"));
        MusicInfo mi=new MusicInfo(name,author,url);
        return mi;
    }


}
