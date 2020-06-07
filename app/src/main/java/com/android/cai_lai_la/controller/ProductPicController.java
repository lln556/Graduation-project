package com.android.cai_lai_la.controller;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.model.ProductPic;
import com.android.cai_lai_la.utils.PostUtils;

import java.util.List;

import okhttp3.FormBody;

public class ProductPicController {
    private static final String TAG = "WebController 商品图片";

    /**
     * 根据 pid 获取图片，pid = -1时，返回所有
     */
    public static List<ProductPic> list(int pid){
        Log.i(TAG, "list: 获取商品图片");
        String url = "/product/pic/list";
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("pid", "" + pid);
        JSONObject jsonObject = PostUtils.postParam(url, builder);
        JSONArray list = jsonObject.getJSONObject("data").getJSONArray("list");
        List<ProductPic> productPics = list.toJavaList(ProductPic.class);
        return productPics;
    }

    /**
     * 获取所有信息
     */
    public static List<ProductPic> list() {
        return list(-1);
    }
}
