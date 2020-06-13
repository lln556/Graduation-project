package com.android.cai_lai_la.model.ui;

import com.android.cai_lai_la.model.Product;

public class SearchResultItemModel {
    public static final int TYPE_SEARCH = 1;  // 搜索框
    public static final int TYPE_PRODUCT = 2;  // 商品列表

    private int type;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;

    public SearchResultItemModel(int type, Product product) {
        this.type = type;
        this.product = product;
    }

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
