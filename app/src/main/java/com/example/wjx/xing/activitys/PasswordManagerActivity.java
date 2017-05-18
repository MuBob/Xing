package com.example.wjx.xing.activitys;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.utils.StringUtil;

public class PasswordManagerActivity extends BaseActivity {

    private EditText passOld, passNew1, passNew2;
    @Override
    protected void initTitle(TextView mLeft, TextView mTitle, TextView mRight) {
        super.initTitle(mLeft, mTitle, mRight);
        mRight.setVisibility(View.GONE);
    }

    @Override
    protected CharSequence getTitleText() {
        return "密码管理";
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        passOld = (EditText) findViewById(R.id.edit_password_old);
        passNew1 = (EditText) findViewById(R.id.edit_password_new1);
        passNew2 = (EditText) findViewById(R.id.edit_password_new2);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_search;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button_ok:
                if(!StringUtil.isPassword(passOld.getText().toString().trim())){
                    showToastShort("请输入正确的原始密码");
                } else if(!StringUtil.isPassword(passNew1.getText().toString().trim())||!StringUtil.isPassword(passNew2.getText().toString().trim())){
                    showToastShort("新密码长度至少8位");
                } else if(!passNew1.getText().toString().trim().equals(passNew2.getText().toString().trim())){
                    showToastShort("两次新密码不一致");
                }else {
                    // TODO: 2017/5/18 请求网络，修改密码接口

                }
                break;
        }
    }

    @Override
    protected void onClickRight() {
        //do nothing
    }
}
