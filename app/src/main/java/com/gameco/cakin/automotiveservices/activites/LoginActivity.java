package com.gameco.cakin.automotiveservices.activites;



import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.myGoogleLoginController;

import com.gameco.cakin.automotiveservices.controller.myFacebookLoginController;
import com.gameco.cakin.automotiveservices.onesignal.NotificationOpenedHandler;
import com.gameco.cakin.automotiveservices.onesignal.NotificationReceivedHandler;
import com.onesignal.OneSignal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {
    private EditText emailtxt, passwordtxt;

    private myGoogleLoginController googleLoginController;
    private myFacebookLoginController facebookLoginController;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /** Intitialize OneSignal */
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationOpenedHandler(this))
                .setNotificationReceivedHandler(new NotificationReceivedHandler(this))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        /** Starting to initialization*/

        emailtxt = (EditText) findViewById(R.id.email_edit);
        passwordtxt = (EditText) findViewById(R.id.password_edit);


        ImageView backView = (ImageView) findViewById(R.id.backButton);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, OpeningActivity.class);
                startActivity(it);
            }
        });



        /**Animation Part*/
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_from_left_overshoot);
        ImageView mainIcon = (ImageView) findViewById(R.id.loginIcon);
        mainIcon.setAnimation(animation);


        /**Email and Password Login Part*/
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validateForm())
                    return;
                String email = emailtxt.getText().toString().trim();
                String password = passwordtxt.getText().toString().trim();
                doEmailLogin(email, password);
            }
        });

        /**Google Sign In Part */
        com.google.android.gms.common.SignInButton googleSignInButton = findViewById(R.id.sign_in_button_google);
        googleLoginController = new myGoogleLoginController(this);
        googleLoginController.initialize();
        if(!googleLoginController.checkLoggedIn()){
            googleSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    googleLoginController.startLogin();
                }
            });
        }
        else
            googleLoginController.startLoggedInProcess();

        /** Facebook Sign In Part*/
        facebookLoginController = new myFacebookLoginController(this);
        facebookLoginController.initialize();
       if(!facebookLoginController.checkLoggedIn())
           facebookLoginController.startLogin();
        else
           facebookLoginController.startLoggedInProcess();

    }
    public void doEmailLogin(String email,String password) {
        facebookLoginController.emailLogin(email,password);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleLoginController.activityResult(requestCode,resultCode,data);
        facebookLoginController.activityResult(requestCode,resultCode,data);
    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(LoginActivity.this, OpeningActivity.class);
        startActivity(it);

    }
    private boolean validateForm(){
        boolean valid =true;
        String email = emailtxt.getText().toString();
        if(TextUtils.isEmpty(email)){
            emailtxt.setError("Required");
            valid =false;
        }
        else
            emailtxt.setError(null);
        String password =passwordtxt.getText().toString();
        if(TextUtils.isEmpty(password)){
            passwordtxt.setError("Required");
            valid =false;
        }
        else
            passwordtxt.setError(null);
        return valid;
    }




}

