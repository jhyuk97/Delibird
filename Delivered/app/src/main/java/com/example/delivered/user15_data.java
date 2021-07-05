package com.example.delivered;

public class user15_data {
    String user15_name;
    String user15_star;
    String user15_img;
    String data;

    public user15_data(String user15_name, String user15_img, String data) {
        this.user15_name = user15_name;
        this.user15_img = user15_img;
        this.data = data;
    }

    public String getData(){
        return  data;
    }

    public String getUser15_name() {
        return user15_name;
    }

    public void setUser15_name(String user15_name) {
        this.user15_name = user15_name;
    }

    public String getUser15_star() {
        return user15_star;
    }

    public void setUser15_star(String user15_star) {
        this.user15_star = user15_star;
    }

    public String getUser15_img() {
        return user15_img;
    }

    public void setUser15_img(String user15_img) {
        this.user15_img = user15_img;
    }
}
