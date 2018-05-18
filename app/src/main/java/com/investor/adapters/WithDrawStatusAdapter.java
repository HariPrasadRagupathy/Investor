package com.investor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.investor.InvestmentStatus;
import com.investor.R;
import com.investor.Withdraw_status;
import com.investor.models.InvestmentStatusList;
import com.investor.models.WithdrawStatusList;

import java.util.ArrayList;

public class WithDrawStatusAdapter extends RecyclerView.Adapter<WithDrawStatusAdapter.MyViewHolder> {


    private final ArrayList<WithdrawStatusList> withdrawStatusList;

    //TextView categoryName;
    ImageView imageUrl;
    Context context;
    Withdraw_status withdraw_status;


    public WithDrawStatusAdapter(ArrayList<WithdrawStatusList> withdrawStatusList, Context context, Withdraw_status withdraw_status) {
        this.withdrawStatusList = withdrawStatusList;
        this.context = context;
        this.withdraw_status = withdraw_status;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_investment_status, parent, false);

        //   view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.status_id.setText(context.getResources().getString(R.string.cnt_withdrawid)+withdrawStatusList.get(position).getStatus_id());
        holder.statusamount.setText(context.getResources().getString(R.string.cnt_amount)+withdrawStatusList.get(position).getAmount());

        if(withdrawStatusList.get(position).getStatus_mode()==1)
        {
            holder.statustext_ll.setVisibility(View.VISIBLE);

            holder.statusbutton_one_ll.setVisibility(View.GONE);
            holder.statusbutton_two_ll.setVisibility(View.GONE);
            holder.statustext.setText(withdrawStatusList.get(position).getStatus_one());

            //setting Color
            if(withdrawStatusList.get(position).getStatus_one().equalsIgnoreCase("pending"))
            holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appyellow));
            else if(withdrawStatusList.get(position).getStatus_one().equalsIgnoreCase("cancelled"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));
            else if(withdrawStatusList.get(position).getStatus_one().equalsIgnoreCase("approved"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appgreen));
            else if(withdrawStatusList.get(position).getStatus_one().equalsIgnoreCase("Success"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appblue));
        }
        else if(withdrawStatusList.get(position).getStatus_mode()==2)
        {
            holder.statustext_ll.setVisibility(View.VISIBLE);
            holder.statusbutton_one_ll.setVisibility(View.VISIBLE);
            holder.statusbutton_two_ll.setVisibility(View.GONE);
            holder.statustext.setText(withdrawStatusList.get(position).getStatus_one());
            holder.statusbutton_one.setText(withdrawStatusList.get(position).getStatus_two());
            holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appyellow));
            holder.statusbutton_one.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));

            holder.statusbutton_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    withdraw_status.showCustomDialog(context.getResources().getString(R.string.dig_withdraw_title),context.getResources().getString(R.string.dig_withdraw_content),R.drawable.happy,1,3);
                }
            });
        }
        else if(withdrawStatusList.get(position).getStatus_mode()==3)
        {
            holder.statustext_ll.setVisibility(View.GONE);
            holder.statusbutton_one_ll.setVisibility(View.VISIBLE);
            holder.statusbutton_two_ll.setVisibility(View.VISIBLE);
            holder.statusbutton_one.setText(withdrawStatusList.get(position).getStatus_one());
            holder.statusbutton_two.setText(withdrawStatusList.get(position).getStatus_two());
            holder.statusbutton_one.setBackgroundTintList(context.getResources().getColorStateList(R.color.appblue));
            holder.statusbutton_two.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));

            holder.statusbutton_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"pending implementation",Toast.LENGTH_LONG).show();
                }
            });


            holder.statusbutton_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    withdraw_status.showCustomDialog("Cancel","Are you sure to cancel Investment?",R.drawable.cancel_investment,1,3);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return withdrawStatusList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView status_id;
        private final TextView statusamount;
        private final TextView statustext;
        private final Button statusbutton_one;
        private final Button statusbutton_two;
        private final LinearLayout statustext_ll;
        private final LinearLayout statusbutton_one_ll;
        private final LinearLayout statusbutton_two_ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            status_id = (TextView) itemView.findViewById(R.id.li_tv_statusid);
            statusamount = (TextView) itemView.findViewById(R.id.li_tv_statusamount);
            statustext = (TextView) itemView.findViewById(R.id.li_tv_status);
            statusbutton_one = (Button) itemView.findViewById(R.id.li_bt_one);
            statusbutton_two = (Button) itemView.findViewById(R.id.li_bt_two);
            statustext_ll = (LinearLayout) itemView.findViewById(R.id.li_ll_tv_status);
            statusbutton_one_ll = (LinearLayout) itemView.findViewById(R.id.li_ll_bt_one);
            statusbutton_two_ll = (LinearLayout) itemView.findViewById(R.id.li_ll_bt_two);
        }
    }
}
