package com.gameco.cakin.automotiveservices.adapters;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;

import java.util.ArrayList;


public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.FriendsViewHolder>{
   private Activity activity;
   private ArrayList<CurrentUser> currentUsers;
   private TextView txtName,txtPoints;
   private ImageView profPic;
   private MyFirebaseDatabase myFirebaseDatabase;
   private ImageButton acceptBtn,rejectBtn;
   private String current_friend_state="not_friends";
   private boolean requestType;

    public class FriendsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public FriendsViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            RelativeLayout relativeLayout = view.findViewById(R.id.group_friend_relativeLayout);
            txtName = relativeLayout.findViewById(R.id.group_friend_profile_name);
            txtPoints = relativeLayout.findViewById(R.id.group_friend_profile_points);
            profPic = relativeLayout.findViewById(R.id.group_friend_profile_icon);
            if(requestType){
                 acceptBtn = relativeLayout.findViewById(R.id.group_friend_profile_acceptBtn);
                acceptBtn.setVisibility(View.VISIBLE);

                 rejectBtn = relativeLayout.findViewById(R.id.group_friend_profile_rejectBtn);
                rejectBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onClick(View view) {
            showDetailedView(getLayoutPosition());
        }
    }
    public FriendsListAdapter(Activity activity, ArrayList<CurrentUser> currentUsers){
        this.activity = activity;
        this.currentUsers = currentUsers;
        myFirebaseDatabase = new MyFirebaseDatabase(activity);
    }


    @NonNull
    @Override
    public FriendsListAdapter.FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_friend,parent,false);
        return new FriendsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsListAdapter.FriendsViewHolder holder, int position) {
        final CurrentUser currentUser = currentUsers.get(position);
        txtName.setText(currentUser.getNickName());
        txtPoints.setText(String.valueOf(currentUser.getPoints()));
        myFirebaseDatabase.getFriendsProfileImage(profPic,currentUser);
        if(acceptBtn != null){
            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myFirebaseDatabase.addFriend(currentUser.getEmail());
                    currentUsers.remove(currentUser);
                    notifyDataSetChanged();
                }
            });
            if(rejectBtn != null){
                rejectBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myFirebaseDatabase.cancelFriendRequest(currentUser.getEmail());
                    }
                });
            }

        }



    }
    public void setRequestType(boolean type){
        this.requestType = type;
    }


    @Override
    public int getItemCount() {
       return currentUsers.size();
    }
    private void showDetailedView(int position){
        final CurrentUser currentUser = currentUsers.get(position);
        View popupView = activity.getLayoutInflater().inflate(R.layout.popup_friend_profile, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TextView tvName = popupView.findViewById(R.id.popup_friend_name);
        TextView tvDescription = popupView.findViewById(R.id.popup_friend_description);
        TextView tvFriendsCount = popupView.findViewById(R.id.popup_friend_friendSize);
        TextView tvPoints = popupView.findViewById(R.id.popup_friend_points);
        final Button addFriends = popupView.findViewById(R.id.popup_friend_addFriend);
        ImageView imageView = popupView.findViewById(R.id.popup_friend_profpic);
        if(myFirebaseDatabase.isCurrentUser(currentUser.getEmail()))
            addFriends.setVisibility(View.INVISIBLE);
        if(!requestType)
            addFriends.setVisibility(View.INVISIBLE);

        tvName.setText(currentUser.getNickName());
        tvDescription.setText(currentUser.getEmail());
        tvFriendsCount.setText(String.valueOf(currentUser.getChallengeCount()));
        tvPoints.setText(String.valueOf(currentUser.getPoints()));
        myFirebaseDatabase.getFriendsProfileImage(imageView,currentUser);


        addFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**Not Friends State */

                if(current_friend_state.equals("not_friends")) {
                    addFriends.setEnabled(false);
                    addFriends.setText("Cancel Friend Request");
                    myFirebaseDatabase.sendFriendRequest(currentUser.getEmail());
                    current_friend_state="req_sent";
                    addFriends.setEnabled(true);
                    return;


                }
                /** Cancel Request State */
                if(current_friend_state.equals("req_sent")) {
                    addFriends.setEnabled(false);
                    addFriends.setText("Send Friend Request");
                    myFirebaseDatabase.cancelFriendRequest(currentUser.getEmail());
                    current_friend_state= "not_friends";
                    addFriends.setEnabled(true);
                    return;
                }
            }
        });



        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 10, 10);
        FloatingActionButton exitFAB = popupView.findViewById(R.id.popup_friend_exit);
        exitFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });


    }
}

