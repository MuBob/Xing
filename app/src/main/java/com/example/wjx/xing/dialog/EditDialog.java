package com.example.wjx.xing.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 编辑对话框
 * Created by Administrator on 2017/5/18.
 */

public class EditDialog extends BaseDialog {

    private OnEditOkClickListener clickOkListener;
    private DialogInterface.OnClickListener negativeListener;
    private TextView text;
    public EditDialog(Context context, OnEditOkClickListener clickOkListener){
        super(context);
        this.clickOkListener=clickOkListener;
        this.negativeListener=negativeListener;
    }
    @Override
    public Dialog initDialog() {
        text = new EditText(mContext);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("请输入：").setView(text)
                .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(clickOkListener!=null){
                            clickOkListener.onClick(dialog, which, text.getText().toString().trim());
                        }
                        text.setText("");
                        Toast.makeText(mContext.getApplicationContext(), "已修改", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text.setText("");
                        Toast.makeText(mContext.getApplicationContext(), "已取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    public void show(String t) {
        super.show();
        text.setText(t);
    }

    public interface OnEditOkClickListener{
        void onClick(DialogInterface dialog, int which, String afterText);
    }
}
