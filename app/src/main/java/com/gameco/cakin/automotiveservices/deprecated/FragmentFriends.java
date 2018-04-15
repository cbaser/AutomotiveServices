package com.gameco.cakin.automotiveservices.deprecated;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gameco.cakin.automotiveservices.R;

/**
 * Created by cakin on 12/25/2017.
 */

public class FragmentFriends extends DialogFragment {

    public FragmentFriends(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_friends,container,false);






        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        getDialog().getWindow().setLayout(width,height);



        return view;
    }
}
