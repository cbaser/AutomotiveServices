package com.gameco.cakin.automotiveservices.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class myFacebookLoginController extends myLoginController {

    private CallbackManager callbackManager;
    private String TAG="Facebook Login Controller";
    private AccessToken accessToken;


    public myFacebookLoginController(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
    }


    @Override
    public void initialize() {
        callbackManager = CallbackManager.Factory.create();
        //AppEventsLogger.activateApp(appCompatActivity);
        //FacebookSdk.sdkInitialize(appCompatActivity);
        //LoginManager loginManager = LoginManager.getInstance();
        LoginManager.getInstance().setLoginBehavior(LoginBehavior.WEB_ONLY);
    }

    @Override
    public void handleAccount() {


    }

    @Override
    public boolean checkPreferences() {
        sharedPreferences = appCompatActivity.getSharedPreferences("facebookPrefs", Context.MODE_PRIVATE);
        if(!sharedPreferences.contains("initialized")){
            editor = sharedPreferences.edit();

            //Indicate that the default shared prefs have been set
            editor.putBoolean("initialized", true);
            editor.apply();
            return false;
        }
        return true;
    }

    @Override
    public void startLogin() {
        final LoginButton loginButton = appCompatActivity.findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, "facebook:onSuccess:" + loginResult.getAccessToken());
                handleFacebookAccessToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Log.e(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {

                Toast.makeText(appCompatActivity, "Could not sign in with facebook.", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "facebook:onError");
            }
        });

    }

    @Override
    public void activityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
  public  void signOut() {
        LoginManager.getInstance().logOut();


    }
    public boolean checkLoggedIn(){

        Profile profile = Profile.getCurrentProfile();
        return profile != null;
    }
    public void startLoggedInProcess(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        handleFacebookAccessToken(accessToken);

    }

    private void handleFacebookAccessToken(final AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(appCompatActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            /** User has logged in with facebook before **/
                            if(checkPreferences()){
                                Log.e("Facebook Controller", "signInWithCredential:success");
                                OneSignal.sendTag("User_ID", Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail());
                                startMainActivity();
                            }
                            else
                            {

                                getFacebookInfo(token);
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(appCompatActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
    private void getFacebookInfo(AccessToken accessToken){
        Bundle parameters = new Bundle();
        GraphRequest myRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    Profile profile = Profile.getCurrentProfile();
                    if (profile != null) {
                        startRegistration(object.getString("email"), object.getString("name"), profile.getProfilePictureUri(100, 100).toString(),true);
                        //  myFirebaseDatabase.createAccountInFirebaseDatabase((String)object.getString("email"),"Provided by 3rd Party", (String) object.getString("name"),VIN, profile.getProfilePictureUri(100, 100).toString());
                    }
                    else{
                        Toast.makeText(appCompatActivity,"Failed To Connect Facebook",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        parameters.putString("fields", "id,name,email,gender,birthday");
        myRequest.setParameters(parameters);
        myRequest.executeAsync();

    }





}
