package com.investor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.investor.adapters.InvestmentStatusAdapter;
import com.investor.adapters.WithDrawStatusAdapter;
import com.investor.models.InvestmentStatusList;
import com.investor.models.WithdrawStatusList;
import com.investor.utils.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class Withdraw_status extends BaseActivity {

    @BindView(R.id.wsa_rv_statuslist)
    RecyclerView wsaRvStatuslist;

    private static RecyclerView.Adapter withdrawStatusAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<WithdrawStatusList> withdraStatusList;

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
        setContent();
    }

    private void setContent() {
        wsaRvStatuslist.setHasFixedSize(true);

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
        wsaRvStatuslist.setAdapter(withdrawStatusAdapter);
    }

    private void initComponents() {
    }




}
