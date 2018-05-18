package com.investor.models;

public class InvestmentPlans {

    String investPlanName;
    String investmentAmount;
    String investmemtROI;

    public InvestmentPlans() {
    }

    public InvestmentPlans(String investPlanName, String investmentAmount, String investmemtROI) {
        this.investPlanName = investPlanName;
        this.investmentAmount = investmentAmount;
        this.investmemtROI = investmemtROI;
    }

    public String getInvestmemtROI() {
        return investmemtROI;
    }

    public void setInvestmemtROI(String investmemtROI) {
        this.investmemtROI = investmemtROI;
    }

    public String getInvestPlanName() {
        return investPlanName;
    }

    public void setInvestPlanName(String investPlanName) {
        this.investPlanName = investPlanName;
    }

    public String getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(String investmentAmount) {
        this.investmentAmount = investmentAmount;
    }


}
