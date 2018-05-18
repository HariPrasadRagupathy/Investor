package com.investor;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.investor.adapters.InvestmentStatusAdapter;
import com.investor.models.InvestmentStatusList;
import com.investor.utils.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestmentStatus extends BaseActivity {

    @BindView(R.id.isa_rv_statuslist)
    RecyclerView isaRvStatuslist;

    private static RecyclerView.Adapter investmentStatusAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<InvestmentStatusList> investmentStatusList;

    @Override
    protected int setLayout() {
        return R.layout.activity_investment_status;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_investmentstatus_title);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {

        initComponents();
        setContent();

    }

    private void setContent() {
        isaRvStatuslist.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        isaRvStatuslist.setLayoutManager(layoutManager);
        isaRvStatuslist.setItemAnimator(new DefaultItemAnimator());


        investmentStatusList = new ArrayList<InvestmentStatusList>();
        investmentStatusList.add(new InvestmentStatusList("1233", "10.00 USD", "pending", "check", 1));
        investmentStatusList.add(new InvestmentStatusList("3434", "15000.00 USD", "Approved", "check", 1));
        investmentStatusList.add(new InvestmentStatusList("4534", "112.00 USD", "proof upload", "cancel", 3));
        investmentStatusList.add(new InvestmentStatusList("3422", "3000.00 USD", "cancelled", "check", 1));
        investmentStatusList.add(new InvestmentStatusList("3483", "2000.00 USD", "pending", "check", 1));
        investmentStatusList.add(new InvestmentStatusList("1209", "4500.00 USD", "Approved", "check", 1));
        investmentStatusList.add(new InvestmentStatusList("3432", "600.00 USD", "proof upload", "cancel", 3));


        investmentStatusAdapter = new InvestmentStatusAdapter(investmentStatusList, getApplicationContext(),this);
        isaRvStatuslist.setAdapter(investmentStatusAdapter);
    }

    private void initComponents() {
    }


}
