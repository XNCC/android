package com.team.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.team.bean.User;
import com.team.fragment.MvListFragment;
import com.team.fragment.MyFragment;
import com.team.fragment.DongtaiFragment;
import com.team.fragment.RecommendFragment;
import com.team.fragment.ZhanghaoFragment;
import com.team.musicplayer.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;
    private FrameLayout frame;
    private User user;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_faxian:
                    frame = findViewById(R.id.context);
                    frame.removeAllViews();
                    getSupportFragmentManager().beginTransaction().add(R.id.context,RecommendFragment.newInstance()).commit();
                    return true;
                case R.id.navigation_shiping:
                    frame = findViewById(R.id.context);
                    frame.removeAllViews();
                    getSupportFragmentManager().beginTransaction().add(R.id.context,MvListFragment.newInstance()).commit();
                    return true;
                case R.id.navigation_wode:
                    frame = findViewById(R.id.context);
                    frame.removeAllViews();
                    getSupportFragmentManager().beginTransaction().add(R.id.context, MyFragment.newInstance()).commit();
                    return true;
                case R.id.navigation_dongtai:
                    frame = findViewById(R.id.context);
                    frame.removeAllViews();
                    DongtaiFragment dt = DongtaiFragment.newInstance();
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("user",user);
                    dt.setArguments(bundle1);
                    getSupportFragmentManager().beginTransaction().add(R.id.context, dt).commit();
                    return true;
                case R.id.navigation_zhanghao:
                    frame = findViewById(R.id.context);
                    frame.removeAllViews();
                    ZhanghaoFragment zh = ZhanghaoFragment.newInstance();
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable("user",user);
                    zh.setArguments(bundle2);
                    getSupportFragmentManager().beginTransaction().add(R.id.context, zh).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = findViewById(R.id.context);
        user = (User)this.getIntent().getExtras().getSerializable("user");
        getSupportFragmentManager().beginTransaction().add(R.id.context,RecommendFragment.newInstance()).commit();
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button btn = findViewById(R.id.toolbar_right_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PlayMusicActivity.class);
                startActivity(intent);
            }
        });
    }

    public User getUser(){
        return user;
    }
}
