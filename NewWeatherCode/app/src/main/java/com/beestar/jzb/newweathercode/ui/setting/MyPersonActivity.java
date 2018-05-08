package com.beestar.jzb.newweathercode.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;

public class MyPersonActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mPersonBack;
    private RelativeLayout mStBlueMy;
    private Button mMusic1;
    private Button mMusic2;
    private Button mMusic3;
    private ImageView mImgSetConn1;
    private LinearLayout mSetConn1;
    private ImageView mImgSetConn2;
    private LinearLayout mSetConn2;
    private RelativeLayout mMusicPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_person);
        initView();
    }

    private void initView() {
        mPersonBack = (ImageView) findViewById(R.id.back_person);
        mPersonBack.setOnClickListener(this);
        mStBlueMy = (RelativeLayout) findViewById(R.id.my_st_blue);
        mStBlueMy.setOnClickListener(this);
        mMusic1 = (Button) findViewById(R.id.music1);
        mMusic1.setOnClickListener(this);
        mMusic2 = (Button) findViewById(R.id.music2);
        mMusic2.setOnClickListener(this);
        mMusic3 = (Button) findViewById(R.id.music3);
        mMusic3.setOnClickListener(this);
        mImgSetConn1 = (ImageView) findViewById(R.id.setConn1_img);
        mSetConn1 = (LinearLayout) findViewById(R.id.setConn1);
        mSetConn1.setOnClickListener(this);
        mImgSetConn2 = (ImageView) findViewById(R.id.setConn2_img);
        mSetConn2 = (LinearLayout) findViewById(R.id.setConn2);
        mSetConn2.setOnClickListener(this);
        mMusicPhone = (RelativeLayout) findViewById(R.id.phone_music);
        mMusicPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_person:
                // TODO 18/05/07
                finish();
                break;
            case R.id.my_st_blue:
                // TODO 18/05/07

                break;
            case R.id.music1:
                // TODO 18/05/07
                break;
            case R.id.music2:
                // TODO 18/05/07
                break;
            case R.id.music3:
                // TODO 18/05/07
                break;
            case R.id.setConn1:
                // TODO 18/05/07
                break;
            case R.id.setConn2:
                // TODO 18/05/07
                break;
            case R.id.phone_music:
                // TODO 18/05/07
                break;
            default:
                break;
        }
    }
}
