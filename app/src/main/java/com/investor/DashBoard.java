package com.investor;

import android.os.Bundle;
import android.widget.TextView;

import com.investor.utils.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashBoard extends BaseActivity {


    @BindView(R.id.dba_tv_pendinginvest)
    TextView dbaTvPendinginvest;
    @BindView(R.id.dba_tv_pendingwithdraw)
    TextView dbaTvPendingwithdraw;

    @Override
    protected int setLayout() {
        return R.layout.dash_board_menu_activity;
    }

    @Override
    protected String setTitle() {
        return "Dashboard";
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return true;
    }

    @Override
    protected void intialize() {
        setContent();

    }

    private void setContent() {
        dbaTvPendinginvest.setSelected(true);
        dbaTvPendingwithdraw.setSelected(true);
    }

    @OnClick(R.id.dba_cv_new_investment)
    public void NewInvestment()
    {
        moveToNewInvestment();
    }

    @OnClick(R.id.dba_cv_withdraw)
    public void withdraw()
    {
       moveToWithdraw();
    }

    @OnClick(R.id.dba_cv_calculator)
    public void calculator()
    {
        moveToCalculator();
    }

    @OnClick(R.id.dba_cv_notifications)
    public void notifications()
    {
       moveToNotifications();
    }

    @OnClick(R.id.dba_cv_pending_investment)
    public void pending_investment()
    {
       moveToInvestmentStatus();
    }

    @OnClick(R.id.dba_cv_pendng_withdraw)
    public void pending_withdraw()
    {
       moveToWithdrawStatus();
    }



}
