package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.EarningsResponse;
import com.investor.models.InvestCancelResponse;
import com.investor.models.TokenUpdateRequest;
import com.investor.models.UserIdRequest;
import com.investor.view.DashboardContractor;
import com.investor.view.EarningsContractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter implements DashboardContractor.presnter {

    DashboardContractor.view dashboardView;
    Context context;
    private ArrayList<EarningsResponse.Detail> earningsdetails;

    public DashboardPresenter(DashboardContractor.view dashboardView, Context context) {
        this.dashboardView = dashboardView;
        this.context = context;
    }


    @Override
    public void getEarnings() {

        UserIdRequest userIdRequest = new UserIdRequest();
        userIdRequest.setUserId(SessionManager.getSessionString(context, SharedKeys.USER_ID, "0"));


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EarningsResponse> call = apiService.earnings("earnings", "aW52ZXN0bWVudF8yMDE4", userIdRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<EarningsResponse>() {
            @Override
            public void onResponse(Call<EarningsResponse> call, Response<EarningsResponse> response) {
                Log.e("deposit_history", response.toString());
                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();
                    List<EarningsResponse.Detail> details = response.body().getDetails();
                    if (details != null) {
                        earningsdetails = new ArrayList<EarningsResponse.Detail>();
                        earningsdetails.addAll(details);
                        dashboardView.earnings_details(earningsdetails.get(0).getAmount().toString(), earningsdetails.get(0).getTotalEarnings().toString());
                        //earningsView.earningDetails(earningsdetails);
                    }

                } else {
                    progressDialog.dismiss();

                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<EarningsResponse> call, Throwable t) {
                Log.e("deposit_history", call.toString());
                Log.e("deposit_history", t.toString());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void checkFCMToken() {
        if (!SessionManager.getSessionBoolean(context, SharedKeys.ISTOKENUPDATED, false)&&!SessionManager.getSessionString(context, SharedKeys.TOKEN, "").equals("")) {
            TokenUpdateRequest tokenUpdateRequest = new TokenUpdateRequest();
            tokenUpdateRequest.setUserId(SessionManager.getSessionString(context, SharedKeys.USER_ID, "0"));
            tokenUpdateRequest.setToken(SessionManager.getSessionString(context, SharedKeys.TOKEN, ""));


            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<InvestCancelResponse> call = apiService.updateToken("new_push_token", "aW52ZXN0bWVudF8yMDE4", tokenUpdateRequest);

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
                    Log.e("deposit_history", response.toString());
                    if (response.body().getStatus() == 1) {
                        SessionManager.saveSessionBoolean(context, SharedKeys.ISTOKENUPDATED, true);
                        progressDialog.dismiss();
                        /*List<InvestCancelResponse.Detail> details = response.body().getDetails();
                        if(details != null)
                        {
                            earningsdetails = new ArrayList<EarningsResponse.Detail>();
                            earningsdetails.addAll(details);
                            dashboardView.earnings_details(earningsdetails.get(0).getAmount().toString(),earningsdetails.get(0).getTotalEarnings().toString());
                            //earningsView.earningDetails(earningsdetails);
                        }*/

                    } else {
                        progressDialog.dismiss();

                        // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<InvestCancelResponse> call, Throwable t) {
                    Log.e("deposit_history", call.toString());
                    Log.e("deposit_history", t.toString());
                    progressDialog.dismiss();
                }
            });


        }
    }
}
