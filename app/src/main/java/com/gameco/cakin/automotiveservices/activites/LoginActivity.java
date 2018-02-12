package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.onesignal.NotificationHandler;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.onesignal.OneSignal;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText emailtxt,passwordtxt;
    private ImageView backView;
    private ImageView mainIcon;
    public static String LoggedIn_User_Email;
    public static String user_full_name;
    private FirebaseAuth firebaseAuth;
    public static FirebaseDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
        }
        firebaseAuth = FirebaseAuth.getInstance();
        emailtxt = (EditText) findViewById(R.id.email_edit);
        emailtxt.setText("cagatayakin.baser@tum.de");
        passwordtxt = (EditText) findViewById(R.id.password_edit);
        passwordtxt.setText("1234567");
        loginBtn = (Button) findViewById(R.id.loginBtn);
        mainIcon = (ImageView) findViewById(R.id.loginIcon);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_from_left_overshoot);
        mainIcon.setAnimation(animation );

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailtxt.getText().toString().trim();
                String password= passwordtxt.getText().toString().trim();
                doLogin(email,password);
            }
        });

        backView=(ImageView) findViewById(R.id.backButton);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this,OpeningActivity.class);
                startActivity(it);
            }
        });
    }

    private void doLogin(final String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("---TESTING---", "sign In Successful:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Log.e("---TESTING---", "signInWithEmail:failed", task.getException());
                    Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(firebaseAuth.getCurrentUser().getEmail().contains("can"))
                    Toast.makeText(LoginActivity.this, "Welcome:" +"Can!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(LoginActivity.this, "Welcome:" +"Cagatay!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                   FirebaseUser user = firebaseAuth.getCurrentUser();
                    Log.d("LOGGED", "user: " + user);


                    //Setting the tags for Current User.
                    if (user != null) {
                        LoggedIn_User_Email =user.getEmail();
                        if(LoggedIn_User_Email.equals("cagatayakin.baser@tum.de"))
                            user_full_name="Cagatay Baser";
                        else
                            user_full_name="Can Turker";
                    }
                    OneSignal.sendTag("User_ID", LoggedIn_User_Email);
                    finish();
                    startActivity(i);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        
        Intent it = new Intent(LoginActivity.this,OpeningActivity.class);
        startActivity(it);

    }









}

// FirebaseDatabase.getInstance().setPersistenceEnabled(true);

//mDatabase.setPersistenceEnabled(true);
//                BackendHelper backendHelper = new BackendHelper();
//                String response;
//                if(emailtxt.getText().toString().equals("Cagatay")&& emailtxt.getText().toString().equals("123")){
//                    response ="admin";
//                }
//                else if(userName_edit.getText().toString().equals("Can")&& password_edit.getText().toString().equals("123")){
//                    response="admin";
//                }
//
//                    else
//                    response =   backendHelper.tryLogin("Login",userName_edit.getText().toString(),password_edit.getText().toString());
//
//
//
//             if(response.equals("admin")){
//                 SharedPreferences settings =getSharedPreferences("UserInfo",0);
//                 SharedPreferences.Editor editor = settings.edit();
//                 editor.putString("Username",userName_edit.getText().toString());
//                 editor.putString("Password",password_edit.getText().toString());
//                 editor.apply();
//                 Intent intent = new Intent(LoginActivity.this,MainActivity.class);
//                 startActivity(intent);
//             }
