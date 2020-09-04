package com.trecoops.onway.Model;

public class Work {
    public boolean courier;
    public boolean food;
    public boolean grocery;
    public boolean pharmacy;
    public boolean taxi;

    public Work(boolean courier, boolean food, boolean grocery, boolean pharmacy, boolean taxi) {
        this.courier = courier;
        this.food = food;
        this.grocery = grocery;
        this.pharmacy = pharmacy;
        this.taxi = taxi;
    }

    public boolean isCourier() {
        return courier;
    }

    public void setCourier(boolean courier) {
        this.courier = courier;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isGrocery() {
        return grocery;
    }

    public void setGrocery(boolean grocery) {
        this.grocery = grocery;
    }

    public boolean isPharmacy() {
        return pharmacy;
    }

    public void setPharmacy(boolean pharmacy) {
        this.pharmacy = pharmacy;
    }

    public boolean isTaxi() {
        return taxi;
    }

    public void setTaxi(boolean taxi) {
        this.taxi = taxi;
    }
}
