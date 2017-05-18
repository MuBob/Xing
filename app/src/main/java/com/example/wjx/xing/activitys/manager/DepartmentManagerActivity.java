package com.example.wjx.xing.activitys.manager;

import android.content.Intent;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wjx.xing.Adapters.DepartmentListAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentBean;
import com.example.wjx.xing.utils.StartActivity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentManagerActivity extends BaseActivity {

    private ListView departmentListView;
    private List<DepartmentBean> departmentBeanList;
    private DepartmentListAdapter listAdapter;

    private static final String TAG = "DepartmentManagerActivi";
    @Override
    protected void onClickLeft() {
        super.onClickLeft();
        Log.i(TAG, "DepartmentManagerActivity.onClickLeft: ");
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_department_manager;
    }

    @Override
    protected CharSequence getTitleText() {
        return "部门管理";
    }

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setText("添加部门");
    }

    @Override
    protected void onClickRight() {
        Intent intent=new Intent(this, AddDepartmentActivity.class);
        StartActivity.jumpTo(this, intent);
    }

    @Override
    protected void initData() {
        departmentBeanList=new ArrayList<>();
        listAdapter=new DepartmentListAdapter(this, departmentBeanList);
    }

    @Override
    protected void initView() {
        departmentListView = (ListView) findViewById(R.id.list_view_department);
    }

    @Override
    protected void initSet() {
        departmentListView.setAdapter(listAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestList();
    }

    /**
     * 网络请求数据
     */
    private void requestList() {
        departmentBeanList.clear();
        for (int i = 0; i < 21; i++) {
            departmentBeanList.add(new DepartmentBean());
            departmentBeanList.get(i).setId(String.valueOf(i+100));
            departmentBeanList.get(i).setName("部门名称"+i);
            departmentBeanList.get(i).setDes("部门介绍"+i);
        }
        listAdapter.notifyDataSetChanged();
    }
}
