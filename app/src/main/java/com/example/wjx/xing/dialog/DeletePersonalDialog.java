package com.example.wjx.xing.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Administrator on 2017/5/19.
 */

public class DeletePersonalDialog extends BaseDialog {
    private DialogInterface.OnClickListener onClickListener;
    public DeletePersonalDialog(Context context, DialogInterface.OnClickListener onClickListener) {
        super(context);
        this.onClickListener=onClickListener;
    }

    @Override
    public Dialog initDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setMessage("是否删除该名员工信息");
        builder.setPositiveButton("仍然删除", onClickListener);
        builder.setNegativeButton("不删除了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder.create();
    }
}
