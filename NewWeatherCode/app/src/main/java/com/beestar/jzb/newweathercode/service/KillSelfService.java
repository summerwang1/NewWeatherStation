package com.beestar.jzb.newweathercode.service;

/**
 * Created by jzb on 2018/1/4.
 */

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

/***
 * 该服务只用来让APP重启，生命周期也仅仅是只是重启APP。重启完即自我杀死
 */
public class KillSelfService extends Service {
    /**
     * 关闭应用后多久重新启动
     */
    private static long stopDelayed = 1000;
    private Handler handler;
    private String PackageName;

    public KillSelfService() {
        handler = new Handler();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        stopDelayed = intent.getLongExtra("Delayed", 1000);
        PackageName = intent.getStringExtra("PackageName");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage(PackageName);
                startActivity(LaunchIntent);
                KillSelfService.this.stopSelf();
            }
        },stopDelayed);
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}