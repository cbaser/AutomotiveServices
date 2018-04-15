package com.gameco.cakin.automotiveservices.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.gameco.cakin.automotiveservices.R;
import com.gameco.cakin.automotiveservices.activites.LoginActivity;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentAchievements;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentChallengeCategories;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentCarstatus;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentHome;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentMyChallenges;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentProfile;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentRanking;
import com.gameco.cakin.automotiveservices.adapters.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by cakin on 1/2/2018.
 */

public class myActivityController implements NavigationView.OnNavigationItemSelectedListener {
    private AppCompatActivity activity;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ViewPagerAdapter viewPagerAdapter;
  //  private myNotificationController myNotificationController;
    public myActivityController(AppCompatActivity activity){
        this.activity = activity;

     //   myNotificationController = new myNotificationController(activity);

    }
    public TabLayout getTabLayout(){
        return tabLayout;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(0);
            tabLayout.setVisibility(View.VISIBLE);
        }
        else if (id == R.id.nav_challenges) {
            viewPager.setCurrentItem(1);
            tabLayout.setVisibility(View.VISIBLE);

        }
        else if (id == R.id.nav_myAchievements) {
            viewPager.setCurrentItem(2);
            tabLayout.setVisibility(View.VISIBLE);

        }  else if (id == R.id.nav_challengeCategories) {
            viewPager.setCurrentItem(3);
            tabLayout.setVisibility(View.INVISIBLE);

        }
        else if (id == R.id.nav_ranking) {
            viewPager.setCurrentItem(4);
            tabLayout.setVisibility(View.INVISIBLE);
        }
        else if(id==R.id.nav_carStatus){
            viewPager.setCurrentItem(5);
            tabLayout.setVisibility(View.INVISIBLE);
        }
        else if(id==R.id.nav_profile){
            viewPager.setCurrentItem(6);
            tabLayout.setVisibility(View.INVISIBLE);
        }



        else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setUpTabs(){
         toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
         viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        String[] pageTitles = {"Home","My Challenges","Achievements"};
        tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        for (int i = 0; i < pageTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitles[i]));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
    public void setupNavigationMenu(){
        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }
    public ViewPager getViewPager(){

        viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        return viewPager;
    }


    public void setupViewPager(ViewPager viewPager){
        viewPagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        viewPagerAdapter.addFrag(new FragmentHome(), "ONE");
        viewPagerAdapter.addFrag(new FragmentMyChallenges(), "TWO");
        viewPagerAdapter.addFrag(new FragmentAchievements(),"THREE");
        viewPagerAdapter.addFrag(new FragmentChallengeCategories(),"FOUR");
        viewPagerAdapter.addFrag(new FragmentRanking(),"FIVE");
        viewPagerAdapter.addFrag(new FragmentCarstatus(),"SIX");
        viewPagerAdapter.addFrag(new FragmentProfile(),"SEVEN");
        viewPager.setAdapter(viewPagerAdapter);
    }

//    public void showDailyChallenge() {
//
//
//        CountDownTimer timer = new CountDownTimer(3000, 1000) {
//            @Override
//            public void onTick(long l) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                RelativeLayout dailyRelative =(RelativeLayout) activity.findViewById(R.id.dailyChallengeLayout);
//                dailyRelative.setVisibility(View.INVISIBLE);
//                LayoutInflater layoutInflater = LayoutInflater.from(activity);
//
//                //Inflate the view from a predefined XML layout
//                View layout = layoutInflater.inflate(R.layout.content_daily_challenge, null);
//                layout.setAnimation(AnimationUtils.loadAnimation(activity, R.anim.anim_daily_content));
//                // create a 300px width and 470px height PopupWindow
//                PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, 700, true);
//                // display the popup in the center
//                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
//                dailyRelative.setVisibility(View.VISIBLE);
//                Button playBtn =(Button) layout.findViewById(R.id.playChallengeButton);
//                playBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//      //                  myNotificationController.showPopUp();
//                    }
//                });
//
//            }
//
//        };
//        timer.start();
//
//
//        }

    }



//        new Handler().post(new Runnable(){
//
//            @Override
//            public void run() {
//
//            }
//        });
//        LayoutInflater layoutInflater = LayoutInflater.from(activity);
//      final  View layout = layoutInflater.inflate(R.layout.content_daily_challenge,null);
//        final PopupWindow pw = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, 500, true);
//        layout.post(new Runnable() {
//            @Override
//            public void run() {
//                pw.showAtLocation(layout, Gravity.CENTER, 0, 0);
//                pw.update();
//            }



//        View mainView = fragment.getView().findViewById(R.id.include_daily_challenge_content);
//        View popupView = mainView.findViewById(R.layout.content_daily_challenge);
//        View dailyChallenge = fragment.getView().findViewById(R.id.txtfullName);
//        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        popupWindow.showAtLocation(dailyChallenge, Gravity.NO_GRAVITY,10,10);