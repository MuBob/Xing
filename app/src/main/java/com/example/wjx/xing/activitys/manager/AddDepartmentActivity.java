package com.example.wjx.xing.activitys.manager;

import android.widget.EditText;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;

public class AddDepartmentActivity extends BaseActivity {

    private EditText nameText, numberText, descText;
    @Override
    protected int getResourceId() {
        return R.layout.activity_add_department;
    }

    @Override
    protected CharSequence getTitleText() {
        return "新增部门";
    }

    @Override
    protected void onClickRight() {
        // TODO: 2017/5/18  网络请求，新增一条部门数据
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        nameText = (EditText) findViewById(R.id.tv_department_name);
        numberText = (EditText) findViewById(R.id.tv_department_number);
        descText = (EditText) findViewById(R.id.tv_department_desc);
    }

    @Override
    protected void initSet() {
        nameText.requestFocus();
    }
}
