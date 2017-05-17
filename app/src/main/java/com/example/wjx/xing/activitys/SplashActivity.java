package com.example.wjx.xing.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.wjx.xing.Common;
import com.example.wjx.xing.R;
import com.example.wjx.xing.utils.StartActivity;


/**
 * 闪屏界面
 */
public class SplashActivity extends AppCompatActivity {

    private static int SUCCESS = 100;
    private SharedPreferences mPreferences;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SUCCESS) {
                //收到消息之后跳转页面
                mPreferences = getSharedPreferences("Seting_ordering", 0);
                Boolean isLogin = mPreferences.getBoolean(Common.KEY_BOOLEAN_LOGIN_AUTO, false);
                isLogin = true;
                if (isLogin) {
                    //已经登录过了 
                    Toast.makeText(SplashActivity.this, "欢迎回来", Toast.LENGTH_SHORT).show();
                    StartActivity.StartActivity(SplashActivity.this, MainActivity.class);
                } else {
                    StartActivity.StartActivity(SplashActivity.this, LoginActivity.class);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置当前界面无菜单栏 无标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initview();
    }

    /**
     * 初始化界面
     */
    private void initview() {
        //两秒之后发送一个标识为 success的空消息
        handler.sendEmptyMessageDelayed(SUCCESS, 2000);
    }
}
