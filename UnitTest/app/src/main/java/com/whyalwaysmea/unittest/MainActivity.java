package com.whyalwaysmea.unittest;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testCache();
            }
        });

        ImageView imageview = (ImageView) findViewById(R.id.iv);
        ClipDrawable background = (ClipDrawable) imageview.getBackground();
        background.setLevel(5000);
    }

    private void testCache() {
        System.out.println("getCacheDir() : " + getCacheDir().getPath());
        System.out.println("getExternalCacheDir() : " + getExternalCacheDir().getPath());

        File cacheFile = new File(getCacheDir(), "cache");
        int cacheSize = 10 * 1024 * 1024;
        final Cache cache = new Cache(cacheFile, cacheSize);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .cache(cache)
                        .build();

                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(60, TimeUnit.SECONDS)
                        .build();
                Request request = new Request.Builder()
                        .url("http://www.artselleasy.com/seditionapi/get_art_list?page=1&limit=10")
                        .cacheControl(cacheControl)
                        .build();
                Call call1 = okHttpClient.newCall(request);
                Response response = null;
                try {
                    response = okHttpClient.newCall(request).execute();
                    response.body().close();;
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String url = "https://publicobject.com/";

                Request build = new Request.Builder()
                        .url("http://www.artselleasy.com/seditionapi/get_art_list?page=1&limit=10")
                        .build();

                /*Retrofit build1 = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .client(okHttpClient)
                        .build();
                Api api = build1.create(Api.class);
                api.get().enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                        Log.e("api:" , response.message() + " .. " + response.body().toString());
                    }

                    @Override
                    public void onFailure(retrofit2.Call<String> call, Throwable t) {

                    }
                });*/

                Call call = okHttpClient.newCall(build);
                try {
                    Response execute = call.execute();
                    Log.d("first : " , "" +  execute.cacheResponse());
                    Log.d("first : " , "" +  execute.networkResponse());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Call call2 = okHttpClient.newCall(build);
                try {
                    Response execute2 = call2.execute();
                    Log.e("execute2 : " , "" +  execute2.cacheResponse());
                    Log.e("execute2 : " , "" +  execute2.networkResponse());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
