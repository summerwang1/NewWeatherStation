package com.beestar.jzb.newweathercode.service;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.util.Log;


import com.beestar.jzb.newweathercode.MyAPP;
import com.beestar.jzb.newweathercode.bean.DeviceBean;
import com.beestar.jzb.newweathercode.gen.DeviceBeanDao;
import com.beestar.jzb.newweathercode.utils.ActivityController;

/**
 * Created by jzb on 2017/11/28.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MyBroadCastReciver extends BroadcastReceiver {
    private String TAG="BroadcastReceiver";
    private NotificationManager mNotificationManager;
    private Notification notification;

    @Override
    public void onReceive(Context context, Intent intent) {
       DeviceBeanDao deviceBeanDao = MyAPP.getContext().getDaoSession().getDeviceBeanDao();
        if (intent.getAction().equals(MyServiceBlueTooth.BLUETOOTHCONNECTED)){
            //连接成功
            Log.i("info","----------reciver----已连接-");

        }else if (intent.getAction().equals(MyServiceBlueTooth.BLUETOOTHDISCONNECT)){
            //连接断开


        }else if (intent.getAction().equals(MyServiceBlueTooth.MYSENDDATA)){
            //收发数据
            final BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("device");
            String data = intent.getStringExtra("data");
            Log.i("info","reciver-------------接收数据"+data);
            if (data.contains("ef")){
                DeviceBean deviceBean = deviceBeanDao.queryBuilder().where(DeviceBeanDao.Properties.Mac.eq(device.getAddress())).build().unique();
                DeviceBean deviceBean2 = deviceBeanDao.queryBuilder().where(DeviceBeanDao.Properties.IsChoose.eq(true)).build().unique();

                if (deviceBean2==null){

                }else {
                    deviceBean2.setIsChoose(false);
                    deviceBeanDao.update(deviceBean2);
                }
                if (deviceBean==null){
                    Log.i(TAG, "onReceive: 没有符合条件的对象");

                    deviceBeanDao.insert(new DeviceBean(device.getName(),null,device.getAddress(),true,true));
                }else {
                    Log.i(TAG, "onReceive: you符合条件的对象"+deviceBean.getName());

                    deviceBean.setIsChoose(true);
                    deviceBean.setIsConn(true);
                    deviceBeanDao.update(deviceBean);
                }
                broadcastUpdate(MyServiceBlueTooth.BING_SUCCESS,device);
                broadcastUpdate(MyServiceBlueTooth.UPDATUI_BINGSUCCESS,device);
            }else if (data.contains("cd")){//绑定失败
                broadcastUpdate(MyServiceBlueTooth.BING_FAILD,device);
            }else if (data.contains("f2")){//一期等待链接
                Intent intent2 = new Intent();
                intent2.setAction(MyServiceBlueTooth.DISCONNECTED_SENDBINDDATA);
                intent2.putExtra("address",device.getAddress());
                MyAPP.getContext().sendBroadcast(intent2);
            } else if (data.length()>8){//获取到温度等数据
                String s = new String(hexStringToBytes(data));
                if (s.subSequence(0,1).equals("Q")){
                    if (s.substring(s.length()-1,s.length()).equals("R")){
                        Intent it=new Intent(MyServiceBlueTooth.LINEDATA);
                        it.putExtra("linedata",s);
                        MyAPP.getContext().sendBroadcast(it);
                    }
                }else {
                    Log.i(TAG, "onReceive: 数据转换");
                    Intent intent3 = new Intent();
                    intent3.setAction(MyServiceBlueTooth.UPDATTEMP);
                    intent3.putExtra("data",s);
                    MyAPP.getContext().sendBroadcast(intent3);
                }

            }else if (data.contains("bb")){//已经被绑定
                Intent intent4=new Intent();
                intent4.setAction(MyServiceBlueTooth.HAVEBING_WITHOTHERS);
                intent4.putExtra("device",device);
                MyAPP.getContext().sendBroadcast(intent4);
            }else if (data.contains("aa")||data.contains("AA")){
                Log.i("jzb", "onReceive:收到AA 设备呼叫手机");


            }else if (data.contains("f9")||data.contains("F9")){
                final Activity activity = ActivityController.getCurrentActivity();
                AlertDialog.Builder dialog = new AlertDialog.Builder(activity,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                dialog.setTitle("提醒")
                        .setMessage("蓝牙修改名称成功")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.create().show();
            }else if (data.contains("fc")||data.contains("FC")){
                DeviceBean deviceBean = deviceBeanDao.queryBuilder().where(DeviceBeanDao.Properties.Mac.eq(device.getAddress())).build().unique();
                if (deviceBean!=null){
                    //设备刚重置 删除数据并断开蓝牙
                    deviceBeanDao.delete(deviceBean);
                    Intent in=new Intent();
                    in.setAction(MyServiceBlueTooth.DISCONNECTED);
                    in.putExtra("address",deviceBean.getMac());
                    MyAPP.getContext().sendBroadcast(in);
                }else{
                    sendData(device.getAddress(),"FC");
                }
            }

        }
        else if (intent.getAction().equals(MyServiceBlueTooth.UPDATSTEP)){
            //固件升级 刷新更新步骤 step
            Intent it=new Intent(MyServiceBlueTooth.PROGRESSTEP);
            it.putExtra("step",intent.getIntExtra("step",-1));
            it.putExtra("error",intent.getIntExtra("error",-1));
            it.putExtra("memDevValue",intent.getIntExtra("memDevValue",-1));
            context.sendBroadcast(it);
        }
    }
    public void sendData(String address,String data){
        Intent intent=new Intent();
        intent.setAction(MyServiceBlueTooth.SEND_DATA);
        intent.putExtra("address",address);
        intent.putExtra("data",data);
        MyAPP.getContext().sendBroadcast(intent);
    }
    private void broadcastUpdate(final String action, final BluetoothDevice device) {
        final Intent intent = new Intent(action);
        intent.putExtra("device",device);
        MyAPP.getContext().sendBroadcast(intent);
    }
    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}
