package com.example.wjx.xing.fragments;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.PasswordManagerActivity;
import com.example.wjx.xing.activitys.normal.EveryDayActivity;
import com.example.wjx.xing.activitys.normal.NotifyActivity;
import com.example.wjx.xing.activitys.normal.SelfInfoActivity;
import com.example.wjx.xing.utils.StartActivity;

/**
 * 个人信息
 * Created by youzi on 2017/3/30.
 */

public class NormalFragment extends BaseFragment implements View.OnClickListener {

    private TextView mNotify;//通知公告
    private TextView mInfo;//个人信息
    private TextView mSearch;//密码管理
    private TextView mEveryDay;//日常考勤
    private TextView mMore;//退出登陆


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_normal;
    }


    @Override
    protected void initSet() {
        //添加监听
        mNotify.setOnClickListener(this);
        mInfo.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mEveryDay.setOnClickListener(this);
        mMore.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    protected void initView(View mView) {
        mNotify = (TextView) mView.findViewById(R.id.tv_notify);
        mInfo = (TextView) mView.findViewById(R.id.tv_mine_info);
        mSearch = (TextView) mView.findViewById(R.id.tv_mine_search);
        mEveryDay = (TextView) mView.findViewById(R.id.tv_every_day);
        mMore = (TextView) mView.findViewById(R.id.main_title_normal_right);
    }

    private static final String TAG = "NormalFragment";
    @Override
    public void onClick(View v) {
        Log.i(TAG, "NormalFragment.onClick: v="+v);
        Intent intent=null;
        switch (v.getId()) {
            case R.id.tv_notify://通知公告界面
                intent=new Intent(getActivity(), NotifyActivity.class);
                break;
            case R.id.tv_mine_info://个人信息界面
                intent= new Intent(getActivity(), SelfInfoActivity.class);
                break;
            case R.id.tv_mine_search://密码管理界面
                intent= new Intent(getActivity(), PasswordManagerActivity.class);
                break;
            case R.id.tv_every_day:  //日常考勤
                intent = new Intent(getActivity(), EveryDayActivity.class);
                break;
            case R.id.main_title_normal_right://退出当前账号，跳转登陆界面
                logout();
                break;
        }
        StartActivity.jumpTo(getActivity(), intent);
    }
}
