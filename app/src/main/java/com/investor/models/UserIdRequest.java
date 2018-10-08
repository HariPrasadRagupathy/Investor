package com.investor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserIdRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserIdRequest() {
    }

    /**
     *
     * @param userId
     */
    public UserIdRequest(String userId) {
        super();
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}