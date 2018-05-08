package com.beestar.jzb.newweathercode.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.ui.login_rigister.LoginActivity;
import com.beestar.jzb.newweathercode.ui.login_rigister.WelcomeActivity;

public class MainActivity extends BaseActivity {
    private String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {

       addStation();
    }

    public void get(View view) {

    }
    public void addStation() {
        PermissionHelper.requestPhone(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                Log.i(TAG, "onPermissionGranted: ------");
                startActivity(new Intent(MainActivity.this,StationInfoActivity.class));
            }
        }, new PermissionHelper.OnPermissionDeniedListener() {
            @Override
            public void onPermissionDenied() {
                Log.i(TAG, "onPermissionDenied: --------");
            }
        });
    }

    public void login(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void resgite(View view) {
        startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
    }

    public void main_activity(View view) {
        startActivity(new Intent(MainActivity.this,MenuActivity.class));
    }
}
