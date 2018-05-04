package com.beestar.jzb.newweathercode.ui.myinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.utils.APKVersionCodeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AboutAppActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private TextView mVersion;
    private Button mNotice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
        initView();
        mVersion.setText("版本号："+APKVersionCodeUtils.getVersionCode(this)+"(版本号:"+APKVersionCodeUtils.getVerName(this)+")");
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mVersion = (TextView) findViewById(R.id.version);
        mVersion.setOnClickListener(this);
        mNotice = (Button) findViewById(R.id.notice);
        mNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 18/04/27
                finish();
                break;
            case R.id.version:
                // TODO 18/04/27
                break;
            case R.id.notice:
                // 版权说明

                break;
            default:
                break;
        }
    }


}
