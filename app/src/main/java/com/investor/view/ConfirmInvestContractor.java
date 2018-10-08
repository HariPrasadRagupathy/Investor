package com.investor.view;

public interface ConfirmInvestContractor {


    interface view
    {
        void sendAcknowledgement(String status,String orderId);
    }

    interface presenter
    {
        void intiateDeposit(String plan_id, String amount);
    }
}
