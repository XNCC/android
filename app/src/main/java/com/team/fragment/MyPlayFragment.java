package com.team.fragment;

import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.VideoView;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.adapter.ncc2MyListAdapterForRecommend;
import com.team.bean.comments;
import com.team.musicplayer.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyPlayFragment extends Fragment {
    VideoView video;
    TabHost tab; //切换详情与评论部分
    TextView geming ,geshou,title,singers,cishu,time,details;   //详情部分
    ImageView tupian;
    String url;
    private List<comments> commentsList= null;
    private final int IS_FINSIH = 1;
    private View view;
    // 标准的写法
    Handler handler = new Handler() {
        // 使用handleMessage去处理消息！！，里面的参数Message msg既是发送过来的参数~
        @Override
        public void handleMessage(Message msg) {
            // 在此接受发送过来的消息<---msg
            byte[] data = (byte[]) msg.obj;  //转型
            if (msg.what == IS_FINSIH) {
                String str2= new String (data);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+str2);

                ObjectMapper mapper=new ObjectMapper();
                JavaType jt2 = mapper.getTypeFactory().constructParametricType(ArrayList.class, comments.class);

                try {
                    commentsList = mapper.readValue(str2,jt2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println( ">>9999999"+commentsList.get(0).getName());
                ncc2MyListAdapterForRecommend adapter=new ncc2MyListAdapterForRecommend(getActivity(), R.layout.mv_reconmend_pinglun_item,commentsList);
                ListView listview = (ListView) view.findViewById(R.id.commentss);
                listview.setAdapter(adapter);
                // 点击事件
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                    {
                        // Toast.makeText(MainActivity.this, "点击 pos: " + i, Toast.LENGTH_SHORT).show();

                    }
                });

            }

        };
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(getActivity(),R.layout.fragment_mv_play,null);
        //播放区
        video=view.findViewById(R.id.video);
        geming=view.findViewById(R.id.geming);
        geshou=view.findViewById(R.id.geshou);
        //乡亲
        title=view.findViewById(R.id.title);
        singers=view.findViewById(R.id.singers);
        cishu=view.findViewById(R.id.cishu);
        time=view.findViewById(R.id.time);
        details=view.findViewById(R.id.details);

        //接受来自上一个页面的信息
        Bundle bundle=getArguments();
        url = (String)bundle.get("url");
        geming.setText((String)bundle.get("geming"));
        geshou.setText((String)bundle.get("geshou"));
        title.setText((String)bundle.get("title"));
        singers.setText((String)bundle.get("singers"));
        cishu.setText((String)bundle.get("cishu"));
        time.setText((String)bundle.get("time"));
        details.setText((String)bundle.get("details"));

        startVideo(url); //视频播放

        initTab();
        new Thread(new ncc2MyThread((String)bundle.get("geming"))).start();
        return view;
    }
    //初始化页面切换
    public void initTab(){
        //初始化TabHost容器
        TabHost tab = (TabHost) view.findViewById(android.R.id.tabhost);
        tab.setup();
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        tab.addTab(tab.newTabSpec("tab1").setIndicator("详情" , null).setContent(R.id.tab1));
        tab.addTab(tab.newTabSpec("tab2").setIndicator("评论" , null).setContent(R.id.tab2));

    }
    //视频播放方法
    public  void  startVideo(String url){
        //播放视频部分
        video.setMediaController(new MediaController(getActivity()));
        Uri uri = Uri.parse(url);
        video.setVideoURI(uri);
        video.start();
        video.requestFocus();
    }



    //线程请求网络数据 okhttp
    class ncc2MyThread implements Runnable {
        String music_name;
        public ncc2MyThread(String music_name) {
            this.music_name=music_name;
        }

        @Override
        public void run() {   //run方法里面写要发送的消息对象，并对消息携带的信息进行定义！！
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/ncc2/"+music_name)
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
