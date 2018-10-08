package com.investor.view;

public interface DashboardContractor {

    interface view
    {
        void earnings_details(String total_investment,String total_earnnigs);
    }

    interface presnter
    {
        void getEarnings();

        void checkFCMToken();
    }


}
