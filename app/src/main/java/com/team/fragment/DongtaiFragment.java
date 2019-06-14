package com.team.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.adapter.DongtaiAdapter;
import com.team.bean.User;
import com.team.bean.dynamic;
import com.team.musicplayer.R;
import com.team.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DongtaiFragment extends Fragment {
    private BottomSheetDialog dialog;
    private View view;
    private DongtaiAdapter adapter;
    private final int IS_FINSIH = 1;
    private List<dynamic> dynamicList;
    private User user;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            // 在此接受发送过来的消息<---msg
            byte[] data = (byte[]) msg.obj;  //转型
            if (msg.what == IS_FINSIH) {
                String str= new String (data);
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>"+str);

                ObjectMapper mapper=new ObjectMapper();
                JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, dynamic.class);

                try {
                    dynamicList = mapper.readValue(str,jt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println( dynamicList.get(0).getUser());
                adapter = new DongtaiAdapter(dynamicList,getActivity());
                ListView listview = (ListView) view.findViewById(R.id.msg_list_view);
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
        }
    };
    public static DongtaiFragment newInstance(){
        return new DongtaiFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_dongtai,null);
        new Thread(new HttpUtils("http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/dynamic",handler)).start();
        TextView text = view.findViewById(R.id.speak);
        user = (User)getArguments().getSerializable("user");
        text.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showCommentDialog();
            }
        });
        return view;
    }
    private void showCommentDialog(){
        dialog = new BottomSheetDialog(getActivity());
        View recentView = LayoutInflater.from(getActivity()).inflate(R.layout.recent_dialog_layout,null);
        final EditText commentText = (EditText) recentView.findViewById(R.id.dialog_comment_et);
        final Button bt_comment = (Button) recentView.findViewById(R.id.dialog_comment_bt);
        dialog.setContentView(recentView);
        /**
         * 解决bsd显示不全的情况
         */
        View parent = (View) recentView.getParent();
        BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
        recentView.measure(0,0);
        behavior.setPeekHeight(recentView.getMeasuredHeight());
        bt_comment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String commentContent = commentText.getText().toString().trim();
                if(!TextUtils.isEmpty(commentContent)){
                    //commentOnWork(commentContent);
                    dialog.dismiss();
                    dynamic recent = new dynamic();
                    recent.setUser(user.getUsername());
                    recent.setTime(new Date().toString());
                    recent.setContents(commentContent);
                    dynamicList.add(recent);
                    String url = "http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/insertDynamic"
                            + "/" + recent.getUser()
                            + "/" + recent.getContents()
                            +"/" + recent.getTime();
                    new Thread(new HttpUtils(url,handler)).start();
                    Toast.makeText(getActivity(),"动态发布成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(),"动态内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
}
