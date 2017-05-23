package com.example.wjx.xing.activitys.normal;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.dialog.SpinnerDialog;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.GsonUtil;
import com.example.wjx.xing.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InfoSkillActivity extends BaseActivity {
    private TextView accountingText, computerText, civilText, safetyText;
    private List<String> accountingList, computerList, civilList, safetyList;
    private SpinnerDialog accountingDialog, computerDialog, civilDialog, safetyDialog;

    @Override
    protected CharSequence getTitleText() {
        return "职称技能";
    }

    @Override
    protected void confirmDialogContinue(View clickView) {
        super.confirmDialogContinue(clickView);
        switch (clickView.getId()) {
            case R.id.rl_accounting:
                accountingDialog.show();
                break;
            case R.id.rl_computer:
                computerDialog.show();
                break;
            case R.id.rl_civil_engineer:
                civilDialog.show();
                break;
            case R.id.rl_safety_engineer:
                safetyDialog.show();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rl_accounting:
                if (StringUtil.isNull(accountingText.getText().toString())) {
                    accountingDialog.show();
                } else {
                    confirmDialogShow(v);
                }
                break;
            case R.id.rl_computer:
                Log.i(TAG, "InfoSkillActivity.onClick: computer");
                if (StringUtil.isNull(computerText.getText().toString())) {
                    computerDialog.show();
                } else {
                    confirmDialogShow(v);
                }
                break;
            case R.id.rl_civil_engineer:
                if (StringUtil.isNull(civilText.getText().toString())) {
                    civilDialog.show();
                } else {
                    confirmDialogShow(v);
                }
                break;
            case R.id.rl_safety_engineer:
                if (StringUtil.isNull(safetyText.getText().toString())) {
                    safetyDialog.show();
                } else {
                    confirmDialogShow(v);
                }
                break;
        }
    }

    @Override
    protected void initSet() {
        initNetRequest();
    }

    private static final String TAG = "InfoSkillActivity";

    private void initNetRequest() {
        String url = RequestPath.getSelfInfoSkill(currentUid);
        Log.i(TAG, "InfoSkillActivity.initNetRequest: url=" + url);
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override

            public void onResponse(JSONObject response) {
                Log.i(TAG, "InfoBaseActivity.onResponse: res=" + response);
                try {
                    int code = response.getInt("code");
                    if (code == 0) {
                        JSONObject data = new JSONObject(response.optString("data"));
                        String skillLevel = data.optString("skillLevel");
                        Map<String, String> skillMap= (Map<String, String>) GsonUtil.fromJsonToObject(skillLevel, Map.class);
                        Set<String> strings = skillMap.keySet();
                        Iterator<String> iterator = strings.iterator();
                        while (iterator.hasNext()){
                            String nextId = iterator.next();
                            String nextLevel=skillMap.get(nextId);
                            setLevelById(nextId, nextLevel);
                        }
                    } else {
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
                Log.i(TAG, "InfoBaseActivity.onErrorResponse: e=" + error.getMessage());
            }
        });
        mRequestQueue.add(mRequest);
    }

    @Override
    protected void initData() {
        accountingList = new ArrayList<>();
        accountingList.add("注册");
        accountingList.add("初级");
        accountingList.add("中级");
        accountingList.add("高级");
        computerList = new ArrayList<>();
        computerList.add("初级");
        computerList.add("中级");
        computerList.add("高级");
        civilList = new ArrayList<>();
        civilList.add("否");
        civilList.add("是");
        safetyList = new ArrayList<>();
        safetyList.add("否");
        safetyList.add("是");
    }

    private int selectAccountingPosition, selectComputerPosition, selectCivilPosition, selectSatyPosition;
    @Override
    protected void initView() {
        accountingText = (TextView) findViewById(R.id.tv_accounting);
        computerText = (TextView) findViewById(R.id.tv_computer);
        civilText = (TextView) findViewById(R.id.tv_civil_engineer);
        safetyText = (TextView) findViewById(R.id.tv_safety_engineer);
        accountingDialog = new SpinnerDialog(this, accountingList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                accountingText.setText(accountingList.get(selectId));
                selectAccountingPosition=selectId;
            }
        });

        computerDialog = new SpinnerDialog(this, computerList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                computerText.setText(computerList.get(selectId));
                selectComputerPosition=selectId;
            }
        });

        civilDialog = new SpinnerDialog(this, civilList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                civilText.setText(civilList.get(selectId));
                selectCivilPosition=selectId;
            }
        });

        safetyDialog = new SpinnerDialog(this, safetyList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                safetyText.setText(safetyList.get(selectId));
                selectSatyPosition=selectId;
            }
        });

    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onClickRight() {
        //保存
        String url = RequestPath.getModifySelfInfoSkill(currentUid,
                selectAccountingPosition, selectComputerPosition, selectCivilPosition, selectSatyPosition);
        Log.i(TAG, "InfoSkillActivity.onClickRight: url=" + url);
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "InfoSkillActivity.onResponse: res=" + response);
                try {
                    String msg = response.getString("msg");
                    showToastShort(msg);
                    int code = response.getInt("code");
                    if (code == 0) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    showToastShort(R.string.error_net);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "InfoEducationActivity.onErrorResponse: err=" + error.getMessage());
                showToastShort(R.string.error_net);
            }
        });
        mRequestQueue.add(request);
    }
    private void setLevelById(String id, String level) {
        if ("1".equals(id)) {
            accountingText.setText(accountingList.get(Integer.parseInt(level)));
        } else if ("2".equals(id)) {
            computerText.setText(computerList.get(Integer.parseInt(level)));
        } else if ("3".equals(id)) {
            civilText.setText(civilList.get(Integer.parseInt(level)));
        } else if ("4".equals(id)) {
            safetyText.setText(safetyList.get(Integer.parseInt(level)));
        }
    }
}
