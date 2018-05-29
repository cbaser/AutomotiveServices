package com.gameco.cakin.automotiveservices.controller;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.gameco.cakin.automotiveservices.R;

import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.onesignal.OneSignal;

public class myGoogleLoginController extends myLoginController {

    private GoogleApiClient googleApiClient;

    private FirebaseAuth firebaseAuth;
    private String TAG = getClass().getName();
    public static final int RequestSignInCode = 7;
    public myGoogleLoginController(AppCompatActivity appCompatActivity){
        super(appCompatActivity);
    }


    public void initialize(){
        myFirebaseDatabase = new MyFirebaseDatabase(appCompatActivity);
        firebaseAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(appCompatActivity.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(appCompatActivity)
                .enableAutoManage(appCompatActivity, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(appCompatActivity, "Failed to Connect Google", Toast.LENGTH_SHORT).show();
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }

    @Override
    public void handleAccount() {

    }
    public void signOut(){
        if(googleApiClient!= null){
            if (googleApiClient.isConnected()) {
                Auth.GoogleSignInApi.signOut(googleApiClient);
                googleApiClient.disconnect();
            }
        }

    }
    public boolean checkLoggedIn(){
        Log.e(TAG,String.valueOf(googleApiClient.isConnected()));
        Log.e(TAG,String.valueOf(googleApiClient.isConnected()));
        return googleApiClient != null && googleApiClient.isConnected();
    }
    public void startLoggedInProcess(){
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(appCompatActivity);
        handleGoogleAccount(googleSignInAccount);
    }


    public void startLogin() {
        Intent authIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        //TODO:Check request code
        appCompatActivity.startActivityForResult(authIntent, RequestSignInCode);
    }
    public boolean checkPreferences(){
        SharedPreferences sharedPreferences = appCompatActivity.getSharedPreferences("googlePrefs", appCompatActivity.MODE_PRIVATE);
        if(!sharedPreferences.contains("initialized")){
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //Indicate that the default shared prefs have been set
            editor.putBoolean("initialized", true);
            editor.commit();
            return false;
        }
        return true;
    }
    public void activityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == RequestSignInCode) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()) {
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                handleGoogleAccount(googleSignInAccount);
            }
            else
                Log.e(TAG,""+googleSignInResult.getStatus());

        }


    }
    public void handleGoogleAccount(final GoogleSignInAccount googleSignInAccount) {

        final AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);

        Log.e(TAG, "" + authCredential.getProvider());

        firebaseAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(appCompatActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task AuthResultTask) {

                        if (AuthResultTask.isSuccessful()) {
                            /** Check User has logged in with Google Before */
                            if (checkPreferences()) {
                                OneSignal.sendTag("User_ID", firebaseAuth.getCurrentUser().getEmail());
                                startMainActivity();
                            } else {
                                startRegistration(googleSignInAccount.getEmail(), googleSignInAccount.getDisplayName(), googleSignInAccount.getPhotoUrl().toString(), false);
                            }
                        } else {
                            Toast.makeText(appCompatActivity, "Something Went Wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }





}
