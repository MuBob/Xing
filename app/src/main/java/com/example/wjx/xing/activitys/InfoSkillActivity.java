package com.example.wjx.xing.activitys;

import android.content.DialogInterface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.dialog.SpinnerDialog;
import com.example.wjx.xing.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class InfoSkillActivity extends BaseActivity {
    private RelativeLayout accountingLayout, computerLayout, civilLayout, safetyLayout;
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
        switch (clickView.getId()){
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
        switch (v.getId()){
            case R.id.rl_accounting:
                if(StringUtil.isNull(accountingText.getText().toString())){
                    accountingDialog.show();
                }else {
                    confirmDialogShow(v);
                }
                break;
            case R.id.rl_computer:
                if(StringUtil.isNull(computerText.getText().toString())){
                    computerDialog.show();
                }else {
                    confirmDialogShow(v);
                }
                break;
            case R.id.rl_civil_engineer:
                if(StringUtil.isNull(civilText.getText().toString())){
                    civilDialog.show();
                }else {
                    confirmDialogShow(v);
                }
                break;
            case R.id.rl_safety_engineer:
                if(StringUtil.isNull(safetyText.getText().toString())){
                    safetyDialog.show();
                }else {
                    confirmDialogShow(v);
                }
                break;
        }
    }
    @Override
    protected void initSet() {
        accountingLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        accountingList=new ArrayList<>();
        accountingList.add("初级");
        accountingList.add("中级");
        accountingList.add("高级");
        accountingList.add("注册");
        computerList=new ArrayList<>();
        computerList.add("初级");
        computerList.add("中级");
        computerList.add("高级");
        civilList=new ArrayList<>();
        civilList.add("是");
        civilList.add("否");
        safetyList=new ArrayList<>();
        safetyList.add("是");
        safetyList.add("否");
    }

    @Override
    protected void initView() {
        accountingLayout = (RelativeLayout) findViewById(R.id.rl_accounting);
        computerLayout = (RelativeLayout) findViewById(R.id.rl_computer);
        civilLayout = (RelativeLayout) findViewById(R.id.rl_civil_engineer);
        safetyLayout = (RelativeLayout) findViewById(R.id.rl_safety_engineer);
        accountingText = (TextView) findViewById(R.id.tv_accounting);
        computerText = (TextView) findViewById(R.id.tv_computer);
        civilText = (TextView) findViewById(R.id.tv_civil_engineer);
        safetyText = (TextView) findViewById(R.id.tv_safety_engineer);
        accountingDialog=new SpinnerDialog(this, accountingList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                accountingText.setText(accountingList.get(selectId));
            }
        });

        computerDialog=new SpinnerDialog(this, computerList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                computerText.setText(computerList.get(selectId));
            }
        });

        civilDialog=new SpinnerDialog(this, civilList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                civilText.setText(civilList.get(selectId));
            }
        });

        safetyDialog=new SpinnerDialog(this, safetyList, new SpinnerDialog.OnClickOkListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, int selectId) {
                safetyText.setText(safetyList.get(selectId));
            }
        });

    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onClickRight() {

    }

}
