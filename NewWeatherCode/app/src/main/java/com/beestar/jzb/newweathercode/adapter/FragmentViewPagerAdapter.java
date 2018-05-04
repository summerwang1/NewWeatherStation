package com.beestar.jzb.newweathercode.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jzb on 2018/4/8.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private int viewPagePosition;
    private List<Fragment> fragmentList;
    public FragmentViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;

    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        Log.i("jzb", "setPrimaryItem: ----"+position);
        viewPagePosition=position;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public int getPostion(){
        return viewPagePosition;
    }
    public void addFragmentData(List<Fragment> fmData){
        fragmentList.clear();
        fragmentList.addAll(fmData);
        notifyDataSetChanged();
    }
}
