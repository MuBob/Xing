package com.example.wjx.xing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.bean.PersonalBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class PersonalListAdapter extends BaseAdapter{
    private Context context;
    private List<PersonalBean> list;
    public PersonalListAdapter(Context context, List<PersonalBean> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public PersonalBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ViewHolder holder;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_personal_list, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        PersonalBean personalBean = list.get(position);
        holder.nameText.setText(personalBean.getName());
        holder.departmentText.setText(personalBean.getDepartment());
        return convertView;
    }

    public class ViewHolder{
        private TextView nameText;
        private TextView departmentText;
        public ViewHolder(View view){
            nameText=(TextView) view.findViewById(R.id.item_personal_name);
            departmentText=(TextView) view.findViewById(R.id.item_personal_department);
        }
    }

}
