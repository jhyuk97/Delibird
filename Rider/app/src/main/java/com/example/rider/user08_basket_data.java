package com.example.rider;

import java.io.Serializable;

public class user08_basket_data implements Serializable {
    private String name;
    private String detail;
    private String money;
    private String count;
    private String number;

    public user08_basket_data(String name, String detail, String money, String count, String number) {
        this.name = name;
        this.detail = detail;
        this.money = money;
        this.count = count;
        this.number = number;
    }

    public String getNumber() { return number; }

    public void setNumber(String number){ this.number = number; }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getMoney() {
        return money;
    }

    public String getCount() {
        return count;
    }
}
