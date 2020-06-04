package com.android.cai_lai_la.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.utils.PostUtils;

import java.util.List;

import okhttp3.FormBody;

public class ProductController {
    public static List<Product> list(){
        String url = "/product/list";
        JSONObject body = PostUtils.postJson(url, "");
        JSONArray list = body.getJSONObject("data").getJSONArray("list");
        List<Product> products = list.toJavaList(Product.class);
        return products;
    }

    /**
     * 根据关键字筛选商品
     * @param key 关键字
     */
    public static List<Product> filter(String key){
        return filter(key, 0, 999999);
    }

    /**
     * 根据价格区间筛选商品
     * @param minPrice 下限
     * @param maxPrice 上限
     */
    public static List<Product> filter(int minPrice, int maxPrice){
        return filter("", minPrice, maxPrice);
    }

    /**
     * 筛选商品
     * @param key 关键字
     * @param minPrice 价格下限
     * @param maxPrice 价格上限
     */
    public static List<Product> filter(String key, int minPrice, int maxPrice){
        FormBody.Builder param = new FormBody.Builder();
        param.add("key", key);
        param.add("minPrice", "" + minPrice);
        param.add("maxPrice", "" + maxPrice);
        String url = "/product/filter";
        JSONObject body = PostUtils.postParam(url, param);
        JSONArray list = body.getJSONArray("data");
        List<Product> products = list.toJavaList(Product.class);
        return products;
    }

}
