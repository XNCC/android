package com.team.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ncchttpRecommend {

    public String post() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/RecommendMusic")
                .build();
        Call call = okHttpClient.newCall(request);
      //1.异步请求，通过接口回调告知用户 http 的异步执行结果
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String s=response.body().string();
                    System.out.println(s);
                }
            }
        });


        return "";
    }
}
