package com.investor.utils;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.investor.Calculator;
import com.investor.DashBoard;
import com.investor.Earnings;
import com.investor.InvestmentStatus;
import com.investor.Login;
import com.investor.ModePayment;
import com.investor.NewInvestment;
import com.investor.Notifications;
import com.investor.Profile;
import com.investor.R;
import com.investor.Settings;
import com.investor.Withdraw;
import com.investor.Withdraw_status;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class
BaseActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    @BindView(R.id.toolbar)
    @Nullable
    public Toolbar toolbar;

    @BindView(R.id.cm_tv_titletext)
    @Nullable
    public TextView cm_tv_titletext;

    public Boolean isInternetConnected = true;

    private int view;

    protected abstract int setLayout();

    protected abstract String setTitle();

    protected abstract Boolean setNavigationDrawer();


    protected abstract void intialize();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = setLayout();
        if (view != 0) {
            String languageToLoad = "pt"; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
            setContentView(view);
            ButterKnife.bind(this);
            if(view!=R.layout.activity_login && view!=R.layout.splash_activity)
            initActionBar(setNavigationDrawer());
            try {

                cm_tv_titletext.setText(setTitle());
            } catch (Exception e) {

            }

            intialize();
            isInternetConnected = ConnectivityReceiver.isConnected();
            Log.e("internet",isInternetConnected+"<!>");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        InvestApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Log.e("internet",isConnected+"<>");
        showSnack(isConnected);
        isInternetConnected=isConnected;

    }


    public boolean checkInternet()
    {
// get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

         //   Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

         //   Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }




    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Internet Connection Lost";
            color = Color.RED;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }


    private void initActionBar(boolean showNavigation) {
        if (showNavigation)
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
      /*  toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked

            }
        });*/
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //   toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        if (showNavigation)
            initNavigationMenu(toolbar);
        //setBatchCount("");
        // intialize();
        //  firebase();
    }

    public void initNavigationMenu(Toolbar toolbar) {
        final NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.home_menu_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                //     updateCounter(nav_view);
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                // Toast.makeText(getApplicationContext(), item.getOrder() + " Selected", Toast.LENGTH_SHORT).show();


                switch (item.getItemId()) {
                    case R.id.action_profile:
                        moveToProfile();
                        break;
                    case R.id.action_new_investment:
                        moveToNewInvestment();
                        break;
                    case R.id.action_investment_status:
                        moveToInvestmentStatus();
                        break;
                    case R.id.action_withdraw_fund:
                        moveToWithdraw();
                        break;
                    case R.id.action_withdraw_status:
                        moveToWithdrawStatus();
                        break;
                    case R.id.action_calculator:
                        moveToCalculator();
                        break;
                    case R.id.action_earnings:
                        moveToEarnings();
                        break;
                    case R.id.action_notification:
                        moveToNotifications();
                        break;
                    case R.id.action_settings:
                        moveToSettings();
                        break;
                    case R.id.action_logout:
                        moveToLogout();
                        break;

                }


                 /*
                else if(item.getTitle().equals("Help")) {
                    moveToHelp();
                }
                else if(item.getTitle().equals("Settings")) {
                    moveToSettings();
                }*/

                drawer.closeDrawers();
                return true;
            }
        });


    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /*Mode - 1=>yes/no , 2=>ok*/
    public void showCustomDialog(String title, String content, int titleimage, int mode, final int moveToActivity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_light);
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.cm_tv_dialogtitle)).setText(title);
        ((TextView) dialog.findViewById(R.id.cm_tv_dialogcontent)).setText(content);
        // ((CircleImageView) dialog.findViewById(R.id.image)).setImageResource(titleimage);

        CircularImageView image = dialog.findViewById(R.id.cm_iv_dialogimage);
        image.setImageResource(titleimage);

        switch (mode) {
            case 1:
                dialog.findViewById(R.id.cm_bt_dialogyes).setVisibility(View.VISIBLE);
                dialog.findViewById(R.id.cm_bt_dialogno).setVisibility(View.VISIBLE);
                dialog.findViewById(R.id.cm_bt_dialogok).setVisibility(View.GONE);
                break;
            case 2:
                dialog.findViewById(R.id.cm_bt_dialogyes).setVisibility(View.GONE);
                dialog.findViewById(R.id.cm_bt_dialogno).setVisibility(View.GONE);
                dialog.findViewById(R.id.cm_bt_dialogok).setVisibility(View.VISIBLE);
                break;
        }

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (moveToActivity) {
                    case 1:
                        moveToSignin();
                        SessionManager.clearSession(getApplicationContext());
                        Log.e("shared","session cleared");
                        break;
                    case 2:
                        moveToInvestmentStatus();
                        break;
                    case 3:
                        moveToDashboard();
                        break;
                    case 4:
                        moveToWithdrawStatus();
                        break;
                    case 5:
                        moveToWithdraw();
                        break;
                }
                finish();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogyes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (moveToActivity) {
                    case 1:
                        moveToSignin();
                        SessionManager.clearSession(getApplicationContext());
                        Log.e("shared","session cleared");
                        break;
                    case 2:
                        moveToInvestmentStatus();
                        break;
                    case 3:
                        moveToDashboard();
                        break;
                    case 4:
                        moveToWithdrawStatus();
                        break;
                    case 5:
                        moveToWithdraw();
                        break;
                }
                finish();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogno)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public String encodeImage(Bitmap bm)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);

        return encImage;
    }

    public void moveToSignin() {
        Intent moveToSignin = new Intent(this, Login.class);
        startActivity(moveToSignin);
    }


    public void moveToProfile() {
        Intent moveToProfile = new Intent(this, Profile.class);
        startActivity(moveToProfile);
    }

    public void moveToNotifications() {
        Intent moveToNotifications = new Intent(this, Notifications.class);
        startActivity(moveToNotifications);
    }

    public void moveToSettings() {
        Intent moveToSettings = new Intent(this, Settings.class);
        startActivity(moveToSettings);
    }

    public void moveToCalculator() {
        Intent moveToCalculator = new Intent(this, Calculator.class);
        startActivity(moveToCalculator);
    }

    public void moveToEarnings() {
        Intent moveToEarnings = new Intent(this, Earnings.class);
        startActivity(moveToEarnings);
    }

    public void moveToWithdrawStatus() {
        Intent moveToWithdrawStatus = new Intent(this, Withdraw_status.class);
        startActivity(moveToWithdrawStatus);
    }

    public void moveToNewInvestment() {
        Intent moveToNewInvestment = new Intent(this, NewInvestment.class);
        startActivity(moveToNewInvestment);
    }

    public void moveToInvestmentStatus() {
        Intent moveToInvestmentStatus = new Intent(this, InvestmentStatus.class);
        startActivity(moveToInvestmentStatus);
    }

    public void moveToWithdraw() {
        Intent moveToNewInvestment = new Intent(this, Withdraw.class);
        startActivity(moveToNewInvestment);
    }

    public void moveToDashboard() {
        Intent moveToDashboard = new Intent(this, DashBoard.class);
        startActivity(moveToDashboard);
    }

    public void moveToLogout() {
        showCustomDialog(getResources().getString(R.string.dig_logout_content), getResources().getString(R.string.dig_logout_content), R.drawable.logout, 1, 1);
    }


    public void moveToModePayment(String orderId, String planId)
    {
        Intent moveToModePayment = new Intent(this, ModePayment.class);
        moveToModePayment.putExtra("orderId",orderId);
        moveToModePayment.putExtra("planId",planId);
        startActivity(moveToModePayment);
    }

    public void logLargeString(String str) {
        final int CHUNK_SIZE = 24076;  // Typical max logcat payload.
        int offset = 0;
        while (offset + CHUNK_SIZE <= str.length()) {
            Log.d("upload", str.substring(offset, offset += CHUNK_SIZE));
        }
        if (offset < str.length()) {
            Log.d("upload", str.substring(offset));
        }
    }

    public void showshortToast(String msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    public void showToast(String msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }



}
