package com.example.wjx.xing.activitys.manager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.wjx.xing.Adapters.DepartmentListAdapter;
import com.example.wjx.xing.Adapters.PersonalListAdapter;
import com.example.wjx.xing.Adapters.StringSpinnerAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentBean;
import com.example.wjx.xing.bean.PersonalBean;
import com.example.wjx.xing.data.DefaultDaysList;

import java.util.ArrayList;
import java.util.List;

public class AddAttendanceActivity extends BaseActivity {

    private Spinner departmentSpinner, personalSpinner, daysEvectionSpinner, daysLeaveSpinner, daysAttendanceSpinner;
    private List<DepartmentBean> departmentBeanList;
    private List<PersonalBean> personalBeanList;
    private List<String> daysOfMonths;
    private int selectDepartmentPosition = -1;
    private int selectPersonalPosition = -1;
    private int selectDaysEvectionPosition = -1;
    private int selectDaysLeavePosition = -1;
    private int selectDaysAttendancePosition = -1;
    private DepartmentListAdapter departmentListAdapter;
    private PersonalListAdapter personalListAdapter;
    private StringSpinnerAdapter daysEvectionAdapter, daysLeaveAdapter, daysAttendanceAdapter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_add_attendance;
    }

    @Override
    protected CharSequence getTitleText() {
        return "添加考勤信息";
    }

    @Override
    protected void onClickRight() {
        // TODO: 2017/5/19 网络请求，添加一条考勤信息
    }

    @Override
    protected void initData() {
        departmentBeanList = new ArrayList<>();
        personalBeanList = new ArrayList<>();
        daysOfMonths = DefaultDaysList.instanceHalfOfDay;
        departmentListAdapter = new DepartmentListAdapter(this, departmentBeanList);
        personalListAdapter = new PersonalListAdapter(this, personalBeanList);
        daysEvectionAdapter = new StringSpinnerAdapter(this, daysOfMonths);
        daysLeaveAdapter = new StringSpinnerAdapter(this, daysOfMonths);
        daysAttendanceAdapter = new StringSpinnerAdapter(this, daysOfMonths);
    }

    @Override
    protected void initView() {
        departmentSpinner = (Spinner) findViewById(R.id.spinner_select_department);
        personalSpinner = (Spinner) findViewById(R.id.spinner_select_personal);
        daysEvectionSpinner = (Spinner) findViewById(R.id.spinner_select_days_evection);
        daysLeaveSpinner = (Spinner) findViewById(R.id.spinner_select_days_leave);
        daysAttendanceSpinner = (Spinner) findViewById(R.id.spinner_select_days_attendance);
    }

    @Override
    protected void initSet() {
        departmentSpinner.setAdapter(departmentListAdapter);
        personalSpinner.setAdapter(personalListAdapter);
        daysEvectionSpinner.setAdapter(daysEvectionAdapter);
        daysLeaveSpinner.setAdapter(daysLeaveAdapter);
        daysAttendanceSpinner.setAdapter(daysAttendanceAdapter);

        requestDepartmentList();

        departmentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDepartmentPosition = position;
            }
        });
        personalSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPersonalPosition = position;
            }
        });
        daysEvectionSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDaysEvectionPosition = position;
            }
        });
        daysLeaveSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDaysLeavePosition = position;
            }
        });
        daysAttendanceSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectDaysAttendancePosition = position;
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
