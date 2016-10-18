package com.softdesign.skillbranch.iceandfirepedia.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.softdesign.skillbranch.iceandfirepedia.R;
import com.softdesign.skillbranch.iceandfirepedia.data.storage.models.SwornMember;

import java.util.List;

/**
 * Created by Иван on 13.10.2016.
 */
public class SwornMembersAdapter extends RecyclerView.Adapter<SwornMembersAdapter.SwornMemberViewHolder> {
    private final int mDrawableId;
    CustomItemClickListener mListener;
    List<SwornMember> mMemberList;
    Context mContext;

    public SwornMembersAdapter(List<SwornMember> memberList, CustomItemClickListener listener, int drawableId) {
        mMemberList = memberList;
        mListener = listener;
        mDrawableId = drawableId;
    }

    @Override
    public SwornMemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_member, parent, false);
        return new SwornMemberViewHolder(convertView, mListener);
    }

    @Override
    public void onBindViewHolder(SwornMemberViewHolder holder, int position) {
        Glide.with(mContext)
                .load("")
                .placeholder(mDrawableId)
                .fitCenter()
                .centerCrop()
                .into(holder.mImageView);
        holder.mTextView.setText(mMemberList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return mMemberList.size();
    }

    public class SwornMemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CustomItemClickListener mItemClickListener;
        ImageView mImageView;
        TextView mTextView;

        public SwornMemberViewHolder(View itemView, CustomItemClickListener listener) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.emblem);
            mTextView = (TextView) itemView.findViewById(R.id.name_tv);
            mItemClickListener = listener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            Log.e("Exception", " onClick: " + view.getId());
            if (mItemClickListener != null)
                mItemClickListener.onItemClick(getAdapterPosition());
        }
    }


    public interface CustomItemClickListener {
        void onItemClick(int position);
    }
}
