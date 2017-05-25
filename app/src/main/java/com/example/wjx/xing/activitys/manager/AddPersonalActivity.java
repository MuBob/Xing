package com.example.wjx.xing.activitys.manager;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.Adapters.DepartmentListAdapter;
import com.example.wjx.xing.Adapters.RecordSpinnerAdapter;
import com.example.wjx.xing.Adapters.RoleSpinnerAdapter;
import com.example.wjx.xing.Adapters.SkillSpinnerAdapter;
import com.example.wjx.xing.Adapters.StringSpinnerAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentListDataResponse;
import com.example.wjx.xing.bean.RecordBean;
import com.example.wjx.xing.bean.RoleBean;
import com.example.wjx.xing.bean.SkillBean;
import com.example.wjx.xing.data.DefaultRecordList;
import com.example.wjx.xing.data.DefaultRoleList;
import com.example.wjx.xing.data.DefaultSexList;
import com.example.wjx.xing.data.DefaultSkillList;
import com.example.wjx.xing.db.TableDepartment;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddPersonalActivity extends BaseActivity {

    private EditText nameEdit, numberEdit, emaiEdit, passwordEdit;
    private List<TableDepartment> departmentBeanList;
    private List<RoleBean> roleBeenList;
    private List<String> sexList;
    private List<RecordBean> recordBeanList;
    private List<SkillBean> skillBeanList;
    private int
            selectDepartmentPosition,
            selectRolePosition,
            selectSexPosition,
            selectRecordPostion,
            selectSkillPosition;
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

    private static final String TAG = "AddPersonalActivity";
    @Override
    protected void onClickRight() {
        String addUrl = RequestPath.getAddPersonal(
                numberEdit.getText().toString().trim(),
                nameEdit.getText().toString().trim(),
                passwordEdit.getText().toString(),
                departmentBeanList.get(selectDepartmentPosition).getId(),
                sexList.get(selectSexPosition),
                emaiEdit.getText().toString().trim(),
                recordBeanList.get(selectRecordPostion).getName(),
                skillBeanList.get(selectSkillPosition).getName(),
                roleBeenList.get(selectRolePosition).getName()
                );
        Log.i(TAG, "AddPersonalActivity.onClickRight: url="+addUrl);
        mRequest = new JsonObjectRequest(addUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mWaitDialog.dismiss();
                try {
                    Log.i(TAG, "AddPersonalActivity.onResponse: res="+response);
                    String msg = response.getString("msg");
                    showToastShort(msg);
                    int code = response.getInt("code");
                    if (code == 0) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "AddPersonalActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
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
        numberEdit = (EditText) findViewById(R.id.edit_personal_number);
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
        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDepartmentPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        roleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectRolePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSexPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        recordSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectRecordPostion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        skillSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectSkillPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 网络请求部门列表数据
     */
    private void requestDepartmentList() {
        String urlList = RequestPath.getListDepartment();
        Log.i(TAG, "DepartmentManagerActivity.requestList: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "DepartmentManagerActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String data=response.getString("data");
                        DepartmentListDataResponse departmentList = (DepartmentListDataResponse) GsonUtil.fromJsonToObject(data, DepartmentListDataResponse.class);
                        departmentBeanList.clear();
                        if(departmentList!=null&&departmentList.getList()!=null){
                            departmentBeanList.addAll(departmentList.getList());
                        }else{
                            showToastShort("没有所属部门，请先添加部门信息");
                        }
                        departmentListAdapter.notifyDataSetChanged();
                    }else {
                        String msg = response.getString("msg");
                        showToastShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWaitDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "DepartmentManagerActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }
}
