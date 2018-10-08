package com.investor.view;

import java.util.List;

public interface ModePaymentContractor {

    interface view
    {

        void paymentModeList(List<String> paymentListResponses);

        void paymentModeStatus(int mode, int status,String message);

    }


    interface presenter
    {

        void getPaymentModeList();

        void uploadProof(String orderId,String paymentMethod,String referenceNo, String proofImage);

        void cancelDeposit(String orderId);

    }



}
