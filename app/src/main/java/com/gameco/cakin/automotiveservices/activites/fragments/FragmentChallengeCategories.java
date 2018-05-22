package com.gameco.cakin.automotiveservices.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.ChallengeCategoriesAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
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
        RecyclerView recyclerView =  view.findViewById(R.id.recyclerViewChallengeCategories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);


       ChallengeCategoriesAdapter challengeCategoriesAdapter = new ChallengeCategoriesAdapter(challengeList, this);
        recyclerView.setAdapter(challengeCategoriesAdapter);
        challengeCategoriesAdapter.notifyDataSetChanged();
    }

}