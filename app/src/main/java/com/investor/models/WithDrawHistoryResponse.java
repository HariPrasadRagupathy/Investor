package com.investor.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithDrawHistoryResponse {

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
    public WithDrawHistoryResponse() {
    }

    /**
     *
     * @param message
     * @param details
     * @param status
     */
    public WithDrawHistoryResponse(String message, List<Detail> details, Integer status) {
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
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("payment_mode")
        @Expose
        private String paymentMode;
        @SerializedName("plan_id")
        @Expose
        private String planId;
        @SerializedName("plan_name")
        @Expose
        private String planName;
        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("createdate")
        @Expose
        private String createdate;

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
         * @param paymentMode
         * @param status
         * @param userId
         * @param createdate
         * @param planId
         * @param date
         * @param orderId
         */
        public Detail(String id, String userId, String amount, String date, String paymentMode, String planId, String planName, String orderId, String status, String createdate) {
            super();
            this.id = id;
            this.userId = userId;
            this.amount = amount;
            this.date = date;
            this.paymentMode = paymentMode;
            this.planId = planId;
            this.planName = planName;
            this.orderId = orderId;
            this.status = status;
            this.createdate = createdate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
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

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
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

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

    }

}