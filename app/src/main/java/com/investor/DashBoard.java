package com.investor;

import android.os.Bundle;
import android.widget.TextView;

import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.presenter.DashboardPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.DashboardContractor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashBoard extends BaseActivity implements DashboardContractor.view {


    @BindView(R.id.dba_tv_pendinginvest)
    TextView dbaTvPendinginvest;
    @BindView(R.id.dba_tv_pendingwithdraw)
    TextView dbaTvPendingwithdraw;
    @BindView(R.id.da_tv_tot_investment)
    TextView daTvTotInvestment;
    @BindView(R.id.dba_tv_tot_earnings)
    TextView dbaTvTotEarnings;
    private DashboardPresenter presenter;

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
        presenter = new DashboardPresenter(this, this);
        setContent();

    }

    private void setContent() {


        dbaTvPendinginvest.setSelected(true);
        //  dbaTvPendingwithdraw.setSelected(true);
        //  daTvTotInvestment.setText(SessionManager.getSessionString(getApplicationContext(), SharedKeys.TOTAL_INVESTMENT,"0"));
        //presenter.getEarnings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkInternet()) {
            presenter.checkFCMToken();
            presenter.getEarnings();
        }
        else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
            //showToast("No Internet !!");
    }

    @OnClick(R.id.dba_cv_new_investment)
    public void NewInvestment() {
        moveToNewInvestment();
    }

    @OnClick(R.id.dba_cv_withdraw)
    public void withdraw() {
        moveToWithdraw();
    }

    @OnClick(R.id.dba_cv_calculator)
    public void calculator() {
        moveToCalculator();
    }

    @OnClick(R.id.dba_cv_notifications)
    public void notifications() {
        moveToNotifications();
    }

    @OnClick(R.id.dba_cv_pending_investment)
    public void pending_investment() {
        moveToInvestmentStatus();
    }

    @OnClick(R.id.dba_cv_pendng_withdraw)
    public void pending_withdraw() {
        moveToWithdrawStatus();
    }


    @Override
    public void earnings_details(String total_investment, String total_earnnigs) {
        daTvTotInvestment.setText(total_investment + " EUR");
        dbaTvTotEarnings.setText(total_earnnigs + " EUR");
        SessionManager.saveSessionString(getApplicationContext(), SharedKeys.TOTAL_INVESTMENT, total_investment);
    }
}
