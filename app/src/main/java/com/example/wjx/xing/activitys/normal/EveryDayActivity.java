package com.example.wjx.xing.activitys.normal;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.utils.StartActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

public class EveryDayActivity extends BaseActivity implements OnDateSelectedListener {
    private MaterialCalendarView mCalendar;
    private Button mSign;
    private Button mLeave;
    private Button mEvection;

    @Override
    protected CharSequence getTitleText() {
        return "日常考勤";
    }

    @Override
    protected void initTitle(TextView left, TextView title, TextView right) {
        super.initTitle(left, title, right);
        right.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Log.i(TAG, "EveryDayActivity.onClick: v="+v.getId());
        switch (v.getId()){
            //签到
            case R.id.btn_everyday_sign:
                Intent intent_sign=new Intent(this, SignInOutActivity.class);
                StartActivity.jumpTo(this, intent_sign);
                break;
            //请假
            case R.id.btn_everyday_leave:
                //点击之后  弹出一个小界面   填写理由
                Intent intent_leave = new Intent(this, LeaveActivity.class);
                StartActivity.jumpTo(this, intent_leave);
                break;
            //出差
            case R.id.btn_everyday_evection:
                Intent intent_evection = new Intent(this, EvectionActivity.class);
                StartActivity.jumpTo(this, intent_evection);
                break;
        }
    }

    @Override
    protected void initSet() {
        //默认选中“今天”
        mCalendar.setDateSelected(CalendarDay.today(), true);
        mCalendar.setOnDateChangedListener(this);
        mCalendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2016, 7, 16))
                .setMaximumDate(CalendarDay.from(2017, 7, 16))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        //日历控件
        mCalendar = (MaterialCalendarView) findViewById(R.id.calendar_everyday);
    }

    @Override
    protected int getResourceId() {
        return R.layout.fragment_everyday;
    }

    @Override
    protected void onClickRight() {

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        Log.i(TAG, "EveryDayActivity.onDateSelected: date="+date+", selected="+selected);
    }

    private static final String TAG = "EveryDayActivity";
}
