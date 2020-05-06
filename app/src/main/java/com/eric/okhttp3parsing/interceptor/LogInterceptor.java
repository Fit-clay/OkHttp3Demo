package com.eric.okhttp3parsing.interceptor;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;

public class LogInterceptor implements Interceptor {
    String TAG= "LogInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Response response=chain.proceed(request);

        for(String key :request.headers().toMultimap().keySet()){
            Log.d(TAG,key+":"+request.headers().toMultimap().get(key));
        }
        Log.e("zp_test", "url: " + request.url().uri().toString());
        ResponseBody responseBody = response.body();

        if (HttpHeaders.hasBody(response) && responseBody != null) {
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(responseBody.byteStream(), "utf-8"));
            String result;
            while ((result = bufferedReader.readLine()) != null) {
                Log.e("zp_test", "response: " + result);
            }
            // 测试代码
            responseBody.string();
        }
    // 注意，这样写，等于重新创建Request，获取新的Response，避免在执行以上代码时，
    // 调用了responseBody.string()而不能在返回体中再次调用。
//        return response.newBuilder().build();
        return null;
 }
}
