package com.beestar.jzb.newweathercode.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;

public class UpdataActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mUupdataBack;
    private TextView mStuVersion;
    private ProgressBar mProVersion;
    private Button mVisionCutUpdate;
    private RelativeLayout mVisionBarUpdat;
    private RelativeLayout mStChoose;
    private TextView mVisionSt;
    private Button mStUpdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updata);
        initView();
    }

    private void initView() {
        mUupdataBack = (ImageView) findViewById(R.id.back_uupdata);
        mUupdataBack.setOnClickListener(this);
        mStuVersion = (TextView) findViewById(R.id.version_stu);
        mProVersion = (ProgressBar) findViewById(R.id.version_pro);
        mVisionCutUpdate = (Button) findViewById(R.id.update_vision_cut);
        mVisionCutUpdate.setOnClickListener(this);
        mVisionBarUpdat = (RelativeLayout) findViewById(R.id.updat_vision_bar);
        mStChoose = (RelativeLayout) findViewById(R.id.choose_st);
        mStChoose.setOnClickListener(this);
        mVisionSt = (TextView) findViewById(R.id.st_vision);
        mStUpdata = (Button) findViewById(R.id.updata_st);
        mStUpdata.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_uupdata:
                // TODO 18/05/07
                break;
            case R.id.update_vision_cut:
                // TODO 18/05/07
                break;
            case R.id.choose_st:
                // TODO 18/05/07
                break;
            case R.id.updata_st:
                // TODO 18/05/07
                break;
            default:
                break;
        }
    }
}
