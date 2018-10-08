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
import com.investor.models.DepositHistoryResponse;
import com.investor.models.InvestmentStatusList;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

public class InvestmentStatusAdapter extends RecyclerView.Adapter<InvestmentStatusAdapter.MyViewHolder> {


    private final ArrayList<DepositHistoryResponse.Detail> investmentStatusList;

    //TextView categoryName;
    ImageView imageUrl;
    Context context;
    InvestmentStatus investmentStatus;


    public InvestmentStatusAdapter(ArrayList<DepositHistoryResponse.Detail> investmentStatusList, Context context, InvestmentStatus investmentStatus) {
        this.investmentStatusList = investmentStatusList;
        this.context = context;
        this.investmentStatus = investmentStatus;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.status_id.setText(context.getResources().getString(R.string.cnt_invid)+investmentStatusList.get(position).getId());
        holder.statusamount.setText(context.getResources().getString(R.string.cnt_amount)+investmentStatusList.get(position).getAmount());

        if(Integer.parseInt(investmentStatusList.get(position).getStatus())==1)
        {
            holder.statustext_ll.setVisibility(View.VISIBLE);

            holder.statusbutton_one_ll.setVisibility(View.GONE);
            holder.statusbutton_two_ll.setVisibility(View.GONE);
            holder.statustext.setText(context.getResources().getString(R.string.cnt_approved));

            //setting Color
          /*  if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("pending"))
            holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appyellow));
            else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("cancelled"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));*/
           // else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("approved"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appgreen));
           /* else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("Success"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appblue));*/
        }
        else if(Integer.parseInt(investmentStatusList.get(position).getStatus())==2)
        {
            holder.statustext_ll.setVisibility(View.VISIBLE);

            holder.statusbutton_one_ll.setVisibility(View.GONE);
            holder.statusbutton_two_ll.setVisibility(View.GONE);
            holder.statustext.setText(context.getResources().getString(R.string.cnt_pending));

            //setting Color
          /*  if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("pending"))
            holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appyellow));
            else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("cancelled"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));*/
            // else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("approved"))
            holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appyellow));
           /* else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("Success"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appblue));*/
        }
        else if(Integer.parseInt(investmentStatusList.get(position).getStatus())==0)
        {
            holder.statustext_ll.setVisibility(View.GONE);
            holder.statusbutton_one_ll.setVisibility(View.VISIBLE);
            holder.statusbutton_two_ll.setVisibility(View.VISIBLE);
            holder.statusbutton_one.setText(context.getResources().getString(R.string.cnt_proof_upload));
            holder.statusbutton_two.setText(context.getResources().getString(R.string.cnt_cancel));
            holder.statusbutton_one.setBackgroundTintList(context.getResources().getColorStateList(R.color.appblue));
            holder.statusbutton_two.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));

            holder.statusbutton_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(investmentStatus);*/
                    investmentStatus.moveToModePayment(investmentStatusList.get(position).getId().toString(),investmentStatusList.get(position).getPlanId());

                //    Toast.makeText(context,"pending implementation",Toast.LENGTH_LONG).show();
                }
            });


            holder.statusbutton_two.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    investmentStatus.showCustomInvestDialog(context.getResources().getString(R.string.dig_invest_title),context.getResources().getString(R.string.dig_invest_content),R.drawable.cancel_investment,1,3,investmentStatusList.get(position).getId().toString());
                }
            });
        }
        else
        {
            holder.statustext_ll.setVisibility(View.VISIBLE);

            holder.statusbutton_one_ll.setVisibility(View.GONE);
            holder.statusbutton_two_ll.setVisibility(View.GONE);
            holder.statustext.setText(context.getResources().getString(R.string.cnt_cancelled));

            //setting Color
          /*  if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("pending"))
            holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appyellow));
            else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("cancelled"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));*/
            // else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("approved"))
            holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appred));
           /* else if(investmentStatusList.get(position).getStatus_one().equalsIgnoreCase("Success"))
                holder.statustext.setBackgroundTintList(context.getResources().getColorStateList(R.color.appblue));*/
        }

    }

    @Override
    public int getItemCount() {
        return investmentStatusList.size();
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
