package com.gameco.cakin.automotiveservices.onesignal;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by cakin on 1/3/2018.
 */

public class NotificationHandler implements OneSignal.NotificationOpenedHandler {
    private Activity activity;
    private myNotificationController notificationController;
    public NotificationHandler(Activity activity){
        this.activity = activity;
        notificationController = new myNotificationController(activity);
    }
    @Override
    public void notificationOpened(OSNotificationOpenResult result) {
        JSONObject data = result.notification.payload.additionalData;
        if (data != null) {
            String myCustomData = data.optString("key", null);
            Log.e("------HOPPPP-!---",myCustomData);
        }
        OSNotificationAction.ActionType actionType = result.action.type;
        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

    //    notificationController.acceptOrDeclineChallenge(notificationController.getChallenge());
        notificationController.acceptOrDeclineChallenge();
    }
}
