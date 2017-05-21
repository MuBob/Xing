package com.example.wjx.xing.activitys.manager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.wjx.xing.Adapters.DepartmentListAdapter;
import com.example.wjx.xing.Adapters.RecordSpinnerAdapter;
import com.example.wjx.xing.Adapters.RoleSpinnerAdapter;
import com.example.wjx.xing.Adapters.StringSpinnerAdapter;
import com.example.wjx.xing.Adapters.SkillSpinnerAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentBean;
import com.example.wjx.xing.bean.RecordBean;
import com.example.wjx.xing.bean.RoleBean;
import com.example.wjx.xing.bean.SkillBean;
import com.example.wjx.xing.data.DefaultRecordList;
import com.example.wjx.xing.data.DefaultRoleList;
import com.example.wjx.xing.data.DefaultSexList;
import com.example.wjx.xing.data.DefaultSkillList;

import java.util.ArrayList;
import java.util.List;

public class AddPersonalActivity extends BaseActivity {

    private EditText nameEdit, emaiEdit, passwordEdit;
    private List<DepartmentBean> departmentBeanList;
    private List<RoleBean> roleBeenList;
    private List<String> sexList;
    private List<RecordBean> recordBeanList;
    private List<SkillBean> skillBeanList;
    private int
            selectDepartmentPosition = -1,
            selectRolePosition = -1,
            selectSexPosition = -1,
            selectRecordPostion = -1,
            selectSkillPosition = -1;
    private Spinner departmentSpinner, roleSpinner, sexSpinner, recordSpinner, skillSpinner;
    private DepartmentListAdapter departmentListAdapter;
    private RoleSpinnerAdapter roleSpinnerAdapter;
    private StringSpinnerAdapter sexSpinnerAdapter;
    private RecordSpinnerAdapter recordSpinnerAdapter;
    private SkillSpinnerAdapter skillSpinnerAdapter;

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
        departmentBeanList = new ArrayList<>();
        roleBeenList = DefaultRoleList.instance;
        sexList = DefaultSexList.instance;
        recordBeanList = DefaultRecordList.instance;
        skillBeanList = DefaultSkillList.instance;
        departmentListAdapter = new DepartmentListAdapter(this, departmentBeanList);
        roleSpinnerAdapter = new RoleSpinnerAdapter(this, roleBeenList);
        sexSpinnerAdapter = new StringSpinnerAdapter(this, sexList);
        recordSpinnerAdapter = new RecordSpinnerAdapter(this, recordBeanList);
        skillSpinnerAdapter = new SkillSpinnerAdapter(this, skillBeanList);
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
        departmentSpinner.setAdapter(departmentListAdapter);
        roleSpinner.setAdapter(roleSpinnerAdapter);
        sexSpinner.setAdapter(sexSpinnerAdapter);
        recordSpinner.setAdapter(recordSpinnerAdapter);
        skillSpinner.setAdapter(skillSpinnerAdapter);

        requestDepartmentList();
        departmentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDepartmentPosition = position;
            }
        });
        roleSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectRolePosition=position;
            }
        });
        sexSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectSexPosition=position;
            }
        });
        recordSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectRecordPostion=position;
            }
        });
        skillSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectSkillPosition=position;
            }
        });
    }

    /**
     * 网络请求部门列表数据
     */
    private void requestDepartmentList() {
        // TODO: 2017/5/19
        departmentBeanList.clear();
        for (int i = 0; i < 21; i++) {
            departmentBeanList.add(new DepartmentBean());
            departmentBeanList.get(i).setId(String.valueOf(i+100));
            departmentBeanList.get(i).setName("部门名称"+i);
            departmentBeanList.get(i).setDes("部门介绍"+i);
        }
        departmentListAdapter.notifyDataSetChanged();
    }
}
