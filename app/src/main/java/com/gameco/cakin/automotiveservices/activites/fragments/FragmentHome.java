package com.gameco.cakin.automotiveservices.activites.fragments;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.TransitionHelper;
import com.gameco.cakin.automotiveservices.adapters.UserHelper;

/**
 * Created by cakin on 11/22/2017.
 */

public class FragmentHome extends Fragment {
    private TransitionHelper transitionHelper;
    private UserHelper userHelper;
public FragmentHome(){

}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

     View view = inflater.inflate(R.layout.fragment_home,container,false);

        userHelper = new UserHelper();
        transitionHelper = new TransitionHelper();
        transitionHelper.setFragment(this);




        Button challengeButton = (Button) view.findViewById(R.id.playChallengeButton);
        challengeButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          final View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup_daily_challenge,null);
          final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
          popupWindow.showAtLocation(getView(), Gravity.NO_GRAVITY,10,10);
          Button start_challenge = (Button) popupView.findViewById(R.id.start_challenge_yourself_button);
          start_challenge.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  transitionHelper.sendNotification("Challenge Started!","Duration: 7 days!");
                  popupWindow.dismiss();

              }
          });
          Button send_challenge = (Button) popupView.findViewById(R.id.send_challenge_to_friend_button);
          send_challenge.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  userHelper.sendNotification();
              }
          });
            FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitFAB);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

        }
    });
    Button seeAllFriendsButton = (Button) view.findViewById(R.id.seeFriendsBtn);

    seeAllFriendsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            transitionHelper.showFriends();
        //    FragmentFriends fragmentFriends = new FragmentFriends();
        //    FragmentManager fm = getActivity().getSupportFragmentManager();
         //   fragmentFriends.show(fm,"Dialog Fragment");
        }
    });


        userHelper.setUserInformation(view,getActivity().getSharedPreferences("UserInfo",0));



return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
