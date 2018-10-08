package com.investor.view;

import com.investor.models.DepositHistoryResponse;
import com.investor.models.EarningsResponse;

import java.util.ArrayList;

public interface EarningsContractor {

    interface view
    {
        void earningDetails( ArrayList<EarningsResponse.Detail> earningDetails);
    }


    interface presenter
    {

        public void getEarnings();

    }


}
