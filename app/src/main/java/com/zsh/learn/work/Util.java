package com.zsh.learn.work;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by legend on 2017/3/13.
 */

public class Util {
    /**
     * 保存是否首次进入app
     * @param context
     */
    public static void setIsFirstInApp(Context context,boolean b){
        SharedPreferences spf=context.getSharedPreferences("isFirstInApp",Context.MODE_PRIVATE);//声明对象
        SharedPreferences.Editor editor=spf.edit();//获得操作体
        editor.putBoolean("first",b);//保存数据
        editor.commit();//提交
    }

    /**
     * 获取是否首次进入app
     * @param context
     * @return
     */
    public static boolean getIsFirstInApp(Context context){
        SharedPreferences spf=context.getSharedPreferences("isFirstInApp",Context.MODE_PRIVATE);
        return spf.getBoolean("first",true);//去除共享参数的数据
    }

}
