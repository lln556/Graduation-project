package com.android.cai_lai_la.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.HomeRecyclerAdapter;
import com.android.cai_lai_la.controller.UserController;
import com.android.cai_lai_la.model.User;
import com.android.cai_lai_la.model.ui.HomeItemModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavHomeFragment extends Fragment {

    // RecyclerView 具体内容展示的列表
    @BindView(R.id.recycler_home_content)
    RecyclerView recyclerView;
    HomeRecyclerAdapter adapter;  // 相应 adapter

    Activity activity;
    private Context context;
    List<HomeItemModel> list;
    private int uid;
    private boolean isLog;

    public static NavHomeFragment newInstance() {
        return new NavHomeFragment();
    }

    /**
     * 初始化布局
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_home, container, false);  // 载入布局
        ButterKnife.bind(this, view);  // 自动绑定
        activity = getActivity();
        context = activity.getApplicationContext();
        this.isLog = UserController.isLog(context);
        setUid(isLog);
        initData();
        initView();
        return view;
    }



    public void setUid(boolean isLog) {
        if (isLog) {
            User user = UserController.loadUser(context);
            uid = user.getUid();
        } else {
            uid = 0;
            Toast.makeText(context, "您还没有登录哦", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 具体初始化View
     */
    private void initView(){
        // 设置 recyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        // 设置 Adapter
        adapter = new HomeRecyclerAdapter(context, activity, list, uid);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        // 设置 recycler adapter 不同的item
        list = new ArrayList<>();
        list.add(new HomeItemModel(HomeItemModel.TYPE_CAROUSEL));  // 滚动图片
        list.add(new HomeItemModel(HomeItemModel.TYPE_CATEGORY));  // 商品分类
        list.add(new HomeItemModel(HomeItemModel.TYPE_RECOMMEND_DIVIDE));  // 分隔栏
        list.add(new HomeItemModel(HomeItemModel.TYPE_RECOMMEND));  // 推荐商品
        list.add(new HomeItemModel(HomeItemModel.TYPE_FOOTER));  // 底线
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }
}
