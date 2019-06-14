package com.team.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.bean.User;
import com.team.musicplayer.R;
import com.team.utils.HttpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends Activity {
    private EditText et_username;
    private EditText et_password;
    private Button bt_log;
    private Button bt_bos;
    private final int IS_FINSIH = 1;
    private String loginMessage;
    private String username,password;
    private List<User> userList;
    Handler handler = new Handler(){
        // 使用handleMessage去处理消息！！，里面的参数Message msg既是发送过来的参数~
        @Override
        public void handleMessage(Message msg) {
            // 在此接受发送过来的消息<---msg
            byte[] data = (byte[]) msg.obj;  //转型
            if (msg.what == IS_FINSIH) {
                loginMessage= new String (data);
                Log.e("DATA",loginMessage);
                ObjectMapper mapper=new ObjectMapper();
                JavaType jt = mapper.getTypeFactory().constructParametricType(ArrayList.class, User.class);
                try {
                    userList = mapper.readValue(loginMessage,jt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!userList.isEmpty()){
                    Toast.makeText(LoginActivity.this,"用户名和密码正确！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user",userList.get(0));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this,"请检查用户名和密码！",Toast.LENGTH_SHORT).show();
                }
            }

        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //利用布局资源文件设置用户界面
        setContentView(R.layout.activity_login);

        //通过资源标识获得控件实例
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_log = (Button) findViewById(R.id.bt_log);
        bt_bos = (Button) findViewById(R.id.bt_bos);

        //给登录按钮注册监听器，实现监听器接口，编写事件
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的数据
                username = et_username.getText().toString();
                password = et_password.getText().toString();

                //判断用户名和密码是否正确（为可以进行测试，将用户名和密码都定义为admin）
                //Toast:是一个类，主要管理消息的提示。
                //makeText()，是Toast的一个方法，用来显示信息，分别有三个参数。
                //第一个参数：this，是上下文参数，指当前页面显示
                //第二个参数：“string string string ”是你想要显示的内容，。随便定义，显示你想要显示的内容。
                //第三个参数：Toast.LENGTH_LONG，是你指你提示消息，显示的时间，这个是稍微长点，对应的另一个是ToastLENGTH_SHORT，这个时间短点，大概2秒钟。
                String url = "http://212.129.148.99:8080/android-0.0.1-SNAPSHOT/user"
                        + "/" + username
                        + "/" + password;
                Log.e("URL",url);
                new Thread(new HttpUtils(url,handler)).start();
            }
        });
        //给取消按钮注册监听器，实现监听器接口，编写事件
        bt_bos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
