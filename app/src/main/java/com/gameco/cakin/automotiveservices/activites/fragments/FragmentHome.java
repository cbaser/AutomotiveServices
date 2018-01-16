package com.gameco.cakin.automotiveservices.activites.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.FrontController;
import com.gameco.cakin.automotiveservices.controller.myFragmentController;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;

/**
 * Created by cakin on 11/22/2017.
 */

public class FragmentHome extends Fragment {
    private FrontController frontController;
    private Fragment fragment;
    private myNotificationController controller;
public FragmentHome(){

}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.fragment_home,container,false);
     fragment = this;
     controller = new myNotificationController(this);
        frontController = new FrontController(this);
        frontController.createFragment(view);
        CardView cardView = (CardView) view.findViewById(R.id.cardview_daily_content);
        LinearLayout linearLayout = (LinearLayout) cardView.findViewById(R.id.include_daily_challenge_content);
        Button challengeButton = (Button) linearLayout.findViewById(R.id.playChallengeButton);
        final int color = Color.parseColor("#2c3e50");
    challengeButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         controller.showPopUp("Daily Challenge!","Time : One Week","Points to get : 2000","Current Consumption 8.4l /100km","Target Consumption : 7.9l /100km",color);//  frontController.showChallenge();
       }
    });
    Button seeAllFriendsButton = (Button) view.findViewById(R.id.seeFriendsBtn);
    seeAllFriendsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            frontController.showFriends();

        }
    });

//    RelativeLayout relativeLayout1 = (RelativeLayout) view.findViewById(R.id.challengesLayoutHome);
//    Button showBtn = (Button) relativeLayout1.findViewById(R.id.seeYourChallengesBtn);
//    showBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//           // myFragmentController fragmentController = new myFragmentController(fragment);
//          //  fragmentController.replaceFragment(R.id.home_frameLayout,new FragmentMainChallenges());
//            final Dialog dialog = new Dialog(fragment.getContext());
//            dialog.setContentView(R.layout.fragment_sub_yourchallenges);
//
//            dialog.setTitle("Title...");
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//            dialog.show();
//
//        }
//    });
return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
