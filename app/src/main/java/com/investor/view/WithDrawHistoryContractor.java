package com.investor.view;

import com.investor.models.DepositHistoryResponse;
import com.investor.models.WithDrawHistoryResponse;

import java.util.ArrayList;

public interface WithDrawHistoryContractor {


    interface view
    {
        void withdrawDetails( ArrayList<WithDrawHistoryResponse.Detail> withdrawDetails);

        void withdrawModeStatus(int mode, int status,String message);
    }


    interface presenter
    {
        public void getWithdrawHistory();


        public void cancelWithdraw(String orderId);
    }

}
