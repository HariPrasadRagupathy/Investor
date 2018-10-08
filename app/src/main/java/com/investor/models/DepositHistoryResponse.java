package com.investor.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepositHistoryResponse {

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
    public DepositHistoryResponse() {
    }

    /**
     *
     * @param message
     * @param details
     * @param status
     */
    public DepositHistoryResponse(String message, List<Detail> details, Integer status) {
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
        @SerializedName("plan_id")
        @Expose
        private String planId;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("interest")
        @Expose
        private String interest;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("status")
        @Expose
        private String status;

        /**
         * No args constructor for use in serialization
         *
         */
        public Detail() {
        }

        /**
         *
         * @param amount
         * @param id
         * @param planName
         * @param status
         * @param interest
         * @param planId
         * @param date
         */
        public Detail(String id, String planId, String planName, String interest, String amount, String date, String status) {
            super();
            this.id = id;
            this.planId = planId;
            this.planName = planName;
            this.interest = interest;
            this.amount = amount;
            this.date = date;
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlanId() {
            return planId;
        }

        public void setPlanId(String planId) {
            this.planId = planId;
        }

        public String getPlanName() {
            return planName;
        }

        public void setPlanName(String planName) {
            this.planName = planName;
        }

        public String getInterest() {
            return interest;
        }

        public void setInterest(String interest) {
            this.interest = interest;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

}

