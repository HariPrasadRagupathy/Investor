package com.investor.data.db.model;

/**
 * Created by Vyancorp on 2/13/2018.
 */

public class TimeEntry {
    int id;
    String state;
    String datetime;
    String date;
    long intime;



    public TimeEntry() {
    }

    public TimeEntry(int id, String state, String datetime, String date, long intime) {
        this.id = id;
        this.state = state;
        this.datetime = datetime;
        this.date = date;
        this.intime = intime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getIntime() {
        return intime;
    }

    public void setIntime(long intime) {
        this.intime = intime;
    }
}
