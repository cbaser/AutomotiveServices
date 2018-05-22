package com.gameco.cakin.automotiveservices.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;

import java.util.ArrayList;
import java.util.Currency;

public class FriendsChallengeAdapter extends BaseAdapter{
    private Activity activity;
    private ArrayList<CurrentUser> currentUsers;
    private MyFirebaseDatabase myFirebaseDatabase;
    public FriendsChallengeAdapter(Activity activity, ArrayList<CurrentUser> currentUsers){
        this.activity = activity;
        this.currentUsers = currentUsers;
        myFirebaseDatabase = new MyFirebaseDatabase(activity);
    }
    @Override
    public int getCount() {
        return currentUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return currentUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view = activity.getLayoutInflater().inflate(R.layout.group_friend,null);
        ImageView imIcon = (ImageView)view.findViewById(R.id.group_friend_profile_icon);
        TextView tvName = (TextView) view.findViewById(R.id.group_friend_profile_name);
        TextView tvPoint = (TextView) view.findViewById(R.id.group_friend_profile_points);
        CurrentUser currentUser = currentUsers.get(position);
        myFirebaseDatabase.getFriendsProfileImage(imIcon,currentUser);
        tvName.setText(currentUser.getNickName());
        tvPoint.setText(String.valueOf(currentUser.getPoints()));


        return view;
    }
}
