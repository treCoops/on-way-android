package com.trecoops.onway.Model;

import java.util.HashMap;
import java.util.List;

public class User {

    public String account_type;
    public int blocked_status;
    public String contact_no;
    public String email;
    public String first_name;
    public String last_name;
    public LiveLocation live_loc;
    public int main_status;
    public String profile_pic_name;
    public String profile_pic_url;
    public String uid;
    public VehicleDAO vehicle;
    public Work work;

    public User(String account_type, int blocked_status, String contact_no, String email, String first_name, String last_name, LiveLocation live_loc, int main_status, String profile_pic_name, String profile_pic_url, String uid, VehicleDAO vehicle, Work work) {
        this.account_type = account_type;
        this.blocked_status = blocked_status;
        this.contact_no = contact_no;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.live_loc = live_loc;
        this.main_status = main_status;
        this.profile_pic_name = profile_pic_name;
        this.profile_pic_url = profile_pic_url;
        this.uid = uid;
        this.vehicle = vehicle;
        this.work = work;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public int isBlocked_status() {
        return blocked_status;
    }

    public void setBlocked_status(int blocked_status) {
        this.blocked_status = blocked_status;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LiveLocation getLive_loc() {
        return live_loc;
    }

    public void setLive_loc(LiveLocation live_loc) {
        this.live_loc = live_loc;
    }

    public int getMain_status() {
        return main_status;
    }

    public void setMain_status(int main_status) {
        this.main_status = main_status;
    }

    public String getProfile_pic_name() {
        return profile_pic_name;
    }

    public void setProfile_pic_name(String profile_pic_name) {
        this.profile_pic_name = profile_pic_name;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public VehicleDAO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDAO vehicle) {
        this.vehicle = vehicle;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
