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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.Adapters.DepartmentListAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.DepartmentListDataResponse;
import com.example.wjx.xing.db.TableDepartment;
import com.example.wjx.xing.dialog.DeletePersonalDialog;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.GsonUtil;
import com.example.wjx.xing.utils.StartActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DepartmentManagerActivity extends BaseActivity implements AdapterView.OnItemLongClickListener{

    private ListView departmentListView;
    private List<TableDepartment> departmentBeanList;
    private DepartmentListAdapter listAdapter;
    private int longClickPosition=-1;
    private PopupWindow popupWindow;
    private DeletePersonalDialog deletePersonalDialog;

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
        deletePersonalDialog=new DeletePersonalDialog(this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 2017/5/19 请求网络，删除指定部门
                departmentBeanList.remove(longClickPosition);
                listAdapter.notifyDataSetChanged();
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
        departmentListView = (ListView) findViewById(R.id.list_view_department);
    }

    @Override
    protected void initSet() {
        departmentListView.setAdapter(listAdapter);
        departmentListView.setOnItemLongClickListener(this);
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
        // TODO: 2017/5/19 网络请求，请求部门列表接口
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
                        }
                        listAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "DepartmentManagerActivity.onItemLongClick: ");
        longClickPosition=position;
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        return true;
    }
}
