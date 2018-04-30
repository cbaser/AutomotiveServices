package com.gameco.cakin.automotiveservices.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentFriendlist;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.google.firebase.database.DatabaseReference;

public class FriendsListAdapter extends FirebaseRecyclerAdapter<Friend,FriendsListHolder>{
    public FriendsListAdapter(@NonNull FirebaseRecyclerOptions<Friend> options) {
        super(options);
    }

    @Override
    public FriendsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new instance of the ViewHolder, in this case we are using a custom
        // layout called R.layout.message for each item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_friend, parent, false);

        return new FriendsListHolder(view);
    }


    @Override
    protected void onBindViewHolder(@NonNull FriendsListHolder holder, int position, @NonNull Friend model) {

    }
}
