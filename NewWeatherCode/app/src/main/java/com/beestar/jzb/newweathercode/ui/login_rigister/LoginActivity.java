package com.beestar.jzb.newweathercode.ui.login_rigister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.MyAPP;
import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.bean.LoginBean;
import com.beestar.jzb.newweathercode.bean.Login_Return;
import com.beestar.jzb.newweathercode.bean.Registe_UserInfo;
import com.beestar.jzb.newweathercode.bean.ReturnBean;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.ui.MenuActivity;
import com.beestar.jzb.newweathercode.utils.Keyparameter;
import com.beestar.jzb.newweathercode.utils.SPUtils;
import com.beestar.jzb.newweathercode.utils.URL;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private String TAG="LoginActivity";
    private int dv_height;
    private EditText mUesrName;
    private EditText mPassword;
    private Button mLogin;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();


    }

    private void initView() {
        mUesrName = (EditText) findViewById(R.id.uesrName);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.email_sign_in_button);
        mLogin.setOnClickListener(this);
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                // TODO 18/04/20
                loginUser();
                Log.i(TAG, "onClick: 点击登录");
                break;
            case R.id.back:// TODO 18/04/20
                finish();
                break;
            default:
                break;
        }
    }
    private void loginUser() {
        MyAPP.getContext().getMyOkHttp().post()
                .url(URL.url + URL.url_login)
                .tag(this)
                .jsonParams(new Gson().toJson(new LoginBean(mUesrName.getText().toString().trim(), mPassword.getText().toString().trim())))
                .enqueue(new GsonResponseHandler<Login_Return>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                    @Override
                    public void onSuccess(int statusCode, Login_Return response) {
                        if (response.getRtn_code() == 0) {
                            SPUtils.put(MyAPP.getContext(), "isLogin", true);
                            SPUtils.put(MyAPP.getContext(),"phone",mUesrName.getText().toString().trim());
                            SPUtils.put(MyAPP.getContext(),"sex",response.getAdditions().getSex());
                            SPUtils.put(MyAPP.getContext(),"name",response.getAdditions().getName());

                            Toast.makeText(getApplication(), "登录成功", Toast.LENGTH_SHORT).show();
                        } else if (response.getRtn_code()==Keyparameter.CODE_ERROR_SERVICE){
                            Toast.makeText(getApplication(), Keyparameter.CODE_ERROR_SERVICE_STR, Toast.LENGTH_SHORT).show();
                        }else if (response.getRtn_code()==Keyparameter.CODE_PWD_PUT_ERROR){
                            Toast.makeText(getApplication(), Keyparameter.CODE_PWD_ERROR_STR, Toast.LENGTH_SHORT).show();
                        }else if (response.getRtn_code()==Keyparameter.CODE_USER_PWD_ERROR){
                            Toast.makeText(getApplication(), Keyparameter.CODE_USER_PWD_ERROR, Toast.LENGTH_SHORT).show();
                        }else if (response.getRtn_code()==Keyparameter.CODE_USER_ERROR){
                            Toast.makeText(getApplication(), Keyparameter.CODE_USER_ERROR_STR, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplication(), "数据请求出现错误,请稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
