package com.example.wjx.xing.activitys;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wjx.xing.R;

/**
 * 出差管理
 */
public class EvectionActivity extends BaseActivity {
    private EditText spaceEdit, countEdit, trafficEdit, whatEdit;
    @Override
    protected int getResourceId() {
        return R.layout.activity_evection;
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
    protected CharSequence getTitleText() {
        return "出差";
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spaceEdit= (EditText) findViewById(R.id.et_evection_space);
        countEdit= (EditText) findViewById(R.id.et_evection_count);
        trafficEdit= (EditText) findViewById(R.id.et_evection_traffic);
        whatEdit= (EditText) findViewById(R.id.et_evection_what);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_post:
                // TODO: 2017/5/18 网络请求，出差申请
                break;
        }
    }
}
