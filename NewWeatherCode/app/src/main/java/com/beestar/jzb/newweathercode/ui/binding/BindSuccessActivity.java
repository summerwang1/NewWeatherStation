package com.beestar.jzb.newweathercode.ui.binding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.ui.MenuActivity;

public class BindSuccessActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBleScanBack;
    private Button mSuccessBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_success);
        initView();
    }

    private void initView() {
        mBleScanBack = (ImageView) findViewById(R.id.back_bleScan);
        mBleScanBack.setOnClickListener(this);
        mSuccessBind = (Button) findViewById(R.id.bind_success);
        mSuccessBind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bleScan:
                // TODO 18/05/07
                finish();
                break;
            case R.id.bind_success:
                // TODO 18/05/07
                startActivity(new Intent(BindSuccessActivity.this, MenuActivity.class));
                break;
            default:
                break;
        }
    }
}
