package com.example.wjx.xing.activitys.normal;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.net.RequestPath;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 出差管理
 */
public class EvectionActivity extends BaseActivity {
    private EditText spaceEdit, countEdit, trafficEdit, whatEdit;
    @Override
    protected int getResourceId() {
        return R.layout.activity_evection;
    }

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setVisibility(View.GONE);
    }

    @Override
    protected void onClickRight() {

    }
    @Override
    protected CharSequence getTitleText() {
        return "出差";
    }

    @Override
    protected void initSet() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spaceEdit= (EditText) findViewById(R.id.et_evection_space);
        countEdit= (EditText) findViewById(R.id.et_evection_count);
        trafficEdit= (EditText) findViewById(R.id.et_evection_traffic);
        whatEdit= (EditText) findViewById(R.id.et_evection_what);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.btn_post:
                postApply();
                break;
        }
    }

    private static final String TAG = "EvectionActivity";
    /**
     * 网络请求，出差申请
     */
    private void postApply() {
        String urlApply = RequestPath.getApplyEvection(
                currentUid, countEdit.getText().toString(), spaceEdit.getText().toString(),
                trafficEdit.getText().toString(), null, whatEdit.getText().toString(),null, null);
        Log.i(TAG, "EvectionActivity.onClick: postApply=" + urlApply);
        mRequest = new JsonObjectRequest(urlApply, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "EvectionActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    String msg = response.getString("msg");
                    showToastShort(msg);
                    if (code == 0) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mWaitDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "EvectionActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }

}
