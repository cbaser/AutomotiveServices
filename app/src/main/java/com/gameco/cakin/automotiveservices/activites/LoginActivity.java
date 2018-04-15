package com.gameco.cakin.automotiveservices.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText emailtxt,passwordtxt;
    private ImageView backView;
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private ImageView mainIcon;
    private String VIN;
    private BackendHelper backendHelper;
//    public static String LoggedIn_User_Email;
//    public static String user_full_name;
    public static String user_vin;
    private boolean response;
    private static final String TAG = "LoginActivity";
    private FirebaseAuth firebaseAuth;
    private MyFirebaseDatabase myFirebaseDatabase;
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
        emailtxt.setText("c.akinbaser@hotmail.com");
        passwordtxt = (EditText) findViewById(R.id.password_edit);
        passwordtxt.setText("12345678");
        myFirebaseDatabase = new MyFirebaseDatabase(this);
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
         callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginManager = LoginManager.getInstance();
        LoginManager.getInstance().setLoginBehavior(LoginBehavior.WEB_ONLY);
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends","read_custom_friendlists"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e(TAG, "facebook:onSuccess:" + loginResult);

                checkUserExists(loginResult.getAccessToken());
               handleGraphRequests(loginResult.getAccessToken());
               handleFacebookAccessToken(loginResult.getAccessToken());
         //  }
//           else{
//               Toast.makeText(LoginActivity.this, "Could not sign in with facebook.",
//                       Toast.LENGTH_SHORT).show();
//           }

                GraphRequest.newMyFriendsRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray objects, GraphResponse response) {

                        try{
                            Log.e(TAG,response.toString());
                            JSONObject jsonObject = response.getJSONObject();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

//                        JSONArray friendsArray = objects.getJSONObject("friendlist").getJSONArray("data");
//                        JSONObject friendlistObject = friendsArray.getJSONObject(0);
                    }
                });


            }
            public void checkUserExists(AccessToken accessToken){
                Bundle parameters = new Bundle();
                GraphRequest myRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(final JSONObject object, GraphResponse response) {
                        try{
                          final  Profile profile = Profile.getCurrentProfile();
                            if(profile != null){

                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                                reference.child(object.getString("email").replace(".",",")).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        try{
                                            if(!dataSnapshot.exists()){
                                                myFirebaseDatabase.createAccountInFirebaseDatabase((String)object.getString("email").replace(".",","),"Provided From Facebook",(String)object.getString("name"), getVINFromUser(),profile.getProfilePictureUri(100,100).toString());
                                            }
                                        }catch (Exception e){
                                            Log.e(TAG,e.getMessage());
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                                Log.e(TAG,profile.getProfilePictureUri(100,100).toString());
                                Log.e(TAG,(String)object.getString("email"));
                                Log.e(TAG,(String)object.getString("birthday"));
                                Log.e(TAG,(String)object.getString("name"));
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                parameters.putString("fields", "id,name,email,gender,birthday");
                myRequest.setParameters(parameters);
                myRequest.executeAsync();



            }

            @Override
            public void onCancel() {
            Log.e(TAG,"facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
            Log.e(TAG,"facebook:onError");
            }
        });

    }
    private void handleGraphRequests(AccessToken loginResult){
        Bundle parameters = new Bundle();
        GraphRequest myRequest = GraphRequest.newMeRequest(loginResult, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try{
                    Profile profile = Profile.getCurrentProfile();
                    if(profile != null){
                        Log.e(TAG,profile.getProfilePictureUri(100,100).toString());
                        Log.e(TAG,(String)object.getString("email"));
                        Log.e(TAG,(String)object.getString("birthday"));
                        Log.e(TAG,(String)object.getString("name"));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        parameters.putString("fields", "id,name,email,gender,birthday");
        myRequest.setParameters(parameters);
        myRequest.executeAsync();

   GraphRequest friendRequest= GraphRequest.newMyFriendsRequest(loginResult, new GraphRequest.GraphJSONArrayCallback() {
        @Override
        public void onCompleted(JSONArray objects, GraphResponse response) {
            try{
                JSONObject friendlistObject = objects.getJSONObject(0);
                String friendListID = friendlistObject.getString("id");
                myNewGraphReq(friendListID);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    });
        parameters.putString("fields", "friendlist");
        friendRequest.setParameters(parameters);
        friendRequest.executeAsync();
        // JSONArray jsonArrayFriends = objects.getJSONObject("friendlist").getJSONArray("data");
        /// JSONObject friendlistObject = jsonArrayFriends.getJSONObject(0);
//        GraphRequest request= GraphRequest.newMeRequest(loginResult, new GraphRequest.GraphJSONObjectCallback() {
//            @Override
//            public void onCompleted(JSONObject object, GraphResponse response) {
//                Log.e("LoginActivity", response.toString());
//                try {
//
//                    createAccountInFirebaseDatabase((String)object.getString("email"),"Provided From Facebook",(String)object.getString("name"),user_vin);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        parameters.putString("fields", "friendlist");
//        request.setParameters(parameters);
//        request.executeAsync();
    }
    private void myNewGraphReq(String friendlistId) {
        final String graphPath = "/"+friendlistId+"/members/";
        AccessToken token = AccessToken.getCurrentAccessToken();
        GraphRequest request = new GraphRequest(token, graphPath, null, HttpMethod.GET, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {
                JSONObject object = graphResponse.getJSONObject();
                try {
                    JSONArray arrayOfUsersInFriendList= object.getJSONArray("data");
                    /* Do something with the user list */
                    /* ex: get first user in list, "name" */
                    JSONObject user = arrayOfUsersInFriendList.getJSONObject(0);
                    String usersName = user.getString("name");
                    Log.e(TAG,usersName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle param = new Bundle();
        param.putString("fields", "name");
        request.setParameters(param);
        request.executeAsync();
    }


//    public void createAccountInFirebaseDatabase(String email,String password,String fullname,String VIN){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(email.replace(".",","));
//        Car car = new Car();
//        car.setVIN(VIN);
//        reference.child("Full Name").setValue(fullname);
//        reference.child("Email").setValue(email);
//        reference.child("Password").setValue(password);
//        reference.child("Car").setValue(car);
//        reference.child("Level").setValue("Newbie");
//        reference.child("Points").setValue(0);
//        reference.child("ChallengeCount").setValue(0);
//
//
//    }

    private String getVINFromUser(){

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.popup_get_vin, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        // ToDo get user input here
                        String[] tmp = new String[2];
                        tmp[0]="VIN";
                        tmp[1]=userInputDialogEditText.getText().toString();
                        VIN = userInputDialogEditText.getText().toString();
                        if(backendHelper.tryRegister(tmp).contains("OK"))
                            response=true;
                        else
                            Toast.makeText(LoginActivity.this, "Please Check your Vehicle Identification Number.",
                                    Toast.LENGTH_SHORT).show();
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();

          return  VIN;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            finish();
                            startActivity(i);
                          //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

    public void doLogin(final String email, String password){

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
                    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users");
                    rootRef.child(email.replace(".",",")).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Log.e(TAG,dataSnapshot.child("Nick Name").getValue().toString());
                            Toast.makeText(LoginActivity.this,"Welcome : "+dataSnapshot.child("Nick Name").getValue().toString(),Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Intent i = new Intent(getApplicationContext(), ProgressActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Log.d("LOGGED", "user: " + user.getUid());


                    OneSignal.sendTag("User_ID", firebaseAuth.getCurrentUser().getEmail());
                    startActivity(i);
                    finish();

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

//Setting the tags for Current User.
//                    if (user != null) {
//                        LoggedIn_User_Email =user.getEmail();
//                        if(LoggedIn_User_Email.equals("cagatayakin.baser@tum.de"))
//                            user_full_name="Cagatay Baser";
//                        else
//                            user_full_name="Can Turker";
//                    }
//                    DatabaseReference reference = rootRef.child("Users").child(firebaseAuth.getCurrentUser().getEmail());


//                    if(firebaseAuth.getCurrentUser().getEmail().contains("can"))
//                        Toast.makeText(LoginActivity.this, "Welcome:" +"Can!", Toast.LENGTH_SHORT).show();
//
//                    else
//                        Toast.makeText(LoginActivity.this, "Welcome:" +"Cagatay!", Toast.LENGTH_SHORT).show();
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,gender, birthday");
//                request.setParameters(parameters);
//                request.executeAsync();
//               handleGraphRequests(loginResult.getAccessToken());
//               handleFacebookAccessToken(loginResult.getAccessToken());

//                 GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.e("LoginActivity", response.toString());
//
//                        try {
//                            Profile profile = Profile.getCurrentProfile();
//                            Log.e(TAG,profile.toString());
//                            Log.e(TAG,(String)object.getString("email"));
//                            Log.e(TAG,(String)object.getString("birthday"));
//                            Log.e(TAG,(String)object.getString("name"));
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.gameco.cakin.automotiveservices",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//        Log.d(TAG, "handleFacebookAccessToken:" + token);
//  //      response = myFirebaseAuth.doFacebookLogin(token);
//        if(response){
//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            finish();
//            startActivity(i);
//        }
//         [START_EXCLUDE silent]
//        showProgressDialog();
//         [END_EXCLUDE]
//        response=  myFirebaseAuth.doEmailLogin(email, password, new OnGetDataListener() {
//            @Override
//            public void onSuccessAuth(boolean response) {
//                if(response){
//                  //  LoginActivity.LoggedIn_User_Email =email;
//                    if(LoggedIn_User_Email.equals("cagatayakin.baser@tum.de"))
//                        user_full_name="Cagatay Baser";
//                    else
//                        user_full_name="Can Turker";
//
//                  //  OneSignal.sendTag("User_ID", email);
//                    finish();
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(i);
//                }
//            }
//
//            @Override
//            public void onFailedAuth(boolean response) {
//
//            }
//        });
//  firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Log.d(TAG, "signInWithEmail:success");
//                    FirebaseUser user = firebaseAuth.getCurrentUser();
//                    Log.d(TAG,user.getEmail());
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    finish();
//                    startActivity(i);
//                }
//            }
//        });
//      response=  myFirebaseAuth.doEmailLogin(email,password);
//
//    }
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
