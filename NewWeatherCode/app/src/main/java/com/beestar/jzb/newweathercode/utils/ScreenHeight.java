package com.beestar.jzb.newweathercode.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;

/**
 * Created by guzhen on 2018/4/20.
 */

public class ScreenHeight {

    public static int getScreenHeight(Activity context) {
        int i = getDeviceHeight(context);
        int j = getStatusBarHeight(context) / 2;
        int k = getNavigationBarHeight(context);
        int g = i - k;
        Log.i("info", i + "------" + j + "----" + k + "" + g);
        return g;
    }

    /**获取屏幕的高*/
    public static int getDeviceHeight(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.y;
    }
    /**获取屏幕的宽*/
    public static int getDeviceWidth(Activity context) {
        Display display = context.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        return p.x;
    }

    /**
     * 获取顶部状态栏
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Activity context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Status height:" + height);
        return height;
    }
    /**
     * 获取底部导航栏高度
     */
    public static int getNavigationBarHeight(Activity context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        Log.v("dbw", "Navi height:" + height);
        return height;
    }
}
