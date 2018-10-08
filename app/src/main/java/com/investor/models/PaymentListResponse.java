package com.investor.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentListResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<String> details = null;
    @SerializedName("status")
    @Expose
    private Integer status;

    /**
     * No args constructor for use in serialization
     *
     */
    public PaymentListResponse() {
    }

    /**
     *
     * @param message
     * @param details
     * @param status
     */
    public PaymentListResponse(String message, List<String> details, Integer status) {
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

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}