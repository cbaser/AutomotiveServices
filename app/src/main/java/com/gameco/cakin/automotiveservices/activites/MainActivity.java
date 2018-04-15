package com.gameco.cakin.automotiveservices.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;

import com.gameco.cakin.automotiveservices.controller.myActivityController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.datamodel.CurrentUser;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;
import com.gameco.cakin.automotiveservices.firebase.OnGetDataListener;
import com.gameco.cakin.automotiveservices.onesignal.NotificationOpenedHandler;
import com.gameco.cakin.automotiveservices.onesignal.NotificationReceivedHandler;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "MainActivity";
    private long backPressedTime = 0;
    private MyFirebaseDatabase firebaseDatabase;
    private myActivityController activityController;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private GoogleApiClient GoogleApiClient;
    public static Context contextOfApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationOpenedHandler(this))
                .setNotificationReceivedHandler(new NotificationReceivedHandler(this))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        Branch branch = Branch.getInstance();

        // Branch init
        branch.initSession(new Branch.BranchReferralInitListener() {
            @Override
            public void onInitFinished(JSONObject referringParams, BranchError error) {
                if (error == null) {
                    // params are the deep linked params associated with the link that the user clicked -> was re-directed to this app
                    // params will be empty if no data found
                    // ... insert custom logic here ...
                    Log.i("BRANCH SDK", referringParams.toString());
                } else {
                    Log.i("BRANCH SDK", error.getMessage());
                }
            }
        }, this.getIntent().getData(), this);
        boolean autodeeplink = true;
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(AppInvite.API)
//                .enableAutoManage(this, this)
//                .build();
        activityController = new myActivityController(this);
        setContentView(R.layout.activity_main);
        setTitle("");
        activityController.setUpTabs();
        activityController.setupNavigationMenu();
        firebaseDatabase = new MyFirebaseDatabase(this);
        String token = FirebaseInstanceId.getInstance().getToken(); //İlk çalışmada boş.
        FirebaseDynamicLinks.getInstance().getDynamicLink(this.getIntent()).addOnSuccessListener(new OnSuccessListener<PendingDynamicLinkData>() {
            @Override
            public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                Uri deeplink;
                if(pendingDynamicLinkData != null){
                    deeplink = pendingDynamicLinkData.getLink();
                }
            }
        });


        String msg = "Token :"+token;
        Log.d(TAG, msg);


    }
    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
    @Override
    protected void onStart(){
        super.onStart();
      FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    final FirebaseDatabase    database = FirebaseDatabase.getInstance();

        sharedPreferences = this.getSharedPreferences("userdetails",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        DatabaseReference innerRef = database.getReference().child("Users").child(user.getEmail().replace(".",",")).child("Car").child("vin");
        innerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.getValue().toString().isEmpty()||!dataSnapshot.getValue().toString().equals("null")){
                    editor.putString("VIN",dataSnapshot.getValue().toString());
                    editor.apply();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
             backPressedTime = (backPressedTime + 1);
            Toast.makeText(getApplicationContext(), " Press Back again to Logout ", Toast.LENGTH_SHORT).show();
            if (backPressedTime > 1) {

                FirebaseAuth.getInstance().signOut();
                Intent it = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(it);

            }
          //  super.onBackPressed();
        }
    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
//    @Override
//    public void registerComponents(Context context, Glide glide, Registry registry) {
//        // Register FirebaseImageLoader to handle StorageReference
//   //    registry.append(StorageReference.class,InputStream.class,new F);
//    }
}
//  private FrontController frontController;
//    frontController = new FrontController(this);
// TransitionController_deprecated transitionController = new TransitionController_deprecated(this);
//   frontController.createActivity();
//   transitionController.onCreate();
//    transitionController.showDailyChallenge();
//                Intent a = new Intent(Intent.ACTION_MAIN);
//                a.addCategory(Intent.CATEGORY_HOME);
//                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(a);
//                this.finish();