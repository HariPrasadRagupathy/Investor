package com.investor.view;

import com.investor.Notifications;
import com.investor.models.NotificationList;
import com.investor.models.NotificationResponse;

import java.util.ArrayList;

public interface NotificationsContractor {

    interface view {
        void notificationList(ArrayList<NotificationList> notifications);

    }

    interface presenter {
        void getNotificationList();


    }

}
