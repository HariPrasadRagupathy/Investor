package com.investor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.investor.utils.BaseActivity;

import butterknife.OnClick;

public class Withdraw extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.withdraw_activity;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_withdraw_title);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {

    }
    @OnClick(R.id.wa_bt_withdraw)
    public void confirmWithdraw() {
        showCustomDialog("Withdraw!", "Are you sure to withdraw?", R.drawable.thinking,1,4);
    }

}
