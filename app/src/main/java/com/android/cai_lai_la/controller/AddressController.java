package com.android.cai_lai_la.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.model.Address;
import com.android.cai_lai_la.utils.PostUtils;

import java.util.List;

import okhttp3.FormBody;


public class AddressController {
    public static List<Address> list(int uid){
        FormBody.Builder param = new FormBody.Builder();
        param.add("uid", "" + uid);
        String url = "/address/list";
        JSONObject body = PostUtils.postParam(url, param);
        JSONArray data = body.getJSONArray("data");
        List<Address> addresses = data.toJavaList(Address.class);
        return addresses;
    }

    public static Address update(Address address){
        String addJson = JSON.toJSONString(address);
        String url = "/address/update";
        JSONObject body = PostUtils.postJson(url, addJson);
        JSONObject data = body.getJSONObject("data");
        Address address1 = data.toJavaObject(Address.class);
        return address1;
    }

    public static int add(Address address){
        String addJson = JSON.toJSONString(address);
        String url = "/address/add";
        JSONObject body = PostUtils.postJson(url, addJson);
        JSONObject data = body.getJSONObject("data");
        Address address1 = data.toJavaObject(Address.class);
        return address1.getAid();
    }

    public static boolean delete(int aid){
        FormBody.Builder param = new FormBody.Builder();
        param.add("id", "" + aid);
        String url = "/address/delete";
        JSONObject body = PostUtils.postParam(url, param);
        int code = body.getIntValue("code");
        return code == 200;
    }
}
