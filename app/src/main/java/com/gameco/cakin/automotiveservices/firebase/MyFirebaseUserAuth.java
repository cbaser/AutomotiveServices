package com.gameco.cakin.automotiveservices.firebase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyFirebaseUserAuth {


    private FirebaseAuth firebaseAuth;
    private CurrentUser currentUser;
    private FirebaseUser firebaseUser;
    private MyFirebaseDatabase myFirebaseDatabase;
    private AuthCredential authCredential;
    private String TAG = "MyFirebaseUserAuth";
    private Activity activity;
    public MyFirebaseUserAuth(Activity activity) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        myFirebaseDatabase = new MyFirebaseDatabase(activity);
        currentUser = myFirebaseDatabase.getUserFromPreferences();
        this.activity = activity;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }
    public FirebaseUser getFirebaseUser() {
        return firebaseUser;
    }

    public void deleteUserAuth(){
        authCredential = EmailAuthProvider.getCredential(currentUser.getEmail(),currentUser.getPassword());
        firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                 @Override
                 public void onComplete(@NonNull Task<Void> task) {
                     if(task.isSuccessful()){
                         Toast.makeText(activity,"User Account Deleted",Toast.LENGTH_LONG).show();
                         myFirebaseDatabase.deleteNode(currentUser.getEmail());
                     }

                 }
             });
            }
        });


    }


    public void updateEmailAuth(final String newEmail) {

        authCredential = EmailAuthProvider.getCredential(currentUser.getEmail(),currentUser.getPassword());
        firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.e(TAG,"User Re-Authenticated");

                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                firebaseUser.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            myFirebaseDatabase.moveToNewNode(currentUser.getEmail(),newEmail);
                            Toast.makeText(activity,"Email Address updated!",Toast.LENGTH_LONG).show();
                        }

                    }
                });


            }
        });
    }
    public void updatePassAuth(final String newPass){
        authCredential = EmailAuthProvider.getCredential(currentUser.getEmail(),currentUser.getPassword());
        firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.e(TAG,"User Re-Authenticated");
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                firebaseUser.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            myFirebaseDatabase.updatePassword(newPass);
                            Toast.makeText(activity,"Password updated!",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(activity,"Please Try Again",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


    }


}
