package com.android.cai_lai_la.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.model.PurchaseOrder;
import com.android.cai_lai_la.utils.PostUtils;

import java.util.List;

import okhttp3.FormBody;

public class PurchaseOrderController {
    public static PurchaseOrder add(PurchaseOrder purchaseOrder){
        String url = "/purchase/order/add";
        String purchaseOrderJSON = JSON.toJSONString(purchaseOrder);
        JSONObject body = PostUtils.postJson(url,purchaseOrderJSON);
        JSONObject data = body.getJSONObject("data");
        PurchaseOrder purchaseOrder1 = data.toJavaObject(PurchaseOrder.class);
        return purchaseOrder1;
    }

    /**
     * 查找指定用户的所有订单
     * @param uid  用户编号
     */
    public static List<PurchaseOrder> list(int uid){
        String url = "/purchase/order/list";
        FormBody.Builder param = new FormBody.Builder();
        param.add("uid", ""+ uid);
        JSONObject body = PostUtils.postParam(url,param);
        JSONArray list = body.getJSONArray("data");
        List<PurchaseOrder> orders = list.toJavaList(PurchaseOrder.class);
        return orders;
    }

    /**
     * 删除指定订单
     * @param oid  订单编号
     */
    public static boolean delete(int oid){
        String url = "/purchase/order/deleteOrder";
        FormBody.Builder param = new FormBody.Builder();
        param.add("oid", ""+ oid);
        JSONObject body = PostUtils.postParam(url,param);
        int code = body.getIntValue("code");
        return code == 200;
    }
}
