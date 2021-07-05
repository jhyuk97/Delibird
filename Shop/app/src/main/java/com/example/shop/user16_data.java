package com.example.shop;

public class user16_data {
    String user16_name;
    String user16_detail;
    String data;
    String number;

    public user16_data(String user16_name, String user16_detail, String data, String number) {
        this.user16_name = user16_name;
        this.user16_detail = user16_detail;
        this.data = data;
        this.number = number;
    }

    public String getNumber(){return number;}

    public  String getData(){
        return  data;
    }

    public String getUser16_name() {
        return user16_name;
    }

    public void setUser16_name(String user16_name) {
        this.user16_name = user16_name;
    }

    public String getUser16_detail() {
        return user16_detail;
    }

    public void setUser16_detail(String user16_detail) {
        this.user16_detail = user16_detail;
    }
}
