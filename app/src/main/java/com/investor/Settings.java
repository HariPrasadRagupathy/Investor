package com.investor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.investor.utils.BaseActivity;

public class Settings extends BaseActivity {

    @Override
    protected int setLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_settings_title);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {

    }


}
