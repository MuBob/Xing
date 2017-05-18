package com.example.wjx.xing.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wjx.xing.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class SpinnerDialog extends BaseDialog {
    private OnClickOkListener clickOkListener;
    public SpinnerDialog(Context context, List<String> titles, OnClickOkListener clickOkListener) {
        super(context);
        this.titles=titles;
        this.clickOkListener=clickOkListener;
    }

    private List<String> titles;
    private View contentView;
    private RadioGroup radioGroup;
    private int selectId;
    @Override
    public Dialog initDialog() {
        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView=inflater.inflate(R.layout.view_content_spinner_radio, null);
        radioGroup=(RadioGroup) contentView.findViewById(R.id.radio_group);
        for (int i = 0; i < titles.size(); i++) {
            RadioButton radioButton=new RadioButton(mContext);
            radioButton.setText(titles.get(i));
            radioButton.setTag(i);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        selectId= (int) buttonView.getTag();
                    }
                }
            });
            radioGroup.addView(radioButton, i);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext).setTitle("请选择：").setView(contentView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(clickOkListener!=null){
                            clickOkListener.onClick(dialog, which, selectId);
                        }
                        Toast.makeText(mContext.getApplicationContext(), "已选择", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext.getApplicationContext(), "已取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
    public interface OnClickOkListener{
        void onClick(DialogInterface dialog, int which, int selectId);
    }
}
