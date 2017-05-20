package com.example.wjx.xing.activitys.manager;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wjx.xing.Adapters.PersonalListAdapter;
import com.example.wjx.xing.Adapters.StringSpinnerAdapter;
import com.example.wjx.xing.Common;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.bean.PersonalBean;

import java.util.ArrayList;
import java.util.List;

public class ReplyActivity extends BaseActivity {
    private Spinner replyContentSpinner, applyPersonalSpinner;
    private TextView applyContentText;
    private int applyType;
    private List<String> replyContentList;
    private List<PersonalBean> personalBeanList;
    private StringSpinnerAdapter replyContentAdapter;
    private PersonalListAdapter personalListAdapter;
    private int selectReplyContentPosition=-1;
    private int selectPersonalPosition=-1;

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
        replyContentList.add(0, "同意你的请假申请，照顾好自己哦");
        replyContentList.add(1, "同意你的出差申请，注意安全哦");
        personalBeanList=new ArrayList<>();
        replyContentAdapter=new StringSpinnerAdapter(this, replyContentList);
        personalListAdapter=new PersonalListAdapter(this, personalBeanList);
    }

    @Override
    protected CharSequence getTitleText() {
        return "出差/请假回复";
    }

    @Override
    protected void onClickRight() {
        // TODO: 2017/5/19 网络请求，点击回复某位员工的某种请求

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
        replyContentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectReplyContentPosition=position;
            }
        });
        applyPersonalSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPersonalPosition=position;
            }
        });
    }

    private void requestPersonalList(int applyType) {
        switch (applyType){
            case Common.REPLY_TYPE_EVECTION:
                // TODO: 2017/5/19  网络请求，出差请求人员列表

                break;
            case Common.REPLY_TYPE_LEAVE:
                // TODO: 2017/5/19 网络请求，请假请求人员列表

                break;
            default:
                break;
        }
    }

    private String getContentStringByApplyType(int applyType) {
        String result="";
        switch (applyType){
            case  Common.REPLY_TYPE_EVECTION:
                result="出差";
                break;
            case  Common.REPLY_TYPE_LEAVE:
                result="请假";
                break;
            default:
                break;
        }
        return result;
    }
}
