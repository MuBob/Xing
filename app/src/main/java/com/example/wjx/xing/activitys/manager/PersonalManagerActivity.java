package com.example.wjx.xing.activitys.manager;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wjx.xing.Adapters.PersonalListAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentBean;
import com.example.wjx.xing.bean.PersonalBean;
import com.example.wjx.xing.dialog.DeletePersonalDialog;
import com.example.wjx.xing.utils.StartActivity;

import java.util.ArrayList;
import java.util.List;

public class PersonalManagerActivity extends BaseActivity implements AdapterView.OnItemLongClickListener{
    private ListView listView;
    private List<PersonalBean> beanList;
    private PersonalListAdapter adapter;
    private int longClickPosition=-1;
    private PopupWindow popupWindow;
    private DeletePersonalDialog deletePersonalDialog;


    @Override
    protected int getResourceId() {
        return R.layout.activity_personal_manager;
    }

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setText("添加员工");
    }

    @Override
    protected CharSequence getTitleText() {
        return "员工管理";
    }

    @Override
    protected void onClickRight() {
        Intent intent=new Intent(this, AddPersonalActivity.class);
        StartActivity.jumpTo(this, intent);
    }

    @Override
    protected void initData() {
        beanList=new ArrayList<>();
        adapter=new PersonalListAdapter(this, beanList);
        deletePersonalDialog=new DeletePersonalDialog(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 2017/5/19 请求接口，删除指定员工
                beanList.remove(longClickPosition);
                adapter.notifyDataSetChanged();
            }
        });
        popupWindow=new PopupWindow(this);
        View contentView = getLayoutInflater().inflate(R.layout.popwindow_del, null);
        contentView.findViewById(R.id.tv_del).setOnClickListener(this);
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.list_view_personal);
    }

    @Override
    protected void initSet() {
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "PersonalManagerActivity.onItemLongClick: position="+position);
        longClickPosition=position;
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        return true;
    }

    private static final String TAG = "PersonalManagerActivity";

    @Override
    protected void onResume() {
        super.onResume();
        requestList();
    }

    /**
     * 请求网络，获取员工数据
     */
    private void requestList() {
        // TODO: 2017/5/19 网络请求，请求员工列表接口
        beanList.clear();
        for (int i = 0; i < 21; i++) {
            beanList.add(new PersonalBean());

            PersonalBean personalBean = beanList.get(i);
            personalBean.setNumber(String.valueOf(i+100));
            personalBean.setName("员工"+i);
            personalBean.setDepartmentBean(new DepartmentBean());
            personalBean.getDepartmentBean().setName("职位"+i);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_del:
                if(longClickPosition>=0){
                    deletePersonalDialog.show();
                }
                popupWindow.dismiss();
                break;
        }
    }
}
