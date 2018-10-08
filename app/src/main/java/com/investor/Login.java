package com.investor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.widget.Toast;

import com.investor.presenter.LoginPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.LoginContractor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends BaseActivity implements LoginContractor.view {

    @BindView(R.id.la_et_username)
    TextInputEditText laEtUsername;
    @BindView(R.id.la_et_password)
    TextInputEditText laEtPassword;
    private LoginPresenter loginPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected String setTitle() {
        return "";
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {


        loginPresenter = new LoginPresenter(this, this);


    }


    @OnClick(R.id.la_bt_signin)
    public void checkLogin() {
        if (checkInternet())
            loginPresenter.checkLogin(laEtUsername.getText().toString(), laEtPassword.getText().toString());
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
    }


    //@OnClick(R.id.la_bt_signin)
    public void moveToHome() {
        Intent moveToHome = new Intent(this, DashBoard.class);
        startActivity(moveToHome);
        finish();
    }

    @Override
    public void loginSuccess() {
        //  Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), "Login Success", Snackbar.LENGTH_LONG);
        snackbar.show();
        moveToHome();

    }

    @Override
    public void loginFailed() {
        //Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_LONG).show();
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), "Login Failed", Snackbar.LENGTH_LONG);
        snackbar.show();
    }


}
