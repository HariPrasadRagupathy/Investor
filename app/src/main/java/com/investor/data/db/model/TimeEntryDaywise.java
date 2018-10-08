package com.investor.data.db.model;

/**
 * Created by Vyancorp on 2/14/2018.
 */

public class TimeEntryDaywise {

    String date;
    String playedsec;

    public TimeEntryDaywise(String date, String playedsec) {
        this.date = date;
        this.playedsec = playedsec;
    }

    public TimeEntryDaywise() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlayedsec() {
        return playedsec;
    }

    public void setPlayedsec(String playedsec) {
        this.playedsec = playedsec;
    }
}
