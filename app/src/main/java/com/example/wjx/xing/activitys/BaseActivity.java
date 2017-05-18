package com.example.wjx.xing.activitys;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wjx.xing.R;

/**
 * Created by Administrator on 2017/5/18.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected JsonObjectRequest mRequest;
    public static RequestQueue mRequestQueue;
    protected AlertDialog mConfirmDialog;
    protected ProgressDialog mWaitDialog;

    private TextView mLeft;
    private TextView mRight;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(getResourceId());
            mRequestQueue = Volley.newRequestQueue(this);
            mLeft = (TextView) findViewById(R.id.message_left);
            mRight = (TextView) findViewById(R.id.message_right);
            mTitle = (TextView) findViewById(R.id.message_center);

            initTitle(mLeft, mTitle, mRight);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("该信息已选择，确定要重新修改吗？").setPositiveButton("继续修改", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    confirmDialogContinue(clickView);
                    dialog.dismiss();
                }
            }).setNegativeButton("不修改了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            mConfirmDialog = builder.create();
            mWaitDialog = new ProgressDialog(this);
            mWaitDialog.setMessage("加载中，请稍候");
            mWaitDialog.setCancelable(false);
            mWaitDialog.setCanceledOnTouchOutside(false);
            mWaitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            initData();
            initView();
            initSet();
        } catch (Exception e) {
            Log.i(TAG, "BaseActivity.onCreate: e=" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        mRequestQueue.stop();
        super.onDestroy();
    }

    protected void initTitle(TextView left, TextView title, TextView right) {
        if (left != null) {
            left.setOnClickListener(this);
        }
        if (right != null) {
            right.setOnClickListener(this);
        }
        if (title != null) {
            title.setText(getTitleText());
        }
    }

    protected View clickView;

    protected void confirmDialogContinue(View clickView) {
    }

    protected void confirmDialogShow(View clickView) {
        this.clickView = clickView;
        mConfirmDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_left:
                onClickLeft();
            case R.id.message_right:
                onClickRight();
        }
    }

    public void showToastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    protected void onClickLeft() {
        Log.i(TAG, "BaseActivity.onClickLeft: ");
        onBackPressed();
    }

    private static final String TAG = "BaseActivity";
    protected abstract int getResourceId();

    protected abstract CharSequence getTitleText();

    protected abstract void onClickRight();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initSet();
}
