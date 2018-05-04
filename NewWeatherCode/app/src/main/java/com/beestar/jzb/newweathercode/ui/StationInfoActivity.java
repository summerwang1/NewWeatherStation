package com.beestar.jzb.newweathercode.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.beestar.jzb.newweathercode.MyAPP;
import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.adapter.FragmentViewPagerAdapter;
import com.beestar.jzb.newweathercode.bean.DeviceBean;
import com.beestar.jzb.newweathercode.fragment.BlueToothInfoFragment;
import com.beestar.jzb.newweathercode.gen.DeviceBeanDao;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

public class StationInfoActivity extends AppCompatActivity implements View.OnClickListener {


    private String TAG = "Station_InfoActivity";
    private LocationClient mlocationClient;
    private BDAbstractLocationListener myLocationListener = new MyLocationListener();
    private ImageView mInfoBgStation;
    private RelativeLayout m1Layout;
    private ViewPager mInfoVpStation;
    private ImageView mMenuStaion;
    private ImageView mSettingStation;
    private DeviceBeanDao deviceBeanDao;
    private List<DeviceBean> list;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);
        deviceBeanDao = MyAPP.getContext().getDaoSession().getDeviceBeanDao();
        list = deviceBeanDao.queryBuilder().list();
        initView();
        setVPLimit();
        setFragmentdata();
        getPermission();
    }

    private void setFragmentdata() {
        List<Fragment> fml=new ArrayList<>();
        for (int i=0;i<4;i++){
            fml.add(BlueToothInfoFragment.newInstance(i));
        }
        fragmentViewPagerAdapter.addFragmentData(fml);
    }

    private void setVPLimit() {
        mInfoVpStation.setOffscreenPageLimit(4);
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), new ArrayList<Fragment>());
        mInfoVpStation.setAdapter(fragmentViewPagerAdapter);
    }

    private void initView() {
        mInfoBgStation = (ImageView) findViewById(R.id.station_info_bg);
        mMenuStaion = (ImageView) findViewById(R.id.staion_menu);
        mMenuStaion.setOnClickListener(this);
        mSettingStation = (ImageView) findViewById(R.id.station_setting);
        mSettingStation.setOnClickListener(this);
        m1Layout = (RelativeLayout) findViewById(R.id.layout_1);
        mInfoVpStation = (ViewPager) findViewById(R.id.station_info_vp);
    }


    private void getPermission() {
        PermissionHelper.requestLocation(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                getLocation();
            }
        });
    }

    /**
     * 获取地址
     */
    private void getLocation() {
        mlocationClient = new LocationClient(MyAPP.getContext());
        mlocationClient.registerLocationListener(myLocationListener);

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

        mlocationClient.setLocOption(option);


        mlocationClient.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.staion_menu:
                // TODO 18/05/04
                break;
            case R.id.station_setting:
                // TODO 18/05/04
                break;
            default:
                break;
        }
    }

    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if (bdLocation.getAddrStr() != null) {
                Log.i(TAG, "onReceiveLocation: " + bdLocation.getAddrStr());

                mlocationClient.stop();
            } else {
            }
        }
    }


}
