package com.team.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.adapter.xyMyAdapter;
import com.team.bean.Song;
import com.team.musicplayer.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class yxMusicListFragment extends Fragment {
    private ListView mListView;
    private List<Song> list;
    private xyMyAdapter adapter;
    String url;
    private static View view;
    Handler handler = new Handler() {
        // 使用handleMessage去处理消息！！，里面的参数Message msg既是发送过来的参数~
        @Override
        public void handleMessage(Message msg) {
            // 在此接受发送过来的消息<---msg
            byte[] data = (byte[]) msg.obj;  //转型
            if (msg.what == IS_FINSIH) {
                String str= new String (data);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+str);

                ObjectMapper mapper=new ObjectMapper();
                JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, Song.class);

                try {
                    list = mapper.readValue(str,jt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println( list.get(0).getSong());
                adapter = new xyMyAdapter(getActivity(), list);
                // 点击事件
                System.out.println(view);
                ListView listview = (ListView) view.findViewById(R.id.main_listview);
                Log.e("ERROR","添加成功");
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        Toast.makeText(getActivity(), "点击 pos: " + i, Toast.LENGTH_SHORT).show();
                        ///点击这里进入播放页面    可惜没人做
                        //  Intent intent=new Intent();
                        //  intent.setClass(mvList.this,mvPlay.class);
                        //  Bundle bundle=new Bundle();
                        //所传递的页面需要以下值
                        //list传不了

                        //  startActivity(intent);
                    }
                });
            }

        };
    };

    private final int IS_FINSIH = 1;
    // 标准的写法
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取上个页面床过来的url
        Bundle bundle=getArguments();
        url= (String) bundle.get("url");
        System.out.println(url);
        new Thread(new yxMusicListFragment.xyMyThread(url)).start();
        Log.i("ERROR","跳转成功");
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        return view = inflater.inflate(R.layout.fragment_yx_music_list,
                container, false);
    }
    //线程请求网络数据 okhttp
    class xyMyThread implements Runnable {
        String url;
        public xyMyThread(String url){
            this.url=url;
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
                        handler.sendMessage(message); // 将数据发送过去~
                    }
                }
            });


        }

    }
}
