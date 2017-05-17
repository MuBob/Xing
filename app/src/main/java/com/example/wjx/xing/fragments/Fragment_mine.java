package com.example.wjx.xing.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.InfoselfActivity;
import com.example.wjx.xing.activitys.MessageActivity;
import com.example.wjx.xing.activitys.MoreActivity;
import com.example.wjx.xing.activitys.SearchActivity;
import com.example.wjx.xing.activitys.SettingActivity;

/**
 * 个人信息
 * Created by youzi on 2017/3/30.
 */

public class Fragment_mine extends Fragment implements View.OnClickListener {

    private View mView;
    private TextView mInfo;//个人信息
    private TextView mMessage;//学历信息界面
    private TextView mSetting;//职称技能
    private TextView mSearch;//密码管理
    private TextView mMore;//更多


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragments_mine1,null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }
    private void initView() {
        mInfo = (TextView) mView.findViewById(R.id.tv_mine_info);
        mMessage = (TextView) mView.findViewById(R.id.tv_mine_message);
        mSetting = (TextView) mView.findViewById(R.id.tv_mine_setting);
        mSearch = (TextView) mView.findViewById(R.id.tv_mine_search);
        mMore = (TextView) mView.findViewById(R.id.tv_mine_more);
        //添加监听
        mInfo.setOnClickListener(this);
        mMessage.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        mMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_mine_info://个人信息界面
                Intent intent_infoself = new Intent(getActivity(), InfoselfActivity.class);
                getActivity().startActivity(intent_infoself);
                break;
           case R.id.tv_mine_message://学历信息界面
                Intent intent_message = new Intent(getActivity(), MessageActivity.class);
                getActivity().startActivity(intent_message);
                break;
            case R.id.tv_mine_setting://职称技能界面
                Intent intent_setting = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent_setting);
                break;
            case R.id.tv_mine_search://密码管理界面
                Intent intent_search = new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent_search);
                break;
            case R.id.tv_mine_more://密码管理界面
                Intent intent_more = new Intent(getActivity(), MoreActivity.class);
                getActivity().startActivity(intent_more);
                break;
        }
    }
}
