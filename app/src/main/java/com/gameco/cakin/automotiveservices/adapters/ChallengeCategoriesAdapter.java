package com.gameco.cakin.automotiveservices.adapters;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.github.aakira.expandablelayout.ExpandableLayout;

import java.util.List;

public class ChallengeCategoriesAdapter extends RecyclerView.Adapter<ChallengeCategoriesAdapter.MyViewHolder> {
    private List<Challenge> challengeList;
    private TextView txtTitle,txtDescription,txtTarget,txtCurrent;
    private Button expandableButton,startChallengeButton;
    private ExpandableLayout expandableLayout;
    private myNotificationController controller;
    private int color;
    public ChallengeCategoriesAdapter(List<Challenge> challengeList, Fragment fragment) {
        this.challengeList = challengeList;
        controller = new myNotificationController(fragment);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_challenge_categories, parent, false);
        return new ChallengeCategoriesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        expandableLayout.setExpanded(false);
        final Challenge challenge = challengeList.get(position);
        expandableButton.setText(challenge.getChallengeTitle());
        expandableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLayout.toggle();
            }
        });
        txtTitle.setText(challenge.getChallengeTitle());
        txtCurrent.setText(challenge.getCurrent());
        txtDescription.setText(challenge.getDescription());
        txtTarget.setText(challenge.getTarget());
        startChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = Color.rgb(44, 62, 80);
                controller.showPopUp(challenge.getChallengeTitle(),challenge.getTime(),challenge.getPoints(),challenge.getCurrent(),challenge.getTarget(), color);
            }
        });



    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder {
        protected MyViewHolder(View itemView) {
            super(itemView);
            LinearLayout linearLayout = itemView.findViewById(R.id.content_challenge_categories_linearLayout);
            expandableButton = linearLayout.findViewById(R.id.expandableButton);
            expandableLayout = linearLayout.findViewById(R.id.expandableLayout);
            txtTitle = linearLayout.findViewById(R.id.expandableLayout_description_title);
            txtDescription = linearLayout.findViewById(R.id.expandableLayout_description);
            txtCurrent = linearLayout.findViewById(R.id.expandableLayout_current);
            txtTarget = linearLayout.findViewById(R.id.expandableLayout_target);
            startChallengeButton = linearLayout.findViewById(R.id.expandableLayout_start_challenge_button);
        }
    }
}
