package com.zsh.learn.work;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.zsh.learn.myapplication.R;

/**
 * Created by legend on 2017/3/7.
 */

public class WorkProgressBarActivity extends AppCompatActivity {
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workprogress);

        iv=(ImageView) findViewById(R.id.iv_progressbar);
        iv.setImageLevel(10000);

        handler.postDelayed(runnable,2000);
    }
    private  int mUnmber=0;
    private Handler handler=new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if(mUnmber<=10000){
                iv.getDrawable().setLevel(mUnmber);
                handler.postDelayed(runnable,1);
                mUnmber+=100;
            }
        }
    };
}
