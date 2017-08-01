package com.zsh.learn.work;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by zsh on 2017/3/13.
 * 服务
 */

public class BootService extends Service{
    ServiceDB serviceDB;
    ServiceInfo serviceInfo=null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceDB=new ServiceDB(this);

        if(Util.getIsFirstInApp(this)){//第一次进入app
            Util.setIsFirstInApp(this,false);//修改共享参数。
            Toast.makeText(this, "已经添加了数据", Toast.LENGTH_SHORT).show();
            //想数据库里添加东西
            serviceInfo=new ServiceInfo("1","已经在开机时自动启动服务，单数此进入此app会向数据库添加此数据");
            serviceDB.Add(serviceInfo);
        }else{
            serviceDB.deleteAll();
            Toast.makeText(this, "已经清除上一次的数据", Toast.LENGTH_SHORT).show();
            Util.setIsFirstInApp(this,true);
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
