package com.investor.data.db.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nandhu on 11/27/2017.
 */

public class Ads_Details {

    int id=0;
    @SerializedName("ads_id")
    String adId;
    @SerializedName("ads_name")
    String adName;
    String playedDate;
    String playedTime;
    @SerializedName("no_of_times")
    int totalPlayCount =0;

    public int getTotalPlayCount() {
        return totalPlayCount;
    }

    public void setTotalPlayCount(int totalPlayCount) {
        this.totalPlayCount = totalPlayCount;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(String playedDate) {
        this.playedDate = playedDate;
    }

    public String getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(String playedTime) {
        this.playedTime = playedTime;
    }
}
