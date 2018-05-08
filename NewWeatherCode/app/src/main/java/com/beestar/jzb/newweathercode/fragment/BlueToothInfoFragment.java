package com.beestar.jzb.newweathercode.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.MyAPP;
import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.bean.DeviceBean;
import com.beestar.jzb.newweathercode.bean.Weather_Bean;
import com.beestar.jzb.newweathercode.gen.DeviceBeanDao;
import com.beestar.jzb.newweathercode.service.MyServiceBlueTooth;
import com.beestar.jzb.newweathercode.ui.setting.NodiscrbActivity;
import com.beestar.jzb.newweathercode.utils.URL;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlueToothInfoFragment extends Fragment implements View.OnClickListener {

    private static final String FRAGMENTTAG = "BLUETOOTHINFOFRAGMENTTAG";
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
    private Button mBtnCall;
    private TextView mTextLocation;
    private DeviceBeanDao deviceBeanDao;
    private List<DeviceBean> list;


    public int getFragmentTag() {
        return fragmentTag;
    }

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
        deviceBeanDao = MyAPP.getContext().getDaoSession().getDeviceBeanDao();
        list = deviceBeanDao.queryBuilder().list();
        if (getArguments() != null) {
            fragmentTag = getArguments().getInt(FRAGMENTTAG);
        }
//        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blue_tooth_info, container, false);
        initView(view);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

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
        mBtnCall = (Button) itemView.findViewById(R.id.call_btn);
        mBtnCall.setOnClickListener(this);
        mOffDiscribBtnOn.setOnClickListener(this);
        mFormstationBtnUpdatetime.setOnClickListener(this);
        mTextLocation = (TextView) itemView.findViewById(R.id.location_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call_btn:
                // TODO 18/05/04 呼叫
                    Toast.makeText(getContext(), "call" + fragmentTag, Toast.LENGTH_SHORT).show();
                if (mBtnCall.getText().equals("呼叫")){
                    sendData(list.get(fragmentTag).getMac(),"aa");
                    mBtnCall.setBackgroundResource(R.mipmap.qxz_sbzy_ssqxzan_hjtz3);
                    mBtnCall.setText("停止");
                }else if (mBtnCall.getText().equals("停止")){
                    sendData(list.get(fragmentTag).getMac(),"ab");
                    mBtnCall.setBackgroundResource(R.mipmap.qxz_sbzy_ssqxzan_hj3);
                    mBtnCall.setText("呼叫");
                }

                break;
            case R.id.on_off_discrib_btn:
                // TODO 18/05/04 勿扰
//                Toast.makeText(getContext(), "discrib" + fragmentTag, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), NodiscrbActivity.class));
                break;
            case R.id.updatetime_formstation_btn:
                // TODO 18/05/04 更新时间
//                Toast.makeText(getContext(), "updatatime" + fragmentTag, Toast.LENGTH_SHORT).show();
                mFormstationTextUpdatetime.setText(getTime());
                break;
            default:
                break;
        }
    }
    public void sendData(String address,String data){
        Intent intent=new Intent();
        intent.setAction(MyServiceBlueTooth.SEND_DATA);
        intent.putExtra("address",address);
        intent.putExtra("data",data);
        getActivity().sendBroadcast(intent);
    }
    //设置地址
    public void setLocationText(String location,String name) {
        mTextLocation.setText(location);
        Log.i("fragment", "setData: " + location);
        mMystation.setText(name);
    }
    //设置 天气信息
    public void setWertherData(int i,String updattime,String pm,String temp){
        mTimeFlush.setText(updattime);
        mPm.setText(pm);
        mTemp.setText(temp+"℃");
        if (i <= 50) {
            mTextAir.setText("优");

        } else if (i > 50 && i < 100) {
            mTextAir.setText("良");

        } else if (i > 100 && i < 150) {
            mTextAir.setText("轻度污染");

        } else if (i > 150 && i < 200) {
            mTextAir.setText("中度污染");

        } else if (i > 200 && i < 300) {
            mTextAir.setText("重度污染");

        } else if (i > 300 && i < 500) {
            mTextAir.setText("严重污染");
        }
    }
    //获取当前时间
    private String getTime(){
        Date date =new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");//只有时分秒
        return sdf.format(date);
    }
}
