package com.gameco.cakin.automotiveservices.controller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cakin on 1/2/2018.
 */

public class myFragmentController {
    private Fragment fragment;
    private LayoutInflater layoutInflater;
    private FirebaseAuth firebaseAuth;
    private String emailToSend;
    private myNotificationController notificationController;

    public myFragmentController(Fragment fragment){
        this.fragment = fragment;

        notificationController = new myNotificationController(fragment);
    }
    public void replaceFragment(int where,Fragment someFragment) {
        FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
        transaction.replace(where,someFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    public void showFriends(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
        final AlertDialog dialog;
        layoutInflater = LayoutInflater.from(fragment.getActivity());
        View mview = layoutInflater.inflate(R.layout.fragment_friends, null);

        ListView listView;
        List<Friend> friendList = new ArrayList<>();
        friendList.clear();
        friendList.add(new Friend("LEA",123456));
        friendList.add(new Friend("HAMPUS",123456));
        friendList.add(new Friend("CAN",123454));
        friendList.add(new Friend("CHRISTOPHER",123450));
        listView = (ListView)mview.findViewById(R.id.friendsListview);
        ViewGroup header_friends = (ViewGroup)layoutInflater.inflate(R.layout.header_friends_list,listView,false);
        listView.addHeaderView(header_friends);
        FriendsListAdapter friendsListAdapter = new FriendsListAdapter(fragment.getActivity(),friendList);
        listView.setAdapter(friendsListAdapter);

//        FloatingActionButton closeFriendsBtn = (FloatingActionButton) mview.findViewById(R.id.closeFriendsFAB);
//        closeFriendsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        dialog.dismiss();
//                    }
//                });
//            }
//        });

        builder.setView(mview);

        dialog= builder.create();
        dialog.show();
    }
    public void setUserInformation(View view){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String firebaseUserEmail = firebaseUser.getEmail();

        //  String username = settings.getString("Username","");
        if(firebaseUserEmail.contains("can"))
        {
            NavigationView navigationView = (NavigationView) fragment.getActivity().findViewById(R.id.nav_view);

            View headerLayout = navigationView.getHeaderView(0);

            ((TextView)  headerLayout.findViewById(R.id.userTextV)).setText("Can Türker");
            ((TextView) headerLayout.findViewById(R.id.userEmailV)).setText("can.tuerker@tum.de");


            LinearLayout frameLayout = (LinearLayout) view.findViewById(R.id.home_frameLayout);
            RelativeLayout relativeLayout =(RelativeLayout) frameLayout.findViewById(R.id.home_mainLayout);

            ImageView imageView = (ImageView) relativeLayout.findViewById(R.id.profPic);
            imageView.setImageResource(R.drawable.ic_can);
            ((TextView) relativeLayout.findViewById(R.id.txtfullName)).setText(R.string.canName);
            ((TextView) relativeLayout.findViewById(R.id.txtLevel)).setText(R.string.canLevel);
            ((TextView) relativeLayout.findViewById(R.id.txtScore)).setText(R.string.canPoints);

        }


    }





}
//                final View popupView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_challenge,null);
//                final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY,10,10);
//                Button start_challenge = (Button) popupView.findViewById(R.id.start_challenge_yourself_button);
//                start_challenge.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        myNotificationController.sendNotificationToSelf(fragment.getActivity(),"Challenge Started","Duration 7 days");
//                        //   transitionHelper.sendNotification("Challenge Started!","Duration: 7 days!");
//                        popupWindow.dismiss();
//
//                    }
//                });
//                Button send_challenge = (Button) popupView.findViewById(R.id.send_challenge_to_friend_button);
//                send_challenge.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        myNotificationController.sendNotificationToFriend("Can");
//                        //     userHelper.sendNotification();
//                    }
//                });
//                FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitFAB);
//                floatingActionButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        popupWindow.dismiss();
//                    }
//                });
//        FloatingActionButton exitChallengeButton = (Button) view.findViewById(R.id.exitDaily);
//        exitChallengeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                   @Override
//                   public void onDismiss(DialogInterface dialog) {
//                    dialog.dismiss();
//                   }
//               });
//            }
//        });
//            layoutInflater =(LayoutInflater) activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            //   View popupView = fragment.getActivity().getLayoutInflater().inflate(R.layout.content_daily_challenge,null);
//            View popupView = layoutInflater.inflate(R.layout.content_daily_challenge,null);
//            DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
//            PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            popupWindow.showAtLocation(drawerLayout, Gravity.NO_GRAVITY,10,10);



// AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());

//  AlertDialog dialog = null ;

//    LayoutInflater  layoutInflater = LayoutInflater.from(fragment.getActivity());
//  View view = layoutInflater.inflate(R.layout.content_daily_challenge, null);


//        Button challengeButton = (Button) popupView.findViewById(R.id.playChallengeButton);
//        challengeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                notificationController.showPopUp();
//
//
//            }
//        });


//   builder.setView(view);

//    dialog= builder.create();
//    dialog.setCancelable(true);
//    dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//       @Override
//       public void onCancel(DialogInterface dialog) {
//          dialog.dismiss();
//      }
//    });
//   dialog.show();
