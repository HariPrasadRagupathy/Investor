package com.investor.models;

public class WithdrawStatusList {


    String status_id;
    String amount;
    String status_one = "";
    String status_two = "";
    int status_mode = 1;


    public WithdrawStatusList(String status_id, String amount, String status_one, String status_two, int status_mode) {
        this.status_id = status_id;
        this.amount = amount;
        this.status_one = status_one;
        this.status_two = status_two;
        this.status_mode = status_mode;
    }

    public WithdrawStatusList() {
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus_one() {
        return status_one;
    }

    public void setStatus_one(String status_one) {
        this.status_one = status_one;
    }

    public String getStatus_two() {
        return status_two;
    }

    public void setStatus_two(String status_two) {
        this.status_two = status_two;
    }

    public int getStatus_mode() {
        return status_mode;
    }

    public void setStatus_mode(int status_mode) {
        this.status_mode = status_mode;
    }
}
