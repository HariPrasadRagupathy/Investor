package com.investor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.investor.utils.BaseActivity;

import butterknife.OnClick;

public class Profile extends BaseActivity {

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

    }



    @OnClick(R.id.pa_iv_edit)
    public void moveToProfileEdit()
    {
        Intent moveToProfileEdit= new Intent(this,Profile_edit.class);
        startActivity(moveToProfileEdit);
    }




}
