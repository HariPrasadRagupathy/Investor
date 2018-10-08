package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.DepositHistoryResponse;
import com.investor.models.InvestCancelResponse;
import com.investor.models.OrderIdRequest;
import com.investor.models.ProofUploadResponse;
import com.investor.models.UserIdRequest;
import com.investor.models.WithDrawHistoryResponse;
import com.investor.view.ConfirmInvestContractor;
import com.investor.view.WithDrawHistoryContractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WithDrawHistoryPresenter implements WithDrawHistoryContractor.presenter {

    WithDrawHistoryContractor.view withdrawView;
    Context context;
    private ArrayList<WithDrawHistoryResponse.Detail> withdrawdetails;

    public WithDrawHistoryPresenter(WithDrawHistoryContractor.view withdrawView, Context context) {
        this.withdrawView = withdrawView;
        this.context = context;
    }



    @Override
    public void getWithdrawHistory() {
        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(SessionManager.getSessionString(context, SharedKeys.USER_ID,"0"));




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<WithDrawHistoryResponse> call = apiService.withdraw_history("withdraw_history","aW52ZXN0bWVudF8yMDE4",userIdRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<WithDrawHistoryResponse>() {
            @Override
            public void onResponse(Call<WithDrawHistoryResponse> call, Response<WithDrawHistoryResponse> response) {
                Log.e("deposit_history",response.toString());
                if(response.body().getStatus()==1)
                {
                    progressDialog.dismiss();
                    List<WithDrawHistoryResponse.Detail> details = response.body().getDetails();
                    if(details != null)
                    {
                        withdrawdetails = new ArrayList<WithDrawHistoryResponse.Detail>();
                        withdrawdetails.addAll(details);
                        withdrawView.withdrawDetails(withdrawdetails);
                    }

                }
                else
                {
                    progressDialog.dismiss();

                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<WithDrawHistoryResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void cancelWithdraw(String orderId) {
        OrderIdRequest orderIdRequest = new OrderIdRequest();
        orderIdRequest.setOrderId(orderId);


        Log.e("cancel_history", orderId);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<InvestCancelResponse> call = apiService.cancelWithdraw("withdraw_cancel", "aW52ZXN0bWVudF8yMDE4",orderIdRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<InvestCancelResponse>() {
            @Override
            public void onResponse(Call<InvestCancelResponse> call, Response<InvestCancelResponse> response) {
                Log.e("cancel_history", response.toString());
                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();
                    Log.e("cancel_history", response.body().toString());
                    withdrawView.withdrawModeStatus(1,response.body().getStatus(),response.body().getMessage());

                } else {
                    progressDialog.dismiss();
                    withdrawView.withdrawModeStatus(1,response.body().getStatus(),response.body().getMessage());
                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<InvestCancelResponse> call, Throwable t) {

            }
        });
    }
}
