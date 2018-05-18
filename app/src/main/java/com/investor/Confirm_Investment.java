package com.investor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.investor.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Confirm_Investment extends BaseActivity {

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

    }


    @OnClick(R.id.cia_bt_confirm)
    public void confirmInvestment() {
        showCustomDialog(getResources().getString(R.string.dig_invest_success_title), getResources().getString(R.string.dig_invest_success_content), R.drawable.hand_shake,2,2);
    }

}
