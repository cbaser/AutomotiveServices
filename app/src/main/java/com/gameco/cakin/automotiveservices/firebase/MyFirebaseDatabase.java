package com.gameco.cakin.automotiveservices.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.gameco.cakin.automotiveservices.activites.MainActivity;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.datamodel.Rank;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.concurrent.Semaphore;

public class MyFirebaseDatabase {
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference,leaderBoardReference;
    private CurrentUser currentUser;
    private Activity activity;
    private StorageReference picsRef;


    public MyFirebaseDatabase (Activity activity){
        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
         leaderBoardReference = database.getReference().child("Leaderboards");
        currentUser = new CurrentUser();
        this.activity = activity;
    }

    public DatabaseReference getDatabaseReference() {
        return database.getReference().child("Users").child(user.getEmail().replace(".",","));
    }

    public void createAccountInFirebaseDatabase(String email, String password, String nickname, String VIN, String Uri){
        databaseReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));
        Car car = new Car();
        car.setVIN(VIN);
        databaseReference.child("Nick Name").setValue(nickname);
        databaseReference.child("Email").setValue(email);
        databaseReference.child("Password").setValue(password);
        databaseReference.child("Car").setValue(car);
        databaseReference.child("Level").setValue("Newbie");
        databaseReference.child("Points").setValue(0);
        databaseReference.child("ChallengeCount").setValue(0);
        databaseReference.child("PictureURI").setValue(Uri);
        Rank rank = new Rank();
        rank.setNickname(nickname);
        rank.setPoint(0);
        leaderBoardReference.push().setValue(rank);
                ;
        leaderBoardReference.child("Points").setValue(0);
    }





    public void setCurrentUser (CurrentUser currentUser){
        this.currentUser = currentUser;
    }
    public CurrentUser getCurrentUser(){
        return  currentUser;
    }
    public void updateCarData(final Car car){
        databaseReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("Car").child("Mileage").setValue(car.getMileage());
                dataSnapshot.getRef().child("Car").child("Average distance").setValue(car.getAverage_distance_per_week());
                dataSnapshot.getRef().child("Car").child("Remaining Fuel").setValue(car.getRemaining_fuel());
                dataSnapshot.getRef().child("Car").child("Next Service").setValue(car.getNextServiceDistance());
                dataSnapshot.getRef().child("Car").child("Fuel Consumption").setValue(car.getFuel_consumption());
                dataSnapshot.getRef().child("Car").child("Battery Level").setValue(car.getBatteryVoltage());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getImage(final ImageView imageView){

        databaseReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));

        picsRef = FirebaseStorage.getInstance().getReference().child("images/"+ user.getEmail().replace(".",","));
        try {
            final File localFile = File.createTempFile("images", "jpg");
            picsRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    imageView.setImageBitmap( BitmapFactory.decodeFile(localFile.getAbsolutePath()));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(activity,"Problem for downloading image",Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void uploadImage(Bitmap bitmap){
        databaseReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));

        picsRef = FirebaseStorage.getInstance().getReference().child("images/"+ user.getEmail().replace(".",","));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = picsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                final Uri downloadUrl = taskSnapshot.getDownloadUrl();
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("PictureURI").setValue(downloadUrl);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Log.d("MyFirebaseDatabase",downloadUrl.getEncodedPath());
            }
        });


    }







    public void getCurrentUserFromDatabase(){
        databaseReference = database.getReference().child("Users").child(user.getEmail().replace(".",","));

        final Semaphore semaphore = new Semaphore(0);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CurrentUser currentUser = dataSnapshot.getValue(CurrentUser.class);
              setCurrentUser(dataSnapshot.getValue(CurrentUser.class));
                semaphore.release();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

    }


}
