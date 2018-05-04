package com.beestar.jzb.newweathercode.ui.addstation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.utils.CacheActivity;

public class AddStartActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private ImageView mClose;
    private Button mAddBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_start);
        CacheActivity.addActivity(AddStartActivity.this);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mClose = (ImageView) findViewById(R.id.close);
        mClose.setOnClickListener(this);
        mAddBtnStart = (Button) findViewById(R.id.start_add_btn);
        mAddBtnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 18/04/25
                CacheActivity.delectActivity(AddStartActivity.this);
                finish();
                break;
            case R.id.close:
                // TODO 18/04/25
                CacheActivity.finishActivity();
                break;
            case R.id.start_add_btn:
                // TODO 18/04/25
                startActivity(new Intent(AddStartActivity.this,BleScanListActivity.class));
                break;
            default:
                break;
        }
    }
}
