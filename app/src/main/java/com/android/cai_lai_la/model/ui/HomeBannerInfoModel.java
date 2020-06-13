package com.android.cai_lai_la.model.ui;

import com.stx.xhb.androidx.entity.BaseBannerInfo;

public class HomeBannerInfoModel implements BaseBannerInfo {

    private int imageId;  // 资源id
    private String title;  //  项目标题
    private String url;

    public int getImageId() {
        return imageId;
    }

    public HomeBannerInfoModel(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }

    @Override
    public String getXBannerTitle() {
        return title;
    }

    public HomeBannerInfoModel(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
