package com.beestar.jzb.newweathercode.ui.login_rigister;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.beestar.jzb.newweathercode.MyAPP;
import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.adapter.FragmentViewPagerAdapter;
import com.beestar.jzb.newweathercode.bean.Registe_UserInfo;
import com.beestar.jzb.newweathercode.bean.ReturnBean;
import com.beestar.jzb.newweathercode.fragment.StepFourFragment;
import com.beestar.jzb.newweathercode.fragment.StepOneFragment;
import com.beestar.jzb.newweathercode.fragment.StepThreeFragment;
import com.beestar.jzb.newweathercode.fragment.StepTwoFragment;
import com.beestar.jzb.newweathercode.ui.BaseActivity;
import com.beestar.jzb.newweathercode.utils.Keyparameter;
import com.beestar.jzb.newweathercode.utils.URL;
import com.beestar.jzb.newweathercode.view.MyViewPager;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.util.ArrayList;
import java.util.List;

public class NewResgiteActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mBack;
    private MyViewPager mNewResgiteViewPage;
    private Button mPrevBtn;
    private Button mNextBtn;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;
    private StepOneFragment stepOneFragmentTel;
    private StepTwoFragment stepTwoFragmentSms;
    private StepThreeFragment stepThreeFragmentPwd;
    private StepFourFragment stepFourFragmentName;
    private List<android.support.v4.app.Fragment> fragmentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_resgite);
        initView();

    }

    private void initView() {
        mBack = (ImageView) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mNewResgiteViewPage = (MyViewPager) findViewById(R.id.viewPage_newResgite);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mNextBtn.setOnClickListener(this);

        stepOneFragmentTel = StepOneFragment.newInstance();
        stepTwoFragmentSms = StepTwoFragment.newInstance();
        stepThreeFragmentPwd = StepThreeFragment.newInstance("请设置您的密码");
        stepFourFragmentName = StepFourFragment.newInstance();
        fragmentList.add(stepOneFragmentTel);
        fragmentList.add(stepTwoFragmentSms);
        fragmentList.add(stepThreeFragmentPwd);
        fragmentList.add(stepFourFragmentName);
        mNewResgiteViewPage.setOffscreenPageLimit(4);

        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        mNewResgiteViewPage.setAdapter(fragmentViewPagerAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                // TODO 18/04/20
                Log.i("info", "1onClick: "+fragmentViewPagerAdapter.getPostion());
                onGetPostionViewPagerAdapter(1,fragmentViewPagerAdapter.getPostion());
                break;
            case R.id.nextBtn:
                // TODO 18/04/20
                onGetPostionViewPagerAdapter(0,fragmentViewPagerAdapter.getPostion());
                break;
            default:
                break;
        }
    }
    public void onGetPostionViewPagerAdapter(int type,int postion){
        if (type==0){
            switch (postion){
                case 0:
                    if (stepOneFragmentTel.getDataTel()!=null){
                        mNewResgiteViewPage.setCurrentItem(1);
                        stepTwoFragmentSms.setTel(stepOneFragmentTel.getDataTel());
                    }else {
                        Toast.makeText(NewResgiteActivity.this,"注册手机号不可为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1:
                    if (stepTwoFragmentSms.getDataSms()!=null){
                        mNewResgiteViewPage.setCurrentItem(2);
                    }else {
                        Toast.makeText(NewResgiteActivity.this,"验证码不可为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    if (stepThreeFragmentPwd.getDataPwd()!=null){
                        mNewResgiteViewPage.setCurrentItem(3);
                    }else {
                        Toast.makeText(NewResgiteActivity.this,"注册密码不可为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 3:
                    if (stepFourFragmentName.getDataName()!=null){
                        //注册 账号
                        resgite(stepOneFragmentTel.getDataTel(),stepTwoFragmentSms.getDataSms(),stepThreeFragmentPwd.getDataPwd()
                        ,stepFourFragmentName.getDataName());
                    }else {
                        Toast.makeText(NewResgiteActivity.this,"注册姓名不可为空",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }else if (type==1){
            switch (postion){
                case 0:
                    finish();
                    break;
                case 1:
                    mNewResgiteViewPage.setCurrentItem(0);
                    break;
                case 2:
                    mNewResgiteViewPage.setCurrentItem(1);
                    break;
                case 3:
                    mNewResgiteViewPage.setCurrentItem(2);
                    break;
            }
        }
    }

    /**
     * 注册
     * @param tel 电话号码
     * @param sms 验证码
     * @param pwd 密码
     * @param name 姓名
     */
    public void resgite(String tel,String sms,String pwd,String name){
        MyAPP.getContext().getMyOkHttp().post()
                .url(URL.url+URL.url_register)
                .tag(this)
                .jsonParams(new Gson().toJson(new Registe_UserInfo(tel,name,pwd,pwd,"0",sms)))
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        ReturnBean returnBean = new Gson().fromJson(response, ReturnBean.class);
                        if (returnBean.getRtn_code()==0){
//                            startActivity(new Intent(NewResgiteActivity.this,RegisterThreeActivity.class));
                            Toast.makeText(NewResgiteActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }else if(returnBean.getRtn_code()== Keyparameter.CODE_HAVE_REGISTE) {
                            Toast.makeText(NewResgiteActivity.this,Keyparameter.CODE_HAVE_REGISTE_STR,Toast.LENGTH_SHORT).show();
                        }else if(returnBean.getRtn_code()== Keyparameter.CODE_PWD_PADERNOSAME) {
                            Toast.makeText(NewResgiteActivity.this,Keyparameter.CODE_PWD_PADERNOSAME_STR,Toast.LENGTH_SHORT).show();
                        }else if(returnBean.getRtn_code()== Keyparameter.CODE_USER_ERROR) {
                            Toast.makeText(NewResgiteActivity.this,Keyparameter.CODE_USER_ERROR_STR,Toast.LENGTH_SHORT).show();
                        }else if(returnBean.getRtn_code()== Keyparameter.CODE_MSG_ERROR) {
                            Toast.makeText(NewResgiteActivity.this,Keyparameter.CODE_MSG_ERROR_STR,Toast.LENGTH_SHORT).show();
                        }else if(returnBean.getRtn_code()== Keyparameter.CODE_HAVE_REGISTE) {
                            Toast.makeText(NewResgiteActivity.this,Keyparameter.CODE_HAVE_REGISTE_STR,Toast.LENGTH_SHORT).show();
                        }else if(returnBean.getRtn_code()== Keyparameter.CODE_ERROR_SERVICE) {
                            Toast.makeText(NewResgiteActivity.this,Keyparameter.CODE_ERROR_SERVICE_STR,Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(NewResgiteActivity.this,"数据请求发生错误，请稍后重试...",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.i("jzb", "onFailure:注册接口 ");
                    }
                });
    }
}
