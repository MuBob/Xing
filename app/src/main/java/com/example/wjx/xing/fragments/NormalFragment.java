package com.example.wjx.xing.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.EveryDayActivity;
import com.example.wjx.xing.activitys.InfoEducationActivity;
import com.example.wjx.xing.activitys.InfoSelfActivity;
import com.example.wjx.xing.activitys.InfoSkillActivity;
import com.example.wjx.xing.activitys.MoreActivity;
import com.example.wjx.xing.activitys.PasswordManagerActivity;
import com.example.wjx.xing.utils.StartActivity;

/**
 * 个人信息
 * Created by youzi on 2017/3/30.
 */

public class NormalFragment extends BaseFragment implements View.OnClickListener {

    private TextView mInfo;//个人信息
    private TextView mMessage;//学历信息界面
    private TextView mSetting;//职称技能
    private TextView mSearch;//密码管理
    private TextView mEveryDay;//日常考勤
    private TextView mMore;//更多


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_normal;
    }

    @Override
    protected void initSet() {
        //添加监听
        mInfo.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mEveryDay.setOnClickListener(this);
        mMore.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    protected void initView(View mView) {
        mInfo = (TextView) mView.findViewById(R.id.tv_mine_info);
        mMessage = (TextView) mView.findViewById(R.id.tv_mine_message);
        mSetting = (TextView) mView.findViewById(R.id.tv_mine_setting);
        mSearch = (TextView) mView.findViewById(R.id.tv_mine_search);
        mEveryDay = (TextView) mView.findViewById(R.id.tv_every_day);
        mMore = (TextView) mView.findViewById(R.id.tv_mine_more);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mine_info://个人信息界面
                Intent intent_infoself = new Intent(getActivity(), InfoSelfActivity.class);
                StartActivity.jumpTo(getActivity(), intent_infoself);
                break;
            case R.id.tv_mine_message://学历信息界面
                Intent intent_message = new Intent(getActivity(), InfoEducationActivity.class);
                StartActivity.jumpTo(getActivity(), intent_message);
                break;
            case R.id.tv_mine_setting://职称技能界面
                Intent intent_setting = new Intent(getActivity(), InfoSkillActivity.class);
                StartActivity.jumpTo(getActivity(), intent_setting);
                break;
            case R.id.tv_mine_search://密码管理界面
                Intent intent_search = new Intent(getActivity(), PasswordManagerActivity.class);
                StartActivity.jumpTo(getActivity(), intent_search);
                break;
            case R.id.tv_every_day:  //日常考勤
                Intent intent_every_day = new Intent(getActivity(), EveryDayActivity.class);
                StartActivity.jumpTo(getActivity(), intent_every_day);
                break;
            case R.id.tv_mine_more://更多界面
                Intent intent_more = new Intent(getActivity(), MoreActivity.class);
                StartActivity.jumpTo(getActivity(), intent_more);
                break;
        }
    }
}
