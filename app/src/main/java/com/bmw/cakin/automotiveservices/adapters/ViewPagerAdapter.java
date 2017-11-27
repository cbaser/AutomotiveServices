package com.bmw.cakin.automotiveservices.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.bmw.cakin.automotiveservices.activites.fragments.FragmentCarstatus;
import com.bmw.cakin.automotiveservices.activites.fragments.FragmentChallenges;
import com.bmw.cakin.automotiveservices.activites.fragments.FragmentDriving;
import com.bmw.cakin.automotiveservices.activites.fragments.FragmentUserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cakin on 11/20/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(android.support.v4.app.FragmentManager manager) {
        super(manager);
        mFragmentList.add(new FragmentChallenges());
        mFragmentList.add(new FragmentCarstatus());
        mFragmentList.add(new FragmentDriving());
        mFragmentList.add(new FragmentUserDetails());
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

