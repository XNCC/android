package com.team.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.team.bean.dynamic;
import com.team.musicplayer.R;

import java.util.List;

public class DongtaiAdapter extends BaseAdapter {
    private List<dynamic> strList;
    private LayoutInflater inflater;
    public DongtaiAdapter() {}

    public DongtaiAdapter(List<dynamic> strList, Context context) {
        this.strList = strList;
        this.inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return strList==null?0:strList.size();
    }

    @Override
    public dynamic getItem(int position) {
        return strList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        dynamic recent = getItem(position);
        //加载布局为一个视图
        View view=inflater.inflate(R.layout.item_dongtai,null);
        TextView tv_name= (TextView) view.findViewById(R.id.item_userName);
        TextView tv_time= (TextView) view.findViewById(R.id.item_time);
        TextView tv_content= (TextView) view.findViewById(R.id.item_content);
        tv_name.setText(recent.getUser());
        tv_time.setText(recent.getTime());
        tv_content.setText(recent.getContents());
        return view;
    }
}
