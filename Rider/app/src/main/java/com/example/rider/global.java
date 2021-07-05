package com.example.rider;

import android.app.Application;

import java.util.ArrayList;

public class global extends Application {
    private ArrayList<user08_basket_data> arr = new ArrayList<user08_basket_data>();
    private String address;
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<user08_basket_data> getArr() {
        return arr;
    }

    public void setArr(ArrayList<user08_basket_data> arr) {
        this.arr = arr;
    }
}
