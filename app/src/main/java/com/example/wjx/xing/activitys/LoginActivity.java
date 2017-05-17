package com.example.wjx.xing.activitys;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wjx.xing.R;
import com.example.wjx.xing.Common;
import com.example.wjx.xing.utils.StartActivity;
import com.example.wjx.xing.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 输入账号密码时  进度条隐藏
 */
public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mNumberView;
    private EditText mPasswordView;
    private ProgressBar mProgressView;
    private View mLoginFormView;
    private EditText mNumber;
    private EditText mPassword;
    private TextInputLayout mInput_number;
    private TextInputLayout mInput_password;
    private RequestQueue mRequestQueue;
    private String mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //这里的this指的是Context
        mRequestQueue = Volley.newRequestQueue(this);
        InitView();
    }

    /**
     * 初始化界面
     */
    private void InitView() {
        mNumberView = (AutoCompleteTextView) findViewById(R.id.number_login);
        mPasswordView = (EditText) findViewById(R.id.password_login);
        //修改软键盘的Enter
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        Button mEmailRegisterInButton = (Button) findViewById(R.id.email_register_in_button);
        mEmailRegisterInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });
        //整个登录界面
        mLoginFormView = findViewById(R.id.login_form);
        //进度条
        mProgressView = (ProgressBar) findViewById(R.id.login_progress);
        mInput_number = (TextInputLayout) findViewById(R.id.textinput_number_main);
        mInput_password = (TextInputLayout) findViewById(R.id.textinput_password_main);
        mNumber = (EditText)findViewById(R.id.number_login);
        mPassword = (EditText) findViewById(R.id.password_login);
        mNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s.toString())) {
                    if(false) {
                        mInput_number.setError(getString(R.string.error_login_empty));
                    }
                }else{
                    mInput_number.setError(null);
                }
            }
        });
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s.toString())) {
                    if(false) {
                        mInput_password.setError(getString(R.string.error_login_empty));
                    }
                }else{
                    mInput_password.setError(null);
                }
            }
        });
    }

    /**
     * 跳转注册界面
     */
    private void attemptRegister() {
        // TODO: 2017/5/17
    }

    /**
     * 执行登录操作
     */
    private void attemptLogin() {
        String number = mNumber.getText().toString().trim();
        if(!StringUtil.isNumber(number)){
            mInput_number.setError("请输入正确工号（9位）");
            mNumber.setText("");
            return;
        }
        String password = mPassword.getText().toString().trim();
        if(!StringUtil.isPassword(password)){
            mInput_password.setError("请输入至少6位密码");
            mPassword.setText("");
            return;
        }
        //显示进度条
        showProgress(true);
        //初步验证通过 开始访问数据库进行对比
        String url = Common.baseurl+"LoginServlet?number="+number+"&password="+password;
        JsonObjectRequest request = new JsonObjectRequest(url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mResult = response.getString("result");
                    if(!"".equals(mResult)){
                        //有账号就登录 没有账号就注册
                        Toast.makeText(LoginActivity.this, mResult, Toast.LENGTH_SHORT).show();
                        getSharedPreferences("Seting_ordering", 0).edit().putBoolean("isLogin",true).commit();
                        if(!"登录失败".equals(mResult)){
                            StartActivity.StartActivity(LoginActivity.this,MainActivity.class);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ceshi", "onErrorResponse: fail!"+error.getMessage());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(3*1000, 1, 1.0f));
        mRequestQueue.add(request);
        //隐藏进度条
        showProgress(false);

    }

    /**
     * 进度条的隐藏与否
     * @param flag  true:显示   false:隐藏
     */
    private void showProgress(boolean flag){
        if(!flag){
             //进度条隐藏
            mProgressView.setVisibility(View.GONE);
            mLoginFormView.setVisibility(View.VISIBLE);
        }else{
            //进度条显示
            mProgressView.setVisibility(View.VISIBLE);
            mLoginFormView.setVisibility(View.GONE);
        }
    }
}
