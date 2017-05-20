package com.example.wjx.xing.Adapters;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> list;

    public MyBaseAdapter(Context context, List<T> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
