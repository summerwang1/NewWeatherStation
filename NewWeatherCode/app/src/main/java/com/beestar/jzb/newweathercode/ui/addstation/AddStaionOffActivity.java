package com.beestar.jzb.newweathercode.ui.addstation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.utils.CacheActivity;

public class AddStaionOffActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private ImageView mClose;
    private Button mBtnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staion_off);
        CacheActivity.addActivity(AddStaionOffActivity.this);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mClose = (ImageView) findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mBtnNext = (Button) findViewById(R.id.next_btn);
        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 18/04/25
                CacheActivity.delectActivity(AddStaionOffActivity.this);
                finish();
                break;
            case R.id.close:
                // TODO 18/04/25
                CacheActivity.finishActivity();
                break;
            case R.id.next_btn:
                // TODO 18/04/25
                finish();
                break;
            default:
                break;
        }
    }
}