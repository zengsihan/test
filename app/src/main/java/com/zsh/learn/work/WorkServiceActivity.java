package com.zsh.learn.work;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.zsh.learn.myapplication.R;

/**
 * Created by zsh on 2017/3/13.
 * 开机自动启动服务的actiity
 */

public class WorkServiceActivity extends Activity {
    private TextView tv;
    ServiceDB serviceDB;
    ServiceInfo serviceInfo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_service);

        tv= (TextView) findViewById(R.id.work_service_tv);
        serviceDB=new ServiceDB(this);
        serviceInfo=serviceDB.getServiceInfo("1");
        if(serviceInfo!=null){
            tv.setText(serviceInfo.getContent());
        }else{
            tv.setText("没有内容");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
