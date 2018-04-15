package com.gameco.cakin.automotiveservices.onesignal;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.google.gson.Gson;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by cakin on 1/3/2018.
 */

public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
    private Activity activity;
    private myNotificationController notificationController;
    public NotificationOpenedHandler(Activity activity){
        this.activity = activity;
        notificationController = new myNotificationController(activity);
    }
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        try{
            JSONObject data = result.notification.payload.additionalData;
            String tmp = data.toString();
            if(!tmp.contains("Accepted!")&& !tmp.contains("Rejected!")){
                Gson gson = new Gson();
                Challenge receivedChallenge = gson.fromJson(tmp,Challenge.class);
                notificationController.acceptOrDeclineChallenge(receivedChallenge);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


// String challengeObject = gson.toJson(test);
//   Challenge receivedChallenge = gson.fromJson(challengeObject,Challenge.class);
//        if (data != null) {
//            String myCustomData = data.optString("key", null);
//            Log.e("------HOPPPP-!---",myCustomData);
//        }
//        OSNotificationAction.ActionType actionType = result.action.type;
//        if (actionType == OSNotificationAction.ActionType.ActionTaken)
//            Log.e("------HOPPPP-!---", "Button pressed with id: " + result.action.actionID);
//
//    //    notificationController.acceptOrDeclineChallenge(notificationController.getChallenge());

