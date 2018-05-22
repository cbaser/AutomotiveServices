package com.gameco.cakin.automotiveservices.adapters;


import android.animation.ObjectAnimator;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.myNotificationController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;


import java.util.List;

public class ChallengeCategoriesAdapter extends RecyclerView.Adapter<ChallengeCategoriesAdapter.MyViewHolder> {
    private List<Challenge> challengeList;
    private TextView txtTitle,txtDescription,txtTarget,txtCurrent,expandableLayout_title;
    private RelativeLayout expandableRelativeLayout;
    private Button expandableButton,startChallengeButton;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private myNotificationController controller;
    public ChallengeCategoriesAdapter(List<Challenge> challengeList, Fragment fragment) {
        this.challengeList = challengeList;
        controller = new myNotificationController(fragment.getActivity());
        for (int i = 0; i < challengeList.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_challenge_categories, parent, false);
        return new ChallengeCategoriesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        final Challenge challenge = challengeList.get(holder.getAdapterPosition());
        holder.setIsRecyclable(false);
        txtTitle.setText(challenge.getChallengeTitle());
        txtCurrent.setText(challenge.getCurrent());
        txtDescription.setText(challenge.getDescription());
        txtTarget.setText(challenge.getTarget());
        startChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.showChallengePopup(challenge);
                // controller.showPopUp(challenge.getChallengeTitle(),challenge.getTime(),challenge.getPoints(),challenge.getCurrent(),challenge.getTarget(), color);
            }
        });




        holder.expandableLayout.setInRecyclerView(true);
        holder.expandableLayout.collapse();
        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                super.onPreOpen();

                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                super.onPreClose();

                expandState.put(position, false);
            }
        });



        expandableButton.setText(challenge.getChallengeTitle());
        expandableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.expandableLayout.toggle();
            }
        });




    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ExpandableLinearLayout expandableLayout;
        public LinearLayout linearLayout;



         MyViewHolder(View itemView) {
            super(itemView);

            expandableButton = itemView.findViewById(R.id.expandableButton);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
            txtTitle = itemView.findViewById(R.id.expandableLayout_description_title);
            txtDescription = itemView.findViewById(R.id.expandableLayout_description);
            txtCurrent = itemView.findViewById(R.id.expandableLayout_current);
            txtTarget = itemView.findViewById(R.id.expandableLayout_target);
            startChallengeButton = itemView.findViewById(R.id.expandableLayout_start_challenge_button);
        }
    }

}
