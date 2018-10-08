package com.investor.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.investor.R;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.NotificationList;
import com.investor.view.ConfirmInvestContractor;
import com.investor.view.NotificationsContractor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class NotificationsPresenter implements NotificationsContractor.presenter {

    NotificationsContractor.view notificationView;
    Context context;

    public NotificationsPresenter(NotificationsContractor.view notificationView, Context context) {
        this.notificationView = notificationView;
        this.context = context;
    }

    @Override
    public void getNotificationList() {

      /*  ArrayList<NotificationList> listsave = new ArrayList<NotificationList>();
        listsave.add(new NotificationList(1, "data1", "date1"));
        listsave.add(new NotificationList(2, "data2", "date2"));


        Gson gson = new Gson();
        String json = gson.toJson(listsave);

        Log.e("notification", json);

        SessionManager.saveSessionString(context, SharedKeys.NOTIFICATIONLIST, json);*/
       // SessionManager.saveSessionString(context, SharedKeys.NOTIFICATIONLIST, "");
        ArrayList<NotificationList> list = new ArrayList<NotificationList>();
        Log.e("notification", SessionManager.getSessionString(context, SharedKeys.NOTIFICATIONLIST, ""));
        try {
            JSONArray arr = new JSONArray(SessionManager.getSessionString(context, SharedKeys.NOTIFICATIONLIST, ""));
            Log.e("notification", arr.length()+"");
            for (int i = 0; i < arr.length(); i++) {

                JSONObject obj = arr.getJSONObject(i);

                list.add(new NotificationList(R.drawable.ic_notifications_active,obj.get("notif_details").toString(),obj.get("notif_date").toString()));

            }

            Collections.reverse(list);


            notificationView.notificationList(list);
        } catch (Exception e) {
            Log.e("notification", e.getMessage());
        }
    }
}
