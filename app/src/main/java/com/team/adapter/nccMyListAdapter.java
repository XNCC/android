package com.team.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import com.team.bean.RecommendMusic;
import com.team.musicplayer.R;

import java.util.List;

public class nccMyListAdapter extends ArrayAdapter<RecommendMusic> {
    private static final String TAG = "Error";
    private int resourceId;


    public nccMyListAdapter(Context context, int textViewResourceId, List<RecommendMusic> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecommendMusic RecommendMusic = getItem(position);           //获取当前项的实例

        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView MusicName = view.findViewById(R.id.MusicName);
        TextView Singer = view.findViewById(R.id.Singer);
        ImageView videoImage=view.findViewById(R.id.videoImage);
        TextView Recommendnames=view.findViewById(R.id.usernames);

        Recommendnames.setText(RecommendMusic.getRecommendnames());
        MusicName.setText(RecommendMusic.getMusicName());
        Singer.setText(RecommendMusic.getSinger());
        return view;
    }



}