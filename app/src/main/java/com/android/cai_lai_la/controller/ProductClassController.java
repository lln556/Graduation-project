package com.android.cai_lai_la.controller;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ProductClass;
import com.android.cai_lai_la.utils.PostUtils;

import java.util.List;

import okhttp3.FormBody;

public class ProductClassController {

    private static final String TAG = "WebController 商品分类";

    public static List<ProductClass> list(){
        Log.i(TAG, "list: 查找所有分类");
        String url = "/product/class/list";
        JSONObject body = PostUtils.postJson(url,"");
        JSONArray list = body.getJSONArray("data");
        List<ProductClass> classes = list.toJavaList(ProductClass.class);
        return classes;
    }

    /**
     * 查看指定种类的所有商品
     * @param pcid  商品分类编号
     */
    public static List<Product> product(int pcid){
        String url = "/product/class/product";
        FormBody.Builder param = new FormBody.Builder();
        param.add("pcid", ""+ pcid);
        JSONObject body = PostUtils.postParam(url, param);
        JSONArray list = body.getJSONArray("data");
        List<Product> products = list.toJavaList(Product.class);
        return products;
    }
}
