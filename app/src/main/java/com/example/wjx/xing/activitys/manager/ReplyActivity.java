package com.example.wjx.xing.activitys.manager;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.Adapters.StringSpinnerAdapter;
import com.example.wjx.xing.Adapters.UnReplyPersonalListAdapter;
import com.example.wjx.xing.Common;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.UnReplyPersonalBean;
import com.example.wjx.xing.bean.UnReplyPersonalListDataResponse;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.DateUtil;
import com.example.wjx.xing.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReplyActivity extends BaseActivity {
    private Spinner replyContentSpinner, applyPersonalSpinner;
    private TextView applyContentText;
    private int applyType;
    private List<String> replyContentList;
    private List<UnReplyPersonalBean> personalBeanList;
    private StringSpinnerAdapter replyContentAdapter;
    private UnReplyPersonalListAdapter personalListAdapter;
    private int selectReplyContentPosition;
    private int selectPersonalPosition;

    @Override
    protected int getResourceId() {
        return R.layout.activity_reply;
    }

    @Override
    protected void initData() {
        applyType = getIntent().getIntExtra(Common.KEY_REPLY_TYPE, 0);
        if(applyType<=0){
            showToastShort("界面进入出错了");
            finish();
            return;
        }
        replyContentList=new ArrayList<>();
        switch (applyType){
            case Common.APPLY_TYPE_EVECTION:
                replyContentList.add(0, "同意你的出差申请，注意安全哦");
                replyContentList.add(1, "拒绝你的出差申请");
                break;
            case Common.APPLY_TYPE_LEAVE:
                replyContentList.add(0, "同意你的请假申请，照顾好自己哦");
                replyContentList.add(1, "拒绝你的请假申请");

                break;
default:break;
        }
        personalBeanList=new ArrayList<>();
        replyContentAdapter=new StringSpinnerAdapter(this, replyContentList);
        personalListAdapter=new UnReplyPersonalListAdapter(this, personalBeanList);
    }

    @Override
    protected CharSequence getTitleText() {
        return "出差/请假回复";
    }

    @Override
    protected void onClickRight() {
        // 网络请求，点击回复某位员工的某种请求
        requestReply(applyType, selectReplyContentPosition+1, personalBeanList.get(selectPersonalPosition).getName(), replyContentList.get(selectReplyContentPosition), DateUtil.getCurrentTime());
    }

    @Override
    protected void initView() {
        applyContentText = (TextView) findViewById(R.id.tv_apply_content);
        replyContentSpinner = (Spinner) findViewById(R.id.spinner_select_conten_reply);
        applyPersonalSpinner = (Spinner) findViewById(R.id.spinner_select_personal);
    }

    @Override
    protected void initSet() {
        applyContentText.setText(getContentStringByApplyType(applyType));
        replyContentSpinner.setAdapter(replyContentAdapter);
        applyPersonalSpinner.setAdapter(personalListAdapter);
        requestPersonalList(applyType);
        replyContentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectReplyContentPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        applyPersonalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectPersonalPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void requestPersonalList(int applyType) {
        switch (applyType){
            case Common.APPLY_TYPE_EVECTION:
                requestList(Common.APPLY_TYPE_EVECTION);
                break;
            case Common.APPLY_TYPE_LEAVE:
                requestList(Common.APPLY_TYPE_LEAVE);
                break;
            default:
                break;
        }
    }

    private String getContentStringByApplyType(int applyType) {
        String result="";
        switch (applyType){
            case  Common.APPLY_TYPE_EVECTION:
                result="出差";
                break;
            case  Common.APPLY_TYPE_LEAVE:
                result="请假";
                break;
            default:
                break;
        }
        return result;
    }

    private static final String TAG = "ReplyActivity";
    private void requestList(int applyType) {
        String urlList=null;
        switch (applyType){
            case Common.APPLY_TYPE_EVECTION:
//                网络请求，出差请求人员列表
                urlList = RequestPath.getListPersonalUnreplyTrip();
                break;
            case Common.APPLY_TYPE_LEAVE:
                // 网络请求，请假请求人员列表
                urlList = RequestPath.getListPersonalUnreplyLeave();
                break;
            default:
                break;
        }
        Log.i(TAG, "PersonalManagerActivity.requestList: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "PersonalManagerActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String data=response.getString("data");
                        UnReplyPersonalListDataResponse personalList = (UnReplyPersonalListDataResponse) GsonUtil.fromJsonToObject(data, UnReplyPersonalListDataResponse.class);
                        personalBeanList.clear();
                        if(personalList!=null&&personalList.getList()!=null){
                            personalBeanList.addAll(personalList.getList());
                        }else {
                            showToastShort("没有员工申请");
                        }
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
        Log.i(TAG, "ReplyActivity.requestTripList: url="+urlList);
    }

    private void requestReply(int applyType, int replyType, String id, String reason, String time) {
        String urlList=null;
        switch (applyType){
            case Common.APPLY_TYPE_EVECTION:
//                网络请求，出差请求人员列表
                urlList = RequestPath.getReplyTrip(id, replyType, reason, time);
                break;
            case Common.APPLY_TYPE_LEAVE:
                // 网络请求，请假请求人员列表
                urlList=RequestPath.getReplyLeave(id, replyType, reason, time);
                break;
            default:
                break;
        }
        Log.i(TAG, "PersonalManagerActivity.requestList: url="+urlList);
        mRequest = new JsonObjectRequest(urlList, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "PersonalManagerActivity.onResponse: resp=" + response);
                    mWaitDialog.dismiss();
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
                Log.i(TAG, "PersonalManagerActivity.onErrorResponse: " + error.getMessage());
                mWaitDialog.dismiss();
            }
        });
        mWaitDialog.show();
        mRequestQueue.add(mRequest);
    }
}
