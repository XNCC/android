package com.team.bean;




import com.team.utils.ncchttpRecommend;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        ncchttpRecommend ncchttpRecommend=new ncchttpRecommend();
        String s=ncchttpRecommend.post();
        System.out.println(s);
    }
}
