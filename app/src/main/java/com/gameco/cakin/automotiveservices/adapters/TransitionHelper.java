package com.gameco.cakin.automotiveservices.adapters;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.Friend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 12/26/2017.
 */

public class TransitionHelper {
    private Fragment fragment;
    public TransitionHelper(){

    }
    public void setFragment(Fragment fragment){
        this.fragment = fragment;
    }


    public void replaceFragment(int where,Fragment someFragment) {
        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
       // transaction.replace(R.id.challengesFrameLayout, someFragment);
        transaction.replace(where,someFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    public void sendNotification(String title,String content){
        Notification notification = new NotificationCompat.Builder(fragment.getActivity())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setLargeIcon(BitmapFactory.decodeResource(fragment.getActivity().getResources(),R.drawable.main_icon)).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager)
                fragment.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        //Set a tag so that the same notification doesn't get reposted over and over again and
        //you can grab it again later if you need to.
        notificationManager.notify(1, notification);


    }
    public void showFriends(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
        final AlertDialog dialog;
        LayoutInflater li = LayoutInflater.from(fragment.getActivity());
        View mview = li.inflate(R.layout.fragment_friends, null);

        ListView listView;
        List<Friend> friendList = new ArrayList<>();
        friendList.clear();
        friendList.add(new Friend("LEA",23494));
        friendList.add(new Friend("CAN",21421));
        friendList.add(new Friend("CAGATAY",19180));
        friendList.add(new Friend("CHRISTOPHER",14032));
        listView = (ListView)mview.findViewById(R.id.friendsListview);
        ViewGroup header_friends = (ViewGroup)li.inflate(R.layout.header_friends_list,listView,false);
        listView.addHeaderView(header_friends);
        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(fragment.getActivity(),friendList);
        listView.setAdapter(friendsListAdapter);

        FloatingActionButton closeFriendsBtn = (FloatingActionButton) mview.findViewById(R.id.closeFriendsFAB);
      closeFriendsBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                  @Override
                  public void onDismiss(DialogInterface dialog) {
                      dialog.cancel();;
                  }
              });
          }
      });

        builder.setView(mview);

        dialog= builder.create();
        dialog.show();
    }
}
