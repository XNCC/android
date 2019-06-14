package com.team.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.team.bean.User;
import com.team.musicplayer.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ZhanghaoFragment extends Fragment {
    private Unbinder bind;
    private View view;
    public static ZhanghaoFragment newInstance(){
        return new ZhanghaoFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(),R.layout.fragment_zhanghao,null);
        User user= (User)getArguments().getSerializable("user");
        TextView userName = view.findViewById(R.id.user_name);
        userName.setText(user.getUsername());
        TextView userVal = view.findViewById(R.id.user_val);
        userVal.setText(user.getPhone());
        ImageView hHead = view.findViewById(R.id.h_head);
        Log.e("PATH",user.getLocalpath());
        hHead.setImageResource(getResources().getIdentifier(user.getLocalpath(),"drawable",getActivity().getPackageName()));
        bind = ButterKnife.bind(getActivity());
        return view;
    }
    /**
     * 点击事件
     */
    @OnClick({R.id.about,R.id.version,R.id.my_asset_mange,R.id.quit,R.id.dengji})
    public void Onclickd(View v){
        switch (v.getId()){
            case R.id.about:
                Toast.makeText(getActivity(), "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.version:
                Toast.makeText(getActivity(), "version", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_asset_mange:
                Toast.makeText(getActivity(), "gerenxingxi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.quit:
                Toast.makeText(getActivity(), "quit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dengji:
                Toast.makeText(getActivity(), "dengji", Toast.LENGTH_SHORT).show();
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
