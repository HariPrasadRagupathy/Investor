package com.investor.view;

import com.investor.models.DepositHistoryResponse;


import java.util.ArrayList;

public interface DepositHistoryContractor {

    interface view {
        void depositDetails( ArrayList<DepositHistoryResponse.Detail> depositDetails);

        void paymentModeStatus(int mode, int status,String message);
    }


    interface presenter {
        void getDepositHistory();

        void cancelDeposit(String orderId);
    }
}
