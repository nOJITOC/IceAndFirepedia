package com.softdesign.skillbranch.iceandfirepedia.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.softdesign.skillbranch.iceandfirepedia.R;
import com.softdesign.skillbranch.iceandfirepedia.data.managers.DataManager;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.SwornMember;
import com.softdesign.skillbranch.iceandfirepedia.ui.adapters.ViewPagerAdapter;
import com.softdesign.skillbranch.iceandfirepedia.ui.fragments.SwornMembersFragment;
import com.softdesign.skillbranch.iceandfirepedia.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private ListView mListView;
    DataManager mDataManager;
    List<String> mHouses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataManager =DataManager.getInstance();
        mViewPager =(ViewPager) findViewById(R.id.houses_vp);
        mTabLayout = (TabLayout) findViewById(R.id.house_tl);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationView =(NavigationView)findViewById(R.id.nav_view);
        mListView = (ListView)findViewById(R.id.left_drawer) ;
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setupViewPager();
        setupDrawer();

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupDrawer() {
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,R.layout.driwer_list_item,mHouses);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mTabLayout.getTabAt(i).select();
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar,R.string.open_menu , R.string.close_menu);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        int i = 0;
        for (Long aLong : ConstantManager.HOUSES_ID) {
            SwornMembersFragment smf = new SwornMembersFragment();
            List<SwornMember> sm = mDataManager.loadMembersOfHouseFromDbByHouseId(aLong);
            smf.setMemberList(sm);
            smf.setDrawableId(ConstantManager.HOUSES_DRAWABLE_ID[i]);
            mHouses.add((mDataManager.getDaoSession().getHouseDao().load(aLong).getName().split(" "))[1]);
            adapter.addFragment(smf,(mDataManager.getDaoSession().getHouseDao().load(aLong).getName().split(" "))[1]);
            i++;
        }
        mViewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
