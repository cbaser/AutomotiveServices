package com.gameco.cakin.automotiveservices.activites.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gameco.cakin.automotiveservices.R;

/**
 * Created by cakin on 12/24/2017.
 */

public class FragmentSubYourChallenges extends Fragment {
    private ProgressBar progressBar;
    public FragmentSubYourChallenges(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_yourchallenges,container,false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setProgress(3);
        return view;
    }
}
