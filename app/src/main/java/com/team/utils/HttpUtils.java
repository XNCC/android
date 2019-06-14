package com.team.utils;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils implements Runnable{
    String url;
    private final int IS_FINSIH = 1;
    Handler handler;

    public HttpUtils(String url,Handler handler) {
        this.url=url;
        this.handler = handler;
    }
    @Override
    public void run() {   //run方法里面写要发送的消息对象，并对消息携带的信息进行定义！！
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
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
                    Message message = Message.obtain();
                    byte data[]= s.getBytes();
                    message.obj = data;
                    message.what = IS_FINSIH; // 结束标志位
                    handler.sendMessage(message); // 将数据发送过去
                }
            }
        });


    }
}
