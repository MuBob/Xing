package com.example.wjx.xing.activitys.normal;

import android.view.View;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;

public class NotifyActivity extends BaseActivity {
    private TextView notifyText;
    @Override
    protected int getResourceId() {
        return R.layout.activity_notify;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected CharSequence getTitleText() {
        return "公文通告";
    }

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setVisibility(View.GONE);
    }

    @Override
    protected void onClickRight() {

    }

    @Override
    protected void initView() {
        notifyText = (TextView) findViewById(R.id.tv_notify);
    }

    @Override
    protected void initSet() {
        notifyText.setText("2017年1月1日\n\t热烈欢迎人力资源上线！");
    }
}
