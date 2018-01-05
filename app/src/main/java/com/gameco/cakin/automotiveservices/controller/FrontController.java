package com.gameco.cakin.automotiveservices.controller;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Created by cakin on 1/2/2018.
 */

public class FrontController {
    private com.gameco.cakin.automotiveservices.controller.myActivityController myActivityController;
    private com.gameco.cakin.automotiveservices.controller.myFragmentController myFragmentController;
    public FrontController(AppCompatActivity activity){
        myActivityController = new myActivityController(activity);
    }
    public FrontController(Fragment fragment){
        myFragmentController = new myFragmentController(fragment);
    }
    public void createActivity(){
        myActivityController.setUpTabs();
        myActivityController.setupNavigationMenu();
        myActivityController.showDailyChallenge();
    }
    public void createFragment(View view){
        myFragmentController.setUserInformation(view);
    }
    public void showFriends(){
        myFragmentController.showFriends();
    }
    public void replaceFragment(int challengesFrameLayout, Fragment fragment){
        myFragmentController.replaceFragment(challengesFrameLayout,fragment);
    }
    public void showChallenge(){
        myFragmentController.playChallenge();
    }


}
