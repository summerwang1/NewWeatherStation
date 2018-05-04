package com.beestar.jzb.newweathercode.ui.myinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.utils.Keyparameter;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyHomeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private RoundedImageView mMyhomeIcon;
    private TextView mMyhomeName;
    private LinearLayout mUsCall;
    private LinearLayout mAppAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
        initView();
    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mMyhomeIcon = (RoundedImageView) findViewById(R.id.icon_myhome);
        mMyhomeName = (TextView) findViewById(R.id.name_myhome);
        mMyhomeIcon.setOnClickListener(this);
        mUsCall = (LinearLayout) findViewById(R.id.call_us);
        mUsCall.setOnClickListener(this);
        mAppAbout = (LinearLayout) findViewById(R.id.about_app);
        mAppAbout.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermissionHelper.requestStorage(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                try {
                    Bitmap read = read();
                    if (read!=null){
                        mMyhomeIcon.setImageBitmap(read);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 18/04/27
                finish();
                break;
            case R.id.call_us:
                // TODO 18/04/27
                startActivity(new Intent(MyHomeActivity.this, CallUSActivity.class));
                break;
            case R.id.about_app:
                // TODO 18/04/27
                startActivity(new Intent(MyHomeActivity.this, AboutAppActivity.class));
                break;
            case R.id.icon_myhome:// TODO 18/04/27
                startActivity(new Intent(MyHomeActivity.this,MyInfoActivity.class));
                break;
            default:
                break;
        }
    }

    private Bitmap read() throws FileNotFoundException {
        Bitmap bm = null;
        Matrix matrix = new Matrix();
        matrix.setScale(0.2f, 0.2f);
        Bitmap bitmap = BitmapFactory.decodeFile(Keyparameter.PICURL + "weather_icon", null);
        if (bitmap != null) {
            bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), matrix, true);
        }
        if (bm == null) {
            Log.i("info", "------null-------");
        }
        return bm;
    }
}
