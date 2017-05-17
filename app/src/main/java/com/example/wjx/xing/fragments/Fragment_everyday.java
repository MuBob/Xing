package com.example.wjx.xing.fragments;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.Common;
import com.example.wjx.xing.activitys.EvectionActivity;
import com.example.wjx.xing.activitys.LeaveActivity;
import com.example.wjx.xing.activitys.MainActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * 日常
 */
public class Fragment_everyday extends Fragment implements OnDateSelectedListener, View.OnClickListener {

    private View mView;
    private MaterialCalendarView mCalendar;
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private Location mLocation;
    private Button mSign;
    private Button mLeave;
    private Button mEvection;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_everyday, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        //日历控件
        mCalendar = (MaterialCalendarView) mView.findViewById(R.id.calendar_everyday);
        mCalendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2016, 7, 16))
                .setMaximumDate(CalendarDay.from(2017, 7, 16))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        //默认选中“今天”
        mCalendar.setDateSelected(CalendarDay.today(), true);
        mCalendar.setOnDateChangedListener(this);
        //按钮
        mSign = (Button) mView.findViewById(R.id.btn_everyday_sign);
        mLeave = (Button) mView.findViewById(R.id.btn_everyday_leave);
        mEvection = (Button) mView.findViewById(R.id.btn_everyday_evection);
        //添加点击监听事件
        mSign.setOnClickListener(this);
        mLeave.setOnClickListener(this);
        mEvection.setOnClickListener(this);
    }

    /**
     * 点击监听函数
     *
     * @param mcv      日历控件
     * @param date     选中的日期
     * @param selected 是否选中（状态）
     */
    @Override
    public void onDateSelected(@NonNull MaterialCalendarView mcv, @NonNull CalendarDay date, boolean selected) {
        Log.i(TAG, "onDateSelected: " + getSelectedDatesString());

    }

    private String getSelectedDatesString() {
        CalendarDay date = mCalendar.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    public void getCurrrentLocation() {
        //获得当前位置的坐标
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (mLocation == null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30 * 1000, 5, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.i(TAG, "onLocationChanged: " + location);
                    mLocation = location;
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            });
        }
        //显示
        Log.i(TAG, "getCurrrentLocation: " + mLocation);
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        double latitude = 0;
        double longitude = 0 ;
        if(mLocation!=null){
            latitude = mLocation.getLatitude();
            longitude = mLocation.getLongitude();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentTime = sdf.format(new Date());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            StringBuilder builder = new StringBuilder();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    builder.append(address.getAddressLine(i)).append("_");
                }
                builder.append("(").append(currentTime).append(")");
                Log.i(TAG, "getCurrrentLocation: "+builder);
                Toast.makeText(getActivity(), "签到成功\n" + builder, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "getCurrrentLocation: " + builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理按钮点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        JsonObjectRequest request = null;
        String url = Common.baseurl + "EverydayServlet?";
        switch (v.getId()) {
            //签到
            case R.id.btn_everyday_sign:
                getCurrrentLocation();
                //同时保存在服务器上
                url += "id=0";
                Log.i(TAG, "onClick: "+url);
                request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = response.getString("result");
                            if("签到成功".equals(result)){
                                int count_sign = response.getInt("sign");
                                int count_evection = response.getInt("evection");
                                int count_leave = response.getInt("leave");
                                Log.i(TAG, "onResponse: " + result + "--" + count_sign + "--" + count_leave + "--" + count_evection);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, "onErrorResponse: " + error.getMessage());
                    }
                });
                break;
            //请假
            case R.id.btn_everyday_leave:
                //点击之后  弹出一个小界面   填写理由
                Intent intent_leave = new Intent(getActivity(), LeaveActivity.class);
                getActivity().startActivity(intent_leave);
                break;
            //出差
            case R.id.btn_everyday_evection:
                Intent intent_evection = new Intent(getActivity(), EvectionActivity.class);
                getActivity().startActivity(intent_evection);
                break;
        }
        if (request != null) {
            MainActivity.mRequestQueue.add(request);
        }
    }
}
