package com.example.wjx.xing.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wjx.xing.Common;

/**
 * 编辑对话框
 * Created by Administrator on 2017/5/18.
 */

public class InputServiceDialog extends BaseDialog {

    private TextView text;
    public InputServiceDialog(Context context){
        super(context);
    }
    @Override
    public Dialog initDialog() {
        text = new EditText(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("请输入服务器地址：").setView(text)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Common.SERVICE_IP=text.getText().toString().trim();
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        return alertDialog;
    }

    @Override
    public void show() {
        super.show();
        text.setText(Common.SERVICE_IP);
    }
}
