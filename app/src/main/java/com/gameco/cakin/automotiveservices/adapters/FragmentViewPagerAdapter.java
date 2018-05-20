package com.gameco.cakin.automotiveservices.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.gameco.cakin.automotiveservices.activites.fragments.FragmentChallengeCategories;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentCarstatus;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentFriendlist;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentMyChallenges;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentHome;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentAchievements;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentProfile;
import com.gameco.cakin.automotiveservices.activites.fragments.FragmentRanking;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 11/20/2017.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public FragmentViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
        super(manager);
        FragmentHome fragmentHome = new FragmentHome();
        Bundle bundle = new Bundle();
        fragmentHome.setArguments(bundle);
        mFragmentList.add(fragmentHome);
        mFragmentList.add(new FragmentMyChallenges());
        mFragmentList.add(new FragmentAchievements());
        mFragmentList.add(new FragmentChallengeCategories());
        mFragmentList.add(new FragmentRanking());
        mFragmentList.add(new FragmentFriendlist());
        mFragmentList.add(new FragmentCarstatus());
        mFragmentList.add(new FragmentProfile());

    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position) {

        return null;
    }

}

