package com.gameco.cakin.automotiveservices.activites.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.FrontController;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

/**
 * Created by cakin on 12/8/2017.
 */

public class FragmentMyChallenges extends Fragment  {
    private ImageView image;
    private View view;
    private TextView title, winning,time,friendName;


    private FrontController frontController;


    public FragmentMyChallenges(){

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_main_challenges,container,false);
        Button expandbleBtn1 = (Button) view.findViewById(R.id.expandableButton1);
        Button expandbleBtn2 = (Button) view.findViewById(R.id.expandableButton2);
        Button expandbleBtn3 = (Button) view.findViewById(R.id.expandableButton3);




        expandbleBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image= (ImageView) view.findViewById(R.id.profPic_Can);
                time = (TextView) view.findViewById(R.id.txtTime1);
                friendName =(TextView) view.findViewById(R.id.txtFriendName1);
                title =(TextView) view.findViewById(R.id.txtDescription1);
                showDetails(image,time.getText().toString(),friendName.getText().toString(),
                        title.getText().toString(),
                        "You visited the target REWE!",
                        "Your friend has not visited the target REWE yet!",true);

            }
        });
        expandbleBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image= (ImageView) view.findViewById(R.id.profPic_lea);
                time = (TextView) view.findViewById(R.id.txtTime2);
                friendName =(TextView) view.findViewById(R.id.txtFriendName2);
                title =(TextView) view.findViewById(R.id.txtDescription2);
                showDetails(image,time.getText().toString(),friendName.getText().toString(),
                        title.getText().toString(),
                        "You drove 55 km with ECO Mode",
                        "Your friend drove 13 km with ECO Mode",true);

            }
        });
        expandbleBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image= (ImageView) view.findViewById(R.id.profPic_hampus);
                time = (TextView) view.findViewById(R.id.txtTime3);
                friendName =(TextView) view.findViewById(R.id.txtFriendName3);
                title =(TextView) view.findViewById(R.id.txtDescription3);
                showDetails(image,time.getText().toString(),friendName.getText().toString(),
                        title.getText().toString(),
                "Your fuel consumption 0.1l/100km",
                        "Your friend's fuel consumption 0.2l/100km",false);

            }
        });

        frontController = new FrontController(this);

        return view;
    }

public void showDetails(ImageView image,String time,String friendName,String title,String myCurrent,String friendCurrent,boolean winning)
    {
        View detailView = this.getLayoutInflater().inflate(R.layout.popup_challenge_details,null);
        final PopupWindow detailWindow = new PopupWindow(detailView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        detailWindow.showAtLocation(detailView, Gravity.CENTER,10,10);
        TextView txtDetailTime = (TextView) detailView.findViewById(R.id.txtdetailTime);
        txtDetailTime.setText(time);
        TextView txtDetailTitle = (TextView) detailView.findViewById(R.id.txtdetailTitle);
        txtDetailTitle.setText(title);
        TextView txtDetailMy = (TextView) detailView.findViewById(R.id.txtdetailMy);
        txtDetailMy.setText(myCurrent);
        TextView txtDetailFriend = (TextView) detailView.findViewById(R.id.txtdetailFriend);
        txtDetailFriend.setText(friendCurrent);
        TextView txtDetailFriendName = (TextView) detailView.findViewById(R.id.txtdetailFriendName);
        txtDetailFriendName.setText(friendName);
        TextView txtWinning = (TextView) detailView.findViewById(R.id.txtdetailWinning);
        if(winning)
        {
            txtWinning.setText("Congratulations! You are on the lead ! Keep up the good work");
            txtWinning.setTextColor(getActivity().getResources().getColor(R.color.colorLeaGreen));
        }
        else{
            txtWinning.setText("You are loosing. Try harder!");
            txtWinning.setTextColor(getActivity().getResources().getColor(R.color.orange));
        }



        ImageView friendImage = (ImageView) detailView.findViewById(R.id.txtdetailFriendPic);
        friendImage.setImageDrawable(image.getDrawable());

        FloatingActionButton exitmyChallenge = (FloatingActionButton) detailView.findViewById(R.id.exitdetail);
        exitmyChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailWindow.dismiss();
            }
        });
    }
}
//        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
//        progressBar.setProgress(3);
// frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
//        Button allChallenges =(Button) view.findViewById(R.id.allChallengesBtn);
//        allChallenges.setOnClickListener(this);
//        Button yourChallenges = (Button) view.findViewById(R.id.onGoingChallengesBtn);
//        yourChallenges.setOnClickListener(this);



//        expandbleBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableRelativeLayout2.toggle();
//            }
//        });
//        expandbleBtn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableRelativeLayout3.toggle();
//            }
//        });
//    Fragment fragment = new FragmentAllChallenges();
// @Override
//    public void onClick(View view) {
//        Fragment fragment = null;
//
//        switch (view.getId()) {
//            case R.id.allChallengesBtn:
//                fragment = new FragmentAllChallenges();
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
//    }