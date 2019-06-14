package com.team.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.team.bean.comments;
import com.team.musicplayer.R;

import java.util.List;

/*
      tupian             评论区头像图片
      name               评论者
      times              评论时间
      recommnendContent  评论内容
 */
public class ncc2MyListAdapterForRecommend extends ArrayAdapter<comments> {
    private int resourceId;

    public ncc2MyListAdapterForRecommend(Context context, int textViewResourceId, List<comments> objects)
    {
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        comments comments=getItem(position);           //获取当前项的实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        //ImageView tupian=view.findViewById(R.id.tupian);
        //ImageView tupian=(ImageView) view.<View>findViewById(R.id.tupian);
        TextView name=view.findViewById(R.id.name);
        TextView times=view.findViewById(R.id.times);
        TextView recommnendContent=view.findViewById(R.id.recommnendContent);

       // tupian.setImageResource(comments.getTupian());
        //tupian.setImageResource(R.drawable.apple);
        name.setText(comments.getName());
        times.setText(comments.getTimes());
        recommnendContent.setText(comments.getRecommnendContent());

        return view;
    }
}
