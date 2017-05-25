package com.example.wjx.xing.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.db.TableDepartment;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class DepartmentListAdapter extends BaseAdapter{
    private Context context;
    private List<TableDepartment> list;
    public DepartmentListAdapter(Context context, List<TableDepartment> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public TableDepartment getItem(int position) {
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
            convertView = inflater.inflate(R.layout.item_department_list, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.nameText.setText(list.get(position).getName());
        return convertView;
    }

    public class ViewHolder{
        private TextView nameText;
        public ViewHolder(View view){
            nameText=(TextView) view.findViewById(R.id.item_department_name);
        }
    }

}
