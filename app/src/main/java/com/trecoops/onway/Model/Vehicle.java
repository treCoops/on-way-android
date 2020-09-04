package com.trecoops.onway.Model;

public class Vehicle {
    public String cat_name;
    public boolean isChecked = false;
    public int resID;

    public Vehicle(String cat_name, int resID) {
        this.cat_name = cat_name;
        this.resID = resID;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
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
