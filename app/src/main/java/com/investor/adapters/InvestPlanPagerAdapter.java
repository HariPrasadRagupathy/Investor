package com.investor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.investor.R;
import com.investor.models.InvestmentPlans;

import java.util.ArrayList;

public class InvestPlanPagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<InvestmentPlans> investmentPlans;

    public InvestPlanPagerAdapter(Context context, ArrayList<InvestmentPlans> investmentPlans) {
        mContext = context;
        this.investmentPlans = investmentPlans;
    }

    @Override
    public int getCount() {
        return investmentPlans.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Using different layouts in the view pager instead of images.

        int resId = -1;
        resId = R.layout.include_investmentplan;
        //Getting my layout's in my adapter. Three layouts defined.


        View view = inflater.inflate(resId, container, false);
        ((ViewPager) container).addView(view, 0);
        TextView nip_tv_planname = (TextView) view.findViewById(R.id.nip_tv_planname);
        TextView nip_tv_investment_amount = (TextView) view.findViewById(R.id.nip_tv_investment_amount);
        TextView nip_tv_investment_roi = (TextView) view.findViewById(R.id.nip_tv_investment_roi);

        nip_tv_planname.setText(mContext.getResources().getString(R.string.cnt_plan_name)+investmentPlans.get(position).getInvestPlanName());
        nip_tv_investment_amount.setText(mContext.getResources().getString(R.string.cnt_plan_amt)+investmentPlans.get(position).getInvestmentAmount());
        nip_tv_investment_roi.setText(mContext.getResources().getString(R.string.cnt_roi)+investmentPlans.get(position).getInvestmemtROI());
        return view;
    }
}
