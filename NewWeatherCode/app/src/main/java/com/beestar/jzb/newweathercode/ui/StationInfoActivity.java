package com.beestar.jzb.newweathercode.ui;

import android.content.Intent;
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
import com.beestar.jzb.newweathercode.bean.MessageEvent;
import com.beestar.jzb.newweathercode.bean.Weather_Bean;
import com.beestar.jzb.newweathercode.fragment.BlueToothInfoFragment;
import com.beestar.jzb.newweathercode.gen.DeviceBeanDao;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.service.MyServiceBlueTooth;
import com.beestar.jzb.newweathercode.utils.URL;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class StationInfoActivity extends BaseActivity implements View.OnClickListener {


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
    private List<BlueToothInfoFragment> fragmentList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_info);
        deviceBeanDao = MyAPP.getContext().getDaoSession().getDeviceBeanDao();
        list = deviceBeanDao.queryBuilder().list();
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        list = deviceBeanDao.queryBuilder().list();
        setVPLimit(list.size());
        setFragmentdata(list.size());
        getPermission();
    }

    private void setFragmentdata(int size) {
        fragmentList.clear();
        List<Fragment> fml=new ArrayList<>();
        for (int i=0;i<size;i++){
            fml.add(BlueToothInfoFragment.newInstance(i));
            fragmentList.add(BlueToothInfoFragment.newInstance(i));
        }
        fragmentViewPagerAdapter.addFragmentData(fml);

    }

    private void setVPLimit(int size) {
        mInfoVpStation.setOffscreenPageLimit(size);
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
                diconnectst();
                break;
            default:
                break;
        }
    }
    //断开连接
    private void diconnectst() {
        sendData(list.get(fragmentViewPagerAdapter.getPostion()).getMac(),"CC");
        disConnect(list.get(fragmentViewPagerAdapter.getPostion()).getMac());
        startActivity(new Intent(StationInfoActivity.this,MenuActivity.class));
    }

    private class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if (bdLocation.getAddrStr() != null) {
                mlocationClient.stop();
                Log.i(TAG, "onReceiveLocation: " + bdLocation.getAddrStr());
                for (int i=0;i<fragmentList.size();i++){
                    Log.i(TAG, "onReceiveLocation: "+fragmentList.size());
                    //设置地址
                    BlueToothInfoFragment blueToothInfoFragment = (BlueToothInfoFragment) fragmentViewPagerAdapter.instantiateItem(mInfoVpStation, i);
                    blueToothInfoFragment.setLocationText(bdLocation.getCity(),list.get(i).getName());
                    getWeatherInfor(bdLocation.getCity(),i);
                }
            } else {

            }
        }
    }
    public void sendData(String address,String data){
        Intent intent=new Intent();
        intent.setAction(MyServiceBlueTooth.SEND_DATA);
        intent.putExtra("address",address);
        intent.putExtra("data",data);
        sendBroadcast(intent);
    }
    public void disConnect(String address){
        Intent intent=new Intent();
        intent.setAction(MyServiceBlueTooth.DISCONNECTED);
        intent.putExtra("address",address);
        sendBroadcast(intent);
    }
    //获取天气信息
    private void getWeatherInfor(String city, final int j) {
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

                        BlueToothInfoFragment blueToothInfoFragment = (BlueToothInfoFragment) fragmentViewPagerAdapter.instantiateItem(mInfoVpStation, j);
                        blueToothInfoFragment.setWertherData(Integer.parseInt(result.getResult().getAqi().getPm2_5()),
                                response.getResult().getResult().getUpdatetime().substring(10)+"发布",
                                response.getResult().getResult().getAqi().getPm2_5(),
                                response.getResult().getResult().getTemp()
                                );
                    }
                });
    }
}
