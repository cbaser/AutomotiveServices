package com.gameco.cakin.automotiveservices.activites;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;


import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.adapters.SlideViewPagerAdapter;


public class OpeningActivity extends AppCompatActivity{
    private long backPressedTime = 0;
    private VideoView videoView;
    private Button signup;
    private Button singin;
    private ImageView mainIcon;


    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_from_bottom_bounce);
        mainIcon = (ImageView) findViewById(R.id.mainIcon);
        mainIcon.setAnimation(animation);

        singin = (Button) findViewById(R.id.signin_button);
        signup = (Button) findViewById(R.id.signup_button);

        viewPager = (ViewPager) findViewById(R.id.activity_opening_pager);
        videoView = (VideoView) findViewById(R.id.videoView);




        try {
            videoView.setMediaController(null);
            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.game_eco_video));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();

        }
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
                mp.setLooping(true);
            }
        });
        videoView.start();


        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIcon.setAnimation(null);
                Intent it = new Intent(OpeningActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainIcon.setAnimation(null);
                Intent it = new Intent(OpeningActivity.this,SingupActivity.class);
                startActivity(it);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onBackPressed() {
        backPressedTime = (backPressedTime + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();
        if (backPressedTime > 1) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            OpeningActivity.this.finish();
        }
    }
    private void setupViewPager(){
        String [] titles = new String[]{"Reduce Fuel Consumption","Get Benefits","Play with your friends"};
        String [] descriptions = new String[]{"Earn money","Get free coffee","Challenge to your friends"};
        SlideViewPagerAdapter slideViewPagerAdapter = new SlideViewPagerAdapter(this,titles,descriptions);
        viewPager.setAdapter(slideViewPagerAdapter);

    }
}
