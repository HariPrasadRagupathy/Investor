package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.DepositHistoryResponse;
import com.investor.models.InvestmentPlans;
import com.investor.models.UserIdRequest;
import com.investor.models.WithDrawRequest;
import com.investor.models.WithDrawResponse;
import com.investor.view.NewInvestContractor;
import com.investor.view.WithdrawContractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithdrawPresenter implements WithdrawContractor.presenter {

    WithdrawContractor.view withdrawView;
    Context context;
    ArrayList<DepositHistoryResponse.Detail> plandetails;

    public WithdrawPresenter(WithdrawContractor.view withdrawView, Context context) {
        this.withdrawView = withdrawView;
        this.context = context;
    }


    @Override
    public void getInvestDetails() {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(SessionManager.getSessionString(context, SharedKeys.USER_ID, "0"));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DepositHistoryResponse> call = apiService.deposit_history("deposit_history", "aW52ZXN0bWVudF8yMDE4", userIdRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<DepositHistoryResponse>() {
            @Override
            public void onResponse(Call<DepositHistoryResponse> call, Response<DepositHistoryResponse> response) {
                Log.e("deposit_history", response.toString());
                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();
                    List<DepositHistoryResponse.Detail> details = response.body().getDetails();
                    if (details != null) {
                        plandetails = new ArrayList<DepositHistoryResponse.Detail>();
                        for (int i = 0; i < details.size(); i++) {
                            if (Integer.parseInt(details.get(i).getStatus()) == 1)
                                plandetails.add(details.get(i));
                        }

                        withdrawView.deposit_details(plandetails);

                    }

                } else {
                    progressDialog.dismiss();

                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<DepositHistoryResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void withdrawAmount(String planId, String orderId, String Amount) {
        WithDrawRequest withDrawRequest = new WithDrawRequest();
        withDrawRequest.setPlanId(planId);
        withDrawRequest.setOrderId(orderId);
        withDrawRequest.setAmount(Amount);
        withDrawRequest.setUserId(SessionManager.getSessionString(context, SharedKeys.USER_ID, "0"));
        Log.e("withdraw", withDrawRequest.toString());

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<WithDrawResponse> call = apiService.withdraw("withdraw", "aW52ZXN0bWVudF8yMDE4", withDrawRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<WithDrawResponse>() {
            @Override
            public void onResponse(Call<WithDrawResponse> call, Response<WithDrawResponse> response) {
                Log.e("deposit_history", response.toString());
                if (response.body().getStatus() != -1) {
                    progressDialog.dismiss();
                    WithDrawResponse.Details details = response.body().getDetails();
                    if (details != null) {
                        withdrawView.withdrawStatus(response.body().getStatus(), response.body().getMessage(), details.getOrderId());

                    } else {
                        withdrawView.withdrawStatus(response.body().getStatus(), response.body().getMessage(), "0");
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Withdraw failed", Toast.LENGTH_LONG).show();
                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<WithDrawResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void calculateWithdraw(String amount) {
        double balance = Double.parseDouble(SessionManager.getSessionString(context, SharedKeys.TOTAL_INVESTMENT, "0")) - Double.parseDouble(amount);
        withdrawView.withdrawCalculate(balance + "", "0");
    }
}
