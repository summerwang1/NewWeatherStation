package com.beestar.jzb.newweathercode.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;

public class NodiscrbActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mPersonBack;
    private ImageView mCallStPhone;
    private Button mTimeStart;
    private ImageView mOnOffNodicrb;
    private Button mTimeEnd;
    private ImageView mCallPhoneSt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodiscrb);
        initView();
    }

    private void initView() {
        mPersonBack = (ImageView) findViewById(R.id.back_person);
        mPersonBack.setOnClickListener(this);
        mCallStPhone = (ImageView) findViewById(R.id.phone_call_st);
        mCallStPhone.setOnClickListener(this);
        mTimeStart = (Button) findViewById(R.id.start_time);
        mTimeStart.setOnClickListener(this);
        mOnOffNodicrb = (ImageView) findViewById(R.id.nodicrb_on_off);
        mOnOffNodicrb.setOnClickListener(this);
        mTimeEnd = (Button) findViewById(R.id.end_time);
        mTimeEnd.setOnClickListener(this);
        mCallPhoneSt = (ImageView) findViewById(R.id.st_call_phone);
        mCallPhoneSt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_person:
                // TODO 18/05/07
                break;
            case R.id.phone_call_st:
                // TODO 18/05/07
                break;
            case R.id.start_time:
                // TODO 18/05/07
                break;
            case R.id.nodicrb_on_off:
                // TODO 18/05/07
                break;
            case R.id.end_time:
                // TODO 18/05/07
                break;
            case R.id.st_call_phone:
                // TODO 18/05/07
                break;
            default:
                break;
        }
    }
}
