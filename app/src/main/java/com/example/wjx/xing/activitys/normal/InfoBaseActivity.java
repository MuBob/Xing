package com.example.wjx.xing.activitys.normal;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.dialog.EditDialog;
import com.example.wjx.xing.entitys.Items_message;
import com.example.wjx.xing.net.RequestPath;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InfoBaseActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private List<Items_message> mList;
    private EditDialog editDialog;
    private myAdapter mAdapter;
    private int clickPosition;

    @Override
    protected int getResourceId() {
        return R.layout.activity_infoself;
    }
    @Override
    protected void initView() {
        mListView = (ListView) findViewById(R.id.lv_message);
    }
    @Override
    protected void initData() {
        //准备数据
        if(mList == null){
            mList = new ArrayList<>();
        }
        mList.clear();
        mList.add(0, new Items_message("姓名",""));
        mList.add(1, new Items_message("性别",""));
        mList.add(2, new Items_message("身份证号",""));
        mList.add(3, new Items_message("出生日期",""));
        mAdapter = new myAdapter(this, mList);
        editDialog=new EditDialog(this, new EditDialog.OnEditOkClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, String afterText) {
                mList.get(clickPosition).setValue(afterText);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected CharSequence getTitleText() {
        return "基本信息";
    }

    @Override
    protected void initSet() {
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        initNetRequest();
    }

    private static final String TAG = "InfoBaseActivity";

    private void initNetRequest() {
        String url= RequestPath.getSelfInfoBase(currentUid);
        mRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "InfoBaseActivity.onResponse: res="+response);
                try {
                    int code = response.getInt("code");
                    if(code==0){
                        JSONObject data=new JSONObject(response.optString("data"));
                        mList.get(0).setValue(data.optString("name"));
                        mList.get(1).setValue(data.optString("sex"));
                        mList.get(2).setValue(data.optString("idCard"));
                        mList.get(3).setValue(data.optString("birthday"));
                        mAdapter.notifyDataSetChanged();
                    }else {
                        String msg = response.optString("msg");
                        showToastShort(msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showToastShort(R.string.error_net);
                Log.i(TAG, "InfoBaseActivity.onErrorResponse: e="+error.getMessage());
            }
        });
        mRequestQueue.add(mRequest);
    }


    @Override
    protected void onClickRight() {
        //保存
        String url = RequestPath.getModifySelfInfoBase(currentUid, mList.get(0).getValue(), mList.get(1).getValue(), mList.get(2).getValue(), mList.get(3).getValue());
        Log.i(TAG, "InfoBaseActivity.onClickRight: url="+url);
        JsonObjectRequest request = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "InfoBaseActivity.onResponse: res="+response);
                try {
                    String msg = response.getString("msg");
                    showToastShort(msg);
                    int code=response.getInt("code");
                    if(code==0){
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "InfoBaseActivity.onErrorResponse: err="+error.getMessage());
                showToastShort(R.string.error_net);
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    protected void onClickLeft() {
        onBackPressed();
    }

    /**
     * listview的条目点击
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        clickPosition=position;
        editDialog.show(mList.get(clickPosition).getValue());
    }

    private class myAdapter extends BaseAdapter{

        private Context mContext;
        private List<Items_message> mList;

        public myAdapter(Context context, List<Items_message> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public int getCount() {
            if(mList == null){
                return 0;
            }else{
                return mList.size();
            }
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewholder;
            if(convertView == null){
                convertView = LayoutInflater.from(mContext).inflate(R.layout.items_message,null);
                mViewholder = new ViewHolder();
                mViewholder.mTextView_key = (TextView) convertView.findViewById(R.id.tv_message_key);
                mViewholder.mTextView_value = (TextView)convertView.findViewById(R.id.tv_message_value);
                convertView.setTag(mViewholder);
            }
            mViewholder = (ViewHolder) convertView.getTag();
            if(mList!=null){
                mViewholder.mTextView_key.setText(mList.get(position).getKey());
                mViewholder.mTextView_value.setText(mList.get(position).getValue());
            }
            return convertView;
        }
        class ViewHolder{
            TextView mTextView_key;
            TextView mTextView_value;
        }
    }
}
