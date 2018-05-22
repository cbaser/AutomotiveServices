package com.gameco.cakin.automotiveservices.activites.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;

import java.util.ArrayList;

/**
 * Created by cakin on 11/22/2017.
 */

public class FragmentHome extends Fragment {
    private myNotificationController controller;
    private MyFirebaseDatabase firebaseDatabase;
    private ImageView imageView;
    private TextView txtScore, txtProgressCount, txtLevel, txtWelcome, txtheaderName, txtheaderEmail;
    private ProgressBar progressBar;
    private Challenge dailyChallenge,weeklyChallenge;
    private LinearLayout dailyChallengeLayout,weeklyChallengeLayout;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity a;

        if (context instanceof Activity) {
            a = (Activity) context;

        }

    }

    public FragmentHome() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        /**Initialize Part */
        controller = new myNotificationController(this.getActivity());
        firebaseDatabase = new MyFirebaseDatabase(this.getActivity());
        RelativeLayout homeMainLayout = view.findViewById(R.id.home_mainLayout);
        txtScore = homeMainLayout.findViewById(R.id.txtScore);
        txtProgressCount = homeMainLayout.findViewById(R.id.progressCount);
        txtLevel = homeMainLayout.findViewById(R.id.txtLvl);
        progressBar = view.findViewById(R.id.progressBarLevel);
        txtWelcome = view.findViewById(R.id.txtWelcome);
        progressBar.setMax(5);
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        txtheaderName = headerLayout.findViewById(R.id.userTextV);
        txtheaderEmail = headerLayout.findViewById(R.id.userEmailV);
        imageView = (ImageView) homeMainLayout.findViewById(R.id.profPic);
        CardView dailyChallengeCardView = (CardView) view.findViewById(R.id.cardview_daily_content);
        dailyChallengeLayout = (LinearLayout) dailyChallengeCardView.findViewById(R.id.include_daily_challenge_content);
        CardView weeklyChallengeCardView = (CardView) view.findViewById(R.id.cardview_shell_content);
        weeklyChallengeLayout = (LinearLayout) weeklyChallengeCardView.findViewById(R.id.include_shell_challenge_content);

        /** Setup GUI and Challenges From Database*/
        setupGUI();
        setupChallenges();

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupGUI() {
        firebaseDatabase.getProfileImage(imageView);
        CurrentUser currentUser = firebaseDatabase.getUserFromPreferences();
        txtLevel.setText(currentUser.getLevel());
        txtProgressCount.setText(String.valueOf(progressBar.getMax() - currentUser.getChallengeCount()));
        txtScore.setText(String.valueOf(currentUser.getPoints()));
        txtWelcome.setText("Welcome " + currentUser.getNickName() + " To GamECO!");
        progressBar.setProgress((int) currentUser.getChallengeCount());
        txtheaderName.setText(currentUser.getNickName());
        txtheaderEmail.setText(currentUser.getEmail());

    }



    private void setupChallenges(){
        ArrayList<Challenge> challenges = firebaseDatabase.getChallengesFromPreferences();
        for(Challenge challenge :challenges ){
            if(challenge.getChallengeTitle().equals("Daily Challenge"))
                dailyChallenge = challenge;
            else if(challenge.getChallengeTitle().equals("Weekly Challenge"))
                weeklyChallenge =challenge;
        }

        TextView txtDailyTitle = (TextView) dailyChallengeLayout.findViewById(R.id.textDaily_title);
        txtDailyTitle.setText(dailyChallenge.getChallengeTitle());
        TextView txtDailyDescription = (TextView) dailyChallengeLayout.findViewById(R.id.textDaily_Description);
        txtDailyDescription.setText(dailyChallenge.getDescription());

        Button challengeDailyButton = (Button) dailyChallengeLayout.findViewById(R.id.playDailyChallengeButton);
        final int color = Color.parseColor("#2c3e50");
        challengeDailyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showChallengePopup(dailyChallenge);
                //controller.showPopUp(dailyChallenge.getChallengeTitle(), dailyChallenge.getTime(), dailyChallenge.getPoints(), dailyChallenge.getCurrent() ,dailyChallenge.getTarget(), color);

            }
        });


        TextView txtWeeklyTitle = (TextView) weeklyChallengeLayout.findViewById(R.id.textWeekly_Title);
        txtWeeklyTitle.setText(weeklyChallenge.getChallengeTitle());
        TextView txtWeeklyDescription = (TextView) weeklyChallengeLayout.findViewById(R.id.textWeekly_Description);
        txtWeeklyDescription.setText(weeklyChallenge.getDescription());

        Button challengeWeeklyButton = (Button) weeklyChallengeLayout.findViewById(R.id.playWeeklyChallengeButton);
        challengeWeeklyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // controller.showPopUp(weeklyChallenge.getChallengeTitle(), weeklyChallenge.getTime(), weeklyChallenge.getPoints(), weeklyChallenge.getCurrent(), weeklyChallenge.getTarget(), color);//  frontController.showChallenge();
                controller.showChallengePopup(weeklyChallenge);
            }
        });




    }
}