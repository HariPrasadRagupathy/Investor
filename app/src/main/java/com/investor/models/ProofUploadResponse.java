package com.investor.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProofUploadResponse {

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
    public ProofUploadResponse() {
    }

    /**
     *
     * @param message
     * @param details
     * @param status
     */
    public ProofUploadResponse(String message, List<Detail> details, Integer status) {
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
        @SerializedName("reference_number")
        @Expose
        private String referenceNumber;
        @SerializedName("proof")
        @Expose
        private String proof;
        @SerializedName("payment_mode")
        @Expose
        private String paymentMode;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("interest_date")
        @Expose
        private String interestDate;
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
         * @param paymentMode
         * @param planName
         * @param status
         * @param interest
         * @param referenceNumber
         * @param userId
         * @param createdate
         * @param proof
         * @param interestDate
         * @param planId
         * @param date
         */
        public Detail(String id, String userId, String planId, String planName, String interest, String amount, String referenceNumber, String proof, String paymentMode, String date, String interestDate, String status, String createdate) {
            super();
            this.id = id;
            this.userId = userId;
            this.planId = planId;
            this.planName = planName;
            this.interest = interest;
            this.amount = amount;
            this.referenceNumber = referenceNumber;
            this.proof = proof;
            this.paymentMode = paymentMode;
            this.date = date;
            this.interestDate = interestDate;
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

        public String getReferenceNumber() {
            return referenceNumber;
        }

        public void setReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
        }

        public String getProof() {
            return proof;
        }

        public void setProof(String proof) {
            this.proof = proof;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getInterestDate() {
            return interestDate;
        }

        public void setInterestDate(String interestDate) {
            this.interestDate = interestDate;
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