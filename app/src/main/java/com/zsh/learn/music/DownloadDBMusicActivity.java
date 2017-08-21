package com.zsh.learn.music;

import android.app.DownloadManager;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zsh.learn.myapplication.R;

import java.io.File;

/**
 * Created by zsh on 2017/8/8.
 * 速度太慢了，不用这个
 */

public class DownloadDBMusicActivity extends AppCompatActivity{
    private TextView tv;
    private Button btn;
    private MediaPlayer mediaPlayer;
    private StringBuffer str = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        tv= (TextView) findViewById(R.id.activity_music_tv);
        btn= (Button) findViewById(R.id.activity_music_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                downloadMusic();
            }
        });

    }

    private void downMusic(){

    }


    private void downloadMusic() {
        File file = null;
        file = Tools.createFile(this,file);
        long beginTime = System.currentTimeMillis();
        int flag = Tools.copyBD(this,"localmusic.db",file);
        while(flag==1){
            long useTime = System.currentTimeMillis()-beginTime;
            tv.setText("复制数据库耗时：" +useTime+ " MS");
        }
        MusicInfo mi = Tools.getSingleMusicInfo(file,3);

        String url = mi.getUrl().trim();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir("/MusicDownload/","2.mp3");
        DownloadManager downloadManager = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }

}
