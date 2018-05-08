package com.beestar.jzb.newweathercode.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.ui.BaseActivity;

public class NodiscrbTimeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mPerson2Back;
    private ImageView mBg1Day;
    private ImageView mBg2Day;
    private ImageView mBg3Day;
    private ImageView mBg4Day;
    private ImageView mBg5Day;
    private ImageView mBg6Day;
    private ImageView mBg7Day;
    private TextView mTimeStart;
    private TextView mTimeEnd;
    private Button mBtnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodiscrb_time);
        initView();
    }

    private void initView() {
        mPerson2Back = (ImageView) findViewById(R.id.back_person_2);
        mPerson2Back.setOnClickListener(this);
        mBg1Day = (ImageView) findViewById(R.id.day_bg1);
        mBg1Day.setOnClickListener(this);
        mBg2Day = (ImageView) findViewById(R.id.day_bg2);
        mBg2Day.setOnClickListener(this);
        mBg3Day = (ImageView) findViewById(R.id.day_bg3);
        mBg3Day.setOnClickListener(this);
        mBg4Day = (ImageView) findViewById(R.id.day_bg4);
        mBg4Day.setOnClickListener(this);
        mBg5Day = (ImageView) findViewById(R.id.day_bg5);
        mBg5Day.setOnClickListener(this);
        mBg6Day = (ImageView) findViewById(R.id.day_bg6);
        mBg6Day.setOnClickListener(this);
        mBg7Day = (ImageView) findViewById(R.id.day_bg7);
        mBg7Day.setOnClickListener(this);
        mTimeStart = (TextView) findViewById(R.id.start_time);
        mTimeStart.setOnClickListener(this);
        mTimeEnd = (TextView) findViewById(R.id.end_time);
        mTimeEnd.setOnClickListener(this);
        mBtnSure = (Button) findViewById(R.id.sure_btn);
        mBtnSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_person_2:
                // TODO 18/05/07
                break;
            case R.id.day_bg1:
                // TODO 18/05/07
                break;
            case R.id.day_bg2:
                // TODO 18/05/07
                break;
            case R.id.day_bg3:
                // TODO 18/05/07
                break;
            case R.id.day_bg4:
                // TODO 18/05/07
                break;
            case R.id.day_bg5:
                // TODO 18/05/07
                break;
            case R.id.day_bg6:
                // TODO 18/05/07
                break;
            case R.id.day_bg7:
                // TODO 18/05/07
                break;
            case R.id.start_time:
                // TODO 18/05/07
                break;
            case R.id.end_time:
                // TODO 18/05/07
                break;
            case R.id.sure_btn:
                // TODO 18/05/07
                break;
            default:
                break;
        }
    }
}
