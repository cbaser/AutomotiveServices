package com.gameco.cakin.automotiveservices.activites.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.FrontController;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 11/22/2017.
 */

public class FragmentHome extends Fragment {
    private FrontController frontController;
    private myNotificationController controller;
    private DatabaseReference mRef;
    public static FirebaseDatabase mDatabase;
    private long challengeCount,points;
    private String remaining;
    private TextView txtScore,txtProgressCount,txtLevel;
    private ProgressBar progressBar;
    private Activity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
        }

    }
public FragmentHome(){

}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.fragment_home,container,false);
     controller = new myNotificationController(this);
        frontController = new FrontController(this);
        frontController.createFragment(view);
        RelativeLayout homeMainLayout = view.findViewById(R.id.home_mainLayout);
         txtScore = homeMainLayout.findViewById(R.id.txtScore);
         txtProgressCount = homeMainLayout.findViewById(R.id.progressCount);
         txtLevel = homeMainLayout.findViewById(R.id.txtLvl);
         progressBar = view.findViewById(R.id.progressBarLevel);

        progressBar.setMax(5);
        try {
            mDatabase =FirebaseDatabase.getInstance();
            mRef = mDatabase.getReference();

            mRef.child("Users").child(LoginActivity.user_full_name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    challengeCount  = (long) dataSnapshot.child("ChallengeCount").getValue();
                    points = (long) dataSnapshot.child("Points").getValue();
                    remaining = String.valueOf(progressBar.getMax()-challengeCount);
                    txtProgressCount.setText(remaining);
                    txtScore.setText(String.valueOf(points));
                    txtLevel.setText((String)dataSnapshot.child("Level").getValue());
                    progressBar.setProgress((int)challengeCount);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            txtProgressCount.setText(remaining);
            txtScore.setText(String.valueOf(points));



        }catch (Exception e){
            e.printStackTrace();
        }

        CardView cardView = (CardView) view.findViewById(R.id.cardview_daily_content);
        LinearLayout linearLayout = (LinearLayout) cardView.findViewById(R.id.include_daily_challenge_content);
        Button challengeDailyButton = (Button) linearLayout.findViewById(R.id.playChallengeButton);
        final int color = Color.parseColor("#2c3e50");
        challengeDailyButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         controller.showPopUp("Daily Challenge!","Time : One Day","Points to get : 2000","Current Consumption 8.4l /100km","Target Consumption : 7.9l /100km",color);//  frontController.showChallenge();

       }
    });
        CardView cardViewShell = (CardView) view.findViewById(R.id.cardview_shell_content);
        LinearLayout shellLayout = (LinearLayout) cardViewShell.findViewById(R.id.include_shell_challenge_content);
        Button challengeShellButton = (Button) shellLayout.findViewById(R.id.playShellChallenge);
        challengeShellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.showPopUp("Weekly Shell Challenge!","Time : One Week","Points to get : 2000","Go to next Shell","Refuel and get free coffee",color);//  frontController.showChallenge();

            }
        });
    Button seeAllFriendsButton = (Button) view.findViewById(R.id.seeFriendsBtn);
    seeAllFriendsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            frontController.showFriends();

        }
    });

return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
//    progressBar.setBackgroundColor(Color.WHITE);
//           Bundle bundle = new Bundle();
//           Gson gson = new Gson();
//
//            List<Challenge> challenges = new ArrayList<>();
//            Challenge challenge = new Challenge();
//            challenge.setFriendName("Cagatay");
//            challenges.add(challenge);
//           String value = gson.toJson(challenges);
//           bundle.putString("Challenge", value);
//
//
//           Log.e("-------Challenge------",bundle.getString("Challenge"));
//           setArguments(bundle);
//        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.tmpFloatAct);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                frontController.replaceFragment();
//            }
//        });



// @Override
//    public void onClick(View view) {
//        Fragment fragment = null;
//
//        switch (view.getId()) {
//            case R.id.allChallengesBtn:
//                fragment = new FragmentChallengeCategories();
//                //transitionHelper.replaceFragment(R.id.challengesFrameLayout,fragment);
//                frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
//                break;
//
//            case R.id.onGoingChallengesBtn:
//                fragment = new FragmentSubYourChallenges();
//               // transitionHelper.replaceFragment(R.id.challengesFrameLayout,fragment);
//                frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
//                break;
//        }









//    RelativeLayout relativeLayout1 = (RelativeLayout) view.findViewById(R.id.challengesLayoutHome);
//    Button showBtn = (Button) relativeLayout1.findViewById(R.id.seeYourChallengesBtn);
//    showBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//           // myFragmentController fragmentController = new myFragmentController(fragment);
//          //  fragmentController.replaceFragment(R.id.home_frameLayout,new FragmentMyChallenges());
//            final Dialog dialog = new Dialog(fragment.getContext());
//            dialog.setContentView(R.layout.fragment_sub_yourchallenges);
//
//            dialog.setTitle("Title...");
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
//            dialog.show();
//
//        }
//    });