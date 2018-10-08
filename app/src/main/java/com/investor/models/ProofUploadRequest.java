package com.investor.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProofUploadRequest {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("reference_number")
    @Expose
    private String referenceNumber;
    @SerializedName("proof")
    @Expose
    private String proof;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProofUploadRequest() {
    }

    /**
     *
     * @param paymentMode
     * @param referenceNumber
     * @param proof
     * @param orderId
     */
    public ProofUploadRequest(String orderId, String referenceNumber, String proof, String paymentMode) {
        super();
        this.orderId = orderId;
        this.referenceNumber = referenceNumber;
        this.proof = proof;
        this.paymentMode = paymentMode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

}