package com.investor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.investor.utils.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends BaseActivity {

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

    }




    @OnClick(R.id.la_bt_signin)
    public void moveToHome()
    {
        Intent moveToHome = new Intent(this,DashBoard.class);
        startActivity(moveToHome);
        finish();
    }
}
