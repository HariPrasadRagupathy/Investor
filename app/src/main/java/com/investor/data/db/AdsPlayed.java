package com.investor.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.autoax.driver.db.model.Ads_Details;
import com.autoax.driver.db.tables.AdsCount_class;
import com.autoax.driver.utils.AdsPlayedAdapter;
import com.autoax.driver.utils.SessionSave;
import com.crashlytics.android.Crashlytics;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsPlayed extends MainActivity {

    @BindView(R.id.tv_assigned_ads)
    TextView tvAssignedAds;
    private TextView tv_not_found;
    RecyclerView recyclerView;

    @Override
    public boolean setVideoPlay() {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
        //onResumeActiveCheck();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // onPauseActiveCheck();
    }

    private void setData() {
        try {
            DBHelper db = new DBHelper(getApplicationContext());

            SQLiteDatabase dbobj = db.getReadableDatabase();

            List<Ads_Details> adList = AdsCount_class.fetch_AdsTables_wholequery(dbobj, "_id,adId,adName,playedDate,playedTime,count(*)", "group by adName,playedDate");

            if (adList.size() > 0) {
                Log.e("size", adList.size() + "");
                Log.e("size", adList.get(0).getTotalPlayCount() + "");
                tv_not_found.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                AdsPlayedAdapter mAdapter = new AdsPlayedAdapter(adList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            } else {
                recyclerView.setVisibility(View.GONE);
                tv_not_found.setVisibility(View.VISIBLE);
            }

            dbobj = null;
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            Crashlytics.logException(e);
        }
    }

    @Override
    public int setLayout() {
        return R.layout.activity_ads_played;
    }

    @Override
    public void Initialize() {
        try {
            tv_not_found = (TextView) findViewById(R.id.tv_not_found);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

            tv_not_found.setVisibility(View.GONE);
            setData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        try {
            if(!SessionSave.getSession("ad_videos", AdsPlayed.this).isEmpty())
            tvAssignedAds.setText(SessionSave.getSession("ad_videos", AdsPlayed.this));
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"error ads", Toast.LENGTH_LONG).show();
        }
    }
}
