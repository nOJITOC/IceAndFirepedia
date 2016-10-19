package com.softdesign.skillbranch.iceandfirepedia.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.softdesign.skillbranch.iceandfirepedia.R;


/**
 * Created by Иван on 14.07.2016.
 */
public class AspectRatioImageView extends ImageView {
    private static final float DEFAULT_ASPECT_RATIO = 720f/1097f;
    private final float mAspectRatio;

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AspectRatioImageView);
        mAspectRatio = a.getFloat(R.styleable.AspectRatioImageView_aspect_ratio, DEFAULT_ASPECT_RATIO);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int newWidth, newHeight;

        newWidth = this.getMeasuredWidth();
        newHeight = (int) (newWidth / DEFAULT_ASPECT_RATIO);
        setMeasuredDimension(newWidth, newHeight);
    }
}
