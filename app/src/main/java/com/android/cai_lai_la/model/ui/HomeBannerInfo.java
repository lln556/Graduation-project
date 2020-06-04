package com.android.cai_lai_la.model.ui;

import com.stx.xhb.androidx.entity.BaseBannerInfo;

public class HomeBannerInfo implements BaseBannerInfo {
    private String imageUrl;
    private int imageId;

    public HomeBannerInfo(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    private String title;
    @Override
    public Object getXBannerUrl() {
        return imageId;
    }

    @Override
    public String getXBannerTitle() {
        return title;
    }
}
