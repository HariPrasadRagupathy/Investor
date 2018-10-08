package com.investor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.investor.presenter.LoginPresenter;
import com.investor.presenter.NewInvestPresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.NewInvestContractor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewInvestment extends BaseActivity implements NewInvestContractor.view {


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
    private NewInvestPresenter presenter;
    private ArrayList<InvestmentPlans.Detail> planDetails;
    private boolean iscalculated = false;

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

        presenter = new NewInvestPresenter(this, this);

        niaEtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                iscalculated = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setContent() {
        if (checkInternet()) {
            presenter.investmentPlans();

            //   setInvestmentPlan();
            setDuration();
        } else
            showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
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
       /* ArrayList<InvestmentPlans> investmentPlans = new ArrayList<InvestmentPlans>();
        investmentPlans.add(new InvestmentPlans("Plan One", "1000 USD", "2.34%"));
        investmentPlans.add(new InvestmentPlans("Plan Two", "4000 USD", "1.14%"));
        investmentPlans.add(new InvestmentPlans("Plan Three", "3000 USD", "1.34%"));
        Log.e("investment", "enter");
        InvestPlanPagerAdapter investPlanPagerAdapter = new InvestPlanPagerAdapter(getApplicationContext(), investmentPlans);
        investmentPlanpager.setAdapter(investPlanPagerAdapter);
        investmentPlanpager.setCurrentItem(0);*/
    }


    @OnClick(R.id.nia_bt_proceed)
    public void moveToConfirmInvestment() {
        if (!niaEtAmount.getText().toString().equals("")) {
            if ((Double.parseDouble(niaEtAmount.getText().toString()) >= Double.parseDouble(planDetails.get(investmentPlanpager.getCurrentItem()).getMinValue())) && (Double.parseDouble(niaEtAmount.getText().toString()) <= Double.parseDouble(planDetails.get(investmentPlanpager.getCurrentItem()).getMaxValue()))) {
                if (iscalculated) {
                    Intent moveToConfirmInvestment = new Intent(this, Confirm_Investment.class);
                    moveToConfirmInvestment.putExtra("plan_id", planDetails.get(investmentPlanpager.getCurrentItem()).getId());
                    moveToConfirmInvestment.putExtra("plan_name", planDetails.get(investmentPlanpager.getCurrentItem()).getPlanName());
                    moveToConfirmInvestment.putExtra("amount_range", planDetails.get(investmentPlanpager.getCurrentItem()).getMinValue() + "-" + planDetails.get(investmentPlanpager.getCurrentItem()).getMaxValue());
                    moveToConfirmInvestment.putExtra("interest_rate", planDetails.get(investmentPlanpager.getCurrentItem()).getInterest() + "%");
                    moveToConfirmInvestment.putExtra("invest_amount", niaEtAmount.getText().toString());
                    moveToConfirmInvestment.putExtra("monthly_profit", niaTvMonthlyProfit.getText().toString());
                    moveToConfirmInvestment.putExtra("cancel_charge", niaTvCancelCharges.getText().toString());
                    startActivity(moveToConfirmInvestment);
                } else
                    showToast("Please hit calculate once !!");
            } else
                showToast("Please enter amount within plan investment range!!");
        } else
            showToast("Please Enter Amount !!");
    }


    @OnClick(R.id.nia_bt_calculate)
    public void calculateInvestment() {

        if (!niaEtAmount.getText().toString().equals("")) {
            presenter.calculateMonthtlyProfit(Long.parseLong(niaEtAmount.getText().toString()), Long.parseLong(planDetails.get(investmentPlanpager.getCurrentItem()).getInterest()));
            iscalculated = true;
        } else
            showToast("Please Enter Amount !!");

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

    @Override
    public void investmentPlans(ArrayList<InvestmentPlans.Detail> planDetails) {
        this.planDetails = planDetails;
        InvestPlanPagerAdapter investPlanPagerAdapter = new InvestPlanPagerAdapter(getApplicationContext(), planDetails);
        investmentPlanpager.setAdapter(investPlanPagerAdapter);
        investmentPlanpager.setCurrentItem(0);
    }

    @Override
    public void montlyProfit(double amount) {
        niaTvMonthlyProfit.setText(String.format("%.2f", amount) + " EUR");
        niaTvCancelCharges.setText("0.0 EUR");
    }
}
