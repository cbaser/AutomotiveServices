package com.gameco.cakin.automotiveservices.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyFirebaseDatabase {
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference usersReference,leaderboardReference,challengesReference,friendRequestReference,friendsReference;
    private Activity activity;
    private StorageReference picsRef;
    private ArrayList<Challenge> challengeList;
    private ArrayList<Rank> rankList;
    private long challengeCount,points;
    private boolean levelUp;
    private String afterLevel;
    private String TAG = "MyFirebaseDatabase";


    public MyFirebaseDatabase (Activity activity){
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        leaderboardReference = database.getReference().child("Leaderboards");
        challengesReference = database.getReference().child("Challenges").child("Title");
        friendRequestReference = database.getReference().child("FriendRequests");
        friendsReference = database.getReference().child("Friends");
        this.activity = activity;
    }
    public String getUsersMail(){
        return user.getEmail();
    }
    public void getFriendRequests(final String friendMail){
        friendRequestReference.child(getUsersMail().replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(friendMail.replace(".",","))){
                    String req_type = dataSnapshot.child(friendMail.replace(".",",")).child("request_type").getValue().toString();
                    if(req_type.equals("received")){

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
         usersReference.child("CarURI").setValue(Uri+"_car");
        Rank rank = new Rank();
        rank.setNickName(nickname);
        rank.setPoints(0);
        leaderboardReference.child(email.replace(".",",")).setValue(rank);
    }
    public void increasePoints(){
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        usersReference.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long point = (long) dataSnapshot.child("Points").getValue();
                point += 100;
                dataSnapshot.getRef().child("Points").setValue(point);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setUserInfoToPreferences();

    }
    public void reducePoints() {
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            //       mRef.child(LoginActivity.user_full_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                points = (long) dataSnapshot.child("Points").getValue();
                points = points - 250;
                dataSnapshot.getRef().child("Points").setValue(points);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setUserInfoToPreferences();

    }



    public void increaseChallengeCount() {
        usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 challengeCount = (long) dataSnapshot.child("ChallengeCount").getValue();
                challengeCount = challengeCount + 1;
                setLevel(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        setUserInfoToPreferences();
    }
    public void setLevel(DataSnapshot dataSnapshot){
        if (challengeCount % 5 == 0) {
            challengeCount = 0;

            String beforeLevel = (String) dataSnapshot.child("Level").getValue();

            switch (beforeLevel) {
                case "Newbie":
                    dataSnapshot.getRef().child("Level").setValue("Star");
                    afterLevel = "Star";
                    levelUp =true;
                    break;
                case "Star":
                    dataSnapshot.getRef().child("Level").setValue("Master");
                    afterLevel = "Master";
                    levelUp = true;
                    break;
                case "Master":
                    dataSnapshot.getRef().child("Level").setValue("Grandmaster");
                    afterLevel = "Grandmaster";
                    levelUp = true;
                    break;
            }

        }
        dataSnapshot.getRef().child("ChallengeCount").setValue(challengeCount);

    }

    public boolean isLevelUp(){
        return levelUp;
    }
    public String getAfterLevel(){
        return afterLevel;
    }



    public void addToDatabase(String title,Challenge incomingChallenge) {
        try {
            usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
            DatabaseReference newRef = usersReference.child("Challenges");
            Map<String, Challenge> challenges = new HashMap<>();
            challenges.put(title, incomingChallenge);
            newRef.child(title).setValue(challenges);
            increaseChallengeCount();
           increasePoints();
        } catch (Exception e) {
            Log.e("EROOOOR AT ADDING", e.getMessage());
        }

        // newRef.setValue(challenges);
    }
    public void addAcceptedChallenge(Challenge incoming){
        try {
            usersReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
            DatabaseReference newRef = usersReference.child("Challenges");
            Map<String, Challenge> challenges = new HashMap<>();
            challenges.put(incoming.getChallengeTitle(), incoming);
            newRef.push().setValue(challenges);
            increaseChallengeCount();
        } catch (Exception e) {
            Log.e("EROOOOR AT ADDING", e.getMessage());
        }

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
                    throw task.getException();
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
                    throw task.getException();
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

    public void getFriendsFromDatabase(){
        final ArrayList<String> friendEmails = new ArrayList<>();
        friendsReference = database.getReference().child("Friends").child(user.getEmail().replace(".",","));
        friendsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    friendEmails.add(snapshot.getKey());
                }
                changeRequestsToObjects(friendEmails,false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }




    public void getFriendRequestFromDatabase(){
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
                changeRequestsToObjects(requestFriendEmails,true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void changeRequestsToObjects(ArrayList<String>arrayList, final boolean requestOrFriends){
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
                       JsonObject object1 = (JsonObject)new JsonParser().parse(gson.toJson(currentUser));
                       jsonArray.add(object1);
                   }
                   else{
                       JsonObject object1 = (JsonObject)new JsonParser().parse(gson.toJson(currentUser));
                       jsonArray.add(object1);
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
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object = jsonArray.getJSONObject(i);
                CurrentUser friend = gson.fromJson(object.toString(),CurrentUser.class);
                friends.add(friend);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  friends;
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
        leaderboardReference.orderByChild("Points").limitToFirst(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for(DataSnapshot childrenShot : children){
                   Rank rank = childrenShot.getValue(Rank.class);
                   rankList.add(rank);
                }

                Gson gson = new Gson();
                editor.putString("rankList",gson.toJson(rankList));
                editor.apply();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,databaseError.getDetails());
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
                if(task.isSuccessful()){

                }
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








}
