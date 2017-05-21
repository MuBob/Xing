package com.example.wjx.xing.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.wjx.xing.Common;
import com.example.wjx.xing.R;
import com.example.wjx.xing.utils.StartActivity;


/**
 * 闪屏界面
 */
public class SplashActivity extends BaseActivity {

    private static int SUCCESS = 100;
    private SharedPreferences mPreferences;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SUCCESS) {
                //收到消息之后跳转页面
                int role=getIntFromSP(Common.KEY_INT_ROLE_LOGIN, 0);
                //测试使用
//                role = 2;
                if (role>0) {
                    //已经登录过了 
                    Toast.makeText(SplashActivity.this, "欢迎回来", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra(Common.KEY_INT_ROLE_LOGIN, role);
                    StartActivity.StartActivity(SplashActivity.this, intent);
                } else {
                    StartActivity.StartActivity(SplashActivity.this, LoginActivity.class);
                }
            }
        }
    };

    @Override
    protected int getResourceId() {
        //设置当前界面无菜单栏 无标题栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected CharSequence getTitleText() {
        return null;
    }

    @Override
    protected void onClickRight() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {
        //两秒之后发送一个标识为 success的空消息
        handler.sendEmptyMessageDelayed(SUCCESS, 2000);
    }

}
