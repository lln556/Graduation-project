package com.android.cai_lai_la.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.model.User;
import com.android.cai_lai_la.utils.PostUtils;

public class UserController {
    public static int add(User user){
        String userJson = JSON.toJSONString(user);
        String url = "/user/add";
        JSONObject body = PostUtils.postJson(url, userJson);
        JSONObject data = body.getJSONObject("data");
        User user1 = data.toJavaObject(User.class);
        return user1.getUid();
    }

    public static User update(User user){
        String userJson = JSON.toJSONString(user);
        String url = "/user/update";
        JSONObject body = PostUtils.postJson(url, userJson);
        JSONObject data = body.getJSONObject("data");
        User user1 = data.toJavaObject(User.class);
        return user1;
    }
}
