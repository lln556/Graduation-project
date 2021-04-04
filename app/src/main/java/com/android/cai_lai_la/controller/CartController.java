package com.android.cai_lai_la.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.model.Cart;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.utils.PostUtils;

import java.util.List;

import okhttp3.FormBody;

public class CartController {
    /**
     * 查看指定用户的购物车列表
     * @param uid 用户编号
     */
    public static List<Product> list(int uid){
        String url = "/cart/list";
        FormBody.Builder param = new FormBody.Builder();
        param.add("uid", "" + uid);
        JSONObject body = PostUtils.postParam(url,param);
        JSONArray list = body.getJSONArray("data");
        return list.toJavaList(Product.class);
    }

    public static Cart add(Cart cart){
        String url = "/cart/add";
        String cartJson = JSON.toJSONString(cart);
        JSONObject body = PostUtils.postJson(url,cartJson);
        JSONObject data = body.getJSONObject("data");
        Cart cart1 = data.toJavaObject(Cart.class);
        return cart1;
    }

    /**
     * 删除指定购物车记录
     * @param uid  用户编号
     * @param pid  商品编号
     */
    public static boolean delete(int uid, int pid){
        String url = "/cart/deleteCart";
        FormBody.Builder param = new FormBody.Builder();
//        param.add("uid", ""+ uid);
//        param.add("pid", ""+ pid);
        param.add("uid", String.valueOf(uid)).add("pid", String.valueOf(pid));
        JSONObject body = PostUtils.postParam(url,param);
        int code = body.getIntValue("code");
        return code == 200;
    }

    /** 更新购物车
     */
    public static void update(Cart cart){
        String url = "/cart/update";
        FormBody.Builder param = new FormBody.Builder();
        param.add("uid", "" + cart.getUid());
        param.add("pid", "" + cart.getPid());
        param.add("num", "" + cart.getNum());
        PostUtils.postParam(url, param);
    }
    public static void update(int uid, int pid, int num){
        Cart cart = new Cart();
        cart.setUid(uid);
        cart.setPid(pid);
        cart.setNum(num);
    }

    public static List<Cart> getCartByUid(int uid){
        String url = "/cart/getAll";
        FormBody.Builder param = new FormBody.Builder();
        param.add("uid", "" + uid);
        JSONObject jsonObject = PostUtils.postParam(url, param);
        List<Cart> data = jsonObject.getJSONArray("data").toJavaList(Cart.class);
        return data;
    }
}
