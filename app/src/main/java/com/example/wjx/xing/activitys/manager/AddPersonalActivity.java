package com.example.wjx.xing.activitys.manager;

import android.widget.EditText;
import android.widget.Spinner;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;

public class AddPersonalActivity extends BaseActivity {

    private EditText nameEdit, emaiEdit, passwordEdit;
    private Spinner departmentSpinner, roleSpinner, sexSpinner, recordSpinner, skillSpinner;
    @Override
    protected int getResourceId() {
        return R.layout.activity_add_personal;
    }

    @Override
    protected CharSequence getTitleText() {
        return "添加员工";
    }

    @Override
    protected void onClickRight() {
        // TODO: 2017/5/18 网络请求，添加一条员工记录
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        nameEdit = (EditText) findViewById(R.id.edit_personal_name);
        emaiEdit = (EditText) findViewById(R.id.edit_personal_email);
        passwordEdit = (EditText) findViewById(R.id.edit_personal_password);
        departmentSpinner = (Spinner) findViewById(R.id.spinner_personal_department);
        roleSpinner = (Spinner) findViewById(R.id.spinner_personal_role);
        sexSpinner = (Spinner) findViewById(R.id.spinner_personal_sex);
        recordSpinner = (Spinner) findViewById(R.id.spinner_personal_record);
        skillSpinner = (Spinner) findViewById(R.id.spinner_personal_skill);
    }

    @Override
    protected void initSet() {

    }
}
