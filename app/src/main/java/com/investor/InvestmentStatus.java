package com.investor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.investor.adapters.InvestmentStatusAdapter;
import com.investor.data.shared.SessionManager;
import com.investor.models.DepositHistoryResponse;
import com.investor.models.InvestmentStatusList;
import com.investor.presenter.DepositHistorypresenter;
import com.investor.utils.BaseActivity;
import com.investor.view.DepositHistoryContractor;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestmentStatus extends BaseActivity implements DepositHistoryContractor.view {


    @BindView(R.id.isa_rv_statuslist)
    RecyclerView isaRvStatuslist;

    private static RecyclerView.Adapter investmentStatusAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<InvestmentStatusList> investmentStatusList;
    private DepositHistorypresenter presenter;
    private Uri mCropImageUri;

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
//        setContent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setContent();
    }

    private void setContent() {
        presenter.getDepositHistory();

      /*  isaRvStatuslist.setHasFixedSize(true);

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
        isaRvStatuslist.setAdapter(investmentStatusAdapter);*/
    }

    private void initComponents() {

        presenter = new DepositHistorypresenter(this, this);
    }


    @Override
    public void depositDetails(ArrayList<DepositHistoryResponse.Detail> depositDetails) {
        isaRvStatuslist.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        isaRvStatuslist.setLayoutManager(layoutManager);
        isaRvStatuslist.setItemAnimator(new DefaultItemAnimator());


        investmentStatusAdapter = new InvestmentStatusAdapter(depositDetails, getApplicationContext(), this);
        isaRvStatuslist.setAdapter(investmentStatusAdapter);

    }

    @Override
    public void paymentModeStatus(int mode, int status, String message) {
        if (mode == 1) {
            if (status == 1)
                showCustomDialog("Success", message, R.drawable.happy, 2, 2);
            else
                showCustomDialog("Failed", message, R.drawable.cancel_investment, 2, 2);
        } else if (mode == 2) {

        }
    }


    public void showCustomInvestDialog(String title, String content, int titleimage, int mode, final int moveToActivity, final String orderId) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_light);
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.cm_tv_dialogtitle)).setText(title);
        ((TextView) dialog.findViewById(R.id.cm_tv_dialogcontent)).setText(content);
        // ((CircleImageView) dialog.findViewById(R.id.image)).setImageResource(titleimage);

        CircularImageView image = dialog.findViewById(R.id.cm_iv_dialogimage);
        image.setImageResource(titleimage);

       /* switch (mode) {
            case 1:*/
        dialog.findViewById(R.id.cm_bt_dialogyes).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.cm_bt_dialogno).setVisibility(View.VISIBLE);
        dialog.findViewById(R.id.cm_bt_dialogok).setVisibility(View.GONE);
                /*break;
            case 2:
                dialog.findViewById(R.id.cm_bt_dialogyes).setVisibility(View.GONE);
                dialog.findViewById(R.id.cm_bt_dialogno).setVisibility(View.GONE);
                dialog.findViewById(R.id.cm_bt_dialogok).setVisibility(View.VISIBLE);
                break;
        }*/

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /*((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        //        switch (moveToActivity) {
                        moveToInvestmentStatus();
          //      }
                finish();
            }
        });*/

        ((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogyes)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInternet())
                    presenter.cancelDeposit(orderId);
                else
                    showCustomDialog("No Internet :(","Please check Your Internet connection!!",R.drawable.no_internet,2,7);
                dialog.dismiss();
            /*    switch (moveToActivity) {
                    case 1:
                        moveToSignin();
                        SessionManager.clearSession(getApplicationContext());
                        Log.e("shared","session cleared");
                        break;
                    case 2:
                      moveToInvestmentStatus();
              /*          break;
                    case 3:
                        moveToDashboard();
                        break;
                    case 4:
                        moveToWithdrawStatus();
                        break;
                    case 5:
                        moveToWithdraw();
                        break;
                }*/
                //    finish();
            }
        });

        ((AppCompatButton) dialog.findViewById(R.id.cm_bt_dialogno)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


}
