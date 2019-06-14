package com.team.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;


import com.team.bean.Song;

import java.util.ArrayList;
import java.util.List;


/**
 * 用来扫描本地音频的音乐工具类,
 * 返回值是一个list集合，集合里面装的泛型就是上一步创建的JavaBean
 */

public class xyMusicUtils {

    /**
     * 扫描系统里面的音频文件，返回一个list集合
     */

    public static List<Song> getMusicData(Context context) {

        List<Song> list = new ArrayList<Song>();

        // 媒体库查询语句（写一个工具类MusicUtils）

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,

                null, MediaStore.Audio.AudioColumns.IS_MUSIC);          //cursor 光标，游标
                                         //contentresolver则是用于管理所有程序的contentprovider实例，通过contentrescolver可以获得数据，
                                         //每一个应用程序程序都拥有一个contentprovider实例进行存储

                                        //ContentResolver是通过网络资源URI来查询ContentProvider中提供的数据。除了URI以外，还必须知道需要获取的数据段的名称，以及此数据段的数据类型。
                                        // 如果需要获取一个特定的记录，还必须知道当前记录的ID，也就是URI中D部分。

        if (cursor != null) {

            while (cursor.moveToNext()) {

                Song song = new Song();

                song.song = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));

                song.singer = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));

                song.path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));

                song.duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

                song.size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

                if (song.size > 1000 * 800) {

                    // 注释部分是切割标题，分离出歌曲名和歌手 （本地媒体库读取的歌曲信息不规范）

                    if (song.song.contains("-")) {

                        String[] str = song.song.split("-");

                        song.singer = str[0];

                        song.song = str[1];

                    }

                    list.add(song);

                }

            }

            // 释放资源

            cursor.close();

        }


        return list;

    }


    /**
     * 定义一个方法用来格式化获取到的时间
     */

    public static String formatTime(int time) {

        if (time / 1000 % 60 < 10) {

            return time / 1000 / 60 + ":0" + time / 1000 % 60;            //eg:时长为4:06


        } else {

            return time / 1000 / 60 + ":" + time / 1000 % 60;             //eg:时长为4:46

        }


    }

}


//Android提供了ContentProvider，一个程序可以通过实现一个Content provider的抽象接口将自己的数据完全暴露出去，
// 而且Content providers是以类似数据库中表的方式将数据暴露。

//如何通过一套标准及统一的接口获取其他应用程序暴露的数据？Android提供了ContentResolver，
// 外界的程序可以通过ContentResolver接口访问ContentProvider提供的数据。


