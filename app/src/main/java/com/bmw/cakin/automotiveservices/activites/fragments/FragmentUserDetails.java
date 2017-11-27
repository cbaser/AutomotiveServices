package com.bmw.cakin.automotiveservices.activites.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bmw.cakin.automotiveservices.R;

/**
 * Created by cakin on 11/5/2017.
 */

public class FragmentUserDetails extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_userdetails,container,false);
        return view;
    }
}
