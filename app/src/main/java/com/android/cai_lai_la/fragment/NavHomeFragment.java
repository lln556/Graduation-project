package com.android.cai_lai_la.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.HomeRecyclerAdapter;
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
    Context context;
    List<HomeItemModel> list;

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
        initData();
        initView();
        return view;
    }

    /**
     * 具体初始化View
     */
    private void initView(){
        // 设置 recyclerView
        // TODO: 设置布局，选用网格布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置 Adapter
        adapter = new HomeRecyclerAdapter(context, activity, list);
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
        list.add(new HomeItemModel(HomeItemModel.TYPE_RECOMMEND));  // 推荐商品
    }


}
