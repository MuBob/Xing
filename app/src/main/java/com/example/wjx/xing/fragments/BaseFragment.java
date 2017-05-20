package com.example.wjx.xing.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wjx.xing.Common;
import com.example.wjx.xing.activitys.LoginActivity;
import com.example.wjx.xing.utils.StartActivity;

/**
 * Created by Administrator on 2017/5/18.
 */

public abstract class BaseFragment extends Fragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initView(mView);
        initSet();
    }

    private static final String TAG = "BaseFragment";
    public void logout(){
        Log.i(TAG, "BaseFragment.logout: ");
        getActivity().getSharedPreferences(Common.SP_NAME_SETTING, 0).edit()
                .putInt(Common.KEY_ROLE_LOGIN, 0).commit();
        StartActivity.StartActivity(getActivity(), LoginActivity.class);
    }

    protected abstract void initSet();

    protected abstract void initData();

    protected abstract void initView(View mView);
}
