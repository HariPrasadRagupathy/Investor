package com.investor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.investor.presenter.ConfirmInvestPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.ConfirmInvestContractor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Confirm_Investment extends BaseActivity implements ConfirmInvestContractor.view {

    @BindView(R.id.cm_ll_screentitle_top)
    LinearLayout cmLlScreentitleTop;
    @BindView(R.id.cia_iv_investmnet_left)
    ImageView ciaIvInvestmnetLeft;
    @BindView(R.id.nip_tv_planname)
    TextView nipTvPlanname;
    @BindView(R.id.nip_tv_investment_amount)
    TextView nipTvInvestmentAmount;
    @BindView(R.id.nip_tv_investment_roi)
    TextView nipTvInvestmentRoi;
    @BindView(R.id.cia_tv_amount)
    TextView ciaTvAmount;
    @BindView(R.id.cia_tv_duration)
    TextView ciaTvDuration;
    @BindView(R.id.cia_tv_total_profit)
    TextView ciaTvTotalProfit;
    @BindView(R.id.cia_tv_monthly_profit)
    TextView ciaTvMonthlyProfit;
    @BindView(R.id.cia_tv_cancellation_charge)
    TextView ciaTvCancellationCharge;
    @BindView(R.id.cia_bt_review)
    Button ciaBtReview;
    @BindView(R.id.cia_bt_confirm)
    Button ciaBtConfirm;

    String plan_id = "";
    private ConfirmInvestPresenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_confirm__investment;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_confirminvestment_title);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {
        presenter = new ConfirmInvestPresenter(this, this);
        setContent();


    }

    private void setContent() {
        plan_id = getIntent().getStringExtra("plan_id");

        nipTvPlanname.setText(getApplicationContext().getResources().getString(R.string.cnt_plan_name) + " :" + getIntent().getStringExtra("plan_name"));
        nipTvInvestmentAmount.setText(getApplicationContext().getResources().getString(R.string.cnt_plan_amt) + " :" + getIntent().getStringExtra("amount_range"));
        nipTvInvestmentRoi.setText(getApplicationContext().getResources().getString(R.string.cnt_roi) + " :" + getIntent().getStringExtra("interest_rate"));
        ciaTvAmount.setText(getIntent().getStringExtra("invest_amount") + " EUR");
        ciaTvMonthlyProfit.setText(getIntent().getStringExtra("monthly_profit"));
        ciaTvCancellationCharge.setText(getIntent().getStringExtra("cancel_charge"));
    }


    @OnClick(R.id.cia_bt_confirm)
    public void confirmInvestment() {
        if (checkInternet())
            presenter.intiateDeposit(plan_id, getIntent().getStringExtra("invest_amount"));
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
        //showCustomDialog(getResources().getString(R.string.dig_invest_success_title), getResources().getString(R.string.dig_invest_success_content), R.drawable.hand_shake,2,2);
    }

    @OnClick(R.id.cia_bt_review)
    public void reviewInvestment() {

        moveToNewInvestment();
        finish();

    }

    @Override
    public void sendAcknowledgement(String status, String orderId) {
        if (status.equals("1")) {
            showCustomDialog(getResources().getString(R.string.dig_invest_success_title), getResources().getString(R.string.dig_invest_success_content) + "(OrderId:" + orderId + ")", R.drawable.hand_shake, 2, 2);
        }

    }
}
