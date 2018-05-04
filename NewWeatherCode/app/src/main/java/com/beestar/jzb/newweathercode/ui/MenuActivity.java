package com.beestar.jzb.newweathercode.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.beestar.jzb.newweathercode.MyAPP;
import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.adapter.GridViewAdapter;
import com.beestar.jzb.newweathercode.bean.DeviceBean;
import com.beestar.jzb.newweathercode.bean.Weather_Bean;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.ui.addstation.AddBoolBlueToothActivity;
import com.beestar.jzb.newweathercode.ui.myinfo.MyHomeActivity;
import com.beestar.jzb.newweathercode.utils.ScreenHeight;
import com.beestar.jzb.newweathercode.utils.URL;
import com.beestar.jzb.newweathercode.view.MyGridView;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mSetting;
    private ImageView mMenuAdd;
    private RelativeLayout m1Rela;
    private TextView mMyStationText;
    private RelativeLayout m2Rela;
    private MyGridView mGridView;
    private RelativeLayout m3Rela;
    private GridViewAdapter gridViewAdapter;

    private List<DeviceBean> data = new ArrayList<>();
    private TextView mLowTemp;
    private TextView mHeightTemp;
    private TextView mNowTemp;
    private TextView mTextView;
    private TextView mTimeFlush;
    private LinearLayout m1Line;
    private RecyclerView mRecycleView;
    private ImageView mImageQxl;
    private LinearLayout m2Line;
    private String TAG = "MenuActivity";
    private TextView mTextTq;
    public LocationClient mLocationClient = null;
    public BDAbstractLocationListener myListener = new MyLocationListener();
    private TextView mMyLoction;
    private TextView mMassAir;
    private TextView mWindpower;

    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getAddrStr() != null) {

                mMyLoction.setText(bdLocation.getAddrStr().trim());
                getWeatherInfor(bdLocation.getCity());
                mLocationClient.stop();
            } else {
//                Toast.makeText(MyApp.getContext(), "定位失败请检查GPS以及手机网络", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();

        getLocation();

        for (int i = 0; i < 3; i++) {
            data.add(new DeviceBean("测试", "测试", "mac", false, false, 2));
        }
        if (data.size() <= 5) {
            m3Rela.getLayoutParams().height = (int) (ScreenHeight.getDeviceHeight(this) * (0.38));
        } else {
            m3Rela.getLayoutParams().height = (int) (ScreenHeight.getDeviceHeight(this) * (0.19 * ((data.size() / 3) + 1)));
        }
        gridViewAdapter.add(data);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //添加
                    Log.i(TAG, "onItemClick:添加 " + position);
                    startActivity(new Intent(MenuActivity.this, AddBoolBlueToothActivity.class));

                } else {
                    Log.i(TAG, "onItemClick: " + position);
                    startActivity(new Intent(MenuActivity.this, StationInfoActivity.class));
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void getWeatherInfor(String city) {
        MyAPP.getContext().getMyOkHttp().post()
                .url(URL.url_weather)
                .tag(this)
                .addParam("city", city)
                .addParam("appkey", "5468ea49f7a75f15be7a51975ddf9087")
                .enqueue(new GsonResponseHandler<Weather_Bean>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }

                    @Override
                    public void onSuccess(int statusCode, Weather_Bean response) {
                        Weather_Bean.ResultBeanX result = response.getResult();
                        mLowTemp.setText(result.getResult().getTemplow() + "℃" );
                        mHeightTemp.setText(result.getResult().getTemphigh() + "℃");
                        mTextTq.setText(result.getResult().getWeather());
                        mWindpower.setText(result.getResult().getWinddirect() + result.getResult().getWindpower());
                        mMassAir.setText(result.getResult().getAqi().getQuality());
                        mNowTemp.setText(result.getResult().getTemp());
                        String img = "weather" + result.getResult().getImg();


                        int i = Integer.parseInt(result.getResult().getAqi().getPm2_5());
                        if (i <= 50) {
                            mMassAir.setText("优");

                        } else if (i > 50 && i < 100) {
                            mMassAir.setText("良");

                        } else if (i > 100 && i < 150) {
                            mMassAir.setText("轻度污染");

                        } else if (i > 150 && i < 200) {
                            mMassAir.setText("中度污染");

                        } else if (i > 200 && i < 300) {
                            mMassAir.setText("重度污染");

                        } else if (i > 300 && i < 500) {
                            mMassAir.setText("严重污染");
                        }
                    }
                });
    }

    private void getLocation() {
        PermissionHelper.requestLocation(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                //===========定位功能=======================================
                mLocationClient = new LocationClient(MyAPP.getContext());
                //声明LocationClient类
                mLocationClient.registerLocationListener(myListener);
                //注册监听函数
                initLocation();
                mLocationClient.start();
                //===============定位开启===================================
            }
        });

    }

    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.setIgnoreCacheException(false);
//        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        option.setWifiValidTime(5*60*1000);
//        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
    }

    private void initView() {
        mSetting = (ImageView) findViewById(R.id.setting);
        mMenuAdd = (ImageView) findViewById(R.id.add_menu);
        m1Rela = (RelativeLayout) findViewById(R.id.rela_1);
        mMyStationText = (TextView) findViewById(R.id.text_my_station);
        m2Rela = (RelativeLayout) findViewById(R.id.rela_2);
        mGridView = (MyGridView) findViewById(R.id.gridView);
        m3Rela = (RelativeLayout) findViewById(R.id.rela_3);

        gridViewAdapter = new GridViewAdapter(this, new ArrayList<DeviceBean>());
        mGridView.setAdapter(gridViewAdapter);
        mLowTemp = (TextView) findViewById(R.id.temp_low);
        mHeightTemp = (TextView) findViewById(R.id.temp_height);
        mNowTemp = (TextView) findViewById(R.id.temp_now);
        mTextView = (TextView) findViewById(R.id.textView);
        mTimeFlush = (TextView) findViewById(R.id.flush_time);
        m1Line = (LinearLayout) findViewById(R.id.line_1);

        m1Line.getLayoutParams().height = (int) (ScreenHeight.getDeviceHeight(this) * (0.24));
        mRecycleView = (RecyclerView) findViewById(R.id.recycleView);
        mImageQxl = (ImageView) findViewById(R.id.qxl_image);
        m2Line = (LinearLayout) findViewById(R.id.line_2);

        m2Line.getLayoutParams().height = (int) (ScreenHeight.getDeviceHeight(this) * (0.69));
        mRecycleView.getLayoutParams().height = (int) (ScreenHeight.getDeviceHeight(this) * (0.3));
        mImageQxl.getLayoutParams().height = (int) (ScreenHeight.getDeviceHeight(this) * (0.2));
        mSetting.setOnClickListener(this);
        mMenuAdd.setOnClickListener(this);
        mTextTq = (TextView) findViewById(R.id.tq_text);
        mMyLoction = (TextView) findViewById(R.id.loction_my);
        mMassAir = (TextView) findViewById(R.id.air_mass);
        mWindpower = (TextView) findViewById(R.id.windpower);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                // TODO 18/04/26
                startActivity(new Intent(MenuActivity.this, MyHomeActivity.class));
                break;
            case R.id.add_menu:
                // TODO 18/04/26
                startActivity(new Intent(MenuActivity.this, AddBoolBlueToothActivity.class));
                break;
            default:
                break;
        }
    }
}
