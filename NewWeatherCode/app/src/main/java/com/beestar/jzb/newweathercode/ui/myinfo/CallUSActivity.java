package com.beestar.jzb.newweathercode.ui.myinfo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.ui.BaseActivity;

public class CallUSActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private Button mTelcall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mTelcall = (Button) findViewById(R.id.telcall);
        mTelcall.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 18/04/27
                finish();
                break;
            case R.id.telcall:
                // TODO 18/04/27
                PermissionHelper.requestPhone(new PermissionHelper.OnPermissionGrantedListener() {
                    @Override
                    public void onPermissionGranted() {
                        callPhone("02585974207");
                    }
                }, new PermissionHelper.OnPermissionDeniedListener() {
                    @Override
                    public void onPermissionDenied() {

                    }
                });

                break;
            default:
                break;
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
}
