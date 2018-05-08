package com.beestar.jzb.newweathercode.ui.binding;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.adapter.MyDeviceListAdapter;
import com.beestar.jzb.newweathercode.bean.DeviceBean;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.service.MyServiceBlueTooth;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.utils.CacheActivity;
import com.beestar.jzb.newweathercode.utils.Keyparameter;

import java.util.ArrayList;
import java.util.List;

public class BleScanListActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private ImageView mClose;
    private LinearLayout mBluetoothOffPhone;
    private TextView mStastuScan;
    private RecyclerView mListDev;
    private LinearLayout mBluetoothOnPhone;
    private String TAG="BleScanListActivity";
    private MyDeviceListAdapter myDeviceListAdapter;
    private List<String > listAddress=new ArrayList<>();
    private List<DeviceBean> list=new ArrayList<>();
    private static boolean mScanning=true;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private static final long SCAN_PERIOD = 10000;
    private String m_szImei;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case BluetoothAdapter.ACTION_STATE_CHANGED:
                    int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueState) {
                        case BluetoothAdapter.STATE_ON:
                            mBluetoothOffPhone.setVisibility(View.VISIBLE);
                            mBluetoothOnPhone.setVisibility(View.GONE);
                        case BluetoothAdapter.STATE_OFF:
                            mBluetoothOffPhone.setVisibility(View.VISIBLE);
                            mBluetoothOnPhone.setVisibility(View.GONE);
                            break;
                    }
                    break;
                case MyServiceBlueTooth.BING_FAILD:
                    startActivity(new Intent(BleScanListActivity.this,BindFaildActivity.class));
                    break;
                case MyServiceBlueTooth.BING_SUCCESS:
                    startActivity(new Intent(BleScanListActivity.this,BindSuccessActivity.class));
                    break;
            }
        }
    };
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    if (checkDecive(device.getAddress())!=null){
                        if (device.getName()!=null){
                            Log.i("info","---设备-"+device.getName()+"--"+device.getAddress());
                            myDeviceListAdapter.addItem(new DeviceBean(device.getName().toString(),device.getAddress().toString(),false,false, Keyparameter.BLETYPECODE));
                            list.add(new DeviceBean(device.getName().toString(),device.getAddress().toString(),false,false,Keyparameter.BLETYPECODE));
                        }else {

                        }
                    }
                }
            };
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public void myScanDevice(final boolean enable) {

        if (enable) {
            new Handler().postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CacheActivity.addActivity(BleScanListActivity.this);
        setContentView(R.layout.activity_ble_scan_list);
        getPermissions();
        initView();
        initblebool();
        initTelphoneMga();
        registerReceiver(mReceiver,makeFilter());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isServiceRunning()){
            startService(new Intent(BleScanListActivity.this,MyServiceBlueTooth.class));
        }
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (null == adapter) {
            //蓝牙不支持
            Log.i(TAG, "onCreate: 不支持蓝牙");
        }else {
            if (adapter.getState()==BluetoothAdapter.STATE_OFF){
                mBluetoothOffPhone.setVisibility(View.VISIBLE);
                mBluetoothOnPhone.setVisibility(View.GONE);
            }else if (adapter.getState()==BluetoothAdapter.STATE_ON){
                mBluetoothOffPhone.setVisibility(View.GONE);
                mBluetoothOnPhone.setVisibility(View.VISIBLE);
                myScanDevice(true);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void initTelphoneMga() {
        BluetoothAdapter mBlueth = BluetoothAdapter.getDefaultAdapter();
        m_szImei = mBlueth.getAddress();
        while (m_szImei.length() != 32) {
            m_szImei += "0";
        }
        m_szImei = "f1" + m_szImei + "1f";
    }

    private void getPermissions() {
        PermissionHelper.requestLocation(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                Log.i(TAG, "onPermissionGranted: 获取地址权限");
            }
        });

    }
    private void initView() {
        mBack = (ImageView) findViewById(R.id.back_bleScan);
        mBack.setOnClickListener(this);
        mClose = (ImageView) findViewById(R.id.close_bleScan);
        mClose.setOnClickListener(this);
        mBluetoothOffPhone = (LinearLayout) findViewById(R.id.phone_bluetooth_off);
        mStastuScan = (TextView) findViewById(R.id.scan_stastu);
        mListDev = (RecyclerView) findViewById(R.id.dev_list);
        mBluetoothOnPhone = (LinearLayout) findViewById(R.id.phone_bluetooth_on);

        myDeviceListAdapter = new MyDeviceListAdapter(getApplication(),new ArrayList<DeviceBean>());
        mListDev.setLayoutManager(new LinearLayoutManager(this));
        mListDev.setAdapter(myDeviceListAdapter);
        mListDev.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        myDeviceListAdapter.setOnItemClickListener(new MyDeviceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            myScanDevice(false);
                Intent intent=new Intent();
                intent.setAction(MyServiceBlueTooth.RECCONECT);
                intent.putExtra("address",list.get(position).getMac());
                sendBroadcast(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bleScan:
                // TODO 18/04/25
                CacheActivity.delectActivity(BleScanListActivity.this);
                finish();

                Log.i(TAG, "onClick: back");
                break;
            case R.id.close_bleScan:
                // TODO 18/04/25
                Log.i(TAG, "onClick: close");
                CacheActivity.finishActivity();
                break;
            default:
                break;
        }
    }
    private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        filter.addAction(MyServiceBlueTooth.BING_SUCCESS);
        filter.addAction(MyServiceBlueTooth.BING_FAILD);
        return filter;
    }
    /**
     * 是否支持Ble设备
     */
    private void initblebool() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this,"您的手机不支持Ble设备", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "您的手机不支持Ble蓝牙", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private String checkDecive(String addressflag) {
        if (listAddress.contains(addressflag)){
            return null;
        }else {
            listAddress.add(addressflag);
            return addressflag;
        }
    }
    //判断蓝牙服务是否已经开启
    private boolean isServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.beestar.jzb.newweathercode.service.MyServiceBlueTooth".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
