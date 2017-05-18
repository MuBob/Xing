package com.example.wjx.xing.activitys;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.wjx.xing.Adapters.MainFragAdapter;
import com.example.wjx.xing.Common;
import com.example.wjx.xing.R;
import com.example.wjx.xing.fragments.NormalFragment;
import com.example.wjx.xing.fragments.ManagerFragment;
import com.example.wjx.xing.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面   分为三个选项卡
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private static final String TAG = "MainActivity";
    private NoScrollViewPager mviewPager;
    //底部导航栏的单选按钮group
    private RadioGroup mRadioGroup;
    //页卡（fragment）的集合
    private List<Fragment> mFragments;
    //底部导航栏的三个单选按钮
    private RadioButton normalRadio, managerRadio;
    private View mlistview;
    private int userRole;

    @Override
    protected CharSequence getTitleText() {
        return null;
    }

    @Override
    protected void initSet() {
        mviewPager.setAdapter(new MainFragAdapter(getSupportFragmentManager(), mFragments));
        mviewPager.setOnPageChangeListener(this);
        mviewPager.setCurrentItem(0);

        //给Radiogroupp添加监听事件
        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.setVisibility(userRole > 1 ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void initData() {
        userRole = getIntent().getIntExtra(Common.KEY_ROLE_LOGIN, 1);
        //给ViewPager添加fragment
        mFragments = new ArrayList<>();
        mFragments.add(new NormalFragment());
        mFragments.add(new ManagerFragment());
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onClickRight() {

    }

    @Override
    protected void initView() {
        mviewPager = (NoScrollViewPager) findViewById(R.id.vp_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
        normalRadio = (RadioButton) findViewById(R.id.rbt_normal);
        managerRadio = (RadioButton) findViewById(R.id.rbt_manager);
        }

    /**
     * radiogroup的按钮选中状态改变事件
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbt_normal:
                mviewPager.setCurrentItem(0, false);
                break;
            case R.id.rbt_manager:
                mviewPager.setCurrentItem(1, false);
                break;
            default:
                break;
        }
    }

    /**
     * viewpager滑动事件监听
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                normalRadio.setChecked(true);
                break;
            case 1:
                managerRadio.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
