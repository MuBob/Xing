package com.example.wjx.xing.activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wjx.xing.R;
import com.example.wjx.xing.Common;
import com.example.wjx.xing.entitys.Items_message;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoselfActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View mLeft;
    private View mRight;
    private ListView mListView;
    private List<Items_message> mList;
    private EditText mText;
    private myAdapter mAdapter;
    private JsonObjectRequest request;
    private RequestQueue mRequestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoself);
        mRequestQueue = Volley.newRequestQueue(this);
        initView();
    }

    private void initView() {
        mLeft = findViewById(R.id.message_left);
        mRight = findViewById(R.id.message_right);
        mLeft.setOnClickListener(this);
        mRight.setOnClickListener(this);
        mListView = (ListView) findViewById(R.id.lv_message);
        initData();
        mAdapter = new myAdapter(this, mList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    private void initData() {
        //准备数据
        if(mList == null){
            mList = new ArrayList<>();
        }
        mList.clear();
        mList.add(new Items_message("姓名","王婧鑫"));
        mList.add(new Items_message("年龄","21"));
        mList.add(new Items_message("学历","本科"));
        mList.add(new Items_message("住址","8号公寓"));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.message_left:
                finish();
                break;
            case R.id.message_right:
                //保存
                String url = Common.baseurl+"infoself?id=1";
                request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("ceshi", "onResponse: 访问成功");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ceshi", "onErrorResponse: 访问失败");
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        //在这里设置需要post的参数
                        Map<String,String> map = new HashMap<String,String>();
                        map.put("姓名","张三");
                        return map;
                    }
                };
                mRequestQueue.add(request);
                break;
        }
    }

    /**
     * listview的条目点击
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        //点击修改
        mText = new EditText(this);
        new AlertDialog.Builder(this).setTitle("请输入：").setView(mText).setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value_updata = mText.getText().toString().trim();
                mList.get(position).setValue(value_updata);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(InfoselfActivity.this, "已修改", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(InfoselfActivity.this, "已取消", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    class myAdapter extends BaseAdapter{

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
