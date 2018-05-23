package com.gameco.cakin.automotiveservices.controller;

import android.app.Activity;

import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;

public class myUserUpdateInfoController {
    private MyFirebaseDatabase firebaseDatabase;
    private Activity activity;
    public myUserUpdateInfoController(Activity activity){
        this.activity = activity;
        firebaseDatabase = new MyFirebaseDatabase(activity);
    }

    public void startUpdate(){
        firebaseDatabase.setUserInfoToPreferences();
        firebaseDatabase.setRankingToPreferences();
        firebaseDatabase.setChallengeInfoToPreferences();
        firebaseDatabase.setUserChallengesToPreferences();
        firebaseDatabase.setUserFriendsToPreferences();
        firebaseDatabase.setUserFriendRequestsToPreferences();
        firebaseDatabase.setChallengeRequestsToPreferences();
    }



}
