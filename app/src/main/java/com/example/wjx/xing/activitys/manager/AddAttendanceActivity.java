package com.example.wjx.xing.activitys.manager;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.Adapters.DepartmentListAdapter;
import com.example.wjx.xing.Adapters.PersonalListAdapter;
import com.example.wjx.xing.Adapters.StringSpinnerAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentListDataResponse;
import com.example.wjx.xing.bean.PersonalBean;
import com.example.wjx.xing.bean.PersonalListDataResponse;
import com.example.wjx.xing.bean.TurnPersonalListDataResponse;
import com.example.wjx.xing.data.DefaultDaysList;
import com.example.wjx.xing.db.TableDepartment;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddAttendanceActivity extends BaseActivity {

    private Spinner departmentSpinner, personalSpinner, daysEvectionSpinner, daysLeaveSpinner, daysAbsenteeismSpinner;
    private List<TableDepartment> departmentBeanList;
    private List<PersonalBean> personalBeanList;
    private List<String> daysOfMonths;
    private int selectDepartmentPosition;
    private int selectPersonalPosition ;
    private int selectDaysEvectionPosition;
    private int selectDaysLeavePosition;
    private int selectDaysAbsenteeism;
    private DepartmentListAdapter departmentListAdapter;
    private PersonalListAdapter personalListAdapter;
    private StringSpinnerAdapter daysEvectionAdapter, daysLeaveAdapter, daysAbsenteeismAdapter;

    @Override
    protected int getResourceId() {
        return R.layout.activity_add_attendance;
    }

    @Override
    protected CharSequence getTitleText() {
        return "添加考勤信息";
    }

    /**
     * 网络请求，添加一条考勤信息
     */
    @Override
    protected void onClickRight() {
        String addUrl = RequestPath.getAddAttendance(
                personalBeanList.get(selectPersonalPosition).getId(),
                Double.parseDouble(daysOfMonths.get(selectDaysEvectionPosition)),
                Double.parseDouble(daysOfMonths.get(selectDaysLeavePosition)),
                Double.parseDouble(daysOfMonths.get(selectDaysAbsenteeism)));
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
        daysOfMonths = DefaultDaysList.instanceHalfOfDay;
        departmentListAdapter = new DepartmentListAdapter(this, departmentBeanList);
        personalListAdapter = new PersonalListAdapter(this, personalBeanList);
        daysEvectionAdapter = new StringSpinnerAdapter(this, daysOfMonths);
        daysLeaveAdapter = new StringSpinnerAdapter(this, daysOfMonths);
        daysAbsenteeismAdapter = new StringSpinnerAdapter(this, daysOfMonths);
    }

    @Override
    protected void initView() {
        departmentSpinner = (Spinner) findViewById(R.id.spinner_select_department);
        personalSpinner = (Spinner) findViewById(R.id.spinner_select_personal);
        daysEvectionSpinner = (Spinner) findViewById(R.id.spinner_select_days_evection);
        daysLeaveSpinner = (Spinner) findViewById(R.id.spinner_select_days_leave);
        daysAbsenteeismSpinner = (Spinner) findViewById(R.id.spinner_select_days_attendance);
    }

    @Override
    protected void initSet() {
        departmentSpinner.setAdapter(departmentListAdapter);
        personalSpinner.setAdapter(personalListAdapter);
        daysEvectionSpinner.setAdapter(daysEvectionAdapter);
        daysLeaveSpinner.setAdapter(daysLeaveAdapter);
        daysAbsenteeismSpinner.setAdapter(daysAbsenteeismAdapter);

        requestDepartmentList();

        departmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDepartmentPosition = position;
                requestPersonalInDepartment(departmentBeanList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        personalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPersonalPosition = position;
                requestPersonalAttendance(personalBeanList.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        daysEvectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDaysEvectionPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        daysLeaveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDaysLeavePosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        daysAbsenteeismSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectDaysAbsenteeism = position;
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
        Log.i(TAG, "AddAttendanceActivity.requestPersonalInDepartment: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "AddAttendanceActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String data=response.getString("data");
                        PersonalListDataResponse personalList = (PersonalListDataResponse) GsonUtil.fromJsonToObject(data, PersonalListDataResponse.class);
                        personalBeanList.clear();
                        if(personalList!=null&&personalList.getList()!=null){
                            personalBeanList.addAll(personalList.getList());
                        }
                        personalListAdapter.notifyDataSetChanged();
                        personalSpinner.setSelection(0);
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
                Log.i(TAG, "AddAttendanceActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }
    private static final String TAG = "AddAttendanceActivity";
    /**
     * 网络请求部门列表数据
     */
    private void requestDepartmentList() {
        String urlList = RequestPath.getListDepartment();
        Log.i(TAG, "AddAttendanceActivity.requestDepartmentList: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "AddAttendanceActivity.requestDepartmentList.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String data=response.getString("data");
                        DepartmentListDataResponse departmentList = (DepartmentListDataResponse) GsonUtil.fromJsonToObject(data, DepartmentListDataResponse.class);
                        departmentBeanList.clear();
                        if(departmentList!=null&&departmentList.getList()!=null){
                            departmentBeanList.addAll(departmentList.getList());
                        }
                        departmentListAdapter.notifyDataSetChanged();
                        departmentSpinner.setSelection(0);
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
                Log.i(TAG, "AddAttendanceActivity.requestDepartmentList.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }

    private void requestPersonalAttendance(String id) {
        String urlList = RequestPath.getListPersonalTurn(id);
        Log.i(TAG, "AddAttendanceActivity.requestPersonalAttendance: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "AddAttendanceActivity.onResponse: res="+response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String data=response.getString("data");
                        TurnPersonalListDataResponse personalList = (TurnPersonalListDataResponse) GsonUtil.fromJsonToObject(data, TurnPersonalListDataResponse.class);

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
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }
}
