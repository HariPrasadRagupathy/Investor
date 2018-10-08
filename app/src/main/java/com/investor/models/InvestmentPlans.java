package com.investor.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InvestmentPlans {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     *
     */
    public InvestmentPlans() {
    }

    /**
     *
     * @param message
     * @param details
     * @param status
     */
    public InvestmentPlans(String message, List<Detail> details, Integer status) {
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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("min_value")
        @Expose
        private String minValue;
        @SerializedName("max_value")
        @Expose
        private String maxValue;
        @SerializedName("min_duration")
        @Expose
        private String minDuration;
        @SerializedName("max_duration")
        @Expose
        private String maxDuration;
        @SerializedName("interest")
        @Expose
        private String interest;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("create_date")
        @Expose
        private String createDate;

        /**
         * No args constructor for use in serialization
         *
         */
        public Detail() {
        }

        /**
         *
         * @param minDuration
         * @param id
         * @param planName
         * @param status
         * @param interest
         * @param minValue
         * @param maxDuration
         * @param createDate
         * @param maxValue
         */
        public Detail(String id, String planName, String minValue, String maxValue, String minDuration, String maxDuration, String interest, String status, String createDate) {
            super();
            this.id = id;
            this.planName = planName;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.minDuration = minDuration;
            this.maxDuration = maxDuration;
            this.interest = interest;
            this.status = status;
            this.createDate = createDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getMinValue() {
            return minValue;
        }

        public void setMinValue(String minValue) {
            this.minValue = minValue;
        }

        public String getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(String maxValue) {
            this.maxValue = maxValue;
        }

        public String getMinDuration() {
            return minDuration;
        }

        public void setMinDuration(String minDuration) {
            this.minDuration = minDuration;
        }

        public String getMaxDuration() {
            return maxDuration;
        }

        public void setMaxDuration(String maxDuration) {
            this.maxDuration = maxDuration;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

    }

}


