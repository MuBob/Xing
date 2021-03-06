package com.example.wjx.xing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.bean.SkillBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class SkillSpinnerAdapter extends MyBaseAdapter<SkillBean>{
    public SkillSpinnerAdapter(Context context, List<SkillBean> list) {
        super(context, list);
    }

    private ViewHolder holder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_role_list, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.nameText.setText(list.get(position).getName());
        return convertView;
    }


    private class ViewHolder{
        private TextView nameText;
        public ViewHolder(View convertView){
            nameText = (TextView) convertView.findViewById(R.id.item_role_name);
        }
    }
}
