package com.trecoops.onway.Model;

public class Service {
    private String serviceName;
    private boolean isChecked = false;
    private int resID;

    public Service(String serviceName, int resID) {
        this.serviceName = serviceName;
        this.resID = resID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }
}
