package com.team.bean;

/*
<!--

      tupian             评论区头像图片
      name               评论者
      times              评论时间
      recommnendContent  评论内容
    -->
 */
public class comments {
  //  int tupian;
    String name;
    String times;
    String recommnendContent;

//    public int getTupian() {
//        return tupian;
//    }
//
//    public void setTupian(int tupian) {
//        this.tupian = tupian;
//    }

    public comments() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getRecommnendContent() {
        return recommnendContent;
    }

    public void setRecommnendContent(String recommnendContent) {
        this.recommnendContent = recommnendContent;
    }
    //int tupian,
    public comments( String name, String times, String recommnendContent) {
       // this.tupian = tupian;
        this.name = name;
        this.times = times;
        this.recommnendContent = recommnendContent;
    }
}
