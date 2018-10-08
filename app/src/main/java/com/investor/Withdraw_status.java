package com.investor;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.investor.adapters.InvestmentStatusAdapter;
import com.investor.adapters.WithDrawStatusAdapter;
import com.investor.models.InvestmentStatusList;
import com.investor.models.WithDrawHistoryResponse;
import com.investor.models.WithdrawStatusList;
import com.investor.presenter.WithDrawHistoryPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.WithDrawHistoryContractor;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class Withdraw_status extends BaseActivity implements WithDrawHistoryContractor.view {

    @BindView(R.id.wsa_rv_statuslist)
    RecyclerView wsaRvStatuslist;

    private static RecyclerView.Adapter withdrawStatusAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<WithdrawStatusList> withdraStatusList;
    private WithDrawHistoryPresenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_withdraw_status;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_withdraw_title_status);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {
        initComponents();
        //setContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContent();
    }

    private void setContent() {
        if(checkInternet())
        presenter.getWithdrawHistory();
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
      /*  wsaRvStatuslist.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        wsaRvStatuslist.setLayoutManager(layoutManager);
        wsaRvStatuslist.setItemAnimator(new DefaultItemAnimator());


        withdraStatusList = new ArrayList<WithdrawStatusList>();
        withdraStatusList.add(new WithdrawStatusList("1233", "10.00 USD", "pending", "cancel", 2));
        withdraStatusList.add(new WithdrawStatusList("3434", "15000.00 USD", "Approved", "check", 1));
        withdraStatusList.add(new WithdrawStatusList("4534", "112.00 USD", "success", "cancel", 1));
        withdraStatusList.add(new WithdrawStatusList("3422", "3000.00 USD", "cancelled", "check", 1));
        withdraStatusList.add(new WithdrawStatusList("3483", "2000.00 USD", "pending", "cancel", 2));
        withdraStatusList.add(new WithdrawStatusList("1209", "4500.00 USD", "Approved", "check", 1));
        withdraStatusList.add(new WithdrawStatusList("3432", "600.00 USD", "success", "cancel", 1));


        withdrawStatusAdapter = new WithDrawStatusAdapter(withdraStatusList, getApplicationContext(),this);
        wsaRvStatuslist.setAdapter(withdrawStatusAdapter);*/
    }

    private void initComponents() {

        presenter = new WithDrawHistoryPresenter(this,this);
    }


    @Override
    public void withdrawDetails(ArrayList<WithDrawHistoryResponse.Detail> withdrawDetails) {
        wsaRvStatuslist.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        wsaRvStatuslist.setLayoutManager(layoutManager);
        wsaRvStatuslist.setItemAnimator(new DefaultItemAnimator());

/*
        withdraStatusList = new ArrayList<WithdrawStatusList>();
        withdraStatusList.add(new WithdrawStatusList("1233", "10.00 USD", "pending", "cancel", 2));
        withdraStatusList.add(new WithdrawStatusList("3434", "15000.00 USD", "Approved", "check", 1));
        withdraStatusList.add(new WithdrawStatusList("4534", "112.00 USD", "success", "cancel", 1));
        withdraStatusList.add(new WithdrawStatusList("3422", "3000.00 USD", "cancelled", "check", 1));
        withdraStatusList.add(new WithdrawStatusList("3483", "2000.00 USD", "pending", "cancel", 2));
        withdraStatusList.add(new WithdrawStatusList("1209", "4500.00 USD", "Approved", "check", 1));
        withdraStatusList.add(new WithdrawStatusList("3432", "600.00 USD", "success", "cancel", 1));*/


        withdrawStatusAdapter = new WithDrawStatusAdapter(withdrawDetails, getApplicationContext(),this);
        wsaRvStatuslist.setAdapter(withdrawStatusAdapter);
    }

    @Override
    public void withdrawModeStatus(int mode, int status, String message) {
        if(mode==1) {
            if (status == 1)
                showCustomDialog("Success", message, R.drawable.happy, 2, 4);
            else
                showCustomDialog("Failed", message, R.drawable.cancel_investment, 2, 4);
        }
        else if(mode == 2)
        {

        }
    }


    public void showCustomWithdrawDialog(String title, String content, int titleimage, int mode, final int moveToActivity, final String orderId) {
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

       /* switch (mode) {
            case 1:*/
        dialog.findViewById(R.id.cm_bt_dialogyes).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.cm_bt_dialogno).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.cm_bt_dialogok).setVisibility(View.GONE);
                /*break;
            case 2:
                dialog.findViewById(R.id.cm_bt_dialogyes).setVisibility(View.GONE);
                dialog.findViewById(R.id.cm_bt_dialogno).setVisibility(View.GONE);
                dialog.findViewById(R.id.cm_bt_dialogok).setVisibility(View.VISIBLE);
                break;
        }*/

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /*((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        switch (moveToActivity) {
                        moveToInvestmentStatus();
          //      }
                finish();
            }
        });*/

        ((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogyes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInternet())
                presenter.cancelWithdraw(orderId);
                else
                    showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
                dialog.dismiss();
            /*    switch (moveToActivity) {
                    case 1:
                        moveToSignin();
                        SessionManager.clearSession(getApplicationContext());
                        Log.e("shared","session cleared");
                        break;
                    case 2:
                      moveToInvestmentStatus();
              /*          break;
                    case 3:
                        moveToDashboard();
                        break;
                    case 4:
                        moveToWithdrawStatus();
                        break;
                    case 5:
                        moveToWithdraw();
                        break;
                }*/
               // finish();
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



}
