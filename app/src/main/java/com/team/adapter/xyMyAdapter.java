package com.team.adapter;

import android.content.Context;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.TextView;


import com.team.bean.Song;
import com.team.musicplayer.R;
import com.team.utils.xyMusicUtils;

import java.util.List;

public class xyMyAdapter extends BaseAdapter {  //Adapter是用来帮助填充数据的中间桥梁，
         // 即将各种数据以合适的形式显示在View中给用户看。Adapter有很多的接口、抽象类、子类可以使用
    private Context context;
    private List<Song> list;
    public xyMyAdapter(FragmentActivity yxMusicList, List<Song> list) {
        this.context = yxMusicList;
        this.list = list;
    }
    /**
     * 返回item的个数
     * @return
     */
    @Override
    public int getCount() {
        return list.size();
    }
    /**
     * 返回每一个item对象
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }
    /**
     * 返回每一个item的id
     * @param i
     * @return
     */

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null) {
            holder = new ViewHolder();
            //引入布局
            view = View.inflate(context, R.layout.item_music_listview, null);
            //实例化对象
            holder.song = (TextView) view.findViewById(R.id.item_mymusic_song);
            holder.singer = (TextView) view.findViewById(R.id.item_mymusic_singer);
            holder.duration = (TextView) view.findViewById(R.id.item_mymusic_duration);
            holder.position = (TextView) view.findViewById(R.id.item_mymusic_postion);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        //给控件赋值
        holder.song.setText(list.get(i).song.toString());
        holder.singer.setText(list.get(i).singer.toString());

        //时间需要转换一下
        int duration = list.get(i).duration;
        String time = xyMusicUtils.formatTime(duration);
        holder.duration.setText(time);
        holder.position.setText(i + 1 + "");

        return view;
    }
    class ViewHolder {
        TextView song;//歌曲名
        TextView singer;//歌手
        TextView duration;//时长
        TextView position;//序号
    }
}

