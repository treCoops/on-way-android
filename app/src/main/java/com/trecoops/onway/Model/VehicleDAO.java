package com.trecoops.onway.Model;

public class VehicleDAO {
    public String cat_name;
    public String category;
    public String vehicle_man;
    public String vehicle_model;
    public String vehicle_no;

    public VehicleDAO() {
    }

    public VehicleDAO(String cat_name, String category, String vehicle_man, String vehicle_model, String vehicle_no) {
        this.cat_name = cat_name;
        this.category = category;
        this.vehicle_man = vehicle_man;
        this.vehicle_model = vehicle_model;
        this.vehicle_no = vehicle_no;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVehicle_man() {
        return vehicle_man;
    }

    public void setVehicle_man(String vehicle_man) {
        this.vehicle_man = vehicle_man;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }
}
