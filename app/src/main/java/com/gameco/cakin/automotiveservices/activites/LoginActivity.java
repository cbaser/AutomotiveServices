package com.gameco.cakin.automotiveservices.activites;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.controller.myGoogleLoginController;

import com.gameco.cakin.automotiveservices.controller.myFacebookLoginController;


public class LoginActivity extends AppCompatActivity {
    private EditText emailtxt, passwordtxt;

    private myGoogleLoginController myGoogleLoginController;
    private myFacebookLoginController myFacebookLoginController;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /** Starting to initialization*/

        emailtxt = (EditText) findViewById(R.id.email_edit);
        emailtxt.setText("c.akinbaser@hotmail.com");
        passwordtxt = (EditText) findViewById(R.id.password_edit);
        passwordtxt.setText("12345678");

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
                String email = emailtxt.getText().toString().trim();
                String password = passwordtxt.getText().toString().trim();
                doEmailLogin(email, password);
            }
        });

        /**Google Sign In Part */
        com.google.android.gms.common.SignInButton googleSignInButton = findViewById(R.id.sign_in_button_google);
        myGoogleLoginController = new myGoogleLoginController(this);
        myGoogleLoginController.initialize();
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myGoogleLoginController.startLogin();
            }
        });




        /** Facebook Sign In Part*/
        myFacebookLoginController = new myFacebookLoginController(this);
        myFacebookLoginController.initialize();
      // if(!myFacebookLoginController.checkLoggedIn())
        myFacebookLoginController.startLogin();

    }
    public void doEmailLogin(String email,String password) {
     myFacebookLoginController.emailLogin(email,password);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        myGoogleLoginController.activityResult(requestCode,resultCode,data);
        myFacebookLoginController.activityResult(requestCode,resultCode,data);
    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(LoginActivity.this, OpeningActivity.class);
        startActivity(it);

    }




}

