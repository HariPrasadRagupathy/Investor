

package com.investor.models;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class LoginResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    @Nullable
    private List<Detail> details = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     */
    public LoginResponse() {
    }

    /**
     * @param message
     * @param details
     * @param status
     */
    public LoginResponse(String message, List<Detail> details, Integer status) {
        super();
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public class Detail {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("profile_picture")
        @Expose
        private String profilePicture;
        @SerializedName("registration_status")
        @Expose
        private String registrationStatus;
        @SerializedName("total_investment")
        @Expose
        private String totalInvestment;

        /**
         * No args constructor for use in serialization
         *
         */
        public Detail() {
        }

        /**
         *
         * @param registrationStatus
         * @param totalInvestment
         * @param profilePicture
         * @param userId
         * @param userName
         */
        public Detail(String userId, String userName, String profilePicture, String registrationStatus, String totalInvestment) {
            super();
            this.userId = userId;
            this.userName = userName;
            this.profilePicture = profilePicture;
            this.registrationStatus = registrationStatus;
            this.totalInvestment = totalInvestment;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getProfilePicture() {
            return profilePicture;
        }

        public void setProfilePicture(String profilePicture) {
            this.profilePicture = profilePicture;
        }

        public String getRegistrationStatus() {
            return registrationStatus;
        }

        public void setRegistrationStatus(String registrationStatus) {
            this.registrationStatus = registrationStatus;
        }

        public String getTotalInvestment() {
            return totalInvestment;
        }

        public void setTotalInvestment(String totalInvestment) {
            this.totalInvestment = totalInvestment;
        }

    }


}