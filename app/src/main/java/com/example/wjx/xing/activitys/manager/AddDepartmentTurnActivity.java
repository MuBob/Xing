package com.example.wjx.xing.activitys.manager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.wjx.xing.Adapters.DepartmentListAdapter;
import com.example.wjx.xing.Adapters.PersonalListAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentBean;
import com.example.wjx.xing.bean.PersonalBean;

import java.util.ArrayList;
import java.util.List;

public class AddDepartmentTurnActivity extends BaseActivity {

    private Spinner oldDepartmentSpinner, newDepartmentSpinner, personalSpinner;
    private EditText descEdit;
    private List<DepartmentBean> oldDepartmentBeanList, newDepartmentBeanList;
    private List<PersonalBean> personalBeanList;
    private int
            selectOldDepartmentPosition = -1,
            selectNewDepartmentPosition = -1,
            selectPersonalPosition = -1;
    private DepartmentListAdapter oldDepartmentListAdapter, newDepartmentListAdapter;
    private PersonalListAdapter personalListAdapter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_add_department_add;
    }

    @Override
    protected CharSequence getTitleText() {
        return "新增调转信息";
    }

    @Override
    protected void onClickRight() {
        // TODO: 2017/5/19 网络请求，修改员工所属部门
    }

    @Override
    protected void initData() {
        oldDepartmentBeanList = new ArrayList<>();
        newDepartmentBeanList = new ArrayList<>();
        personalBeanList = new ArrayList<>();
        oldDepartmentListAdapter = new DepartmentListAdapter(this, oldDepartmentBeanList);
        newDepartmentListAdapter = new DepartmentListAdapter(this, newDepartmentBeanList);
        personalListAdapter = new PersonalListAdapter(this, personalBeanList);
    }

    @Override
    protected void initView() {
        oldDepartmentSpinner = (Spinner) findViewById(R.id.spinner_select_department_old);
        personalSpinner = (Spinner) findViewById(R.id.spinner_select_personal);
        newDepartmentSpinner = (Spinner) findViewById(R.id.spinner_select_department_new);
        descEdit = (EditText) findViewById(R.id.edit_edit_desc);
    }

    @Override
    protected void initSet() {
        oldDepartmentSpinner.setAdapter(oldDepartmentListAdapter);
        newDepartmentSpinner.setAdapter(newDepartmentListAdapter);
        personalSpinner.setAdapter(personalListAdapter);

        requestDepartmentList();

        oldDepartmentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectOldDepartmentPosition = position;
            }
        });
        newDepartmentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectNewDepartmentPosition = position;
            }
        });
        personalSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPersonalPosition = position;
            }
        });
    }

    /**
     * 网络请求部门列表数据
     */
    private void requestDepartmentList() {
        // TODO: 2017/5/19
    }
}