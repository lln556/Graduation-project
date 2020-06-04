package com.android.cai_lai_la.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.HomeRecyclerAdapter;
import com.android.cai_lai_la.model.ui.HomeItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
    List<HomeItem> homeItems;

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

        // 设置 Adapter
        adapter = new HomeRecyclerAdapter(context, activity, homeItems);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData(){
        homeItems = new ArrayList<>();
        homeItems.add(new HomeItem(HomeItem.TYPE_CAROUSEL));
    }


}
