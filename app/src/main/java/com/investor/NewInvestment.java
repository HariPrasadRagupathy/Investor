package com.investor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.investor.adapters.InvestPlanPagerAdapter;
import com.investor.models.InvestmentPlans;
import com.investor.utils.BaseActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewInvestment extends BaseActivity {


    @BindView(R.id.nia_vp_investmentpager)
    ViewPager investmentPlanpager;
    @BindView(R.id.nia_et_amount)
    EditText niaEtAmount;
    @BindView(R.id.nia_sp_duration)
    Spinner niaSpDuration;
    @BindView(R.id.nia_bt_calculate)
    Button niaBtCalculate;
    @BindView(R.id.nia_tv_total_profit)
    TextView niaTvTotalProfit;
    @BindView(R.id.nia_tv_monthly_profit)
    TextView niaTvMonthlyProfit;
    @BindView(R.id.nia_tv_cancel_charges)
    TextView niaTvCancelCharges;
    @BindView(R.id.nia_bt_proceed)
    Button niaBtProceed;

    @Override
    protected int setLayout() {
        return R.layout.new_investment_activity;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_newinvestment_title);
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

    private void initComponents() {
    }

    private void setContent() {
        setInvestmentPlan();
        setDuration();
    }

    private void setDuration() {
        //   String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };
        final List<String> list = new ArrayList<String>();
        list.clear();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        niaSpDuration.setAdapter(dataAdapter);

        niaSpDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                //    ((TextView) view).setTextColor(Color.WHITE);
            }
        });

        // Apply the adapter to the spinner
        niaSpDuration.setAdapter(dataAdapter);
    }

    private void setInvestmentPlan() {
        ArrayList<InvestmentPlans> investmentPlans = new ArrayList<InvestmentPlans>();
        investmentPlans.add(new InvestmentPlans("Plan One", "1000 USD", "2.34%"));
        investmentPlans.add(new InvestmentPlans("Plan Two", "4000 USD", "1.14%"));
        investmentPlans.add(new InvestmentPlans("Plan Three", "3000 USD", "1.34%"));
        Log.e("investment", "enter");
        InvestPlanPagerAdapter investPlanPagerAdapter = new InvestPlanPagerAdapter(getApplicationContext(), investmentPlans);
        investmentPlanpager.setAdapter(investPlanPagerAdapter);
        investmentPlanpager.setCurrentItem(0);
    }

    @OnClick(R.id.nia_bt_proceed)
    public void moveToConfirmInvestment() {
        Intent moveToConfirmInvestment = new Intent(this, Confirm_Investment.class);
        startActivity(moveToConfirmInvestment);
    }

    @OnClick(R.id.nia_iv_investmnet_left)
    public void investmentPlanLeft() {
        int tab = investmentPlanpager.getCurrentItem();
        if (tab > 0) {
            tab--;
            investmentPlanpager.setCurrentItem(tab);
        } else if (tab == 0) {
            investmentPlanpager.setCurrentItem(tab);
        }
    }

    @OnClick(R.id.nia_iv_investmnet_right)
    public void investmentPlanRight() {
        int tab = investmentPlanpager.getCurrentItem();
        tab++;
        investmentPlanpager.setCurrentItem(tab);
    }
}
