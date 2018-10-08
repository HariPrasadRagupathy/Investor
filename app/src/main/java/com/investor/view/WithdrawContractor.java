package com.investor.view;

import com.investor.models.DepositHistoryResponse;

import java.util.ArrayList;

public interface WithdrawContractor {

    interface view
    {

        void deposit_details(ArrayList<DepositHistoryResponse.Detail> details);

        void withdrawStatus(int status,String msg, String withdrawId);

        void withdrawCalculate(String investment_balance,String cancellation_charge);

    }


    interface presenter
    {
        void getInvestDetails();

        void withdrawAmount(String planId,String orderId, String Amount);

        void calculateWithdraw(String amount);

    }
}
