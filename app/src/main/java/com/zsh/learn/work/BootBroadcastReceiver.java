package com.zsh.learn.work;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by zsh on 2017/3/13.
 * 自定义广播
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
    public final static String ACTION_BOOT_COMPLETED="android.intent.action.BOOT_COMPLETED";

    public BootBroadcastReceiver(){
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_BOOT_COMPLETED)){

            //启动应用，参数为需要自动启动的应用的包名
            Intent serIntent=new Intent(context,BootService.class);
            serIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(serIntent);
            Log.i("TAG","开机程序自动启动，，，");
        }
            //后面的WorkServiceActivity.class就是要启动的服务。
            Intent actIntent=new Intent(context.getApplicationContext(),WorkServiceActivity.class);
            actIntent.setAction("android.intent.action.MAIN");
            actIntent.addCategory("android.intent.category.LAUNCHER");
            actIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(actIntent);
            Log.i("TAG", "开机自动服务自动启动。。。");
    }
}
