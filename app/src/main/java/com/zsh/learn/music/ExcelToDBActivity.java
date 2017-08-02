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
import android.widget.Toast;

import com.zsh.learn.myapplication.R;

import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by zsh on 2017/8/1.
 */

public class ExcelToDBActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn;
    private MediaPlayer mediaPlayer;
    private StringBuffer str = new StringBuffer();
    private MusicDB musicDB;
    private int num=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        tv= (TextView) findViewById(R.id.activity_music_tv);
        btn= (Button) findViewById(R.id.activity_music_btn);
        musicDB = new MusicDB(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  tv.setText(str); // ok
                //  PlayMusic(); // ok
                // DownlaodMusic();
                ReadExcel();
            }
        });

    }

    private void DownlaodMusic() {
        String url = null;
        // url="http://m2.music.126.net/NYq7If0Alf1wH0b81vrEpw==/6668538022514769.mp3";
        Workbook workbook = null;
        mediaPlayer = new MediaPlayer();
        try {
            workbook =Workbook.getWorkbook(getAssets().open("music.xls"));
            Sheet sheet = workbook.getSheet(0);
            url = sheet.getCell(4,2).getContents().trim();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDestinationInExternalPublicDir("/MusicDownload/","1.mp3");
        DownloadManager downloadManager = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show();
    }

    private void PlayMusic() {
        Workbook workbook = null;
        mediaPlayer = new MediaPlayer();
        try {
            workbook = Workbook.getWorkbook(getAssets().open("music.xls"));
            Sheet sheet = workbook.getSheet(0);
            String url = sheet.getCell(4,2).getContents().trim();
            Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
            mediaPlayer.setDataSource(url);

            //  mediaPlayer.setDataSource("http://m2.music.126.net/NYq7If0Alf1wH0b81vrEpw==/6668538022514769.mp3");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    Toast.makeText(ExcelToDBActivity.this, "play", Toast.LENGTH_SHORT).show();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
            //  Toast.makeText(MusicActivity.this, "播放失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void ReadExcel() {
        try {
            Workbook workbook = Workbook.getWorkbook(getAssets().open("music.xls"));
            // 获取第一张表的对象
            Sheet sheet = workbook.getSheet(0);
            // 行数
            int rows = sheet.getRows();
            // 列数
            int cols = sheet.getColumns();

            long beginTime = System.currentTimeMillis();
            for (int i = 0; i < rows; i++) {
                num++;
                String name = sheet.getCell(1,i).getContents();
                String author = sheet.getCell(2,i).getContents();
                String url = sheet.getCell(4,i).getContents();
                if("".equals(name) || "".equals(author) || "".equals(url) || "null".equals(url)){
                      num--;
                    continue;
                }
                MusicInfo musicInfo = new MusicInfo(name,author,url);
                musicDB.addSingleMusicInfo(musicInfo);
//                Toast.makeText(this, "add music "+name, Toast.LENGTH_SHORT).show();
            }
            long useTime = System.currentTimeMillis()-beginTime;

            Toast.makeText(this, "添加音乐信息到数据库完毕", Toast.LENGTH_SHORT).show();
            tv.setText("一共添加 " +num+ " 首歌曲信息，耗时：" +useTime+ " MS");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
