package com.softdesign.skillbranch.iceandfirepedia.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.softdesign.skillbranch.iceandfirepedia.R;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.SwornMember;
import com.softdesign.skillbranch.iceandfirepedia.ui.activities.CharacterActivity;
import com.softdesign.skillbranch.iceandfirepedia.ui.adapters.SwornMembersAdapter;
import com.softdesign.skillbranch.iceandfirepedia.utils.ConstantManager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link ListFragment} subclass.
 */
public class SwornMembersFragment extends Fragment{

    RecyclerView mRecyclerView;
    private List<SwornMember> mMemberList = new ArrayList<>();
    SwornMembersAdapter.CustomItemClickListener mListener;
    private int mDrawableId;

    public SwornMembersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setMemberList(List<SwornMember> memberList) {
        mMemberList = memberList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mListener = new SwornMembersAdapter.CustomItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent toCharacterActivity = new Intent(getActivity(), CharacterActivity.class);
                toCharacterActivity.putExtra(ConstantManager.CHARACTER_ID,mMemberList.get(position).getId());
                startActivity(toCharacterActivity);
                //TODO
            }
        };

        View rootView = inflater.inflate(R.layout.fragment_sworn_members, container, false);
        mRecyclerView =(RecyclerView)rootView.findViewById(R.id.members_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(new SwornMembersAdapter(mMemberList, mListener,mDrawableId));
        return rootView;
    }


    public void setDrawableId(int drawableId) {
        mDrawableId = drawableId;
    }
}
