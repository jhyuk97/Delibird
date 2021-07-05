package com.example.rider;

public class user12_grade_data {
    public String name;
    public String phone;
    public String grade;

    public user12_grade_data(String name, String phone, String grade) {
        this.name = name;
        this.phone = phone;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
