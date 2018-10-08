package com.investor.utils;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.investor.data.shared.SessionManager;
import com.investor.data.shared.SharedKeys;

/**
 * Created by Belal on 12/8/2017.
 */

//the class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {


    //this method will be called
    //when the token is generated
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        //now we will have the token
        String token = FirebaseInstanceId.getInstance().getToken();

       /* if(SessionManager.getSessionString(getApplicationContext(), SharedKeys.USER_ID,"0").equals("0"))
        {*/
            SessionManager.saveSessionString(getApplicationContext(),SharedKeys.TOKEN,token);
            SessionManager.saveSessionBoolean(getApplicationContext(),SharedKeys.ISTOKENUPDATED,false);
      //  }

        //for now we are displaying the token in the log
        //copy it as this method is called only when the new token is generated
        //and usually new token is only generated when the app is reinstalled or the data is cleared
        Log.e("MyRefreshedToken", token);
    }
}