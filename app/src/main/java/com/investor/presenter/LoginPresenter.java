package com.investor.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.investor.data.retro.ApiClient;
import com.investor.data.retro.ApiInterface;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.LoginRequest;
import com.investor.models.LoginResponse;
import com.investor.view.LoginContractor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContractor.presenter {
    LoginContractor.view loginView;
    Context context;

    public LoginPresenter(LoginContractor.view loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    public void checkLogin(String userName, String password) {


      /*  if (userName.equals("Hari") & password.equals("1234"))
            loginView.loginSuccess();
        else
            loginView.loginFailed();*/


        LoginRequest loginRequest= new LoginRequest();
        loginRequest.setPhone(userName);
        loginRequest.setPassword(password);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiService.login("login","aW52ZXN0bWVudF8yMDE4",loginRequest);

        // Set up progress before call
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("Please wait !!");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDialog.show();
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("login",response.toString());
                if(response.body().getStatus()==1)
                {
                    progressDialog.dismiss();
                    Log.e("login",response.body().getDetails().get(0).getUserId());
                    SessionManager.saveSessionBoolean(context, SharedKeys.ISLOGGEDIN,true);
                    SessionManager.saveSessionString(context, SharedKeys.USER_ID,response.body().getDetails().get(0).getUserId());
                    SessionManager.saveSessionString(context, SharedKeys.USER_NAME,response.body().getDetails().get(0).getUserName());
                    SessionManager.saveSessionString(context, SharedKeys.PROFILE_PIC,response.body().getDetails().get(0).getProfilePicture());
                    SessionManager.saveSessionBoolean(context, SharedKeys.ISREGISTERED,Boolean.parseBoolean(response.body().getDetails().get(0).getRegistrationStatus()));
                    SessionManager.saveSessionString(context, SharedKeys.TOTAL_INVESTMENT,response.body().getDetails().get(0).getTotalInvestment());
                    loginView.loginSuccess();
                }
                else
                {
                    progressDialog.dismiss();
                    loginView.loginFailed();
                   // loginView.loginFailed(response.body().getStatus(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }
}
