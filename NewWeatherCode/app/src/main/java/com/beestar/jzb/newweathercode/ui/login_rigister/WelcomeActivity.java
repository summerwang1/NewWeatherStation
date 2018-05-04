package com.beestar.jzb.newweathercode.ui.login_rigister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.beestar.jzb.newweathercode.MyAPP;
import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.MenuActivity;
import com.beestar.jzb.newweathercode.utils.Keyparameter;
import com.beestar.jzb.newweathercode.utils.SPUtils;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mNewResgiter;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

        if (SPUtils.contains(MyAPP.getContext(), Keyparameter.ISLOGIN)) {
            if ((Boolean) SPUtils.get(MyAPP.getContext(), Keyparameter.ISLOGIN, false)){
                startActivity(new Intent(WelcomeActivity.this, MenuActivity.class));
                finish();
            }
        }
    }

    private void initView() {
        mNewResgiter = (Button) findViewById(R.id.newResgiter);
        mNewResgiter.setOnClickListener(this);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newResgiter:
                // TODO 18/04/20
                startActivity(new Intent(WelcomeActivity.this,NewResgiteActivity.class));
                break;
            case R.id.login:
                // TODO 18/04/20
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                break;
            default:
                break;
        }
    }
}
