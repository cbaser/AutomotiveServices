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
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
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

public class FragmentChallengeCategories extends Fragment {
    private MyFirebaseDatabase myFirebaseDatabase;
    private View view;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_challenge_categories, container, false);
        myFirebaseDatabase = new MyFirebaseDatabase(this.getActivity());
        setRecyclerView();
        return view;
    }

    private void setRecyclerView() {
        ArrayList<Challenge> challengeList = myFirebaseDatabase.getChallengesFromPreferences();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewChallengeCategories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


       ChallengeCategoriesAdapter challengeCategoriesAdapter = new ChallengeCategoriesAdapter(challengeList, this);
        recyclerView.setAdapter(challengeCategoriesAdapter);
        challengeCategoriesAdapter.notifyDataSetChanged();
    }

}