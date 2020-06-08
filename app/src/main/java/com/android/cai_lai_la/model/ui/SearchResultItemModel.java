package com.android.cai_lai_la.model.ui;

public class SearchResultItemModel {
    public static final int TYPE_SEARCH = 1;  // 搜索框
    public static final int TYPE_PRODUCT = 1;  // 商品列表

    private int type;

    public SearchResultItemModel(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
