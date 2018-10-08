package com.investor.data.db.model;

/**
 * Created by Nandhu on 5/12/2017.
 */

public class IsDataAvailableTbl {

    int count;
    Boolean isAvailable;

    public IsDataAvailableTbl() {
        this.count = 0;
        this.isAvailable = false;
    }


    public IsDataAvailableTbl(int count, Boolean isAvailable) {
        this.count = count;
        this.isAvailable = isAvailable;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
