package com.gameco.cakin.automotiveservices.onesignal;

import android.app.Activity;
import android.util.Log;


import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.gson.Gson;
import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by cakin on 2/19/2018.
 */



public class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
    private Activity activity;
    private MyFirebaseDatabase myFirebaseDatabase;
    public NotificationReceivedHandler(Activity activity){
        this.activity = activity;
        myFirebaseDatabase = new MyFirebaseDatabase(activity);
    }
    @Override
    public void notificationReceived(OSNotification notification) {
        JSONObject data = notification.payload.additionalData;
        String tmp = data.toString();
        if(tmp.contains("Accepted!")) {
            Gson gson = new Gson();
            Challenge receivedChallenge = gson.fromJson(tmp, Challenge.class);
            myFirebaseDatabase.addAcceptedChallenge(receivedChallenge);
        }
    }
}


