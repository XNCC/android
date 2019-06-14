package com.team.bean;

/*
        主要传入六个信息
        //头部信息
        geming
        geshou
        //视频url
        vidourl
        //详情区
        title      标题
        singers    歌手
        cishu      播放次数
        time       发行时间
        details    详细信息
        List<comments>
        */
public class RecommendMusic {
    String musicName;
    String singer;
    String url;
    String cishu;
    String time;
    String details;
    String Recommendnames;
    public RecommendMusic() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getRecommendnames() {
        return Recommendnames;
    }

    public void setRecommendnames(String recommendnames) {
        Recommendnames = recommendnames;
    }

    public RecommendMusic(String musicName, String singer, String url, String cishu, String time, String details, String recommendnames) {
        this.musicName = musicName;
        this.singer = singer;
        this.url = url;
        this.cishu = cishu;
        this.time = time;
        this.details = details;
        Recommendnames = recommendnames;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCishu() {
        return cishu;
    }

    public void setCishu(String cishu) {
        this.cishu = cishu;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }


}
