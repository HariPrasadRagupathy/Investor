package com.investor.models;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithDrawResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private Details details;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     *
     */
    public WithDrawResponse() {
    }

    /**
     *
     * @param message
     * @param details
     * @param status
     */
    public WithDrawResponse(String message, Details details, Integer status) {
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

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public class Details {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("order_id")
        @Expose
        private String orderId;

        /**
         * No args constructor for use in serialization
         *
         */
        public Details() {
        }

        /**
         *
         * @param message
         * @param orderId
         */
        public Details(String message, String orderId) {
            super();
            this.message = message;
            this.orderId = orderId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

    }

}
