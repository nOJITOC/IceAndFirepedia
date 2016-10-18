package com.softdesign.skillbranch.iceandfirepedia.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Иван on 13.10.2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static List<Fragment>  mHouses = new ArrayList<>();
    private static List<String> mTitle = new ArrayList<>();
    Map<String, Fragment> mTabs = new HashMap<>();
    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        mHouses = new ArrayList<>();
        mTitle = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mHouses.get(position);
    }

    @Override
    public int getCount() {
        return mHouses.size();
    }
    public void addFragment(Fragment fragment,String title ){
        mHouses.add(fragment);
        mTitle.add(title);
    }
    public void setTabs(Map<String, Fragment> tabs){

    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
