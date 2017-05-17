package com.example.wjx.xing.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.wjx.xing.Adapters.MainFragAdapter;
import com.example.wjx.xing.R;
import com.example.wjx.xing.fragments.Fragment_everyday;
import com.example.wjx.xing.fragments.Fragment_mine;
import com.example.wjx.xing.fragments.Fragment_report;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面   分为三个选项卡
 */
public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    private static final String TAG = "MainActivity";
    private ViewPager mviewPager;
    //底部导航栏的单选按钮group
    private RadioGroup mRadioGroup;
    //页卡（fragment）的集合
    private List<Fragment> mFragments;
    //底部导航栏的三个单选按钮
    private RadioButton mRb_everyday,mRb_report,mRb_mine;
    private View mlistview;
    public static RequestQueue mRequestQueue ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQueue = Volley.newRequestQueue(this);
        initview();
    }

    private void initview() {
        mviewPager = (ViewPager) findViewById(R.id.vp_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
        mRb_everyday = (RadioButton) findViewById(R.id.rbt_everyday);
        mRb_report = (RadioButton) findViewById(R.id.rbt_report);
        mRb_mine = (RadioButton) findViewById(R.id.rbt_mine);
        initDate();
        MainFragAdapter fragAdapter = new MainFragAdapter(getSupportFragmentManager(),mFragments);
        mviewPager.setAdapter(fragAdapter);
        mviewPager.setOnPageChangeListener(this);
        mviewPager.setCurrentItem(0);

        //给Radiogroupp添加监听事件
        mRadioGroup.setOnCheckedChangeListener(this);
    }
    /**
     * 准备数据
     */
    private void initDate() {
        //给ViewPager添加fragment
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new Fragment_everyday());
        mFragments.add(new Fragment_report());
        mFragments.add(new Fragment_mine());
    }
    /**
     * radiogroup的按钮选中状态改变事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbt_everyday:
                Toast.makeText(this, "您点击了日常", Toast.LENGTH_SHORT).show();
                mviewPager.setCurrentItem(0,false);
                break;
            case R.id.rbt_report:
                Toast.makeText(this, "您点击了报表", Toast.LENGTH_SHORT).show();
                mviewPager.setCurrentItem(1,false);
                break;
            case R.id.rbt_mine:
                Toast.makeText(this, "您点击了我的", Toast.LENGTH_SHORT).show();
                mviewPager.setCurrentItem(2,false);
                break;
            default:
                break;
        }
    }
    /**
     * viewpager滑动事件监听
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch(position){
            case 0:
                mRb_everyday.setChecked(true);
                break;
            case 1:
                mRb_report.setChecked(true);
                break;
            case 2:
                mRb_mine.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
