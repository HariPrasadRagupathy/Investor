package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;


import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.models.InvestmentPlans;
import com.investor.view.NewInvestContractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewInvestPresenter implements NewInvestContractor.presenter {

    NewInvestContractor.view investView;
    Context context;
    ArrayList<InvestmentPlans.Detail> plandetails;

    public NewInvestPresenter(NewInvestContractor.view investView, Context context) {
        this.investView = investView;
        this.context = context;
    }


    @Override
    public void investmentPlans() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<InvestmentPlans> call = apiService.getInvestPlans("plan","aW52ZXN0bWVudF8yMDE4");

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<InvestmentPlans>() {
            @Override
            public void onResponse(Call<InvestmentPlans> call, Response<InvestmentPlans> response) {
                if(response.body().getStatus()==1)
                {
                    progressDialog.dismiss();
                    List<InvestmentPlans.Detail> details = response.body().getDetails();
                    plandetails = new ArrayList<InvestmentPlans.Detail>();
                    plandetails.addAll(details);
                    investView.investmentPlans(plandetails);

                    //loginView.loginSuccess();
                }
                else
                {
                    progressDialog.dismiss();
                   // loginView.loginFailed();
                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<InvestmentPlans> call, Throwable t) {

            }
        });

    }

    @Override
    public void calculateMonthtlyProfit(double amount, double interestrate) {
       Log.e("amount",(double)amount/(double)100+"");
        double monthly_profit = ((double)amount/(double)100)*interestrate;
        investView.montlyProfit(monthly_profit);

    }
}
