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
import com.example.wjx.xing.Adapters.PersonalListAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.PersonalBean;
import com.example.wjx.xing.bean.PersonalListDataResponse;
import com.example.wjx.xing.dialog.DeletePersonalDialog;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.GsonUtil;
import com.example.wjx.xing.utils.StartActivity;

import org.json.JSONException;
import org.json.JSONObject;

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
                String delUrl = RequestPath.getDeletePersonal(beanList.get(longClickPosition).getId());
                Log.i(TAG, "DeletePersonalDialog.onClick: url="+delUrl);
                mRequest = new JsonObjectRequest(delUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i(TAG, "DeletePersonalDialog.onResponse: resp=" + response);
                            int code = response.getInt("code");
                            if (code == 0) {
                                beanList.remove(longClickPosition);
                                adapter.notifyDataSetChanged();
                            }
                            String msg = response.getString("msg");
                            showToastShort(msg);
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
        String urlList = RequestPath.getListPersonal(null);
        Log.i(TAG, "PersonalManagerActivity.requestList: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "PersonalManagerActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String data=response.getString("data");
                        PersonalListDataResponse personalList = (PersonalListDataResponse) GsonUtil.fromJsonToObject(data, PersonalListDataResponse.class);
                        beanList.clear();
                        if(personalList!=null&&personalList.getList()!=null){
                            beanList.addAll(personalList.getList());
                        }
                        adapter.notifyDataSetChanged();
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
                Log.i(TAG, "PersonalManagerActivity.onErrorResponse: " + error.getMessage());
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
}
