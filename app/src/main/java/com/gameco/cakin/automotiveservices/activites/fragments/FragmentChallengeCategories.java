package com.gameco.cakin.automotiveservices.activites.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.ChallengeCategoriesAdapter;
import com.gameco.cakin.automotiveservices.adapters.MyChallengesAdapter;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by cakin on 12/24/2017.
 */

public class FragmentChallengeCategories extends Fragment{

    private ImageView image;
    private View view;
    public static FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private myNotificationController controller;
    private ArrayList<Challenge> challengeList;
    private RecyclerView recyclerView;
    private ChallengeCategoriesAdapter challengeCategoriesAdapter;


    public FragmentChallengeCategories(){

    }
    private void getChallengesFromFirebase(){
        challengeList = new ArrayList<>();
        //      challengeList.clear();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        //   mRef = mDatabase.getReference().child("Users").child(LoginActivity.user_full_name);
       // mRef = mDatabase.getReference().child("Users").child(user.getEmail().replace(".",","));
        mRef = mDatabase.getReference();
        mRef.child("Challenges").child("Title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{

                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for(DataSnapshot childrenShot : children){
                        Challenge challenge = childrenShot.getValue(Challenge.class);
                        challengeList.add(challenge);
                    }


//                    for(DataSnapshot childrenShot : children){
//                        Iterable<DataSnapshot>innerChildren = childrenShot.getChildren();
//                        for (DataSnapshot challengeShot : innerChildren){
//                            Challenge challenge =challengeShot.getValue(Challenge.class);
//                            challengeList.add(challenge);
                     //   }
                    //}
                    setRecyclerView();
                }catch (Exception e){
                    Log.e("ERRORRR",e.getMessage());
                    // e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
    private void setRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewChallengeCategories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        challengeCategoriesAdapter = new ChallengeCategoriesAdapter(challengeList,this);
        recyclerView.setAdapter(challengeCategoriesAdapter);
        challengeCategoriesAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_challenge_categories,container,false);
        // ListView listView = view.findViewById(R.id.challengesList);
        controller = new myNotificationController(this);
        try{
            getChallengesFromFirebase();
        }catch (Exception e){
            Log.e("PROBLEM",e.getMessage());
        }
        return view;
    }



//    ExpandableRelativeLayout expandableLayout1, expandableLayout2, expandableLayout3, expandableLayout4, expandableLayout5;
//   private myNotificationController notificationController;
//   private String title,time,current,target;
//   private long point;
//   private int color;
//    public FragmentChallengeCategories(){
//
//    }
//    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_challenge_categories, container, false);
//        notificationController = new myNotificationController(this);
//
//
//
//
//
//
//
//        Button expandbleBtn1 = (Button) view.findViewById(R.id.expandableButton1);
//        Button expandbleBtn2 = (Button) view.findViewById(R.id.expandableButton2);
//        Button expandbleBtn3 = (Button) view.findViewById(R.id.expandableButton3);
//        Button expandbleBtn4 = (Button) view.findViewById(R.id.expandableButton4);
//
//        expandableLayout1 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout1);
//        expandableLayout2 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout2);
//        expandableLayout3 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout3);
//        expandableLayout4 = (ExpandableRelativeLayout) view.findViewById(R.id.expandableLayout4);
//        expandbleBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout1.toggle();
//            }
//        });
//        expandbleBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout2.toggle();
//            }
//        });
//        expandbleBtn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout3.toggle();
//            }
//        });
//        expandbleBtn4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                expandableLayout4.toggle();
//            }
//        });
//
//        View.OnClickListener onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch((Integer) view.getTag()){
//                    case 1:
//                        title = "REWE Special Challenge!";
//                        time = "Time : One week";
//                        point = 500;
//                   //     point = "500 Points";
//                        current = ((TextView) expandableLayout1.findViewById(R.id.rewe_cardview_description)).getText().toString() ;
//                        target = ((TextView) expandableLayout1.findViewById(R.id.reweTextView)).getText().toString()+" "+((TextView) expandableLayout1.findViewById(R.id.rewe2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//                    case 2:
//                        title = "CO2 Challenge!";
//                        time = "Time :One week";
//                        point = 500;
//                     //   point = "Points to get :500 Points";
//                        current = ((TextView) expandableLayout2.findViewById(R.id.description_co2_description)).getText().toString()+ " "+((TextView) expandableLayout2.findViewById(R.id.currentCo2Textview)).getText().toString() ;
//                        target = ((TextView) expandableLayout2.findViewById(R.id.targetCo2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//                    case 3:
//                        title = "Fuel Challenge!";
//                        time = "Time : Two weeks";
//                        point = 1000;
//                 //       point = "Points to get :1000 Points";
//                        current = ((TextView) expandableLayout3.findViewById(R.id.fuel_cardview_description)).getText().toString()+ " "+((TextView) expandableLayout3.findViewById(R.id.fuelTextView)).getText().toString() ;
//                        target = ((TextView) expandableLayout3.findViewById(R.id.fuel2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//                    case 4:
//                        title = "Driving Style Challenge!";
//                        time = "Time : Two weeks";
//                    //    point = "Points to get : 1000 Points";
//                        point = 1000;
//                        current = ((TextView) expandableLayout4.findViewById(R.id.driving_cardview_description)).getText().toString()+" "+ ((TextView) expandableLayout4.findViewById(R.id.drivingTextView)).getText().toString() ;
//                        target = ((TextView) expandableLayout4.findViewById(R.id.driving2TextView)).getText().toString();
//                        color = Color.parseColor("#2c3e50");
//                        break;
//
//
//                }
//
//
//             notificationController.showPopUp(title,time,point,current,target,color);
//            }
//        };
//
//
//        Button reweBtn = (Button) view.findViewById(R.id.btn_rewe);
//        reweBtn.setTag(1);
//        reweBtn.setOnClickListener(onClickListener);
//
//        Button co2Btn = (Button) view.findViewById(R.id.btn_co2);
//        co2Btn.setTag(2);
//        co2Btn.setOnClickListener(onClickListener);
//
//        Button fuelBtn = (Button) view.findViewById(R.id.btn_fuel);
//        fuelBtn.setTag(3);
//        fuelBtn.setOnClickListener(onClickListener);
//
//        Button drivingBtn = (Button) view.findViewById(R.id.btn_driving_style);
//        drivingBtn.setTag(4);
//        drivingBtn.setOnClickListener(onClickListener);
//       return view;

    }


