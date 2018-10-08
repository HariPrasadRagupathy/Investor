package com.investor.view;

import com.investor.models.InvestmentPlans;

import java.util.ArrayList;


public interface NewInvestContractor {

    interface view
    {
        void investmentPlans( ArrayList<InvestmentPlans.Detail> planDetails);
        void montlyProfit(double amount);
    }

    interface presenter
    {
        void investmentPlans();
        void calculateMonthtlyProfit(double amount, double interestrate);
    }

}
