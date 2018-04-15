package com.gameco.cakin.automotiveservices.activites.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.activites.MainActivity;
import com.gameco.cakin.automotiveservices.adapters.MyChallengesAdapter;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cakin on 12/8/2017.
 */

public class FragmentMyChallenges extends Fragment  {
    private ImageView image;
    private View view;
    public static FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private myNotificationController controller;
    private  ArrayList<Challenge> challengeList;
    private RecyclerView recyclerView;
    private MyChallengesAdapter myChallengesAdapter;


    public FragmentMyChallenges(){

    }
    private void getChallengesFromFirebase(){
        challengeList = new ArrayList<>();
  //      challengeList.clear();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
     //   mRef = mDatabase.getReference().child("Users").child(LoginActivity.user_full_name);
        mRef = mDatabase.getReference().child("Users").child(user.getEmail().replace(".",","));
        mRef.child("Challenges").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for(DataSnapshot childrenShot : children){
                        Iterable<DataSnapshot>innerChildren = childrenShot.getChildren();
                        for (DataSnapshot challengeShot : innerChildren){
                            Challenge challenge =challengeShot.getValue(Challenge.class);
                            challengeList.add(challenge);
                        }
                }
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewChallenges);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        myChallengesAdapter = new MyChallengesAdapter(this.getActivity(),challengeList);
        recyclerView.setAdapter(myChallengesAdapter);
        myChallengesAdapter.notifyDataSetChanged();
    }
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_my_challenges,container,false);
        // ListView listView = view.findViewById(R.id.challengesList);
        controller = new myNotificationController(this);
        try{
            getChallengesFromFirebase();
        }catch (Exception e){
            Log.e("PROBLEM",e.getMessage());
        }
          return view;
    }
}





// setRecyclerView();
//    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                myChallengesAdapter = new MyChallengesAdapter(this.getActivity(), productFromShared);
//                myChallengesAdapter.notifyDataSetChanged();
//                HashMap<String,Challenge> hashMap = (HashMap<String, Challenge>) valuex.get(title);
//                List<Challenge> list = new ArrayList<Challenge>(hashMap.values());
//                Challenge x = (Challenge) hashMap.get(title);
//                for (DataSnapshot challengeShot : challengeChildren){
//                    Challenge challenge =challengeShot.getValue(Challenge.class);
//                    challengeList.add(challenge);
//                    Log.e("IAAAAAAAAAAAAAA",challengeList.size()+"");
//                }
//    private void showDetails(ImageView image, String time, String friendName, String title, String myCurrent, String friendCurrent, boolean winning) {
//        View detailView = this.getActivity().getLayoutInflater().inflate(R.layout.popup_challenge_details,null);
//        final PopupWindow detailWindow = new PopupWindow(detailView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        detailWindow.showAtLocation(detailView, Gravity.CENTER,10,10);
//        TextView txtDetailTime = (TextView) detailView.findViewById(R.id.txtdetailTime);
//        txtDetailTime.setText(time);
//        TextView txtDetailTitle = (TextView) detailView.findViewById(R.id.txtdetailTitle);
//        txtDetailTitle.setText(title);
//        TextView txtDetailMy = (TextView) detailView.findViewById(R.id.txtdetailMy);
//        txtDetailMy.setText(myCurrent);
//        TextView txtDetailFriend = (TextView) detailView.findViewById(R.id.txtdetailFriend);
//        txtDetailFriend.setText(friendCurrent);
//        TextView txtDetailFriendName = (TextView) detailView.findViewById(R.id.txtdetailFriendName);
//        txtDetailFriendName.setText(friendName);
//        TextView txtWinning = (TextView) detailView.findViewById(R.id.txtdetailWinning);
//        if(winning)
//        {
//            txtWinning.setText("Congratulations! You are on the lead ! Keep up the good work");
//            txtWinning.setTextColor(this.getActivity().getResources().getColor(R.color.colorLeaGreen));
//        }
//        else{
//            txtWinning.setText("You are loosing. Try harder!");
//            txtWinning.setTextColor(this.getActivity().getResources().getColor(R.color.orange));
//        }
//
//
//
//        ImageView friendImage = (ImageView) detailView.findViewById(R.id.txtdetailFriendPic);
//        friendImage.setImageDrawable(image.getDrawable());
//
//        FloatingActionButton exitmyChallenge = (FloatingActionButton) detailView.findViewById(R.id.exitdetail);
//        exitmyChallenge.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                detailWindow.dismiss();
//            }
//        });
//
//
//
//    }
//    private TextView title, winning,time,friendName;
//    private ListView listView,listView2;
        //    private static final String PREFS_TAG = "SharedPrefs";
//    private static final String PRODUCT_TAG = "Challenges";
        //  Log.i("TAG",controller.getChallengeList().size()+"");
//        Bundle bundle = this.getArguments();
//        try {
//            String str = bundle.getString("Challenge");
//            Log.e("----------TAG---------",bundle.getString("Challenge"));
//            if (str != null) {
//                Log.e("Hop",str);
//                Gson gson = new Gson();
//                List<Challenge> productFromShared = new ArrayList<>();
//                Type type = new TypeToken<List<Challenge>>() {
//                }.getType();
//                productFromShared = gson.fromJson(str, type);
//
//
//
//            }
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

//        Button expandbleBtn1 = (Button) view.findViewById(R.id.expandableButton1);
//        Button expandbleBtn2 = (Button) view.findViewById(R.id.expandableButton2);
//        Button expandbleBtn3 = (Button) view.findViewById(R.id.expandableButton3);
//        expandbleBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                image= (ImageView) view.findViewById(R.id.profPic_Can);
//                image.setTag(R.drawable.ic_can);
//                time = (TextView) view.findViewById(R.id.txtTime1);
//                int resourceId = R.id.profPic_Can;
//                friendName =(TextView) view.findViewById(R.id.txtFriendName1);
//                title =(TextView) view.findViewById(R.id.txtDescription1);
//                showDetails(image,time.getText().toString(),friendName.getText().toString(),
//                        title.getText().toString(),
//                        "You visited the target REWE!",
//                        "Your friend has not visited the target REWE yet!",true);
//
//            }
//        });
//        expandbleBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                image= (ImageView) view.findViewById(R.id.profPic_lea);
//                int id = R.id.profPic_lea;
//                image.setTag(R.drawable.ic_lea);
//                time = (TextView) view.findViewById(R.id.txtTime2);
//                friendName =(TextView) view.findViewById(R.id.txtFriendName2);
//                title =(TextView) view.findViewById(R.id.txtDescription2);
//                showDetails(image,time.getText().toString(),friendName.getText().toString(),
//                        title.getText().toString(),
//                        "You drove 55 km with ECO Mode",
//                        "Your friend drove 13 km with ECO Mode",true);
//
//            }
//        });
//        expandbleBtn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                image= (ImageView) view.findViewById(R.id.profPic_hampus);
//                image.setTag(R.drawable.ic_hampus);
//                time = (TextView) view.findViewById(R.id.txtTime3);
//                friendName =(TextView) view.findViewById(R.id.txtFriendName3);
//                title =(TextView) view.findViewById(R.id.txtDescription3);
//                showDetails(image,time.getText().toString(),friendName.getText().toString(),
//                        title.getText().toString(),
//                "Your fuel consumption 0.1l/100km",
//                        "Your friend's fuel consumption 0.2l/100km",false);
//
//            }
//        });
        //        Gson gson = new Gson();
//        List<Challenge> productFromShared = new ArrayList<>();
//        SharedPreferences sharedPref = this.getActivity().getApplicationContext().getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE);
//        String jsonPreferences = sharedPref.getString(PRODUCT_TAG, "");
//
//        Type type = new TypeToken<List<Challenge>>() {}.getType();
//        productFromShared = gson.fromJson(jsonPreferences, type);
//
//        return productFromShared;
//                GenericTypeIndicator<HashMap<String, List<Challenge>>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, List<Challenge>>>() {};
//                Map<String, List<Challenge>> hashMap = dataSnapshot.getValue(genericTypeIndicator);
//                for (Map.Entry<String,List<Challenge>> entry : hashMap.entrySet()) {
//                    List<Challenge> challenges = entry.getValue();
//                    for (Challenge challenge: challenges){
//                        Log.i("TEEEEEEEEEEEEEST", challenge.getFriendName());
//                    }
//                }
//                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//                    Challenge challenge = new Challenge();
//                    challenge.setFriendName(postSnapshot.getValue().toString());
//
//                    Log.e("TEEEEEEEEEST",challenge.toString());
//                    Log.e("TEEEEEEEEEST",challenge.getFriendName());
//               }
//                Map<String, Object> td = (HashMap<String,Object>) dataSnapshot.getValue();
//                List<Object> values = (List<Object>) td.values();
//
//                collectChallenges((Map<String,Object>) dataSnapshot.getValue());
//        View popupView = this.getActivity().getLayoutInflater().inflate(R.layout.popup_challenge_details,null);
//        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        ImageView imageView = popupView.findViewById(R.id.txtdetailFriendPic);
//        imageView.setImageResource(R.drawable.ic_cagatay);
//
//        TextView timeView = (TextView) popupView.findViewById(R.id.txtdetailTime);
//        timeView.setText(time);
//        TextView friendNameView = (TextView) popupView.findViewById(R.id.txtdetailFriendName);
//        friendNameView.setText(friendName);
//        TextView titleView = (TextView) popupView.findViewById(R.id.txtdetailTitle);
//        titleView.setText(title);
//        TextView myView = (TextView) popupView.findViewById(R.id.txtdetailMy);
//        myView.setText(my);
//        TextView friendsView = (TextView) popupView.findViewById(R.id.txtdetailFriend);
//        friendsView.setText(friends);
//        TextView detailWinning = (TextView) popupView.findViewById(R.id.txtdetailWinning);
//
//
//        if(!winning) {
//            detailWinning.setText("You are loosing! Be careful!");
//            detailWinning.setTextColor(Color.RED);
//        }
//
//        popupWindow.showAtLocation(popupView, Gravity.CENTER,10,10);



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
//    Fragment fragment = new FragmentChallengeCategories();
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
//    }