package com.example.rider;

public class user13_data {
    String num;
    String name;
    String detail;
    String image;

    public user13_data(String name, String detail, String image, String num) {
        this.num = num;
        this.name = name;
        this.detail = detail;
        this.image = image;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
