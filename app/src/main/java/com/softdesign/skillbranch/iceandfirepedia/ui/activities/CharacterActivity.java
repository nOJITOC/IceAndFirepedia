package com.softdesign.skillbranch.iceandfirepedia.ui.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.skillbranch.iceandfirepedia.R;
import com.softdesign.skillbranch.iceandfirepedia.data.managers.DataManager;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.Alias;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.SwornMember;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.Title;
import com.softdesign.skillbranch.iceandfirepedia.utils.ConstantManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CharacterActivity extends BaseActivity {

    private DataManager mDataManager;
    CoordinatorLayout mCoordinatorLayout;
    Toolbar mToolbar;
    CollapsingToolbarLayout mCollapsingToolbar;
    ImageView mHerbImage;
    TextView mWordsTv, mBornTv, mDiedTv, mTitlesTv, mAliasesTv;
    Button mFatherBtn, mMotherBtn;
    SwornMember mCurrentCharacter;
    List<Long> mCharacterIds = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.root_coordinator_layout);
        mHerbImage = (ImageView) findViewById(R.id.imageHouses);
        mWordsTv = (TextView) findViewById(R.id.words_tv);
        mBornTv = (TextView) findViewById(R.id.born_tv);
        mDiedTv = (TextView) findViewById(R.id.died_tv);
        mTitlesTv = (TextView) findViewById(R.id.titles_tv);
        mAliasesTv = (TextView) findViewById(R.id.aliases_tv);
        mFatherBtn = (Button) findViewById(R.id.father_btn);
        mMotherBtn = (Button) findViewById(R.id.mother_btn);
        Long charId;
        mDataManager = DataManager.getInstance();
        if (savedInstanceState == null) {
            charId = getIntent().getLongExtra(ConstantManager.CHARACTER_ID, 1l);
        } else {
            for (long l : savedInstanceState.getLongArray(ConstantManager.CHARACTER_ARRAY)) {
                mCharacterIds.add(l);
            }
            charId = savedInstanceState.getLong(ConstantManager.CHARACTER_ID);
        }

        setupToolbar();
        setCharacterInfo(charId);


    }

    public void setCharacterInfo(Long charId) {

        mCurrentCharacter = mDataManager.loadCharacterById(charId);

        mHerbImage.setImageResource(ConstantManager.HERBS.get(mCurrentCharacter.getHouseRemoteId()));

        mCollapsingToolbar.setTitle(mCurrentCharacter.getName());
        setInfoAboutCharacter(mWordsTv,
                mDataManager.getDaoSession().getHouseDao().load(mCurrentCharacter.getHouseRemoteId()).getWords());
        setInfoAboutCharacter(mBornTv, mCurrentCharacter.getBorn());
        setInfoAboutCharacter(mDiedTv, mCurrentCharacter.getDied());
        if (!mCurrentCharacter.getDied().isEmpty()) {
            if (mCurrentCharacter.getLastBook() != null)
                showSnackbar("Character died at \"" + mCurrentCharacter.getLastBook().getName() + "\" book.");
        }
        StringBuilder sb = new StringBuilder();
        for (Title title : mCurrentCharacter.getTitles()) {
            sb.append(title.getTitle() + "\n");
        }
        if (sb.length() == 0)
            ((View) mTitlesTv.getParent()).setVisibility(View.GONE);
        else {
            mTitlesTv.setText(sb);
            ((View) mTitlesTv.getParent()).setVisibility(View.VISIBLE);
        }
        sb = new StringBuilder();
        for (Alias alias : mCurrentCharacter.getAliases()) {
            sb.append(alias.getAlias() + "\n");
        }
        if (sb.length() == 0)
            ((View) mAliasesTv.getParent()).setVisibility(View.GONE);
        else {
            mAliasesTv.setText(sb);
            ((View) mAliasesTv.getParent()).setVisibility(View.VISIBLE);

        }
        setParent(mFatherBtn, mCurrentCharacter.getFather());
        setParent(mMotherBtn, mCurrentCharacter.getMother());
    }

    public void setParent(Button parent, Long parentId) {
        if (parentId != null) {
            final SwornMember member = mDataManager.loadCharacterByApiId(parentId);

            if (member != null) {
                ((View) parent.getParent()).setVisibility(View.VISIBLE);
                parent.setText(member.getName());
                parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e(TAG, "onClick:  exception");
                        mCharacterIds.add(0, mCurrentCharacter.getId());
                        setCharacterInfo(member.getId());
//                        Intent intent = new Intent(CharacterActivity.this, CharacterActivity.class);
//                        intent.putExtra(ConstantManager.CHARACTER_ID, member.getId());
//                        startActivity(intent);
                    }
                });
            }

        } else {
            ((View) parent.getParent()).setVisibility(View.GONE);
        }
    }

    public void setInfoAboutCharacter(TextView tv, String title) {
        if (title.isEmpty()) {
            ((View) tv.getParent()).setVisibility(View.GONE);
        } else {
            ((View) tv.getParent()).setVisibility(View.VISIBLE);
            tv.setText(title);
        }
    }


    private void setupToolbar() {
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    Log.e(TAG, "onMenuItemClick: exception" );
//                    if (item.getItemId() == android.R.id.home) {
//
//                        CharacterActivity.this.finish();
//                    }
//                    return true;
//                }
//            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if(mCharacterIds.size() == 0) {
                    return false;
                } else {
                    setCharacterInfo(mCharacterIds.remove(0));
                    return true;
                }
            default:
                return false;
        }

//        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        long[] array = new long[mCharacterIds.size()];
        for (int i = 0; i < mCharacterIds.size(); i++) {
            array[i] = mCharacterIds.get(i);
        }
        outState.putLongArray(ConstantManager.CHARACTER_ARRAY, array);
        outState.putLong(ConstantManager.CHARACTER_ID, mCurrentCharacter.getId());
        super.onSaveInstanceState(outState);

    }
}
