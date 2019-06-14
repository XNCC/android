package com.team.fragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.team.activitys.PlayMusicActivity;
import com.team.adapter.xyMyAdapter;
import com.team.bean.Song;
import com.team.musicplayer.R;
import com.team.utils.xyMusicUtils;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicFragment extends Fragment {
    private ListView mListView;
    private List<Song> list;
    private xyMyAdapter adapter;
    private View view;
    public static LocalMusicFragment newInstance(){
        return new LocalMusicFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(),R.layout.fragment_local_music,null);
        initView();
        mListView = view.findViewById(R.id.main_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                String path  = list.get(i).path;
                Toast.makeText(getActivity(), "点击 pos: " + path, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), PlayMusicActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("path",path);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }
    private void initView() {       //就定义一个ListView并且绑定Adapter

        mListView = view.findViewById(R.id.main_listview);
        list = new ArrayList<>();

        //把扫描到的音乐赋值给list
        list = xyMusicUtils.getMusicData(getActivity());
        for (Song s: list
             ) {
            Log.e("SONG",s.toString());
        }
        adapter = new xyMyAdapter(getActivity(), list);
        mListView.setAdapter(adapter);
    }
}
