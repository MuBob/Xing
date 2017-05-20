package com.example.wjx.xing.activitys.normal;

import android.content.DialogInterface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.dialog.EditDialog;
import com.example.wjx.xing.dialog.SpinnerDialog;
import com.example.wjx.xing.utils.StringUtil;

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

    }

    @Override
    protected void onClickLeft() {
        onBackPressed();
    }
}
