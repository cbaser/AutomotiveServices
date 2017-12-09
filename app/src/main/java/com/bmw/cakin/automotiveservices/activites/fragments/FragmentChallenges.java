package com.bmw.cakin.automotiveservices.activites.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bmw.cakin.automotiveservices.R;

/**
 * Created by cakin on 12/8/2017.
 */

public class FragmentChallenges extends Fragment {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    public FragmentChallenges(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenges,container,false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();






        return view;
    }
}
