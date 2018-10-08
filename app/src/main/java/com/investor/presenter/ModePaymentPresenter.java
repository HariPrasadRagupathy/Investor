package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.DepositRequest;
import com.investor.models.EarningsResponse;
import com.investor.models.OrderIdRequest;
import com.investor.models.PaymentListResponse;
import com.investor.models.ProofUploadRequest;
import com.investor.models.ProofUploadResponse;
import com.investor.view.EarningsContractor;
import com.investor.view.ModePaymentContractor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModePaymentPresenter implements ModePaymentContractor.presenter {
    ModePaymentContractor.view modepaymentView;
    Context context;
    private PaymentListResponse paymentListResponse;

    public ModePaymentPresenter(ModePaymentContractor.view modepaymentView, Context context) {
        this.modepaymentView = modepaymentView;
        this.context = context;
    }


    @Override
    public void getPaymentModeList() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PaymentListResponse> call = apiService.paymentmode_list("payment_mode", "aW52ZXN0bWVudF8yMDE4");

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<PaymentListResponse>() {
            @Override
            public void onResponse(Call<PaymentListResponse> call, Response<PaymentListResponse> response) {
                Log.e("deposit_history", response.toString());
                if (response.body().getStatus() == 1) {
                    progressDialog.dismiss();
                    List<String> details = response.body().getDetails();
                    if (details != null) {


                        modepaymentView.paymentModeList(response.body().getDetails());

                    }

                } else {
                    progressDialog.dismiss();

                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<PaymentListResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void uploadProof(String orderId, String paymentMethod, String referenceNo, String proofImage) {
        Log.e("upload", orderId);
        Log.e("upload", paymentMethod);
        Log.e("upload", referenceNo);

        ProofUploadRequest proofUploadRequest= new ProofUploadRequest();
        proofUploadRequest.setOrderId(orderId);
        proofUploadRequest.setPaymentMode(paymentMethod);
        proofUploadRequest.setReferenceNumber(referenceNo);
        proofUploadRequest.setProof(proofImage);




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProofUploadResponse> call = apiService.uploadProof("bank_proof_upload", "aW52ZXN0bWVudF8yMDE4",proofUploadRequest);

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
                    modepaymentView.paymentModeStatus(1,response.body().getStatus(),response.body().getMessage());

                } else {
                    progressDialog.dismiss();
                    modepaymentView.paymentModeStatus(1,response.body().getStatus(),response.body().getMessage());
                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProofUploadResponse> call, Throwable t) {

            }
        });

        //Log.e("upload", proofImage);
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
                    modepaymentView.paymentModeStatus(1,response.body().getStatus(),response.body().getMessage());

                } else {
                    progressDialog.dismiss();
                    modepaymentView.paymentModeStatus(1,response.body().getStatus(),response.body().getMessage());
                    // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProofUploadResponse> call, Throwable t) {

            }
        });
    }
}
