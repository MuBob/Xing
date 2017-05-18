package com.example.wjx.xing.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.PasswordManagerActivity;
import com.example.wjx.xing.activitys.manager.DepartmentManagerActivity;
import com.example.wjx.xing.activitys.manager.PersonalManagerActivity;
import com.example.wjx.xing.utils.StartActivity;

/**
 * 报表
 */
public class ManagerFragment extends BaseFragment  implements View.OnClickListener{

    private View mView;
    private TextView departmentText, personnelText, departmentTurnText, attendanceText, replyText, passwordText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragments_manager;
    }

    @Override
    protected void initSet() {
        departmentText.setOnClickListener(this);
        personnelText.setOnClickListener(this);
        departmentTurnText.setOnClickListener(this);
        attendanceText.setOnClickListener(this);
        replyText.setOnClickListener(this);
        passwordText.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View mView) {
        departmentText=(TextView) mView.findViewById(R.id.tv_manager_department);
        personnelText=(TextView) mView.findViewById(R.id.tv_manager_personnel);
        departmentTurnText=(TextView) mView.findViewById(R.id.tv_manager_department_turn);
        attendanceText=(TextView) mView.findViewById(R.id.tv_manager_attendance);
        replyText=(TextView) mView.findViewById(R.id.tv_apply_reply);
        passwordText=(TextView) mView.findViewById(R.id.tv_manager_password);
    }

    @Override
    public void onClick(View v) {
        Intent newIntent=null;
        switch (v.getId()){
            case R.id.tv_manager_department://部门管理
                newIntent= new Intent(getActivity(), DepartmentManagerActivity.class);
                break;
            case R.id.tv_manager_personnel://人员管理
                newIntent= new Intent(getActivity(), PersonalManagerActivity.class);
                break;
            case R.id.tv_manager_department_turn://部门调转
                break;
            case R.id.tv_manager_attendance://考勤管理
                break;
            case R.id.tv_apply_reply://申请回复
                break;
            case R.id.tv_manager_password://密码管理
                newIntent = new Intent(getActivity(), PasswordManagerActivity.class);
                break;
            default:
                break;
        }
        if(newIntent!=null){
            StartActivity.jumpTo(getActivity(), newIntent);
        }
    }
}
