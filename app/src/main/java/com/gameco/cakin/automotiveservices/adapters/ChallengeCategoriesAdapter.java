package com.gameco.cakin.automotiveservices.adapters;


import android.animation.ObjectAnimator;
import android.app.Activity;
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
import com.github.aakira.expandablelayout.ExpandableLayout;
import com.github.aakira.expandablelayout.ExpandableLayoutListenerAdapter;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.github.aakira.expandablelayout.Utils;


import java.util.List;





public class ChallengeCategoriesAdapter extends RecyclerView.Adapter<ChallengeCategoriesAdapter.MyViewHolder> {
    private List<Challenge> challengeList;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private myNotificationController controller;
    private Activity activity;

    public ChallengeCategoriesAdapter(List<Challenge> challengeList, Fragment fragment) {
        this.challengeList = challengeList;
        this.activity = fragment.getActivity();
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
        holder.expandableLayout.setExpanded(false);

        holder.txtTitle.setText(activity.getString(R.string.popup_challenge_title,challenge.getChallengeTitle()));
        holder.txtCurrent.setText(activity.getString(R.string.popup_challenge_current,challenge.getCurrent()));
        holder.txtDescription.setText(activity.getString(R.string.popup_challenge_description,challenge.getDescription()));
        holder.txtTarget.setText(activity.getString(R.string.popup_challenge_target,challenge.getTarget()));
        holder.txtPoints.setText(activity.getString(R.string.popup_challenge_point,challenge.getPoints()));
        holder.startChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.showChallengePopup(challenge);
                // controller.showPopUp(challenge.getChallengeTitle(),challenge.getTime(),challenge.getPoints(),challenge.getCurrent(),challenge.getTarget(), color);
            }
        });


        holder.expandableButton.setText(challenge.getChallengeTitle());
        holder.expandableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.expandableLayout.toggle();
            }
        });

        holder.expandableLayout.setInRecyclerView(true);

        holder.expandableLayout.setExpanded(expandState.get(position));
        holder.expandableLayout.setListener(new ExpandableLayoutListenerAdapter() {
            @Override
            public void onPreOpen() {
                super.onPreOpen();
                createRotateAnimator(holder.buttonLayout, 0f, 180f).start();
                expandState.put(position, true);
            }

            @Override
            public void onPreClose() {
                super.onPreClose();
                createRotateAnimator(holder.buttonLayout, 180f, 0f).start();
                expandState.put(position, false);
            }
        });

    }
    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public Button expandableButton;
        public TextView textView;
        public Button buttonLayout;
        public ExpandableLinearLayout expandableLayout;
        public TextView txtTitle,txtDescription,txtPoints,txtTarget,txtCurrent;
        public Button startChallengeButton;
         MyViewHolder(View itemView) {
            super(itemView);
             linearLayout =(LinearLayout) itemView.findViewById(R.id.content_challenge_categories_linearLayout);
             expandableButton = (Button) itemView.findViewById(R.id.expandableButton);
             textView = (TextView) itemView.findViewById(R.id.textView);
             expandableLayout = linearLayout.findViewById(R.id.expandableLayout);

            txtTitle = itemView.findViewById(R.id.expandableLayout_description_title);
            txtDescription = itemView.findViewById(R.id.expandableLayout_description);
            txtCurrent = itemView.findViewById(R.id.expandableLayout_current);
            txtTarget = itemView.findViewById(R.id.expandableLayout_target);
            txtPoints = itemView.findViewById(R.id.expandableLayout_points);
            startChallengeButton = itemView.findViewById(R.id.expandableLayout_start_challenge_button);
        }
    }
    public ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }


}

