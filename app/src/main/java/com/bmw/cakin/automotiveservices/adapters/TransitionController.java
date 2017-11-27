package com.bmw.cakin.automotiveservices.adapters;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bmw.cakin.automotiveservices.R;
import com.bmw.cakin.automotiveservices.activites.LoginActivity;
import com.bmw.cakin.automotiveservices.activites.fragments.FragmentCarstatus;
import com.bmw.cakin.automotiveservices.activites.fragments.FragmentChallenges;
import com.bmw.cakin.automotiveservices.activites.fragments.FragmentDriving;

/**
 * Created by cakin on 11/25/2017.
 */

public class TransitionController implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private String[] pageTitles = {"My Challenges","Car Status","My Driving","User Details"};
    private ViewPagerAdapter viewPagerAdapter;
    private DrawerLayout drawer;
    private ViewPager viewPager;
    private AppCompatActivity activity;
    public  TransitionController(AppCompatActivity activity){
        this.activity = activity;
    }
    public void onCreate(){
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
       setupViewPager(viewPager);
        tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        for (int i = 0; i < pageTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitles[i]));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
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
    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        viewPagerAdapter.addFrag(new FragmentChallenges(), "ONE");
        viewPagerAdapter.addFrag(new FragmentCarstatus(), "TWO");
        viewPagerAdapter.addFrag(new FragmentDriving(),"THREE");
        viewPager.setAdapter(viewPagerAdapter);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_challenges) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_carStatus) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_myDriving) {
            viewPager.setCurrentItem(2);

        } else if (id == R.id.nav_highScores) {
        }
        else if(id==R.id.nav_userDetails){
            viewPager.setCurrentItem(3);
        }



        else if (id == R.id.nav_logout) {
            Intent intent = new Intent(activity,LoginActivity.class);
            activity.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
