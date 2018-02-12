package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;

import com.gameco.cakin.automotiveservices.controller.FrontController;
import com.gameco.cakin.automotiveservices.datamodel.Challenge;
import com.gameco.cakin.automotiveservices.onesignal.NotificationHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.onesignal.OneSignal;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<Challenge> challengeList;
    private long backPressedTime = 0;
    private FrontController frontController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationHandler(this))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

         frontController = new FrontController(this);

       // TransitionController_deprecated transitionController = new TransitionController_deprecated(this);
        setContentView(R.layout.activity_main);

        setTitle("");
        frontController.createActivity();
     //   transitionController.onCreate();
        String token = FirebaseInstanceId.getInstance().getToken(); //İlk çalışmada boş.
    //    transitionController.showDailyChallenge();
        String msg = "Token :"+token;
        Log.d(TAG, msg);


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
//                Intent a = new Intent(Intent.ACTION_MAIN);
//                a.addCategory(Intent.CATEGORY_HOME);
//                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(a);
//                this.finish();
            }
          //  super.onBackPressed();
        }
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



}
