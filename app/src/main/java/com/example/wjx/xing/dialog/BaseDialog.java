package com.example.wjx.xing.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by Administrator on 2017/5/18.
 */

public abstract class BaseDialog {
    protected Context mContext;
    protected Dialog mDialog;

    public BaseDialog(Context context){
        mContext=context;
    }
    public abstract Dialog initDialog();
    public void show(){
        if(mDialog==null){
            mDialog=initDialog();
        }
        mDialog.show();
    }

    public void dismiss(){
        if(mDialog==null){
            mDialog=initDialog();
        }
        mDialog.dismiss();
    }
}
