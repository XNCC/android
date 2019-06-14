package com.team.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.adapter.nccMyListAdapter;
import com.team.bean.RecommendMusic;
import com.team.musicplayer.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MvListFragment extends Fragment {
    Button btns;
    private final int IS_FINSIH = 1;
    List<RecommendMusic> RecommendMusicList= null;
    private View view;
    private MyPlayFragment myplay;

    public static MvListFragment newInstance() {
        MvListFragment fragment = new MvListFragment();
        return fragment;
    }
    // 标准的写法
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
                JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, RecommendMusic.class);

                try {
                    RecommendMusicList = mapper.readValue(str,jt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println( RecommendMusicList.get(0).getMusicName());
                nccMyListAdapter adapter=new nccMyListAdapter(getActivity(), R.layout.layout_list_item,RecommendMusicList);
                ListView listview = (ListView) view.findViewById(R.id.lv_1);
                listview.setAdapter(adapter);
                // 点击事件
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        myplay = new MyPlayFragment();
                        // Toast.makeText(MainActivity.this, "点击 pos: " + i, Toast.LENGTH_SHORT).show();
                        Bundle bundle=new Bundle();
                        //所传递的页面需要以下值
                        //list传不了
                        bundle.putString("geming",RecommendMusicList.get(i).getMusicName());
                        bundle.putString("geshou",RecommendMusicList.get(i).getSinger());
                        bundle.putString("url",RecommendMusicList.get(i).getUrl());
                        bundle.putString("title",RecommendMusicList.get(i).getMusicName());
                        bundle.putString("singers",RecommendMusicList.get(i).getSinger());
                        bundle.putString("cishu",RecommendMusicList.get(i).getCishu());
                        bundle.putString("time",RecommendMusicList.get(i).getTime());
                        bundle.putString("details",RecommendMusicList.get(i).getDetails());
                        myplay.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,myplay).commit();
                    }
                });

            }

        };
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(),R.layout.fragment_mv_list,null);

        new Thread(new ncc1MyThread()).start();
        return view;
    }
    //线程请求网络数据 okhttp
    class ncc1MyThread implements Runnable {
        @Override
        public void run() {   //run方法里面写要发送的消息对象，并对消息携带的信息进行定义！！
            OkHttpClient okHttpClient = new OkHttpClient();
            //该url获取mv信息
            Request request = new Request.Builder()
                    .url("http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/ncc1")
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
