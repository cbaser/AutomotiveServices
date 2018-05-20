package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.gameco.cakin.automotiveservices.R;

import com.gameco.cakin.automotiveservices.controller.myActivityController;

//import com.gameco.cakin.automotiveservices.onesignal.NotificationOpenedHandler;
//import com.gameco.cakin.automotiveservices.onesignal.NotificationReceivedHandler;

import com.gameco.cakin.automotiveservices.onesignal.NotificationOpenedHandler;
import com.gameco.cakin.automotiveservices.onesignal.NotificationReceivedHandler;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.gameco.cakin.automotiveservices.controller.myFacebookLoginController;
import com.gameco.cakin.automotiveservices.controller.myGoogleLoginController;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.iid.FirebaseInstanceId;

import com.onesignal.OneSignal;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private long backPressedTime = 0;
    private myFacebookLoginController myFacebookLoginController;
    private myGoogleLoginController myGoogleLoginController;
    private myActivityController activityController;

    /**Save App to Database code : scp -r gameco.apk MSP@vmkrcmar20.informatik.tu-muenchen.de:/var/www/html/uploads*/
    /** Download app Link : http://vmkrcmar20.informatik.tu-muenchen.de/uploads/gameco.apk*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myFacebookLoginController = new myFacebookLoginController(this);
        myGoogleLoginController = new myGoogleLoginController(this);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new NotificationOpenedHandler(this))
                .setNotificationReceivedHandler(new NotificationReceivedHandler(this))
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();




        activityController = new myActivityController(this);
        setContentView(R.layout.activity_main);
        setTitle("");
        activityController.setUpTabs();
        activityController.setupNavigationMenu();

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
                this.signOut();
                Intent it = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(it);

            }
        }
    }
    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        myGoogleLoginController.signOut();
        myFacebookLoginController.signOut();
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