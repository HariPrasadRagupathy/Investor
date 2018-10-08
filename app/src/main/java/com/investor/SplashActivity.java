package com.investor;


import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;

import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.utils.BaseActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected int setLayout() {
        return R.layout.splash_activity;
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


        new Handler().postDelayed(new Runnable() {


// Using handler with postDelayed called runnable run method

            @Override

            public void run() {


                if (SessionManager.getSessionBoolean(getApplicationContext(), SharedKeys.ISLOGGEDIN, false))
                    moveToDashboard();
                else
                    moveToSignin();


                // close this activity

                finish();

            }

        }, 2 * 1000); // wait for 2 seconds

    }

}
