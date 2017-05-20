package com.example.wjx.xing.activitys.normal;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.utils.RequestPath;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class SignInOutActivity extends BaseActivity implements LocationListener {

    private TextView locationText;
    private Location mLocation;

    @Override
    protected CharSequence getTitleText() {
        return "考勤";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_sign_in:
                // TODO: 2017/5/18 网络请求，签到接口
                getCurrrentLocation();
                String url = RequestPath.EVERY_DAY_SIGN_IN;
                url += "id=0";
                Log.i(TAG, "onClick: " + url);
                mRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mWaitDialog.dismiss();
                            String result = response.getString("result");
                            if ("签到成功".equals(result)) {
                                int count_sign = response.getInt("sign");
                                int count_evection = response.getInt("evection");
                                int count_leave = response.getInt("leave");
                                showToastShort("签到成功");
                                Log.i(TAG, "SignInOutActivity.onResponse: " + result + "--" + count_sign + "--" + count_leave + "--" + count_evection);
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
                mWaitDialog.show();
                mRequestQueue.add(mRequest);
                break;
            case R.id.btn_sign_out:
                // TODO: 2017/5/18  网络请求 签退接口
                break;
        }
    }

    @Override
    protected void initSet() {
        getCurrrentLocation();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        locationText = (TextView) findViewById(R.id.tv_location);
    }

    @Override
    protected int getResourceId() {
        return R.layout.activity_sign_in_out;
    }

    @Override
    protected void onClickRight() {

    }


    public void getCurrrentLocation() {
        //获得当前位置的坐标
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(getApplicationContext(), "请到设置-权限管理中打开定位权限", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (mLocation == null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30 * 1000, 5, this);
        }
        //显示
        Log.i(TAG, "SignInOutActivity.getCurrrentLocation: location=" + mLocation);
        showLocation();

    }

    private void showLocation() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        double latitude = 0;
        double longitude = 0;
        if (mLocation != null) {
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
                Log.i(TAG, "SignInOutActivity.showLocation: addresses=" + addresses);
                builder.append("当前位置：\n");
                builder.append(address.getAddressLine(0)).append("\n");
                builder.append("(").append(currentTime).append(")");
                String s = builder.toString();
                if (locationText != null) {
                    locationText.setText(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, "onLocationChanged: " + location);
        mLocation = location;
        showLocation();
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
}
