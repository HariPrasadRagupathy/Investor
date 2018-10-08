package com.investor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.utils.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class Profile extends BaseActivity {

    @BindView(R.id.pa_tv_account_id)
    TextView pa_tv_account_id;

    @BindView(R.id.pa_tv_username)
    TextView pa_tv_username;

    @BindView(R.id.pa_tv_total_invest)
    TextView pa_tv_total_invest;

    @Override
    protected int setLayout() {
        return R.layout.activity_profile;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_profile_title);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {
        pa_tv_account_id.setText("Se7-" + SessionManager.getSessionString(getApplicationContext(), SharedKeys.USER_ID, "0"));
        pa_tv_username.setText(SessionManager.getSessionString(getApplicationContext(),SharedKeys.USER_NAME,""));
        pa_tv_total_invest.setText(SessionManager.getSessionString(getApplicationContext(),SharedKeys.TOTAL_INVESTMENT,"0 EUR"));
    }


    @OnClick(R.id.pa_iv_edit)
    public void moveToProfileEdit() {
        Intent moveToProfileEdit = new Intent(this, Profile_edit.class);
        startActivity(moveToProfileEdit);
    }


}
