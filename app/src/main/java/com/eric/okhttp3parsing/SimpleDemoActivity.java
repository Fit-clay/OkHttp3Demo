package com.eric.okhttp3parsing;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.eric.okhttp3parsing.interceptor.LogInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SimpleDemoActivity  extends AppCompatActivity {

    OkHttpClient client;
    Request request;
    String baseUrl="http://192.168.136.208:8080/hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        client=CreateOkhttpClient();
//        request=CreateRequest();
        findViewById(R.id.tv_get).setOnClickListener(v->{
            client.newCall(new Request.Builder().url(baseUrl).get().build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Log.d("-----",e.toString());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("Success",response.body().toString());
                }
            });
        });

        findViewById(R.id.tv_post).setOnClickListener(v->{

            //MediaType  设置Content-Type 标头中包含的媒体类型值
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "");//Json提交

            new OkHttpClient().newCall(new Request.Builder().url(baseUrl).post(requestBody).build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Log.d("-----",e.toString());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("Success",response.body().toString());
                }
            });
        });
        findViewById(R.id.tv_from).setOnClickListener(v->{
            RequestBody requestBody=new FormBody.Builder().add("size", "10").build();//表单提交

            new OkHttpClient().newCall(new Request.Builder().url(baseUrl).post(requestBody).build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Log.d("-----",e.toString());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.d("Success",response.body().toString());
                }
            });
        });


    }

    //自定义参数
    private Request CreateRequest() {
        return new Request.Builder().get().url("").build();
    }

    private OkHttpClient CreateOkhttpClient() {
        //自定义参数  可添加拦截器
        return  new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(new LogInterceptor())
                .build();
    }
}
