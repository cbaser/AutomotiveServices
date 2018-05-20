package com.gameco.cakin.automotiveservices.activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.backend.BackendHelper;
import com.gameco.cakin.automotiveservices.datamodel.Car;
import com.gameco.cakin.automotiveservices.firebase.MyFirebaseDatabase;

public class ProgressActivity extends AppCompatActivity {
private Car car;
private MyFirebaseDatabase firebaseDatabase;
private BackendHelper backendHelper;
private static Activity activity;
private String TAG = "ProgressActivity";
private ProgressBar progressBar;
  private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        activity = ProgressActivity.this;
         progressBar = findViewById(R.id.progressBar_activity);
        progressBar.setIndeterminate(true);
//        progressBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.fbutton_color_midnight_blue), android.graphics.PorterDuff.Mode.SRC_IN);
        firebaseDatabase = new MyFirebaseDatabase(this);
        backendHelper = new BackendHelper();


        try{
          firebaseDatabase.setUserInfoToPreferences();
          firebaseDatabase.setRankingToPreferences();
          firebaseDatabase.setChallengeInfoToPreferences();
          firebaseDatabase.getFriendsFromDatabase();
          firebaseDatabase.getFriendRequestFromDatabase();




        }catch (Exception e){
            Toast.makeText(this,"Please Check your Internet Connection and try again",Toast.LENGTH_LONG);
          Intent i = new Intent(ProgressActivity.this, LoginActivity.class);
          startActivity(i);
        }





        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(5000);
                    car = backendHelper.tryTelematics("Telematics");
                    firebaseDatabase.updateCarData(car);


                }
                catch (Exception e) {

                  e.printStackTrace();} // Just catch the InterruptedException

                // Now we use the Handler to post back to the main thread
                handler.post(new Runnable() {
                    public void run() {
                        // Set the View's visibility back on the main UI Thread
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(ProgressActivity.this, MainActivity.class);
                        startActivity(i);
                    }
                });
            }
        }).start();







    }
    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
    public static Activity getContextOfApplication(){
        return activity;
    }
}
