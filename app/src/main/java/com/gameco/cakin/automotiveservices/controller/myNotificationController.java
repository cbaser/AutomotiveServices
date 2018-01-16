package com.gameco.cakin.automotiveservices.controller;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by cakin on 1/2/2018.
 */

public class myNotificationController {
    //private String emailToSend;
    private Fragment fragment;
    private Activity activity;
    private String strJsonBody;
    private String userEmail,friendEmail;
   public myNotificationController(Fragment fragment){
       this.fragment=fragment;
   }
   public myNotificationController(Activity activity){this.activity=activity;}

    public void sendNotificationToSelf(Activity activity, String title, String content){
        Notification notification = new NotificationCompat.Builder(activity)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(),R.drawable.main_icon)).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager)
                activity.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }

    public void showPopUp(String title ,final String time,String points,String current,String target,int color ){
         View popupView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_challenge,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        setEmail();
        TextView txtTitle = (TextView) popupView.findViewById(R.id.txtTitle);
        txtTitle.setText(title);
        TextView txtTime = (TextView) popupView.findViewById(R.id.txtTime);
        txtTime.setText(time);
        TextView txtPoint = (TextView) popupView.findViewById(R.id.txtPoint);
        txtPoint.setText(points);
        TextView txtCurrent = (TextView) popupView.findViewById(R.id.txtCurrent);
        txtCurrent.setText(current);
        TextView txtTarget = (TextView) popupView.findViewById(R.id.txtTarget);
        txtTarget.setText(target);

        RelativeLayout relativeLayout = popupView.findViewById(R.id.popup_element);
        relativeLayout.setBackgroundColor(color);

        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY,10,10);





        Button start_challenge = (Button) popupView.findViewById(R.id.start_challenge_yourself_button);
        start_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotificationToSelf(fragment.getActivity(),"Challenge Started","Duration :"+time);
                //   transitionHelper.sendNotification("Challenge Started!","Duration: 7 days!");
                popupWindow.dismiss();

            }
        });
        Button send_challenge = (Button) popupView.findViewById(R.id.send_challenge_to_friend_button);
        send_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setJsonBody(friendEmail,"Challenge From : " +LoginActivity.LoggedIn_User_Email);
            //    sendNotificationToFriend("Challenge from : "+LoginActivity.LoggedIn_User_Email);
                sendNotificationToFriend();
                //     userHelper.sendNotification();
            }
        });
        Button join_regional = (Button) popupView.findViewById(R.id.join_regional_challenge_button);
        join_regional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotificationToSelf(fragment.getActivity(),"Regional Challenge : München","Duration : " +time);
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

    public void acceptOrDeclineChallenge(){
        View popupView = activity.getLayoutInflater().inflate(R.layout.popup_accept_challenge,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(popupView, Gravity.CENTER,10,10);
        setEmail();
        Button accept_challenge = (Button) popupView.findViewById(R.id.acceptChallengeBtn);
        accept_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setJsonBody(friendEmail,"Challenge Accepted!");
                sendNotificationToFriend();
               // sendNotificationToSelf(activity,"Challenge Accepted!","+250 POINTS");
                popupWindow.dismiss();
            }
        });
        Button reject_challenge = (Button) popupView.findViewById(R.id.rejectChallengeBtn);
        reject_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setJsonBody(friendEmail,"Challenge Rejected!");
                sendNotificationToFriend();
                //sendNotificationToSelf(activity,"Challenge Rejected!","-150 POINTS");
                popupWindow.dismiss();
            }
        });
    }
    private void setEmail(){
        userEmail = LoginActivity.LoggedIn_User_Email;
        if(LoginActivity.LoggedIn_User_Email.equals("cagatayakin.baser@tum.de")){

            friendEmail = "can.tuerker@tum.de";

        }
        else if(LoginActivity.LoggedIn_User_Email.equals("can.tuerker@tum.de")){
            friendEmail="cagatayakin.baser@tum.de";
        }


    }


    public void sendNotificationToFriend(){


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
//                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//                    DatabaseReference reference = rootRef.getRef().child("Cagatay");
//                    DatabaseReference sendRef = reference.child("Email");
//                    sendRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            emailToSend =(String) dataSnapshot.getValue();
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//                    Log.d("Tag",sendRef.toString());


                    try
                    {
                        String jsonResponse;
                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization","Basic NDVlYWYxZTUtNDJiZC00YTEzLThlNjctMTk0MzMwMTc3NjJm");
                        con.setRequestMethod("POST");

//                        String strJsonBody = "{"
//                                + "\"app_id\": \"4a5d1740-b98b-4569-9107-2de771ddc07d\","
//
//                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + email + "\"}],"
//
//                                + "\"buttons\": [{\"id\":\"btnAccept\",\"text\":\"Accept\",\"icon\":\"\"},{\"id\":\"btnDecline\",\"text\":\"Decline\",\"icon\":\"\"}],"
//                              //  + "\"data\": {\"foo\": \"bar\"},"
//                             //   + "\"contents\": {\"en\": \"Challenge from: "+name+ "\"}"
//                                + "\"contents\": {\"en\":\" "+ content +"\"}"
//
//                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
//    public String getStrJsonBody(){
//
//        return strJsonBody;
//
//    }
    public void setJsonBody(String email,String content){
        strJsonBody = "{"
                + "\"app_id\": \"4a5d1740-b98b-4569-9107-2de771ddc07d\","

                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + email + "\"}],"

                //  + "\"buttons\": [{\"id\":\"btnAccept\",\"text\":\"Accept\",\"icon\":\"\"},{\"id\":\"btnDecline\",\"text\":\"Decline\",\"icon\":\"\"}],"
                //  + "\"data\": {\"foo\": \"bar\"},"
                //   + "\"contents\": {\"en\": \"Challenge from: "+name+ "\"}"
                + "\"contents\": {\"en\":\" "+ content +"\"}"

                + "}";

    }
}