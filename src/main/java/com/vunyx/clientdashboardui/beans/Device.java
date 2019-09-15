package com.vunyx.clientdashboardui.beans;

public class Device {

    private String id;
    private String imsi;
    private String devicetype;
    private String deviceowner;
    private String location;

    public Device(){

    }

    public Device(String id, String imsi, String devicetype) {
        this.id = id;
        this.imsi = imsi;
        this.devicetype = devicetype;
    }

    public Device(String id, String imsi, String devicetype, String deviceowner, String location) {
        this.id = id;
        this.imsi = imsi;
        this.devicetype = devicetype;
        this.deviceowner = deviceowner;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDeviceowner() {
        return deviceowner;
    }

    public void setDeviceowner(String deviceowner) {
        this.deviceowner = deviceowner;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toJsonString() {
        return "{" +
                "id='" + id + '\'' +
                ", imsi='" + imsi + '\'' +
                ", devicetype='" + devicetype + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", imsi='" + imsi + '\'' +
                ", devicetype='" + devicetype + '\'' +
                ", deviceowner='" + deviceowner + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
