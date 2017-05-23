package com.example.wjx.xing.activitys.normal;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.dialog.EditDialog;
import com.example.wjx.xing.dialog.SpinnerDialog;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 学历信息界面
 */
public class InfoEducationActivity extends BaseActivity {

    private RelativeLayout levelLayout, schoolLayout;
    private TextView levelText, schoolText;
    private EditDialog editDialog;
    private SpinnerDialog spinnerDialog;

    @Override
    protected void confirmDialogContinue(View clickView) {
        spinnerDialog.show();
    }

    @Override
    protected CharSequence getTitleText() {
        return "学历信息";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rl_current_level:
                if(StringUtil.isNull(levelText.getText().toString().trim())){
                    spinnerDialog.show();
                }else {
                    confirmDialogShow(v);
                }
                break;
            case R.id.rl_school:
                editDialog.show();
                break;
        }
    }

    @Override
    protected void initSet() {
        levelLayout.setOnClickListener(this);
        schoolLayout.setOnClickListener(this);
        initNetRequest();
    }

    private static final String TAG = "InfoEducationActivity";
    private void initNetRequest() {
        String url= RequestPath.getSelfInfoRecord(currentUid);
        Log.i(TAG, "InfoEducationActivity.initNetRequest: url="+url);
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject response) {
                Log.i(TAG, "InfoBaseActivity.onResponse: res="+response);
                try {
                    int code = response.getInt("code");
                    if(code==0){
                        JSONObject data=new JSONObject(response.optString("data"));
                        levelText.setText(data.optString("highRecord"));
                        schoolText.setText(data.optString("graduateSchool"));
                    }else {
                        levelText.setText("");
                        schoolText.setText("");
                        String msg = response.optString("msg");
                        showToastShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToastShort(R.string.error_net);
                Log.i(TAG, "InfoBaseActivity.onErrorResponse: e="+error.getMessage());
            }
        });
        mRequestQueue.add(mRequest);
    }
    List<String> levelList;
    @Override
    protected void initData() {
        levelList=new ArrayList<>();
        levelList.add("博士学位");
        levelList.add("硕士学位");
        levelList.add("学士/双学位");
        levelList.add("学士学位");
        spinnerDialog=new SpinnerDialog(this, levelList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                levelText.setText(levelList.get(selectId));
            }
        });
        editDialog=new EditDialog(this, new EditDialog.OnEditOkClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, String afterText) {
                schoolText.setText(afterText);
            }
        });
    }

    @Override
    protected void initView() {
        levelLayout = (RelativeLayout) findViewById(R.id.rl_current_level);
        schoolLayout = (RelativeLayout) findViewById(R.id.rl_school);
        levelText = (TextView) findViewById(R.id.tv_current_level);
        schoolText = (TextView) findViewById(R.id.tv_school);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_message;
    }

    @Override
    protected void onClickRight() {
        //保存
        String url = RequestPath.getModifySelfInfoRecord(currentUid, levelText.getText().toString(), schoolText.getText().toString());
        Log.i(TAG, "InfoEducationActivity.onClickRight: url="+url);
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "InfoEducationActivity.onResponse: res="+response);
                try {
                    String msg = response.getString("msg");
                    showToastShort(msg);
                    int code=response.getInt("code");
                    if(code==0){
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "InfoEducationActivity.onErrorResponse: err="+error.getMessage());
                showToastShort(R.string.error_net);
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    protected void onClickLeft() {
        onBackPressed();
    }
}
