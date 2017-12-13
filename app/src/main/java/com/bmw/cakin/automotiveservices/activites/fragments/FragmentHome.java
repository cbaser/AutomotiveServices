package com.bmw.cakin.automotiveservices.activites.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.bmw.cakin.automotiveservices.R;
import com.bmw.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.bmw.cakin.automotiveservices.datamodel.CurrentUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cakin on 11/22/2017.
 */

public class FragmentHome extends Fragment {
    private List<String> groupList;
    private List<String> childList;
    private Map<String, List<String>> friendsCollection;
    private ExpandableListView friendsListView;
    private ListView challengesListView;
    private CurrentUser currentUser;

public FragmentHome(){

}
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

    final View root = inflater.inflate(R.layout.fragment_home,container,false);
    Button challengeButton = (Button) root.findViewById(R.id.playChallengeButton);
    challengeButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          final View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup_daily_challenge,null);
          final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
          popupWindow.showAtLocation(root, Gravity.NO_GRAVITY,10,10);
          Button start_challenge = (Button) popupView.findViewById(R.id.start_challenge_yourself_button);
          start_challenge.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Notification notification = new NotificationCompat.Builder(getActivity())
                          .setContentTitle("Challenge Started!")
                          .setContentText("Duration: 7 days!")
                          .setSmallIcon(R.mipmap.ic_launcher)
                          .setLargeIcon(BitmapFactory.decodeResource(getActivity().getResources(),R.drawable.main_icon)).build();

                  notification.flags |= Notification.FLAG_AUTO_CANCEL;
                  NotificationManager notificationManager = (NotificationManager)
                          getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                  //Set a tag so that the same notification doesn't get reposted over and over again and
                  //you can grab it again later if you need to.
                  notificationManager.notify(1, notification);

                  popupWindow.dismiss();
              }
          });
            FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitFAB);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

        }
    });

        createGroupList();
        currentUser = new CurrentUser();
        createCollection();

return root;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



  //     final FriendsListAdapter friendsListAdapter = new FriendsListAdapter(getActivity(), groupList, friendsCollection);
     //   friendsListAdapter.setCurrentUser(currentUser);
  //      friendsListView.setAdapter(friendsListAdapter);

    //    friendsListView.setGroupIndicator(null);


    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Can");
        groupList.add("Lea");
        groupList.add("Hampus");
    }
    private void createCollection() {
        // preparing friends collection(child)
//        String[] canChallenges = { "Challenge 1 : Drive less than 50 km this week"};
//        String[] leaChallenges = { "Challenge 1 :Save one tree for this week"};
//        String[] hampusChallenges = { "Challenge 1 :Drive not faster than 100 km/h this week" };
          String canScore = "23541";
          String leaScore = "21051";
          String hampusScore="21021";

        friendsCollection = new LinkedHashMap<String, List<String>>();

        for (String friend : groupList) {
            if (friend.equals("Can")) {
                loadChild(canScore);
            } else if (friend.equals("Hampus"))
                loadChild(hampusScore);
            else if (friend.equals("Lea"))
                loadChild(leaScore);
            friendsCollection.put(friend, childList);
        }
    }
    private void loadChild(String friend) {
        childList = new ArrayList<>();
        Collections.addAll(childList);
       // for (String friend : friends)
            childList.add(friend);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        friendsListView.setIndicatorBounds(width - getDipsFromPixel(35), width - getDipsFromPixel(5));
    }
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

}
