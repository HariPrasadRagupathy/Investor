package com.investor.utils;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.investor.DashBoard;
import com.investor.Notifications;
import com.investor.R;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;
import com.investor.models.NotificationList;
import com.investor.models.NotificationResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Belal on 12/8/2017.
 */

//class extending FirebaseMessagingService
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private NotificationResponse notification;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.e("gcm", "messagereceived");
        //if the message contains data payload
        //It is a map of custom keyvalues
        //we can read it easily
        if (remoteMessage.getData().size() > 0) {
            //handle the data message here
        }

        //getting the title and the body
        //  String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getData().get("body");

        SessionManager.getSessionString(getApplicationContext(), SharedKeys.NOTIFICATIONLIST, "");

        ArrayList<NotificationList> list = new ArrayList<NotificationList>();

        try {
            JSONArray arr = new JSONArray(SessionManager.getSessionString(getApplicationContext(), SharedKeys.NOTIFICATIONLIST, ""));
            Log.e("notification", arr.length() + "");
            for (int i = 0; i < arr.length(); i++) {

                JSONObject obj = arr.getJSONObject(i);

                list.add(new NotificationList(R.drawable.profile_3x, obj.get("notif_details").toString(), obj.get("notif_date").toString()));

            }
        } catch (Exception e) {
            Log.e("notification", e.getMessage() + "");
        }

        notification = new Gson().fromJson(body, NotificationResponse.class);
        list.add(new NotificationList(R.drawable.profile_3x, notification.getMessage(), notification.getDate()));




        Gson gson = new Gson();
        String json = gson.toJson(list);

        Log.e("notification", json);
        SessionManager.saveSessionString(getApplicationContext(), SharedKeys.NOTIFICATIONLIST, json);

        sendNotification(notification.getTitle(), notification.getMessage());

        Log.e("gcm", body);
        //then here we can use the title and body to build a notification
    }


    public void sendNotification(String title, String message) {

        //Get an instance of NotificationManager//
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getApplicationContext(), Notifications.class);

        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(title)
                        .setSound(soundUri)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)

                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .addAction(R.drawable.ic_launcher, "View", pIntent);

        mBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL;
        // Gets an instance of the NotificationManager service//
        mBuilder.setContentIntent(pIntent);
        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001//

        mNotificationManager.notify(001, mBuilder.build());
    }
}