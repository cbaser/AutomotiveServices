package com.gameco.cakin.automotiveservices.activites.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gameco.cakin.automotiveservices.R;

public class FragmentFriendlist extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friendslist, container, false);
        return view;
    }

}
