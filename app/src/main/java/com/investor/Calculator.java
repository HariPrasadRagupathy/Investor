package com.investor;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.investor.adapters.InvestPlanPagerAdapter;
import com.investor.models.InvestmentPlans;
import com.investor.presenter.NewInvestPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.NewInvestContractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Calculator extends BaseActivity implements NewInvestContractor.view {
    @BindView(R.id.ca_vp_investmentpager)
    ViewPager investmentPlanpager;

    @BindView(R.id.ca_sp_duration)
    Spinner caSpDuration;
    @BindView(R.id.ca_bt_calculate)
    Button caBtCalculate;
    @BindView(R.id.ca_et_amount)
    EditText caEtAmount;
    @BindView(R.id.ca_tv_total_profit)
    TextView caTvTotalProfit;
    @BindView(R.id.ca_tv_monthly_profit)
    TextView caTvMonthlyProfit;

    int duration = 1;
    private NewInvestPresenter presenter;
    private ArrayList<InvestmentPlans.Detail> planDetails;

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

        presenter = new NewInvestPresenter(this, this);
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
        list.add("10");
        list.add("11");
        list.add("12");
        list.add("13");
        list.add("14");
        list.add("15");
        list.add("16");
        list.add("17");
        list.add("18");


        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);

        caSpDuration.setAdapter(dataAdapter);

        caSpDuration.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                duration = Integer.parseInt(list.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
                //    ((TextView) view).setTextColor(Color.WHITE);
            }
        });

        // Apply the adapter to the spinner
        caSpDuration.setAdapter(dataAdapter);
    }


    private void setInvestmentPlan() {
        if (checkInternet())
            presenter.investmentPlans();
        else
            showCustomDialog("No Internet :(", "Please check Your Internet connection!!", R.drawable.no_internet, 2, 7);
       /* ArrayList<InvestmentPlans> investmentPlans = new ArrayList<InvestmentPlans>();
        investmentPlans.add(new InvestmentPlans("Plan One", "1000 USD", "2.34%"));
        investmentPlans.add(new InvestmentPlans("Plan Two", "4000 USD", "1.14%"));
        investmentPlans.add(new InvestmentPlans("Plan Three", "3000 USD", "1.34%"));
        Log.e("investment", "enter");
        InvestPlanPagerAdapter investPlanPagerAdapter = new InvestPlanPagerAdapter(getApplicationContext(), investmentPlans);
        investmentPlanpager.setAdapter(investPlanPagerAdapter);
        investmentPlanpager.setCurrentItem(0);*/
    }


    @OnClick(R.id.ca_iv_investmnet_left)
    public void investmentPlanLeft() {
        int tab = investmentPlanpager.getCurrentItem();
        if (tab > 0) {
            tab--;
            investmentPlanpager.setCurrentItem(tab);
        } else if (tab == 0) {
            investmentPlanpager.setCurrentItem(tab);
        }
    }

    @OnClick(R.id.ca_iv_investmnet_right)
    public void investmentPlanRight() {
        int tab = investmentPlanpager.getCurrentItem();
        tab++;
        investmentPlanpager.setCurrentItem(tab);
    }

    @Override
    public void investmentPlans(ArrayList<InvestmentPlans.Detail> planDetails) {
        this.planDetails = planDetails;
        InvestPlanPagerAdapter investPlanPagerAdapter = new InvestPlanPagerAdapter(getApplicationContext(), planDetails);
        investmentPlanpager.setAdapter(investPlanPagerAdapter);
        investmentPlanpager.setCurrentItem(0);
    }

    @Override
    public void montlyProfit(double amount) {

        caTvMonthlyProfit.setText(String.format("%.2f", amount) + "EUR");
        caTvTotalProfit.setText(String.format("%.2f", (amount * duration)) + " EUR");

    }


    @OnClick(R.id.ca_bt_calculate)
    public void calculate() {
        if (!caEtAmount.getText().toString().equals("")) {
            presenter.calculateMonthtlyProfit(Double.parseDouble(caEtAmount.getText().toString()), Double.parseDouble(planDetails.get(investmentPlanpager.getCurrentItem()).getInterest()));
        } else
            showToast("Please Enter Amount !!");
    }


}
