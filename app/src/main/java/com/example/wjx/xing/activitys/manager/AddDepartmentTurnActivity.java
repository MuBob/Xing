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
import com.example.wjx.xing.Adapters.PersonalListAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentListDataResponse;
import com.example.wjx.xing.bean.PersonalBean;
import com.example.wjx.xing.bean.PersonalListDataResponse;
import com.example.wjx.xing.db.TableDepartment;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddDepartmentTurnActivity extends BaseActivity {

    private Spinner oldDepartmentSpinner, newDepartmentSpinner, personalSpinner;
    private EditText descEdit;
    private List<TableDepartment> departmentBeanList;
    private List<PersonalBean> personalBeanList;
    private int
            selectNewDepartmentPosition,
            selectPersonalPosition;
    private DepartmentListAdapter departmentListAdapter;
    private PersonalListAdapter personalListAdapter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_add_department_add;
    }

    @Override
    protected CharSequence getTitleText() {
        return "新增调转信息";
    }

    private static final String TAG = "AddDepartmentTurnActiviy";
    @Override
    protected void onClickRight() {
        //网络请求，修改员工所属部门
        String addUrl = RequestPath.getTurnPersonalDepartment(
                personalBeanList.get(selectPersonalPosition).getId(),
                departmentBeanList.get(selectNewDepartmentPosition).getId(),
                descEdit.getText().toString());
        Log.i(TAG, "AddDepartmentTurnActivity.onClickRight: url="+addUrl);
        mRequest = new JsonObjectRequest(addUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mWaitDialog.dismiss();
                try {
                    Log.i(TAG, "AddDepartmentTurnActivity.onResponse: res="+response);
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
                Log.i(TAG, "AddDepartmentTurnActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }

    @Override
    protected void initData() {
        departmentBeanList = new ArrayList<>();
        personalBeanList = new ArrayList<>();
        departmentListAdapter = new DepartmentListAdapter(this, departmentBeanList);
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
        oldDepartmentSpinner.setAdapter(departmentListAdapter);
        newDepartmentSpinner.setAdapter(departmentListAdapter);
        personalSpinner.setAdapter(personalListAdapter);

        requestDepartmentList();
        oldDepartmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                requestPersonalInDepartment(departmentBeanList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        newDepartmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectNewDepartmentPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        personalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPersonalPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 根据当前部门选择员工列表
     * @param dId
     */
    private void requestPersonalInDepartment(String dId) {
        String urlList = RequestPath.getListPersonal(dId);
        Log.i(TAG, "AddDepartmentTurnActivity.requestPersonalInDepartment: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "AddDepartmentTurnActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String data=response.getString("data");
                        PersonalListDataResponse personalList = (PersonalListDataResponse) GsonUtil.fromJsonToObject(data, PersonalListDataResponse.class);
                        personalBeanList.clear();
                        if(personalList!=null&&personalList.getList()!=null){
                            personalBeanList.addAll(personalList.getList());
                        }else {
                            showToastShort("当前部门下还没有员工");
                        }
                        personalListAdapter.notifyDataSetChanged();
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
                Log.i(TAG, "AddDepartmentTurnActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }

    /**
     * 网络请求部门列表数据
     */
    private void requestDepartmentList() {
        String urlList = RequestPath.getListDepartment();
        Log.i(TAG, "AddDepartmentTurnActivity.requestList: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "AddDepartmentTurnActivity.onResponse: resp=" + response);
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
                        oldDepartmentSpinner.setSelection(0);
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
                Log.i(TAG, "AddDepartmentTurnActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }

}
