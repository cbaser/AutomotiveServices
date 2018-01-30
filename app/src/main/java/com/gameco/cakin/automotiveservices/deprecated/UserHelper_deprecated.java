package com.gameco.cakin.automotiveservices.deprecated;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.datamodel.Friend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cakin on 12/28/2017.
 */

public class UserHelper_deprecated {


    private FirebaseAuth firebaseAuth;


    private List<Friend> friendList;
    private String emailToSend;

    public void setUserInformation(View view, SharedPreferences settings){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String firebaseUserEmail = firebaseUser.getEmail();




      //  String username = settings.getString("Username","");
        if(firebaseUserEmail.contains("cnturkr"))
        {

             ImageView imageView = (ImageView) view.findViewById(R.id.profPic_lea);
             imageView.setImageResource(R.drawable.ic_can);
            ((TextView) view.findViewById(R.id.txtRewe)).setText(R.string.canName);
            ((TextView) view.findViewById(R.id.txtRewe_con)).setText(R.string.canLevel);
            ((TextView) view.findViewById(R.id.txtScore)).setText(R.string.canPoints);

        }


    }

    public void sendNotification(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                   DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference reference = rootRef.getRef().child("Cagatay");
                    DatabaseReference sendRef = reference.child("Email");
                    sendRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            emailToSend =(String) dataSnapshot.getValue();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    Log.d("Tag",sendRef.toString());

                    if(LoginActivity.LoggedIn_User_Email.equals("c.akinbaser@hotmail.com")){
                        emailToSend = "cnturkr@gmail.com";
                    }
                    else if(LoginActivity.LoggedIn_User_Email.equals("can_turker@gmail.com")){
                        emailToSend="c.akinbaser@hotmail.com";
                    }
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
                        String strJsonBody = "{"
                                + "\"app_id\": \"4a5d1740-b98b-4569-9107-2de771ddc07d\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \"User_ID\", \"relation\": \"=\", \"value\": \"" + emailToSend + "\"}],"

                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"Challenge!\"}"
                                + "}";


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



}
