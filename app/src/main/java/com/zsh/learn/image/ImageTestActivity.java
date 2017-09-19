package com.zsh.learn.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsh.learn.myapplication.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zsh on 2017/9/12.
 */

public class ImageTestActivity extends AppCompatActivity{
    TextView tv;
    ImageView iv;
    Bitmap imageBitmap = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        tv = (TextView) findViewById(R.id.iamge);
        iv = (ImageView) findViewById(R.id.iv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //加入网络图片地址
                new Task().execute("http://img.quwenjiemi.com/2014/0701/thumb_420_234_20140701112917406.jpg");// ok
//                new Task().execute("https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s160-c/A%252520Photographer.jpg");// fail
            }
        });
    }

    Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123){
                iv.setImageBitmap(imageBitmap);
                SavaImage(imageBitmap, Environment.getExternalStorageDirectory().getPath()+"/Test");
            }
        };
    };



    /**
     * 获取网络图片
     * @param imageurl 图片网络地址
     * @return Bitmap 返回位图
     */
    public Bitmap GetImageInputStream(String imageurl){
        Log.i("aa","GetImageInputStream(),url="+imageurl);
        URL url;
        HttpURLConnection connection=null;
        Bitmap bitmap=null;
        try {
            url = new URL(imageurl);
            connection=(HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(6000); //超时设置
            connection.setReadTimeout(10*1000);// 传递数据的超时时间
            connection.setDoInput(true);
            connection.setUseCaches(false); //设置不使用缓存
            InputStream inputStream=connection.getInputStream();
            bitmap= BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            Log.i("aa","GetImageInputStream(), ok");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("aa","GetImageInputStream(), fail, Exception="+e);
        }
        return bitmap;
    }



    /**
     * 异步线程下载图片
     *
     */
    class Task extends AsyncTask<String, Integer, Void> {

        protected Void doInBackground(String... params) {
            imageBitmap =GetImageInputStream(params[0]);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (imageBitmap !=null){
                Log.i("aa","AsyncTask, onPostExecute, imageBitmap != null , bitmap="+imageBitmap);
                Message message=new Message();
                message.what=0x123;
                handler.sendMessage(message);
            }else {
                Log.i("aa","AsyncTask, onPostExecute, imageBitmap == null");
            }
        }
    }

    /**
     * 保存位图到本地
     * @param bitmap
     * @param path 本地路径
     * @return void
     */
    public void SavaImage(Bitmap bitmap, String path){
        File file=new File(path);
        FileOutputStream fileOutputStream=null;
        //文件夹不存在，则创建它
        if(!file.exists()){
            file.mkdir();
        }
        try {
            fileOutputStream=new FileOutputStream(path+"/"+"test.jpg");
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
