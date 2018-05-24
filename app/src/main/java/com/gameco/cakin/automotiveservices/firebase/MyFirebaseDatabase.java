package com.gameco.cakin.automotiveservices.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.datamodel.Rank;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MyFirebaseDatabase {
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference usersReference,leaderboardReference,challengesReference,challengeRequestReference,friendRequestReference,friendsReference;
    private Activity activity;
    private StorageReference picsRef;
    private ArrayList<Challenge> challengeList;
    private ArrayList<Rank> rankList;

    private boolean upper_level;
    private String afterLevel;
    private String TAG = "MyFirebaseDatabase";


    public MyFirebaseDatabase (Activity activity){
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        leaderboardReference = database.getReference().child("Leaderboards");
        challengesReference = database.getReference().child("Challenges").child("Title");
        friendRequestReference = database.getReference().child("FriendRequests");
        challengeRequestReference = database.getReference().child("ChallengeRequests");
        friendsReference = database.getReference().child("Friends");
        this.activity = activity;
    }
    public void createAccountInFirebaseDatabase(String email, String password, String nickname, String VIN, String Uri){
         usersReference = database.getReference().child("Users").child(email.replace(".",","));
        Car car = new Car();
        car.setVIN(VIN);
         usersReference.child("NickName").setValue(nickname);
         usersReference.child("Email").setValue(email);
         usersReference.child("Password").setValue(password);
         usersReference.child("Car").setValue(car);
         usersReference.child("Level").setValue("Newbie");
         usersReference.child("Points").setValue(0);
         usersReference.child("ChallengeCount").setValue(0);
         usersReference.child("PictureURI").setValue(Uri+"_profile");
         usersReference.child("CarURI").setValue("No Picture");
        Rank rank = new Rank();
        rank.setNickName(nickname);
        rank.setPoints(0);
        leaderboardReference.child(email.replace(".",",")).setValue(rank);
    }



    public void updateCarData(final Car car){
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));

        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("Car").child("Mileage").setValue(car.getMileage());
                dataSnapshot.getRef().child("Car").child("Average_Distance").setValue(car.getAverage_Distance());
                dataSnapshot.getRef().child("Car").child("Average_Distance_Longterm").setValue(car.getAverage_Distance_Longterm());
                dataSnapshot.getRef().child("Car").child("Remaining_Fuel").setValue(car.getRemaining_Fuel());
                dataSnapshot.getRef().child("Car").child("Next_Service_Distance").setValue(car.getNext_Service_Distance());
                dataSnapshot.getRef().child("Car").child("Fuel_Consumption").setValue(car.getFuel_Consumption());
                dataSnapshot.getRef().child("Car").child("Battery_Level").setValue(car.getBattery_Level());
                dataSnapshot.getRef().child("Car").child("ECO_Time").setValue(car.getECO_Time());
                setUserInfoToPreferences();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getProfileImage(final ImageView imageView){

        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",",")).child("PictureURI");

        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    if(!((String)dataSnapshot.getValue(String.class)).contains("No Picture")){
                        Picasso.get().load((String)dataSnapshot.getValue(String.class)).into(imageView);
                    }
                }catch (Exception e){
                    Log.e(TAG,e.getLocalizedMessage());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getFriendsProfileImage(final ImageView imageView,CurrentUser currentUser){
        usersReference = database.getReference().child("Users").child(currentUser.getEmail().replace(".",",")).child("PictureURI");
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    if(!((String)dataSnapshot.getValue(String.class)).contains("No Picture")){
                        Picasso.get().load((String)dataSnapshot.getValue(String.class)).into(imageView);
                    }
                }catch (Exception e){
                    Log.e(TAG,e.getLocalizedMessage());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public void getCarImage(final ImageView imageView){
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",",")).child("CarURI");

        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    if(!((String)dataSnapshot.getValue(String.class)).contains("No Picture")){
                        Picasso.get().load((String)dataSnapshot.getValue(String.class)).into(imageView);
                    }
                }catch (Exception e){
                    Log.e(TAG,e.getLocalizedMessage());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void uploadCarImage(Bitmap bitmap){
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        picsRef = FirebaseStorage.getInstance().getReference().child("images/"+ user.getEmail().replace(".",",")+"_car");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = picsRef.putBytes(data);
        Task<Uri>urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }
                return picsRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri downloadUrl = task.getResult();
                    usersReference.getRef().child("CarURI").setValue(downloadUrl.toString());
                }
            }
        });
    }


    public void uploadProfileImage(Bitmap bitmap){
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        picsRef = FirebaseStorage.getInstance().getReference().child("images/"+ user.getEmail().replace(".",",")+"_profile");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = picsRef.putBytes(data);
        Task<Uri>urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw Objects.requireNonNull(task.getException());
                }
                return picsRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){
                    Uri downloadUrl = task.getResult();
                    usersReference.getRef().child("PictureURI").setValue(downloadUrl.toString());
                }
            }
        });


    }

    public void setUserInfoToPreferences(){

        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
       SharedPreferences sharedPreferences = activity.getSharedPreferences("currentUserPref", Context.MODE_PRIVATE);
      final SharedPreferences.Editor editor = sharedPreferences.edit();
        usersReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              CurrentUser currentUser = dataSnapshot.getValue(CurrentUser.class);
              Gson gson = new Gson();
              editor.putString("currentUser",gson.toJson(currentUser));
              editor.apply();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
              Log.e(TAG,databaseError.getDetails());
            }
        });

    }

    public CurrentUser getUserFromPreferences(){
        Gson gson = new Gson();
      SharedPreferences  sharedPreferences = activity.getSharedPreferences("currentUserPref",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("currentUser", "");
        return  gson.fromJson(json, CurrentUser.class);
    }

    public void setUserFriendsToPreferences(){
        final ArrayList<String> friendEmails = new ArrayList<>();
        friendsReference = database.getReference().child("Friends").child(user.getEmail().replace(".",","));
        friendsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    friendEmails.add(snapshot.getKey());
                }
                changeFriendRequestsToObjects(friendEmails,false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void setUserFriendRequestsToPreferences(){
        final ArrayList<String> requestFriendEmails = new ArrayList<>();
        friendRequestReference = database.getReference().child("FriendRequests").child(user.getEmail().replace(".",","));
        friendRequestReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    for(DataSnapshot innerShot : snapshot.getChildren()){
                        String request_type = innerShot.getValue(String.class);
                        if(request_type.equals("received")){
                            requestFriendEmails.add(snapshot.getKey());
                        }
                    }


                }
                changeFriendRequestsToObjects(requestFriendEmails,true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void changeFriendRequestsToObjects(ArrayList<String>arrayList, final boolean requestOrFriends){
        final Gson gson = new Gson();
        final SharedPreferences sharedPreferences = activity.getSharedPreferences("currentUserPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        if(requestOrFriends){
            editor.remove("currentUserRequest");
            editor.commit();
        }
        else{
            editor.remove("currentUserFriends");
            editor.commit();
        }

        for(String emails:arrayList){
            usersReference = database.getReference().child("Users").child(emails.replace(".",","));
            usersReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    CurrentUser currentUser = dataSnapshot.getValue(CurrentUser.class);
                    JsonArray jsonArray = new JsonArray();
                    String json;

                    if(requestOrFriends)
                        json = sharedPreferences.getString("currentUserRequest", "");
                    else
                        json = sharedPreferences.getString("currentUserFriends", "");


                    if(!json.isEmpty() || !json.equals("")){

                        jsonArray = (JsonArray) new JsonParser().parse(json);
                        if(currentUser!=null){
                            JsonObject object1 = (JsonObject)new JsonParser().parse(gson.toJson(currentUser));
                            jsonArray.add(object1);
                        }

                    }
                    else{
                        if(currentUser!=null){
                            JsonObject object1 = (JsonObject)new JsonParser().parse(gson.toJson(currentUser));
                            jsonArray.add(object1);
                        }

                    }




                    if(requestOrFriends)
                        editor.putString("currentUserRequest",jsonArray.toString());
                    else
                        editor.putString("currentUserFriends",jsonArray.toString());

                    editor.apply();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }





    public ArrayList<CurrentUser> getFriendRequestFromPreferences(){


        ArrayList<CurrentUser> currentUsers=new ArrayList<>();
        Gson gson = new GsonBuilder().setLenient().create();
        SharedPreferences  sharedPreferences = activity.getSharedPreferences("currentUserPref",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("currentUserRequest", "");

        if(!json.isEmpty() || !json.equals("")){
            try {
                JSONArray jsonArray = new JSONArray(json);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    CurrentUser currentUser = gson.fromJson(object.toString(),CurrentUser.class);
                    currentUsers.add(currentUser);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        return currentUsers;
    }




    public ArrayList<CurrentUser> getFriendsFromPreferences(){
        ArrayList<CurrentUser> friends=new ArrayList<>();
        Gson gson = new GsonBuilder().setLenient().create();
        SharedPreferences  sharedPreferences = activity.getSharedPreferences("currentUserPref",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("currentUserFriends", "");
        try {
            if(!json.equals("") && !json.isEmpty()){
                JSONArray jsonArray = new JSONArray(json);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    CurrentUser friend = gson.fromJson(object.toString(),CurrentUser.class);
                    friends.add(friend);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  friends;
    }
    public void setUserChallengesToPreferences(){
        challengeList = new ArrayList<>();
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        SharedPreferences sharedPreferences = activity.getSharedPreferences("currentUserPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        usersReference.child("Challenges").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for(DataSnapshot childrenShot : children){
                        Iterable<DataSnapshot>innerChildren = childrenShot.getChildren();
                        for (DataSnapshot challengeShot : innerChildren){
                            Challenge challenge =challengeShot.getValue(Challenge.class);
                            challengeList.add(challenge);
                        }
                    }
                    Gson gson = new Gson();
                    editor.putString("currentUserChallenges",gson.toJson(challengeList));
                    editor.apply();

                }catch (Exception e){
                    Log.e("ERRORRR",e.getMessage());
                    // e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    public void setChallengeInfoToPreferences(){
        challengeList = new ArrayList<>();
        SharedPreferences sharedPreferences = activity.getSharedPreferences("challengesPref",Context.MODE_PRIVATE);
    final  SharedPreferences.Editor  editor = sharedPreferences.edit();
        challengesReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for(DataSnapshot childrenShot : children){
                        Challenge challenge = childrenShot.getValue(Challenge.class);
                        challengeList.add(challenge);
                    }
                    Gson gson = new Gson();
                    editor.putString("challengeList",gson.toJson(challengeList));
                    editor.apply();


                }catch (Exception e){
                    Log.e(TAG,e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public ArrayList<Challenge> getUserChallengesFromPreferences(){
        Gson gson = new Gson();
        SharedPreferences  sharedPreferences = activity.getSharedPreferences("currentUserPref",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("currentUserChallenges", "");
        TypeToken<ArrayList<Challenge>> token = new TypeToken<ArrayList<Challenge>>() {};
        return  gson.fromJson(json, token.getType());
    }
    public ArrayList<Challenge> getChallengesFromPreferences(){
        Gson gson = new Gson();
        SharedPreferences  sharedPreferences = activity.getSharedPreferences("challengesPref",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("challengeList", "");
        TypeToken<ArrayList<Challenge>> token = new TypeToken<ArrayList<Challenge>>() {};
        return  gson.fromJson(json, token.getType());
    }

    public void setRankingToPreferences(){
    rankList = new ArrayList<>();
        SharedPreferences sharedPreferences = activity.getSharedPreferences("rankPref",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        leaderboardReference.orderByChild("/points").limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childrenShot : dataSnapshot.getChildren()){
                    Rank rank = childrenShot.getValue(Rank.class);
                    rankList.add(rank);
                }

                Collections.sort(rankList,Collections.<Rank>reverseOrder());

                Gson gson = new Gson();
                editor.putString("rankList",gson.toJson(rankList));
                editor.apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    public ArrayList<Rank> getRankingFromPreferences(){
        Gson gson = new Gson();
        SharedPreferences  sharedPreferences = activity.getSharedPreferences("rankPref",Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("rankList", "");
        TypeToken<ArrayList<Rank>> token = new TypeToken<ArrayList<Rank>>() {};
        return  gson.fromJson(json, token.getType());
    }
    public void moveToNewNode(String oldEmailAddress,String newEmailAddress){
        final DatabaseReference oldReference = database.getReference().child("Users").child(oldEmailAddress.replace(".",","));
        final DatabaseReference newReference =  database.getReference().child("Users").child(newEmailAddress.replace(".",","));


        oldReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                newReference.setValue(dataSnapshot.getValue(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError != null){
                            Log.e(TAG,databaseError.getDetails());
                            Toast.makeText(activity,"Failed To Change E-Mail",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(activity,"Success!",Toast.LENGTH_LONG).show();
                            oldReference.removeValue();
                            oldReference.setValue(null);
                        }


                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void updatePassword(final String newPass){
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));

        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usersReference.child("Password").setValue(newPass);
                setUserInfoToPreferences();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void deleteNode(String email){
        usersReference = database.getReference().child("Users").child(email.replace(".",","));
        usersReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(activity,"User Deleted",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(activity,"Failed to delete User",Toast.LENGTH_LONG).show();
            }
        });


        leaderboardReference = database.getReference().child("LeaderBoards").child(email.replace(".",","));
        leaderboardReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });


    }
    public void sendFriendRequest(final String receiversMail){
        friendRequestReference.child(user.getEmail().replace(".",","))
                .child(receiversMail.replace(".",","))
                .child("request_type").setValue("sent").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    friendRequestReference.child(receiversMail.replace(".",","))
                            .child(user.getEmail().replace(".",","))
                            .child("request_type").setValue("received").addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(activity,"Friend Request Send!",Toast.LENGTH_LONG).show();
                        }
                    });

                }
                else
                {
                    Toast.makeText(activity,"Failed sending Friend Request",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void cancelFriendRequest(final String receiversMail){
        friendRequestReference.child(user.getEmail().replace(".",","))
                .child(receiversMail.replace(".",",")).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    friendRequestReference.child(receiversMail.replace(".",","))
                            .child(user.getEmail().replace(".",",")).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(activity,"Friend Request Canceled!",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else{
                    Toast.makeText(activity,"Failed cancel Friend Request",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean isCurrentUser(String incomingMail){
        return   incomingMail.equals(user.getEmail());
    }


    public void addFriend(final String friendsEmail){
        final String currentDate= DateFormat.getDateTimeInstance().format(new Date());
        friendsReference.child(user.getEmail().replace(".",",")).child(friendsEmail.replace(".",",")).setValue(currentDate).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                friendsReference.child(friendsEmail.replace(".",",")).child(user.getEmail().replace(".",",")).setValue(currentDate).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        friendRequestReference.child(user.getEmail().replace(".",","))
                                .child(friendsEmail.replace(".",",")).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    friendRequestReference.child(friendsEmail.replace(".",","))
                                            .child(user.getEmail().replace(".",",")).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(activity,"Friend Added!",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(activity,"Failed add Friend !",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });

            }
        });


    }


    public void sendChallengeRequest(final Challenge challenge, final CurrentUser currentUser){
        challengeRequestReference.child(user.getEmail().replace(".",","))
                .child(currentUser.getEmail().replace(".",","))
                .child("challenge_type")
                .setValue(challenge.getChallengeTitle()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if(task.isSuccessful()) {
                challengeRequestReference.child(user.getEmail().replace(".",","))
                        .child(currentUser.getEmail().replace(".",","))
                        .child("request_type").setValue("sent");


                challengeRequestReference.child(currentUser.getEmail().replace(".",","))
                        .child(user.getEmail().replace(".",","))
                        .child("challenge_type")
                        .setValue(challenge.getChallengeTitle()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        challengeRequestReference.child(currentUser.getEmail().replace(".",","))
                                .child(user.getEmail().replace(".",","))
                                .child("request_type").setValue("received");
                        Toast.makeText(activity, "Challenge Request Send!", Toast.LENGTH_LONG).show();
                    }
                });

                }
            else
                    Toast.makeText(activity, "Failed sending Friend Request", Toast.LENGTH_LONG).show();

            }
        });

    }





    public void setChallengeRequestsToPreferences(){
        final HashMap<String,String> challengeRequests=new HashMap<>();
        challengeRequestReference = database.getReference().child("ChallengeRequests").child(user.getEmail().replace(".",","));
        challengeRequestReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String request_type = snapshot.child("request_type").getValue(String.class);
                        if(request_type.equals("received")){
                            challengeRequests.put(snapshot.getKey(),snapshot.child("challenge_type").getValue(String.class));
                    }
                }
                changeChallengeRequestsToObjects(challengeRequests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void changeChallengeRequestsToObjects(HashMap<String,String> challengeRequest){
        final Gson gson = new Gson();
        final SharedPreferences sharedPreferences = activity.getSharedPreferences("currentUserPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("currentUserChallengeRequestsUsers");
        editor.remove("currentUserChallengeTypes");
        editor.apply();

        for(HashMap.Entry<String,String> entry : challengeRequest.entrySet()){

            usersReference = database.getReference().child("Users").child(entry.getKey().replace(".",","));
            usersReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    CurrentUser currentUser = dataSnapshot.getValue(CurrentUser.class);
                    Gson gson = new Gson();
                    editor.putString("currentUserChallengeRequestsUsers",gson.toJson(currentUser));
                    editor.apply();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            challengesReference.child(entry.getValue()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Challenge challenge = dataSnapshot.getValue(Challenge.class);
                    editor.putString("currentUserChallengeTypes",gson.toJson(challenge));
                    editor.apply();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

    }


    public ArrayList<Challenge> getChallengeRequestFromPreferences(){
        ArrayList<Challenge> challengeArrayList = new ArrayList<>();
        Gson gson = new GsonBuilder().setLenient().create();
        SharedPreferences  sharedPreferences = activity.getSharedPreferences("currentUserPref",Context.MODE_PRIVATE);
        String userJson = sharedPreferences.getString("currentUserChallengeRequestsUsers", "");
        String userChallengeJson = sharedPreferences.getString("currentUserChallengeTypes", "");

        if(!userJson.isEmpty() || !userJson.equals("")){
            if(!userChallengeJson.isEmpty() || !userChallengeJson.equals("")){



            try {

                CurrentUser currentUser = gson.fromJson(userJson,CurrentUser.class);
                Challenge challenge = gson.fromJson(userChallengeJson,Challenge.class);
                challenge.setFriendNickName(currentUser.getNickName());
                challenge.setFriendEmail(currentUser.getEmail());
                challenge.setFriendPictureURI(currentUser.getPictureURI());
                challengeArrayList.add(challenge);
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }

        return challengeArrayList;


    }

    public void acceptChallenge(CurrentUser currentUser,Challenge challenge){
        increasePoints(challenge);
        increaseChallengeCount();
        setRankingToPreferences();
        challenge.setFriendPictureURI(currentUser.getPictureURI());
        challenge.setFriendEmail(currentUser.getEmail());
        challenge.setFriendNickName(currentUser.getNickName());
        addChallengeToDatabase(challenge.getChallengeTitle(),challenge);
        setUserInfoToPreferences();
        setUserChallengesToPreferences();
        deleteChallengeRequest(currentUser,challenge);

    }

    public void rejectChallenge(CurrentUser currentUser,Challenge challenge){
        reducePoints();
        setRankingToPreferences();
        deleteChallengeRequest(currentUser,challenge);


    }
    public void deleteChallengeRequest(final CurrentUser currentUser, Challenge challenge){
        if(!currentUser.getEmail().equals(user.getEmail())){
            challengeRequestReference.child(user.getEmail().replace(".",","))
                    .child(currentUser.getEmail().replace(".",",")).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull final Task<Void> task) {
                    if(task.isSuccessful()){
                        challengeRequestReference.child(currentUser.getEmail().replace(".",","))
                                .child(user.getEmail().replace(".",",")).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                              //  Toast.makeText(activity,"Friend Request Canceled!",Toast.LENGTH_LONG).show();

                            }
                        });
                    }
                    else{
                        Log.e(TAG,task.getException().toString());

                    }
                }
            });
        }


    }

    public void increasePoints(final Challenge challenge){
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        usersReference.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long point = (long) dataSnapshot.child("Points").getValue();
                point += challenge.getPoints();
                dataSnapshot.getRef().child("Points").setValue(point);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        leaderboardReference.child(user.getEmail().replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long point = (long) dataSnapshot.child("points").getValue();
                point += challenge.getPoints();
                dataSnapshot.getRef().child("points").setValue(point);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void reducePoints() {
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            //       mRef.child(LoginActivity.user_full_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long points = (long) dataSnapshot.child("Points").getValue();
                points = points - 1000;
                dataSnapshot.getRef().child("Points").setValue(points);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        leaderboardReference.child(user.getEmail().replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long point = (long) dataSnapshot.child("points").getValue();
                point= point -1000;
                dataSnapshot.getRef().child("points").setValue(point);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    public void increaseChallengeCount() {
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               long challengeCount = (long) dataSnapshot.child("ChallengeCount").getValue();
                challengeCount = challengeCount + 1;
                dataSnapshot.getRef().child("ChallengeCount").setValue(challengeCount);
                dataSnapshot.getRef().child("Level").setValue(setLevelName(challengeCount));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public String setLevelName(long challengeCount){
        if(challengeCount%5==0)
            levelUp();
        if(challengeCount>5 && challengeCount<10)
            return "Star";
        if(challengeCount>10 && challengeCount<15)
            return "Master";
        if(challengeCount>20)
            return "Grandmaster";


        return "Newbie";
    }






    public void addChallengeToDatabase(String title,Challenge incomingChallenge) {
        try {
            usersReference = database.getReference().child("Users").child(user.getEmail().replace(".", ","));
            DatabaseReference newRef = usersReference.child("Challenges");
            Map<String, Challenge> challenges = new HashMap<>();
            challenges.put(title, incomingChallenge);
            newRef.child(title).setValue(challenges);
            setUserInfoToPreferences();
        } catch (Exception e) {
            Log.e("EROOOOR AT ADDING", e.getMessage());
        }

    }

    public void levelUp() {

            View popupView = activity.getLayoutInflater().inflate(R.layout.popup_level_up, null);
            popupView.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_popup_levelup));
            TextView textView = (TextView) popupView.findViewById(R.id.level_up_description);
            textView.setText("Level Up");
            final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.showAtLocation(popupView, Gravity.CENTER, 10, 10);
            FloatingActionButton floatingActionButton = (FloatingActionButton) popupView.findViewById(R.id.closeLevelUp);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }


    }















