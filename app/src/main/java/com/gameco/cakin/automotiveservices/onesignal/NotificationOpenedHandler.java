package com.gameco.cakin.automotiveservices.onesignal;



import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.gson.Gson;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by cakin on 1/3/2018.
 */
/*
 */




public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

    private myNotificationController notificationController;
    private MyFirebaseDatabase myFirebaseDatabase;
    public NotificationOpenedHandler(Activity activity){
        notificationController = new myNotificationController(activity);
        myFirebaseDatabase = new MyFirebaseDatabase(activity);
    }
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        try{

            JSONObject data = result.notification.payload.additionalData;
            JSONArray array = data.getJSONArray("dataFromNotification");
            Gson gson = new Gson();
            String tmp = data.toString();
            if(tmp.contains("challenge")){
                Challenge challenge = gson.fromJson(array.getJSONObject(0).toString(),Challenge.class);
                CurrentUser currentUser = gson.fromJson(array.getJSONObject(1).toString(),CurrentUser.class);
                notificationController.acceptOrDeclineChallenge(challenge,currentUser);
            }
            if(tmp.contains("Friend Requests")){
                CurrentUser currentUser = gson.fromJson(array.getJSONObject(1).toString(),CurrentUser.class);
                myFirebaseDatabase.addFriend(currentUser.getEmail());

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




