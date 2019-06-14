package com.team.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.team.musicplayer.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;

public class RecommendFragment extends Fragment {
    Button private_fm, daily_btn, yun, button1, button2, button3, button4, button5, button6;
    FragmentManager fm;
    yxMusicListFragment df;
    Bundle bundle;
    BGABanner banner;
    public static RecommendFragment newInstance() {
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }
    @Nullable
    @Override
    @SuppressWarnings("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getActivity(),R.layout.fragment_recommend,null);
        fm = getFragmentManager();
        private_fm = view.findViewById(R.id.private_fm);
        daily_btn = view.findViewById(R.id.daily_btn);
        yun = view.findViewById(R.id.yun);
        //图片轮播
        banner = view.findViewById(R.id.banner);
        //设置图片
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        BGALocalImageSize localImageSize = new BGALocalImageSize(540, 245, 320, 200);
        // 设置数据源
        banner.setData(localImageSize, ImageView.ScaleType.FIT_XY,
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3,
                R.drawable.banner4);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button4 = view.findViewById(R.id.button4);
        button5 = view.findViewById(R.id.button5);
        button6 = view.findViewById(R.id.button6);
        View.OnClickListener mylistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.private_fm:
                        df = new yxMusicListFragment();
                        bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.daily_btn:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx1");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.yun:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx2");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.button1:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx3");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.button2:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx4");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.button3:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx5");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.button4:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx6");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.button5:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx7");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    case R.id.button6:
                         df = new yxMusicListFragment();
                         bundle=new Bundle();
                        bundle.putString("url","http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/yx8");
                        df.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.context,df).commit();
                        break;
                    default:
                        break;
                }
            }
        };
        private_fm.setOnClickListener(mylistener);
        daily_btn.setOnClickListener(mylistener);
        yun.setOnClickListener(mylistener);
        button1.setOnClickListener(mylistener);
        button2.setOnClickListener(mylistener);
        button3.setOnClickListener(mylistener);
        button4.setOnClickListener(mylistener);
        button5.setOnClickListener(mylistener);
        button6.setOnClickListener(mylistener);
        return view;
    }

}
