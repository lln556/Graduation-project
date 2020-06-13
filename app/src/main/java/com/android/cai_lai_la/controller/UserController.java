package com.android.cai_lai_la.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.cai_lai_la.config.Config;
import com.android.cai_lai_la.model.User;
import com.android.cai_lai_la.utils.PostUtils;

import java.util.List;

public class UserController {
    public static final String TAG = "UserController";
    public static final String IS_LOG_KEY = "is_save";
    public static final String USER_KEY = "user";
    public static final String PATH = Config.USER_DATA;

    /**
     * 是否登录
     * 判断条件，存在标志位且有uer
     */
    public static boolean isLog(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PATH, Context.MODE_PRIVATE);
        boolean isLog = sharedPreferences.getBoolean(IS_LOG_KEY, false);// 是否登录
        boolean containsUser = sharedPreferences.contains(USER_KEY);
        return isLog && containsUser;
    }

    public static void setLog(Context context, boolean isLog){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PATH, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(IS_LOG_KEY, isLog);
        edit.commit();
    }

    public static User add(User user) {
        String userJson = JSON.toJSONString(user);
        String url = "/user/add";
        JSONObject body = PostUtils.postJson(url, userJson);
        JSONObject data = body.getJSONObject("data");
        User user1 = data.toJavaObject(User.class);
        return user1;
    }

    public static User update(User user) {
        String userJson = JSON.toJSONString(user);
        String url = "/user/update";
        JSONObject body = PostUtils.postJson(url, userJson);
        JSONObject data = body.getJSONObject("data");
        User user1 = data.toJavaObject(User.class);
        return user1;
    }
    public static List<User> list(){
        String url = "/user/list";
        JSONObject jsonObject = PostUtils.postJson(url, "");
        List<User> users = jsonObject.getJSONObject("data").getJSONArray("list").toJavaList(User.class);
        return users;
    }

    /**
     * 获取已保存用户信息
     */
    public static User loadUser(Context context) {
        Log.i(TAG, "loadUser: 加载用户");
        User user = null;
        if (isLog(context)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PATH, Context.MODE_PRIVATE);
            String userJson = sharedPreferences.getString(USER_KEY, "");
            Log.i(TAG, "loadUser: 用户json为" + userJson);
            user = JSON.parseObject(userJson, User.class);
            Log.i(TAG, "loadUser: 加载用户信息为" + user);
        }
        return user;
    }

    /**
     * 本地保存用户信息
     */
    public static void saveUser(Context context, User user) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PATH, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(IS_LOG_KEY, true);
        edit.putString(USER_KEY, JSON.toJSONString(user));
        edit.commit();
    }

    /**
     * 退出登录
     */
    public static void logout(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PATH, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(IS_LOG_KEY,false);
        edit.putString(USER_KEY, "");
        edit.commit();
    }
}
