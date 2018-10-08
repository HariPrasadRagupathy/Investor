package com.investor.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EarningsResponse {

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
    public EarningsResponse() {
    }

    /**
     *
     * @param message
     * @param details
     * @param status
     */
    public EarningsResponse(String message, List<Detail> details, Integer status) {
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

        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("total_earnings")
        @Expose
        private Integer totalEarnings;
        @SerializedName("last_month_earnings")
        @Expose
        private Integer lastMonthEarnings;
        @SerializedName("last_year_earnings")
        @Expose
        private Integer lastYearEarnings;

        /**
         * No args constructor for use in serialization
         *
         */
        public Detail() {
        }

        /**
         *
         * @param amount
         * @param lastYearEarnings
         * @param lastMonthEarnings
         * @param totalEarnings
         */
        public Detail(String amount, Integer totalEarnings, Integer lastMonthEarnings, Integer lastYearEarnings) {
            super();
            this.amount = amount;
            this.totalEarnings = totalEarnings;
            this.lastMonthEarnings = lastMonthEarnings;
            this.lastYearEarnings = lastYearEarnings;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public Integer getTotalEarnings() {
            return totalEarnings;
        }

        public void setTotalEarnings(Integer totalEarnings) {
            this.totalEarnings = totalEarnings;
        }

        public Integer getLastMonthEarnings() {
            return lastMonthEarnings;
        }

        public void setLastMonthEarnings(Integer lastMonthEarnings) {
            this.lastMonthEarnings = lastMonthEarnings;
        }

        public Integer getLastYearEarnings() {
            return lastYearEarnings;
        }

        public void setLastYearEarnings(Integer lastYearEarnings) {
            this.lastYearEarnings = lastYearEarnings;
        }

    }

}