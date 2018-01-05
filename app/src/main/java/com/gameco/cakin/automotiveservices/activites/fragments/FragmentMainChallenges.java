package com.gameco.cakin.automotiveservices.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.FrontController;

/**
 * Created by cakin on 12/8/2017.
 */

public class FragmentMainChallenges extends Fragment implements View.OnClickListener {
    private FrontController frontController;
    public FragmentMainChallenges(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_challenges,container,false);
        Fragment fragment = new FragmentSubAllChallenges();
        frontController = new FrontController(this);

        frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
        Button allChallenges =(Button) view.findViewById(R.id.allChallengesBtn);
        allChallenges.setOnClickListener(this);
        Button yourChallenges = (Button) view.findViewById(R.id.onGoingChallengesBtn);
        yourChallenges.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.allChallengesBtn:
                fragment = new FragmentSubAllChallenges();
                //transitionHelper.replaceFragment(R.id.challengesFrameLayout,fragment);
                frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
                break;

            case R.id.onGoingChallengesBtn:
                fragment = new FragmentSubYourChallenges();
               // transitionHelper.replaceFragment(R.id.challengesFrameLayout,fragment);
                frontController.replaceFragment(R.id.challengesFrameLayout,fragment);
                break;
        }
    }

}
