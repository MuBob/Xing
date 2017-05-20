package com.example.wjx.xing.activitys.normal;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.utils.StartActivity;

public class SelfInfoActivity extends BaseActivity {

    @Override
    protected int getResourceId() {
        return R.layout.activity_self_info;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent=null;
        switch (v.getId()){
            case R.id.tv_mine_info_base:
                intent=new Intent(this, InfoBaseActivity.class);
                break;
            case R.id.tv_mine_info_record:
                intent=new Intent(this, InfoEducationActivity.class);
                break;
            case R.id.tv_mine_info_skill:
                intent=new Intent(this, InfoSkillActivity.class);
                break;
            default:
                break;
        }
        if(intent!=null){
            StartActivity.jumpTo(this, intent);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setVisibility(View.GONE);
    }

    @Override
    protected CharSequence getTitleText() {
        return "个人信息";
    }

    @Override
    protected void onClickRight() {

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initSet() {
    }
}
