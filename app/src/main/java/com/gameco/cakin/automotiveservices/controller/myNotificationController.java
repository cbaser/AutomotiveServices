package com.gameco.cakin.automotiveservices.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.activites.MainActivity;
import com.gameco.cakin.automotiveservices.adapters.FriendsListAdapter;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by cakin on 1/2/2018.
 */

public class myNotificationController {
    public static FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Fragment fragment;
    private Activity activity;
    public myNotificationController(Activity activity){this.activity=activity;}
    private String strJsonBody;
    private String userEmail,friendEmail;

    private Challenge challenge;


   public myNotificationController(Fragment fragment){
       this.fragment=fragment;
       mDatabase =FirebaseDatabase.getInstance();
       mRef = mDatabase.getReference("Users");
   }

    public void sendNotificationToSelf(Activity activity, String title, String content){
        long[] pattern = {0, 100, 1000};
        Notification notification = new NotificationCompat.Builder(activity)
                .setContentTitle(title)
                .setContentText(content)
                .setVibrate(pattern)
                .setColor(Color.BLACK)
                .setLights(Color.BLACK, 1000, 500)
                .setSmallIcon(R.drawable.main_icon)
                .setLargeIcon(BitmapFactory.decodeResource(activity.getResources(),R.drawable.icon_transparent)).build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager)
                activity.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }

    public void showPopUp(final String title , String time, String points, String current, String target, int color ){
         View popupView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_challenge,null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        challenge = new Challenge();
        challenge.setChallengeTitle(title);
        challenge.setTime(time);
        challenge.setTimeToLeft(time);
        challenge.setCurrent(current);
        challenge.setTarget(target);
        challenge.setPoints(points);


        setEmail();
        TextView txtTitle = (TextView) popupView.findViewById(R.id.txtdetailTitle);
        txtTitle.setText(challenge.getChallengeTitle());
        TextView txtTime = (TextView) popupView.findViewById(R.id.txtTime);
        txtTime.setText(challenge.getTime());
        TextView txtPoint = (TextView) popupView.findViewById(R.id.txtPoint);
        txtPoint.setText(challenge.getPoints());
        TextView txtCurrent = (TextView) popupView.findViewById(R.id.txtCurrent);
        txtCurrent.setText(challenge.getCurrent());
        TextView txtTarget = (TextView) popupView.findViewById(R.id.txtTarget);
        txtTarget.setText(challenge.getTarget());

        RelativeLayout relativeLayout = popupView.findViewById(R.id.popup_element);
        relativeLayout.setBackgroundColor(color);

        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY,10,10);





        Button start_challenge_yourself = (Button) popupView.findViewById(R.id.start_challenge_yourself_button);
        start_challenge_yourself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotificationToSelf(fragment.getActivity(),"Challenge Started",challenge.getTime());
                if(LoginActivity.LoggedIn_User_Email.equals("cagatayakin.baser@tum.de")) {
                    challenge.setFriendName("cagatay baser");

                    challenge.setWinner(true);
                }
                else{
                    challenge.setFriendName("Can Turker");

                    challenge.setWinner(false);
                }

                Map<String,Challenge> challenges = new HashMap<>();
                challenges.put(title,challenge);
                DatabaseReference newRef= mRef.child(LoginActivity.user_full_name).child("Challenges");

                newRef.child(title).setValue(challenges);
                //        challenge.setFriendPicture(fragment.getActivity().getResources().getDrawable(R.drawable.ic_cagatay));
                //     challenge.setFriendPicture(fragment.getActivity().getResources().getDrawable(R.drawable.ic_can));
            //    mRef.child(LoginActivity.user_full_name).child("Challenges").setValue(challenges);

//                mRef.child("cagatay baser").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        dataSnapshot.getRef("Challenge")
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });
//



                popupWindow.dismiss();


            }
        });
        Button send_challenge_friend = (Button) popupView.findViewById(R.id.send_challenge_to_friend_button);

        send_challenge_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View friendView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_select_friend,null);
                final PopupWindow friendWindow = new PopupWindow(friendView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                friendWindow.showAtLocation(friendView, Gravity.NO_GRAVITY,10,10);




                TextView txtTime = (TextView) friendView.findViewById(R.id.txtSelectFriendTime);
                txtTime.setText(challenge.getTime());
                TextView txtPoint = (TextView) friendView.findViewById(R.id.txtSelectFriendPoint);
                txtPoint.setText(challenge.getPoints());
                TextView txtCurrent = (TextView) friendView.findViewById(R.id.txtSelectFriendCurrent);
                txtCurrent.setText(challenge.getCurrent());
                TextView txtTarget = (TextView) friendView.findViewById(R.id.txtSelectFriendTarget);
                txtTarget.setText(challenge.getTarget());



                final ListView listView = (ListView) friendView.findViewById(R.id.friendsListview);
                List<Friend> friendList = new ArrayList<>();
                friendList.clear();
                if(LoginActivity.LoggedIn_User_Email.equals("cagatayakin.baser@tum.de")) {
                    Friend can = new Friend(fragment.getActivity().getResources().getDrawable(R.drawable.ic_can), "Can Türker", 123455);
                    friendList.add(can);
                }
                else{
                    Friend cagatay = new Friend(fragment.getActivity().getResources().getDrawable(R.drawable.ic_cagatay), "Cagatay Akin Baser", 123457);
                    friendList.add(cagatay);
                }


               ViewGroup header_friends = (ViewGroup)fragment.getActivity().getLayoutInflater().inflate(R.layout.header_available_friends,listView,false);
                listView.addHeaderView(header_friends);

                FriendsListAdapter friendsListAdapter = new FriendsListAdapter(fragment.getActivity(),friendList);
                listView.setAdapter(friendsListAdapter);


              listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                      listView.getChildAt(i).setBackgroundColor(fragment.getActivity().getResources().getColor(R.color.white));
                  }
              });


                FloatingActionButton floatingActionButton = (FloatingActionButton) friendView.findViewById(R.id.exitSelectFriend);
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        friendWindow.dismiss();
                    }
                });


                Button sendBtn = (Button) friendView.findViewById(R.id.sendChallengeBtn);
                sendBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setJsonBody(friendEmail,"Challenge From : " +LoginActivity.LoggedIn_User_Email);
                        Toast.makeText(fragment.getActivity(), "Challenge Sent!", Toast.LENGTH_SHORT).show();
                        sendNotificationToFriend();
                        DatabaseReference newRef= mRef.child(LoginActivity.user_full_name).child("Challenges");
                        Map<String,Challenge> challenges = new HashMap<>();
                        challenges.put(title,challenge);
                        newRef.child(title).setValue(challenges);
                        friendWindow.dismiss();
                    }
                });
        Button closeBtn = (Button) friendView.findViewById(R.id.closeChallengeBtn);
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        friendWindow.dismiss();
                    }
                });



            }
        });

        FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.exitChallenge);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }
    public Challenge getChallenge(){
        return challenge;
    }

    public void acceptOrDeclineChallenge(Challenge challenge){
        View decideView = activity.getLayoutInflater().inflate(R.layout.popup_accept_challenge,null);
        final PopupWindow decideWindow = new PopupWindow(decideView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        decideWindow.showAtLocation(decideView, Gravity.CENTER,10,10);
        setEmail();
        Button accept_challenge = (Button) decideView.findViewById(R.id.acceptChallengeBtn);
        accept_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setJsonBody(friendEmail,"Challenge Accepted!");
                sendNotificationToFriend();
               // sendNotificationToSelf(activity,"Challenge Accepted!","+250 POINTS");
                decideWindow.dismiss();
            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) decideView.findViewById(R.id.closeAcceptChallenge);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decideWindow.dismiss();
            }
        });



        Button reject_challenge = (Button) decideView.findViewById(R.id.rejectChallengeBtn);
        reject_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rejectView = activity.getLayoutInflater().inflate(R.layout.popup_reject_challenge,null);
                final PopupWindow rejectWindow = new PopupWindow(rejectView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                rejectWindow.showAtLocation(rejectView, Gravity.CENTER,10,10);

                FloatingActionButton rejectFloating = (FloatingActionButton) rejectView.findViewById(R.id.closeRejectChallange);
                rejectFloating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rejectWindow.dismiss();
                    }
                });


                Button reject_challenge = (Button) rejectView.findViewById(R.id.rejectSure);
                reject_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(activity, "You lost 1000 points!", Toast.LENGTH_SHORT).show();
                        setJsonBody(friendEmail,"Challenge Rejected!");
                        sendNotificationToFriend();
                        rejectWindow.dismiss();
                    }
                });



                Button accept_challenge = (Button) rejectView.findViewById(R.id.acceptSure);
                accept_challenge.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        rejectWindow.dismiss();
                    }
                });

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

    public void setJsonBody(String email,String content){
        strJsonBody = "{"
                + "\"app_id\": \"4a5d1740-b98b-4569-9107-2de771ddc07d\","

                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + email + "\"}],"
                + "\"contents\": {\"en\":\" "+ content +"\"}"

                + "}";

    }
}
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public String getPoints() {
//        return points;
//    }
//
//    public void setPoints(String points) {
//        this.points = points;
//    }
//
//    public String getCurrent() {
//        return current;
//    }
//
//    public void setCurrent(String current) {
//        this.current = current;
//    }
//
//    public String getTarget() {
//        return target;
//    }
//
//    public void setTarget(String target) {
//        this.target = target;
//    }

//  private String title,time,points,current,target;


//        Button join_regional = (Button) popupView.findViewById(R.id.join_regional_challenge_button);
//        join_regional.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendNotificationToSelf(fragment.getActivity(),"Regional Challenge : München","Duration : " +time);
//            }
//        });
//
//  final AlertDialog.Builder builder = new AlertDialog.Builder(fragment.getActivity());
//                 dialog = null;

//                View friendsView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_select_friend,null);
//                Button sendBtn = (Button) friendsView.findViewById(R.id.sendChallengeBtn);
//                sendBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        sendNotificationToSelf(fragment.getActivity(),"Hello","Test");
//                    }
//                });
//
//                Button closeBtn = (Button) friendsView.findViewById(R.id.closeChallengeBtn);
//                closeBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                            @Override
//                            public void onDismiss(DialogInterface dialogInterface) {
//                             dialog.cancel();
//                            }
//                        });
//                    }
//                });
//
//                builder.setView(friendsView);
//
//                dialog= builder.create();
//                dialog.show();








//    sendNotificationToFriend("Challenge from : "+LoginActivity.LoggedIn_User_Email);
//     userHelper.sendNotification();










//                        View progressView = fragment.getActivity().getLayoutInflater().inflate(R.layout.popup_progress_bar,null);
//                        final PopupWindow progressWindow = new PopupWindow(progressView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        progressWindow.showAtLocation(progressView, Gravity.CENTER,10,10);
//                        final ProgressBar progressBar = progressView.findViewById(R.id.progressBar2);
//                        progressBar.setProgress(0);
//                        progressBar.setIndeterminate(false);
//
//                        final int totalProgressTime = 10;
//                        final Thread t = new Thread() {
//                            @Override
//                            public void run() {
//                                int jumpTime = 0;
//
//                                while(jumpTime < totalProgressTime) {
//                                    try {
//                                        sleep(100);
//                                        jumpTime += 5;
//                                        progressBar.setProgress(jumpTime);
//                                    } catch (InterruptedException e) {
//                                        // TODO Auto-generated catch block
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        };
//                        t.start();
//  + "\"buttons\": [{\"id\":\"btnAccept\",\"text\":\"Accept\",\"icon\":\"\"},{\"id\":\"btnDecline\",\"text\":\"Decline\",\"icon\":\"\"}],"
//  + "\"data\": {\"foo\": \"bar\"},"
//   + "\"contents\": {\"en\": \"Challenge from: "+name+ "\"}"
//    public String getStrJsonBody(){
//
//        return strJsonBody;
//
//    }

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