package com.gameco.cakin.automotiveservices.controller;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.FriendsChallengeAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.gameco.cakin.automotiveservices.onesignal.MainNotificationHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by cakin on 1/2/2018.
 */

public class myNotificationController {
    private Activity activity;
    private MainNotificationHandler mainNotificationHandler;
    private MyFirebaseDatabase myFirebaseDatabase;
    private int friendPos;


    public myNotificationController(Activity activity) {

        this.activity = activity;
        myFirebaseDatabase = new MyFirebaseDatabase(activity);
        mainNotificationHandler = new MainNotificationHandler();
    }

    public void showChallengePopup(final Challenge challenge) {
        View popupView = View.inflate(activity,R.layout.popup_challenge, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //setChallengeAttributes(title, time, points, current, target);
        //setEmail();
        TextView txtTitle =  popupView.findViewById(R.id.txtdetailTitle);
        txtTitle.setText(activity.getString(R.string.popup_challenge_title,challenge.getChallengeTitle()));
        TextView txtTime =  popupView.findViewById(R.id.txtTime);
        txtTime.setText(activity.getString(R.string.popup_challenge_time,challenge.getTime()));
        TextView txtPoint =  popupView.findViewById(R.id.txtPoint);
        txtPoint.setText(activity.getString(R.string.popup_challenge_point,challenge.getPoints()));
        TextView txtCurrent = popupView.findViewById(R.id.txtCurrent);
        txtCurrent.setText(activity.getString(R.string.popup_challenge_current,challenge.getCurrent()));
        TextView txtTarget = popupView.findViewById(R.id.txtTarget);
        txtTarget.setText(activity.getString(R.string.popup_challenge_target,challenge.getTarget()));
        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 10, 10);


        final Button start_challenge_yourself =  popupView.findViewById(R.id.start_challenge_yourself_button);
        start_challenge_yourself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startChallengeToYourself(challenge);
               popupWindow.dismiss();
            }
        });
        Button send_challenge_friend =popupView.findViewById(R.id.send_challenge_to_friend_button);

        send_challenge_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChallengeToFriend(challenge);

            }
        });

        FloatingActionButton floatingActionButton = popupView.findViewById(R.id.exitChallenge);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    private void startChallengeToYourself(Challenge challenge){
        sendNotificationToSelf(activity, "Challenge Started", challenge.getTime());
        myFirebaseDatabase.addToDatabase(challenge.getChallengeTitle(),challenge);
    }


    private void sendNotificationToSelf(Activity activity, String title, String content) {
        long[] pattern = {0, 100, 1000};
        Notification notification = new NotificationCompat.Builder(activity)
                .setContentTitle(title)
                .setContentText(content)
                .setVibrate(pattern)
                .setColor(Color.BLACK)
                .setLights(Color.BLACK, 1000, 500)
                .setSmallIcon(R.drawable.main_icon)
                .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(), R.drawable.icon_transparent)).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager)
                activity.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }




    private void startChallengeToFriend(Challenge challenge){
        ArrayList<CurrentUser> friends = initializeFriends();
        initializeFriendsScreen(challenge,friends);

    }
    private ArrayList<CurrentUser> initializeFriends(){
        return myFirebaseDatabase.getFriendsFromPreferences();
    }


    private void initializeFriendsScreen(final Challenge challenge, final ArrayList<CurrentUser> friends){
        View friendView = View.inflate(activity,R.layout.popup_select_friend, null);
        final PopupWindow friendWindow = new PopupWindow(friendView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        friendWindow.showAtLocation(friendView, Gravity.NO_GRAVITY, 10, 10);


        TextView txtTime =  friendView.findViewById(R.id.txtSelectFriendTime);
        txtTime.setText(activity.getString(R.string.popup_challenge_time,challenge.getTime()));
        TextView txtPoint = friendView.findViewById(R.id.txtSelectFriendPoint);
        txtPoint.setText(activity.getString(R.string.popup_challenge_point,challenge.getPoints()));
        TextView txtCurrent =  friendView.findViewById(R.id.txtSelectFriendCurrent);
        txtCurrent.setText(activity.getString(R.string.popup_challenge_current,challenge.getCurrent()));
        TextView txtTarget = friendView.findViewById(R.id.txtSelectFriendTarget);
        txtTarget.setText(activity.getString(R.string.popup_challenge_target,challenge.getTarget()));



        final ListView listView = friendView.findViewById(R.id.friendsListview);
        ViewGroup header_friends = (ViewGroup) activity.getLayoutInflater().inflate(R.layout.header_available_friends, listView, false);
        listView.addHeaderView(header_friends);
        FriendsChallengeAdapter friendsListAdapter = new FriendsChallengeAdapter(activity, friends);
        listView.setAdapter(friendsListAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                listView.getChildAt(position).setBackgroundColor(activity.getResources().getColor(R.color.white));
                friendPos = position;
            }
        });


        FloatingActionButton floatingActionButton =  friendView.findViewById(R.id.exitSelectFriend);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friendWindow.dismiss();
            }
        });


        Button sendBtn = friendView.findViewById(R.id.sendChallengeBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CurrentUser currentUser = friends.get(friendPos);
                Toast.makeText(activity, "Challenge Sent!", Toast.LENGTH_SHORT).show();
                mainNotificationHandler.setJsonBody(currentUser.getEmail(),"Challenge From: ",generateJsonData(challenge,currentUser));
                mainNotificationHandler.sendNotificationToFriend();
               // myFirebaseDatabase.addToDatabase(challenge.getChallengeTitle(),challenge);
                friendWindow.dismiss();
            }
        });
        Button closeBtn = friendView.findViewById(R.id.closeChallengeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friendWindow.dismiss();
            }
        });
    }


    private String generateJsonData(Challenge challenge,CurrentUser currentUser){
        JSONArray jsonArray = new JSONArray();
        JSONObject finalObject = new JSONObject();
        try{
            Gson gson = new Gson();
            String string1 = gson.toJson(challenge);
            String string2 = gson.toJson(currentUser);

            JsonObject object1 = new JsonParser().parse(string1).getAsJsonObject();
            JsonObject object2 = new JsonParser().parse(string2).getAsJsonObject();


            jsonArray.put(object1);
            jsonArray.put(object2);


            finalObject.put("dataFromNotification",jsonArray);

        }catch (Exception e){
            e.printStackTrace();
        }


        return finalObject.toString();
    }



    private String challengeToJson(Challenge challenge){
        Gson gson = new Gson();
        return gson.toJson(challenge);
    }
    private String userToJson(CurrentUser currentUser){
        JSONObject finalObject = new JSONObject();
        try{
            Gson gson = new Gson();

            finalObject.put("dataFromNotification",gson.toJson(currentUser));
        }catch (Exception e){
            e.printStackTrace();
        }

        return finalObject.toString();
    }









    public void acceptOrDeclineChallenge(final Challenge challenge, final CurrentUser currentUser) {
        View decideView = View.inflate(activity,R.layout.popup_accept_challenge, null);
        final PopupWindow decideWindow = new PopupWindow(decideView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        decideWindow.showAtLocation(decideView, Gravity.CENTER, 10, 10);
        LinearLayout linearLayout = decideView.findViewById(R.id.detailDescription);
        TextView txtTitle = linearLayout.findViewById(R.id.txtFrom);
        txtTitle.setText(activity.getString(R.string.popup_challenge_title,challenge.getChallengeTitle()));

        TextView txtTime = linearLayout.findViewById(R.id.txtTime);
        txtTime.setText(activity.getString(R.string.popup_challenge_time,challenge.getChallengeTitle()));
        TextView txtPoint =  linearLayout.findViewById(R.id.txtPoint);
        txtPoint.setText(activity.getString(R.string.popup_challenge_point,challenge.getPoints()));
        TextView txtCurrent = linearLayout.findViewById(R.id.txtCurrent);
        txtCurrent.setText(activity.getString(R.string.popup_challenge_current,challenge.getCurrent()));
        TextView txtTarget = linearLayout.findViewById(R.id.txtTarget);
        txtTarget.setText(activity.getString(R.string.popup_challenge_target,challenge.getTarget()));


        Button accept_challenge =  decideView.findViewById(R.id.acceptChallengeBtn);
        accept_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainNotificationHandler.setJsonBody(currentUser.getEmail(), "Challenge Accepted!",generateJsonData(challenge,currentUser));
                mainNotificationHandler.sendNotificationToFriend();
                decideWindow.dismiss();
                myFirebaseDatabase.addToDatabase(challenge.getChallengeTitle(),challenge);
            }
        });


        Button reject_challenge = decideView.findViewById(R.id.rejectChallengeBtn);
        reject_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rejectView = View.inflate(activity,R.layout.popup_reject_challenge, null);
                final PopupWindow rejectWindow = new PopupWindow(rejectView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rejectWindow.showAtLocation(rejectView, Gravity.CENTER, 10, 10);

                FloatingActionButton rejectFloating =  rejectView.findViewById(R.id.closeRejectChallange);
                rejectFloating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rejectWindow.dismiss();
                    }
                });


                Button reject_challenge =  rejectView.findViewById(R.id.rejectSure);
                reject_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(activity, "You lost 250 points!", Toast.LENGTH_SHORT).show();
                        myFirebaseDatabase.reducePoints();
                        mainNotificationHandler.setJsonBody(currentUser.getEmail(), "Challenge Rejected!",generateJsonData(challenge,currentUser));
                        mainNotificationHandler.sendNotificationToFriend();
                        rejectWindow.dismiss();
                    }
                });


                Button accept_challenge =  rejectView.findViewById(R.id.acceptSure);
                accept_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rejectWindow.dismiss();
                    }
                });

            }
        });
    }
    public void sendFriendRequest(CurrentUser currentUser){
        mainNotificationHandler.setJsonBody(currentUser.getEmail(),"Friend Request",userToJson(currentUser));
        mainNotificationHandler.sendNotificationToFriend();


    }








//        List<Friend> friendList = new ArrayList<>();
//        friendList.clear();
//                if (LoginActivity.LoggedIn_User_Email.equals("cagatayakin.baser@tum.de")) {
//                    Friend can = new Friend(fragment.getActivity().getResources().getDrawable(R.drawable.ic_can), "Can Türker", 123455);
//                    friendList.add(can);
//                } else {
//                    Friend cagatay = new Friend(fragment.getActivity().getResources().getDrawable(R.drawable.ic_cagatay), "Cagatay Akin Baser", 123457);
//                    friendList.add(cagatay);
//                }






//    private void setEmail() {
//        //TODO: Friend System
////        userEmail = LoginActivity.LoggedIn_User_Email;
////        if (LoginActivity.LoggedIn_User_Email.equals("cagatayakin.baser@tum.de")) {
////            friendEmail = "can.tuerker@tum.de";
////        } else if (LoginActivity.LoggedIn_User_Email.equals("can.tuerker@tum.de")) {
////            friendEmail = "cagatayakin.baser@tum.de";
////        }
//    }


//    private void setChallengeAttributes(String title, String time, long points, String current, String target) {
//
//        challenge.setChallengeTitle(title);
//        challenge.setCurrent(current);
//        challenge.setTime(time);
//        challenge.setTimeToLeft(time);
//        challenge.setTarget(target);
//        challenge.setPoints(points);
//        challenge.setUserStatus("Individual Challenge");
//        challenge.setFriendStatus("No friend Status");
//        if (toSelf) {
//
//            //TODO: Implement Friends System and Winner
////            if (LoginActivity.LoggedIn_User_Email.equals("cagatayakin.baser@tum.de")) {
////                challenge.setFriendName("Cagatay Baser");
////                challenge.setWinner(true);
////            } else {
////                challenge.setFriendName("Can Turker");
////                challenge.setWinner(false);
////            }
////        } else {
////            challenge.setUserStatus("Just Started the challenge");
////            challenge.setFriendStatus("Just Started the challenge");
////            if (LoginActivity.LoggedIn_User_Email.equals("cagatayakin.baser@tum.de")) {
////                challenge.setFriendName("Can Turker");
////                challenge.setWinner(true);
////            } else {
////                challenge.setFriendName("Cagatay Baser");
////                challenge.setWinner(false);
////            }
//        }
//
//    }

//    public Challenge getChallenge() {
//        return challenge;
//    }


// sendNotificationToSelf(activity,"Challenge Accepted!","+250 POINTS");


}


