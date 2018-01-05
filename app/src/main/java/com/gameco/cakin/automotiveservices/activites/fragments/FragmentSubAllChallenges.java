package com.gameco.cakin.automotiveservices.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

/**
 * Created by cakin on 12/24/2017.
 */

public class FragmentSubAllChallenges extends Fragment{
    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;
   private myNotificationController notificationController;
    public  FragmentSubAllChallenges(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_allchallenges,container,false);
         notificationController = new myNotificationController(this);
        Button expandbleBtn1 = (Button) view.findViewById(R.id.expandableButton1);
        Button expandbleBtn2 = (Button) view.findViewById(R.id.expandableButton2);
        Button expandbleBtn3 = (Button) view.findViewById(R.id.expandableButton3);
        Button expandbleBtn4 = (Button) view.findViewById(R.id.expandableButton4);
        Button expandbleBtn5 = (Button) view.findViewById(R.id.expandableButton5);
        expandableLayout1 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout1);
        expandableLayout2 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout2);
        expandableLayout3 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout3);
        expandableLayout4 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout4);
        expandableLayout5 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout5);
        expandbleBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expandableLayout1.toggle();
            }
        });
        expandbleBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout2.toggle();
            }
        });
        expandbleBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout3.toggle();
            }
        });
        expandbleBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout4.toggle();
            }
        });
        expandbleBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout5.toggle();
            }
        });
        return view;
    }



}
