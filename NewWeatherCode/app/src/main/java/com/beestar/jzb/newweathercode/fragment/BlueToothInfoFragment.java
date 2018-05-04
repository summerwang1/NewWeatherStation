package com.beestar.jzb.newweathercode.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlueToothInfoFragment extends Fragment implements View.OnClickListener {

    private static final String FRAGMENTTAG="BLUETOOTHINFOFRAGMENTTAG";
    private int fragmentTag;
    private ImageView mInfoBgStation;
    private ImageView mIconWeather;
    private TextView mTimeFlush;
    private TextView mTextAir;
    private ImageView mImageView;
    private TextView mTemp;
    private ImageView mIconTemp;
    private ImageView mIconPm;
    private TextView mPm;
    private TextView mMystation;
    private TextView mInfoRangeStation;
    private TextView mOffDiscribTextOn;
    private ImageView mOffDiscribBtnOn;
    private ImageView mIconFormstationTemp;
    private TextView mFromstationTemp;
    private TextView mFromstationUnitTemp;
    private ImageView mIconFormstationHum;
    private TextView mFromstationHum;
    private TextView mFromstationUnitHum;
    private ImageView mIconFormstationAirpower;
    private TextView mFromstationAirpower;
    private TextView mFromstationUnitAirpower;
    private TextView mFormstationTextUpdatetime;
    private ImageView mFormstationBtnUpdatetime;
    private RelativeLayout m5Rela;
    private ImageView mBtnCall;

    public BlueToothInfoFragment() {
        // Required empty public constructor
    }

    public static BlueToothInfoFragment newInstance(int tag) {

        BlueToothInfoFragment fragment = new BlueToothInfoFragment();
        Bundle args = new Bundle();
        args.putInt(FRAGMENTTAG, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragmentTag = getArguments().getInt(FRAGMENTTAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blue_tooth_info, container, false);
        initView(view);

        return view;
    }

    private void initView(View itemView) {
        mInfoBgStation = (ImageView) itemView.findViewById(R.id.station_info_bg);
        mIconWeather = (ImageView) itemView.findViewById(R.id.weather_icon);
        mTimeFlush = (TextView) itemView.findViewById(R.id.flush_time);
        mTextAir = (TextView) itemView.findViewById(R.id.air_text);
        mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        mTemp = (TextView) itemView.findViewById(R.id.temp);
        mIconTemp = (ImageView) itemView.findViewById(R.id.temp_icon);
        mIconPm = (ImageView) itemView.findViewById(R.id.pm_icon);
        mPm = (TextView) itemView.findViewById(R.id.pm);
        mMystation = (TextView) itemView.findViewById(R.id.mystation);
        mInfoRangeStation = (TextView) itemView.findViewById(R.id.station_info_range);
        mOffDiscribTextOn = (TextView) itemView.findViewById(R.id.on_off_discrib_text);
        mOffDiscribBtnOn = (ImageView) itemView.findViewById(R.id.on_off_discrib_btn);
        mIconFormstationTemp = (ImageView) itemView.findViewById(R.id.temp_icon_formstation);
        mFromstationTemp = (TextView) itemView.findViewById(R.id.temp_fromstation);
        mFromstationUnitTemp = (TextView) itemView.findViewById(R.id.temp_fromstation_unit);
        mIconFormstationHum = (ImageView) itemView.findViewById(R.id.hum_icon_formstation);
        mFromstationHum = (TextView) itemView.findViewById(R.id.hum_fromstation);
        mFromstationUnitHum = (TextView) itemView.findViewById(R.id.hum_fromstation_unit);
        mIconFormstationAirpower = (ImageView) itemView.findViewById(R.id.airpower_icon_formstation);
        mFromstationAirpower = (TextView) itemView.findViewById(R.id.airpower_fromstation);
        mFromstationUnitAirpower = (TextView) itemView.findViewById(R.id.airpower_fromstation_unit);
        mFormstationTextUpdatetime = (TextView) itemView.findViewById(R.id.updatetime_formstation_text);
        mFormstationBtnUpdatetime = (ImageView) itemView.findViewById(R.id.updatetime_formstation_btn);
        m5Rela = (RelativeLayout) itemView.findViewById(R.id.rela_5);
        mBtnCall = (ImageView) itemView.findViewById(R.id.call_btn);
        mBtnCall.setOnClickListener(this);
        mOffDiscribBtnOn.setOnClickListener(this);
        mFormstationBtnUpdatetime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_btn:
                // TODO 18/05/04 呼叫
                Toast.makeText(getContext(),"call"+fragmentTag,Toast.LENGTH_SHORT).show();
                break;
            case R.id.on_off_discrib_btn:
                // TODO 18/05/04 勿扰
                Toast.makeText(getContext(),"discrib"+fragmentTag,Toast.LENGTH_SHORT).show();
                break;
            case R.id.updatetime_formstation_btn:
                // TODO 18/05/04 更新时间
                Toast.makeText(getContext(),"updatatime"+fragmentTag,Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
