package com.beestar.jzb.newweathercode.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.adapter.MyDeviceListAdapter;
import com.beestar.jzb.newweathercode.bean.DeviceBean;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.utils.Keyparameter;

import java.util.ArrayList;
import java.util.List;

public class ScanAndBindActivity extends AppCompatActivity {

    private String TAG = "ScanAndBindActivity";
    private static final int REQUEST_ENABLE_BT = 1;
    private static final long SCAN_PERIOD = 10000;
    private static boolean mScanning = true;
    BluetoothManager bluetoothManager;
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private String m_szImei;
    private List<String> listAddress = new ArrayList<>();
    private List<DeviceBean> list = new ArrayList<>();
    private RecyclerView mDevList;
    private MyDeviceListAdapter myDeviceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_and_bind);
        initTelphoneMga();//获取唯一地址编码
        boolBluetooth();//是否支持蓝牙
        initblebool();//是否支持ble设备
        getPermissions();//获取需要的权限
        slcan_btn();

    }
    public void slcan_btn() {//点击开始扫描
        list.clear();
        myDeviceListAdapter.clear();
        myScanDevice(true);
    }
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    if (checkDecive(device.getAddress()) != null) {
                        if (device.getName() != null) {
                            Log.i("info", "---设备-" + device.getName() + "--" + device.getAddress());
                            myDeviceListAdapter.addItem(new DeviceBean(device.getName().toString(),device.getAddress().toString(),false,false, Keyparameter.BLETYPECODE));
                            list.add(new DeviceBean(device.getName().toString(), device.getAddress().toString(), false, false,Keyparameter.BLETYPECODE));
                        } else {

                        }
                    }
                }
            };

    //蓝牙扫描
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

    private void getPermissions() {
        PermissionHelper.requestLocation(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                Log.i(TAG, "onPermissionGranted: 获取地址权限");
            }
        });
        PermissionHelper.requestStorage(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                Log.i(TAG, "onPermissionGranted: 获取写权限");
            }
        });
    }

    private void initTelphoneMga() {
        BluetoothAdapter mBlueth = BluetoothAdapter.getDefaultAdapter();
        m_szImei = mBlueth.getAddress();
        while (m_szImei.length() != 32) {
            m_szImei += "0";
        }
        m_szImei = "f1" + m_szImei + "1f";
    }

    /**
     * 判断是否支持蓝牙
     */
    private void boolBluetooth() {
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "对不起，您的设备不支持蓝牙,即将退出", Toast.LENGTH_SHORT).show();
            finish();
        } else if (!mBluetoothAdapter.isEnabled()) {//蓝牙未开启
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //判断是不是启动蓝牙的结果
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                //成功
                Toast.makeText(this, "蓝牙开启成功...", Toast.LENGTH_SHORT).show();
            } else {
                //失败
                Toast.makeText(this, "蓝牙开启失败...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 是否支持Ble设备
     */
    private void initblebool() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "您的手机不支持Ble设备", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "您的手机不支持Ble蓝牙", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //检查蓝牙重复
    private String checkDecive(String addressflag) {
        if (listAddress.contains(addressflag)) {
            return null;
        } else {
            listAddress.add(addressflag);
            return addressflag;
        }
    }

    private void initView() {
        mDevList = (RecyclerView) findViewById(R.id.device_list);

        mDevList = ((RecyclerView) findViewById(R.id.device_list));
        myDeviceListAdapter = new MyDeviceListAdapter(getApplication(),new ArrayList<DeviceBean>());
        mDevList.setLayoutManager(new LinearLayoutManager(this));
        mDevList.setAdapter(myDeviceListAdapter);
        mDevList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
}
