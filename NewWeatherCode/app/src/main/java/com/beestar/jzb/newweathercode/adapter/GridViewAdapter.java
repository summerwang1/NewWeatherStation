package com.beestar.jzb.newweathercode.adapter;

/**
 * Created by guzhen on 2018/4/23.
 */

import android.app.Activity;
import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.beestar.jzb.newweathercode.R;
import com.beestar.jzb.newweathercode.bean.DeviceBean;
import com.beestar.jzb.newweathercode.utils.Keyparameter;
import com.beestar.jzb.newweathercode.utils.ScreenHeight;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<DeviceBean> list;
    LayoutInflater layoutInflater;


    public GridViewAdapter(Context context, List<DeviceBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size() +1;//注意此处
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler=null;
        if (convertView==null){
            convertView = layoutInflater.inflate(R.layout.layout_grid_item, null);
            viewHodler=new ViewHodler();
            initView(viewHodler,convertView);

            convertView.setTag(viewHodler);
        }else {
            viewHodler=(ViewHodler) convertView.getTag();
        }

        if (position<list.size()){
            viewHodler.mAddTjRela.setVisibility(View.GONE);

            Log.i("info", "getView: -----------33333-------------"+list.size());
            viewHodler.mNameStation.setText(list.get(position).getSecondName());//名称
            if (list.get(position).getType()== Keyparameter.BLETYPECODE){//随身气象站
                viewHodler.mItemSs.setImageResource(R.mipmap.qxz_sy_tb_ssqxz);
                viewHodler.mNameStation.setText(list.get(position).getName());
                if (list.get(position).getIsConn()){
                    viewHodler.conn_item.setImageResource(R.mipmap.qxz_sy_tb_sbxh2);
                }else {
                    viewHodler.conn_item.setImageResource(R.mipmap.qxz_sy_tb_sbxh1);
                }
            }else if (list.get(position).getType()== Keyparameter.WIFITYPECODE){//桌面气象站
                viewHodler.mItemSs.setImageResource(R.mipmap.qxz_sy_tb_zmqxz);
                if (list.get(position).getIsConn()){
                    viewHodler.conn_item.setImageResource(R.mipmap.qxz_sy_tb_sbxh2);
                }else {
                    viewHodler.conn_item.setImageResource(R.mipmap.qxz_sy_tb_sbxh1);
                }
            }

        }else {
            viewHodler.mStationinfoRela.setVisibility(View.GONE);
        }

        return convertView;
    }

    private void initView(ViewHodler viewHodler, View itemView) {


        viewHodler.conn_item= ((ImageView) itemView.findViewById(R.id.conn_item));
        viewHodler.mItemSs = (ImageView) itemView.findViewById(R.id.ss_item);
        viewHodler.mNameStation = (TextView) itemView.findViewById(R.id.station_name);

        viewHodler.mStationinfoRela = (RelativeLayout) itemView.findViewById(R.id.rela_stationinfo);
        viewHodler.mAddTjRela = (RelativeLayout) itemView.findViewById(R.id.rela_add_tj);

        viewHodler.rela_all= ((RelativeLayout) itemView.findViewById(R.id.rela_all));

        viewHodler.mItemSs.getLayoutParams().height=(int)(ScreenHeight.getDeviceHeight((Activity) context)*(0.07));
        viewHodler.rela_all.getLayoutParams().height=(int)(ScreenHeight.getDeviceHeight((Activity) context)*(0.18)) ;
        viewHodler.rela_all.getLayoutParams().width=(int)(ScreenHeight.getDeviceWidth((Activity) context)*(0.3)) ;

    }
    class ViewHodler{
        private RelativeLayout rela_all;

        private ImageView conn_item;
        private ImageView mItemSs;
        private TextView mNameStation;
        private RelativeLayout mStationinfoRela;
        private RelativeLayout mAddTjRela;
    }
    public void add(List<DeviceBean> data){

        list.addAll(data);
//        notifyDataSetChanged();
    }
    public void clear(){
        list.clear();
//        notifyDataSetChanged();
    }

}
