package com.beestar.jzb.newweathercode.ui.myinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.helper.PermissionHelper;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.ui.MainActivity;
import com.beestar.jzb.newweathercode.utils.Keyparameter;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyInfoActivity extends BaseActivity implements View.OnClickListener {

    private String TAG="MyInfoActivity";
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;
    //调用照相机返回图片文件
    private File tempFile;

    private ImageView mBack;
    private RoundedImageView mImageicon;
    private RelativeLayout mSetIcon;
    private ImageView mPic2Turn;
    private TextView mNameSet;
    private RelativeLayout mNameSetting;
    private ImageView mPic3Turn;
    private TextView mSexSet;
    private RelativeLayout mSexSetting;
    private ImageView mPic4Turn;
    private TextView mNumberSet;
    private RelativeLayout mNumberSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        initView();
        PermissionHelper.requestStorage(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                try {
                    Bitmap read = read();
                    if (read!=null){
                        mImageicon.setImageBitmap(read);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mImageicon = (RoundedImageView) findViewById(R.id.imageicon);
        mSetIcon = (RelativeLayout) findViewById(R.id.setIcon);
        mSetIcon.setOnClickListener(this);
        mPic2Turn = (ImageView) findViewById(R.id.turn_pic_2);
        mNameSet = (TextView) findViewById(R.id.set_Name);
        mNameSetting = (RelativeLayout) findViewById(R.id.setting_Name);
        mNameSetting.setOnClickListener(this);
        mPic3Turn = (ImageView) findViewById(R.id.turn_pic_3);
        mSexSet = (TextView) findViewById(R.id.set_Sex);
        mSexSetting = (RelativeLayout) findViewById(R.id.setting_Sex);
        mSexSetting.setOnClickListener(this);
        mPic4Turn = (ImageView) findViewById(R.id.turn_pic_4);
        mNumberSet = (TextView) findViewById(R.id.set_Number);
        mNumberSetting = (RelativeLayout) findViewById(R.id.setting_Number);
        mNumberSetting.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 18/04/27
                finish();
                break;
            case R.id.setIcon:
                // TODO 18/04/27
                getPicFromAlbm();
                break;
            case R.id.setting_Name:
                // TODO 18/04/27
                break;
            case R.id.setting_Sex:
                // TODO 18/04/27
                break;
            case R.id.setting_Number:
                // TODO 18/04/27
                break;
            default:
                break;
        }
    }

    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MyInfoActivity.this, "com.beestar.jzb.newweathercode", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            Log.e("dasd", contentUri.toString());
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }
    /**
     * 从相册获取图片
     */
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }
    /**
     * 裁剪图片
     */
    private void startCrop(Uri uri){

        UCrop.Options options = new UCrop.Options();
        //裁剪后图片保存在文件夹中
        Uri destinationUri = Uri.fromFile(new File(getExternalCacheDir(), "weather.jpg"));
        UCrop uCrop = UCrop.of(uri, destinationUri);//第一个参数是裁剪前的uri,第二个参数是裁剪后的uri
        uCrop.withAspectRatio(1,1);//设置裁剪框的宽高比例
        //下面参数分别是缩放,旋转,裁剪框的比例
        options.setAllowedGestures(UCropActivity.ALL,UCropActivity.NONE,UCropActivity.ALL);
        options.setToolbarTitle("移动和缩放");//设置标题栏文字
        options.setCropGridStrokeWidth(2);//设置裁剪网格线的宽度(我这网格设置不显示，所以没效果)
        options.setCropFrameStrokeWidth(10);//设置裁剪框的宽度
        options.setMaxScaleMultiplier(3);//设置最大缩放比例
        options.setHideBottomControls(true);//隐藏下边控制栏
        options.setShowCropGrid(false);  //设置是否显示裁剪网格
        options.setOvalDimmedLayer(true);//设置是否为圆形裁剪框
        options.setShowCropFrame(false); //设置是否显示裁剪边框(true为方形边框)
        options.setToolbarWidgetColor(Color.parseColor("#ffffff"));//标题字的颜色以及按钮颜色
        options.setDimmedLayerColor(Color.parseColor("#AA000000"));//设置裁剪外颜色
        options.setToolbarColor(Color.parseColor("#000000")); // 设置标题栏颜色
        options.setStatusBarColor(Color.parseColor("#000000"));//设置状态栏颜色
        options.setCropGridColor(Color.parseColor("#ffffff"));//设置裁剪网格的颜色
        options.setCropFrameColor(Color.parseColor("#ffffff"));//设置裁剪框的颜色
        uCrop.withOptions(options);
        uCrop.start(this);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(MyInfoActivity.this, "com.beestar.jzb.newweathercode", tempFile);
                        startCrop(contentUri);
                    } else {
                        startCrop(Uri.fromFile(tempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    startCrop(uri);
                }
                break;
            case UCrop.REQUEST_CROP:     //调用剪裁后返回
                if(resultCode==RESULT_OK){
                    Uri resultUri=UCrop.getOutput(intent);
                    Log.i(TAG, "onActivityResult: -------裁剪返回ok");
                    try {
                        Bitmap image = BitmapFactory.decodeStream(getContentResolver().openInputStream(resultUri));
                        //设置到ImageView上
                        mImageicon.setImageBitmap(image);
                        if (image!=null){
                            try {
                                saveFile(image,"weather_icon");
                                Log.i(TAG, "onActivityResult: 去保存");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }else {
                            Log.i(TAG, "onActivityResult: 空 不保存");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.i(TAG, "onActivityResult: -------裁剪返回false");
                }
                break;
        }
    }
    /**
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public void saveFile(Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(Keyparameter.PICURL);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File(Keyparameter.PICURL + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 10, bos);
        Uri uri = Uri.fromFile(myCaptureFile);
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        bos.flush();
        bos.close();
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
            Log.i("info", "------读取图片null-------");
        }
        return bm;
    }
}
