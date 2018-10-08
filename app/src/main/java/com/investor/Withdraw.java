package com.investor;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.investor.adapters.WithDrawPagerAdapter;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.DepositHistoryResponse;
import com.investor.presenter.WithdrawPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.WithdrawContractor;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Withdraw extends BaseActivity implements WithdrawContractor.view {

    @BindView(R.id.wa_vp_investmentpager)
    ViewPager waVpInvestmentpager;
    @BindView(R.id.wa_et_withdraw_amount)
    EditText waEtWithdrawAmount;
    @BindView(R.id.wa_tv_cancel_charge)
    TextView waTvCancelCharge;
    @BindView(R.id.wa_tv_invest_balance)
    TextView waTvInvestBalance;
    @BindView(R.id.wa_tv_invested_amount)
    TextView waTvInvestedAmount;

    private ArrayList<DepositHistoryResponse.Detail> planDetails;
    private WithdrawPresenter presenter;

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

        presenter = new WithdrawPresenter(this, this);
        if (checkInternet())
            presenter.getInvestDetails();
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);

    }

    @OnClick(R.id.wa_bt_withdraw)
    public void confirmWithdraw() {
        Log.e("amt", planDetails.get(waVpInvestmentpager.getCurrentItem()).getId() + " OrderId");
        Log.e("amt", planDetails.get(waVpInvestmentpager.getCurrentItem()).getPlanId() + " planId");
        if (!waEtWithdrawAmount.getText().toString().equals(""))
            showCustomWithdrawDialog("Withdraw?", "Are you sure to withdraw?", R.drawable.thinking);
        else
            showToast("Please Enter Amount to Withdraw");
    }

    @OnClick(R.id.wa_bt_calculate)
    public void calculateWithdraw() {
        if (!waEtWithdrawAmount.getText().toString().equals(""))
            presenter.calculateWithdraw(waEtWithdrawAmount.getText().toString());
        else
            showToast("Please Enter Amount to Withdraw");
    }

    @Override
    public void deposit_details(ArrayList<DepositHistoryResponse.Detail> details) {
        if(details.size()>0) {
            waTvInvestedAmount.setText(SessionManager.getSessionString(getApplicationContext(), SharedKeys.TOTAL_INVESTMENT, "0") + "EUR");
            this.planDetails = details;
            WithDrawPagerAdapter withdrawPagerAdapter = new WithDrawPagerAdapter(getApplicationContext(), planDetails);
            waVpInvestmentpager.setAdapter(withdrawPagerAdapter);
            waVpInvestmentpager.setCurrentItem(0);
        }
        else
        {
            showToast("No Investment Made !!!");
            moveToDashboard();
            finish();
        }
    }

    @Override
    public void withdrawStatus(int status, String msg, String withdrawId) {
        /*
        success - 1
        Your Plan Not Active - 2
        Your Account Balance is low -3
        */
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        switch (status) {
            case 1:
                showCustomDialog("Success!!", "Withdraw Initiated Successfully !!", R.drawable.thinking, 2, 4);
                break;

            case 2:
                showCustomDialog("Failed!!", "Plan Not Activiated !!", R.drawable.thinking, 2, 5);
                break;

            case 3:
                showCustomDialog("Failed!!", "Low Balance !!", R.drawable.thinking, 2, 5);
                break;
        }


    }

    @Override
    public void withdrawCalculate(String investment_balance, String cancellation_charge) {
        waTvInvestBalance.setText(investment_balance + " EUR");
        waTvCancelCharge.setText(cancellation_charge + " EUR");
    }


    @OnClick(R.id.wa_iv_investmnet_left)
    public void investmentPlanLeft() {
        int tab = waVpInvestmentpager.getCurrentItem();
        if (tab > 0) {
            tab--;
            waVpInvestmentpager.setCurrentItem(tab);
        } else if (tab == 0) {
            waVpInvestmentpager.setCurrentItem(tab);
        }
    }

    @OnClick(R.id.wa_iv_investmnet_right)
    public void investmentPlanRight() {
        int tab = waVpInvestmentpager.getCurrentItem();
        tab++;
        waVpInvestmentpager.setCurrentItem(tab);
    }


    public void showCustomWithdrawDialog(String title, String content, int titleimage) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_light);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.cm_tv_dialogtitle)).setText(title);
        ((TextView) dialog.findViewById(R.id.cm_tv_dialogcontent)).setText(content);
        // ((CircleImageView) dialog.findViewById(R.id.image)).setImageResource(titleimage);

        CircularImageView image = dialog.findViewById(R.id.cm_iv_dialogimage);
        image.setImageResource(titleimage);


        dialog.findViewById(R.id.cm_bt_dialogyes).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.cm_bt_dialogno).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.cm_bt_dialogok).setVisibility(View.GONE);


        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        ((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogyes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInternet())
                    presenter.withdrawAmount(planDetails.get(waVpInvestmentpager.getCurrentItem()).getPlanId().toString(), planDetails.get(waVpInvestmentpager.getCurrentItem()).getId().toString(), waEtWithdrawAmount.getText().toString());
                else
                    showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
                dialog.dismiss();
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
