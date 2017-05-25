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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.wjx.xing.R;
import com.example.wjx.xing.activitys.BaseActivity;
import com.example.wjx.xing.db.LogSignIn;
import com.example.wjx.xing.db.LogSignOut;
import com.example.wjx.xing.net.RequestPath;
import com.example.wjx.xing.utils.DateUtil;
import com.example.wjx.xing.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SignInOutActivity extends BaseActivity implements LocationListener {

    private TextView locationText;
    private Location mLocation;
    private Button btnSignIn, btnSignOut;

    @Override
    protected CharSequence getTitleText() {
        return "考勤";
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_sign_in:
                if (v.isSelected()) {

                    getCurrrentLocation();
                    String urlSignIn = RequestPath.getEverydaySignIn(currentUid, locationPlace, DateUtil.getCurrentTime());
                    Log.i(TAG, "SignInOutActivity.onClick: urlSignIn=" + urlSignIn);
                    mRequest = new JsonObjectRequest(urlSignIn, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.i(TAG, "SignInOutActivity.onResponse: res=" + response);
                                mWaitDialog.dismiss();
                                int code = response.getInt("code");
                                String msg = response.getString("msg");
                                showToastShort(msg);
                                if (code == 0) {
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(TAG, "onErrorResponse: " + error.getMessage());
                            mWaitDialog.dismiss();
                        }
                    });
                    mWaitDialog.show();
                    mRequestQueue.add(mRequest);
                }else {
                    showToastShort("今天已签到");
                }
                break;
            case R.id.btn_sign_out:
                if(v.isSelected()){
                    String urlSignOut = RequestPath.getEverydaySignOut(currentUid, locationPlace, DateUtil.getCurrentTime());
                    Log.i(TAG, "SignInOutActivity.onClick: urlSignOut=" + urlSignOut);
                    mRequest = new JsonObjectRequest(urlSignOut, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                Log.i(TAG, "SignInOutActivity.onResponse: resp=" + response);
                                mWaitDialog.dismiss();
                                int code = response.getInt("code");
                                String msg = response.getString("msg");
                                showToastShort(msg);
                                if (code == 0) {
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                mWaitDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i(TAG, "onErrorResponse: " + error.getMessage());
                            mWaitDialog.dismiss();
                        }
                    });
                    mWaitDialog.show();
                    mRequestQueue.add(mRequest);
                }else {
                    showToastShort("今天已签退");
                }
                break;
        }
    }

    private static final String TAG = "SignInOutActivity";
    @Override
    protected void initSet() {
        getCurrrentLocation();
        netRequest();
    }

    private void netRequest() {
        String urlSignIn = RequestPath.getEverydaySignIn(currentUid, null, null);
        Log.i(TAG, "SignInOutActivity.netRequest: urlSignIn=" + urlSignIn);
        JsonObjectRequest requestSignIn = new JsonObjectRequest(urlSignIn, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "SignInOutActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String signInStr=response.optString("signInBean");
                        LogSignIn signIn= (LogSignIn) GsonUtil.fromJsonToObject(signInStr, LogSignIn.class);
                            btnSignIn.setSelected(signIn!=null?signIn.getCount()<2:true);
                    } else {
                        showToastShort("服务器出现异常");
                        btnSignIn.setSelected(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.getMessage());
                showToastShort("服务器出现异常");
                btnSignIn.setSelected(false);
            }
        });
        mRequestQueue.add(requestSignIn);
        String urlSignOut = RequestPath.getEverydaySignOut(currentUid, null, null);
        Log.i(TAG, "SignInOutActivity.netRequest: urlSignOut=" + urlSignOut);
        JsonObjectRequest requestSignOut = new JsonObjectRequest(urlSignOut, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.i(TAG, "SignInOutActivity.onResponse: resp=" + response);
                    int code = response.getInt("code");
                    if (code == 0) {
                        String signOutStr=response.optString("signOutBean");
                        LogSignOut signOut= (LogSignOut) GsonUtil.fromJsonToObject(signOutStr, LogSignOut.class);
                        btnSignOut.setSelected(signOut!=null?signOut.getCount()<2:true);
                    } else {
                        showToastShort("服务器出现异常");
                        btnSignOut.setSelected(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: " + error.getMessage());
                showToastShort("服务器出现异常");
                btnSignIn.setSelected(false);
            }
        });
        mRequestQueue.add(requestSignOut);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        locationText = (TextView) findViewById(R.id.tv_location);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
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

    private String locationPlace;

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
                locationPlace = address.getAddressLine(0);
                Log.i(TAG, "SignInOutActivity.showLocation: addresses=" + addresses);
                builder.append("当前位置：\n");
                builder.append(locationPlace).append("\n");
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
