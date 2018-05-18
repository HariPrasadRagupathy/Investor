package com.investor;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.investor.adapters.InvestPlanPagerAdapter;
import com.investor.models.InvestmentPlans;
import com.investor.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Calculator extends BaseActivity {
    @BindView(R.id.nia_vp_investmentpager)
    ViewPager investmentPlanpager;

    @BindView(R.id.nia_sp_duration)
    Spinner niaSpDuration;

    @Override
    protected int setLayout() {
        return R.layout.activity_calculator;
    }

    @Override
    protected String setTitle() {
        return getResources().getString(R.string.lbl_calculator_title);
    }

    @Override
    protected Boolean setNavigationDrawer() {
        return false;
    }

    @Override
    protected void intialize() {
        setDuration();
        setInvestmentPlan();
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
