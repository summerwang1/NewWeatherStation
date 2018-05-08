package com.beestar.jzb.newweathercode.bean;

/**
 * Created by jzb on 2018/5/8.
 */

public class MessageEvent {
    private String location;

    public MessageEvent(String location) {
        this.location = location;
    }

    public String getMessage() {
        return location;
    }

    public void setMessage(String location) {
        this.location = location;
    }
}
