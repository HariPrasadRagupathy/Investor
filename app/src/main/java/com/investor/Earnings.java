package com.investor;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.investor.models.EarningsResponse;
import com.investor.presenter.EarningsPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.EarningsContractor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Earnings extends BaseActivity implements EarningsContractor.view {

    @BindView(R.id.ea_tv_invested_amt)
    TextView eaTvInvestedAmt;

    @BindView(R.id.ea_tv_lstmnt_earnings)
    TextView eaTvLstmntEarnings;
    @BindView(R.id.ea_tv_lstyr_earnings)
    TextView eaTvLstyrEarnings;
    @BindView(R.id.ea_tv_total_earnings)
    TextView ea_tv_total_earnings;

    private EarningsPresenter presenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_earnings;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_earning_title);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {
        presenter = new EarningsPresenter(this, this);
        if(checkInternet())
        presenter.getEarnings();
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
    }


    @Override
    public void earningDetails(ArrayList<EarningsResponse.Detail> earningDetails) {

        Log.e("earnings",earningDetails.get(0).getLastMonthEarnings()+"");

        eaTvInvestedAmt.setText(earningDetails.get(0).getAmount().toString()+" EUR");
        eaTvLstmntEarnings.setText(earningDetails.get(0).getLastMonthEarnings().toString()+" EUR");
        eaTvLstyrEarnings.setText(earningDetails.get(0).getLastYearEarnings().toString()+" EUR");
        ea_tv_total_earnings.setText(earningDetails.get(0).getTotalEarnings().toString()+" EUR");

    }





}
