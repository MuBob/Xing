package com.example.wjx.xing.activitys.manager;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wjx.xing.Common;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.utils.StartActivity;

public class ApplyReplyActivity extends BaseActivity {

    @Override
    protected int getResourceId() {
        return R.layout.activity_apply_reply;
    }

    @Override
    protected CharSequence getTitleText() {
        return "申请回复";
    }

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int applyType=0;
        switch (v.getId()){
            case R.id.tv_reply_evection:  //出差回复
                applyType=Common.APPLY_TYPE_EVECTION;
                break;
            case R.id.tv_reply_level:  //请假回复
                applyType=Common.APPLY_TYPE_LEAVE;
                break;
            default:
                break;
        }
        if(applyType>0){
            Intent intent=new Intent(this, ReplyActivity.class);
            intent.putExtra(Common.KEY_REPLY_TYPE, applyType);
            StartActivity.jumpTo(this, intent);
        }
    }

    @Override
    protected void onClickRight() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initSet() {

    }
}
