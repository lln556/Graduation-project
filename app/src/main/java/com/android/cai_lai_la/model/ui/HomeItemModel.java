package com.android.cai_lai_la.model.ui;

public class HomeItemModel {
    public static final int TYPE_CAROUSEL = 1;
    public static final int TYPE_CATEGORY = 2;
    public static final int TYPE_RECOMMEND = 3;

    private int type;

    public HomeItemModel(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
