package com.android.cai_lai_la.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpacel;
    int mSpacer;
    int mSpaceb;
    int mSpacet;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpacel;
        outRect.right = mSpacer;
        outRect.bottom = mSpaceb;
        outRect.top = mSpacet;

    }

    public SpaceItemDecoration(int spacel,int spacer,int spaceb,int spacet) {
        this.mSpacel = spacel;
        this.mSpacer = spacer;
        this.mSpaceb = spaceb;
        this.mSpacet = spacet;
    }
}