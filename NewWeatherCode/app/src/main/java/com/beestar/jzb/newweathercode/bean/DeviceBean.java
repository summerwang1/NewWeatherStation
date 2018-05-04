package com.beestar.jzb.newweathercode.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by JZB on 2018/4/19.
 */
@Entity
public class DeviceBean {


    @Id
    private Long id;
    private String name;
    private String secondName;
    private String mac;
    private boolean isConn;
    private boolean isChoose;
    private int type;//1代表随身气象站 2代表桌面气象站

    //随身气象站
    public DeviceBean(String name, String mac, boolean isConn, boolean isChoose, int type) {
        this.name = name;
        this.mac = mac;
        this.isConn = isConn;
        this.isChoose = isChoose;
        this.type = type;
    }

    public DeviceBean(String name, String secondName, String mac, boolean isConn, boolean isChoose, int type) {
        this.name = name;
        this.secondName = secondName;
        this.mac = mac;
        this.isConn = isConn;
        this.isChoose = isChoose;
        this.type = type;
    }

    public DeviceBean(String name, String secondName, String mac, boolean isConn, boolean isChoose) {
        this.name = name;
        this.secondName = secondName;
        this.mac = mac;
        this.isConn = isConn;
        this.isChoose = isChoose;
    }

    public boolean getIsChoose() {
        return this.isChoose;
    }
    public void setIsChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }
    public boolean getIsConn() {
        return this.isConn;
    }
    public void setIsConn(boolean isConn) {
        this.isConn = isConn;
    }
    public String getMac() {
        return this.mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }
    public String getSecondName() {
        return this.secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Generated(hash = 1317242725)
    public DeviceBean(Long id, String name, String secondName, String mac, boolean isConn,
            boolean isChoose, int type) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.mac = mac;
        this.isConn = isConn;
        this.isChoose = isChoose;
        this.type = type;
    }

    @Generated(hash = 74682814)
    public DeviceBean() {
    }
}
