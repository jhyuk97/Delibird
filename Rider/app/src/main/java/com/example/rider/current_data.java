package com.example.rider;

public class current_data {
    String callNumber;
    String marketName;

    public current_data(String callNumber, String marketName) {
        this.callNumber = callNumber;
        this.marketName = marketName;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
}
