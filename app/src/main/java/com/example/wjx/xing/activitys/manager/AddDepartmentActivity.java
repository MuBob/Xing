package com.example.wjx.xing.activitys.manager;

import android.util.Log;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.net.RequestPath;

import org.json.JSONException;
import org.json.JSONObject;

public class AddDepartmentActivity extends BaseActivity {

    private EditText nameText, numberText, descText;
    @Override
    protected int getResourceId() {
        return R.layout.activity_add_department;
    }

    @Override
    protected CharSequence getTitleText() {
        return "新增部门";
    }

    private static final String TAG = "AddDepartmentActivity";
    @Override
    protected void onClickRight() {
        String addUrl = RequestPath.getAddDepartment(numberText.getText().toString().trim(), nameText.getText().toString().trim(), descText.getText().toString());
        Log.i(TAG, "AddDepartmentActivity.onClickRight: url="+addUrl);
        mRequest = new JsonObjectRequest(addUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mWaitDialog.dismiss();
                try {
                    Log.i(TAG, "AddDepartmentActivity.onResponse: res="+response);
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
                Log.i(TAG, "AddDepartmentActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        nameText = (EditText) findViewById(R.id.tv_department_name);
        numberText = (EditText) findViewById(R.id.tv_department_number);
        descText = (EditText) findViewById(R.id.tv_department_desc);
    }

    @Override
    protected void initSet() {
        nameText.requestFocus();
    }
}
