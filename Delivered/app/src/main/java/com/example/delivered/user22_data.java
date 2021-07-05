package com.example.delivered;

public class user22_data {
    String title;
    String date;
    String number;

    public user22_data(String title, String date, String number) {
        this.title = title;
        this.date = date;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
