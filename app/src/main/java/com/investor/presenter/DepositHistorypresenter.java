package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.DepositHistoryResponse;
import com.investor.models.DepositRequest;
import com.investor.models.DepositResponse;
import com.investor.models.InvestmentPlans;
import com.investor.models.OrderIdRequest;
import com.investor.models.ProofUploadResponse;
import com.investor.models.UserIdRequest;
import com.investor.view.ConfirmInvestContractor;
import com.investor.view.DepositHistoryContractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepositHistorypresenter implements DepositHistoryContractor.presenter {
    DepositHistoryContractor.view depositView;
    Context context;
    private ArrayList<DepositHistoryResponse.Detail> depositdetails;

    public DepositHistorypresenter(DepositHistoryContractor.view depositView, Context context) {
        this.depositView = depositView;
        this.context = context;
    }


    @Override
    public void getDepositHistory() {

        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(SessionManager.getSessionString(context, SharedKeys.USER_ID,"0"));




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DepositHistoryResponse> call = apiService.deposit_history("deposit_history","aW52ZXN0bWVudF8yMDE4",userIdRequest);

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
                Log.e("deposit_history",response.toString());
                if(response.body().getStatus()==1)
                {
                    progressDialog.dismiss();
                    List<DepositHistoryResponse.Detail> details = response.body().getDetails();
                    if(details != null)
                        {
                    depositdetails = new ArrayList<DepositHistoryResponse.Detail>();
                    depositdetails.addAll(details);
                    depositView.depositDetails(depositdetails);
                    }

                }
                else
                {
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
    public void cancelDeposit(String orderId) {
        OrderIdRequest orderIdRequest = new OrderIdRequest();
        orderIdRequest.setOrderId(orderId);




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProofUploadResponse> call = apiService.cancelDeposit("deposit_cancel", "aW52ZXN0bWVudF8yMDE4",orderIdRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<ProofUploadResponse>() {
            @Override
            public void onResponse(Call<ProofUploadResponse> call, Response<ProofUploadResponse> response) {
                Log.e("deposit_history", response.toString());
                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();
                    Log.e("deposit_history", response.body().toString());
                    depositView.paymentModeStatus(1,response.body().getStatus(),response.body().getMessage());

                } else {
                    progressDialog.dismiss();
                    depositView.paymentModeStatus(1,response.body().getStatus(),response.body().getMessage());
                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProofUploadResponse> call, Throwable t) {

            }
        });
    }
}
