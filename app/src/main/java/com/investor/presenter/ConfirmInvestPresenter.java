package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.DepositRequest;
import com.investor.models.DepositResponse;
import com.investor.models.LoginRequest;
import com.investor.models.LoginResponse;
import com.investor.view.ConfirmInvestContractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ConfirmInvestPresenter implements ConfirmInvestContractor.presenter {
    ConfirmInvestContractor.view confirmView;
    Context context;

    public ConfirmInvestPresenter(ConfirmInvestContractor.view confirmView, Context context) {
        this.confirmView = confirmView;
        this.context = context;
    }



    @Override
    public void intiateDeposit(String plan_id, String amount) {

        Log.e("deposit", SessionManager.getSessionString(context, SharedKeys.USER_ID,"0"));
        Log.e("deposit", plan_id);
        Log.e("deposit", amount);


        DepositRequest depositRequest= new DepositRequest();
        depositRequest.setUserId(SessionManager.getSessionString(context, SharedKeys.USER_ID,"0"));
        depositRequest.setPlanId(plan_id);
        depositRequest.setAmount(amount);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DepositResponse> call = apiService.deposit("deposit","aW52ZXN0bWVudF8yMDE4",depositRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<DepositResponse>() {
            @Override
            public void onResponse(Call<DepositResponse> call, Response<DepositResponse> response) {
                Log.e("login",response.toString());
                if(response.body().getStatus()==1)
                {
                    progressDialog.dismiss();

                   confirmView.sendAcknowledgement("1",response.body().getDetails().getOrderId());
                }
                else
                {
                    progressDialog.dismiss();
                    confirmView.sendAcknowledgement("0","");
                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<DepositResponse> call, Throwable t) {

            }
        });


    }
}
