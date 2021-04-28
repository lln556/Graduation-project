package com.android.cai_lai_la.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.HomeCategoryRecyclerAdapter;
import com.android.cai_lai_la.adapter.HomeRecyclerAdapter;
import com.android.cai_lai_la.controller.ProductClassController;
import com.android.cai_lai_la.model.ProductClass;
import com.android.cai_lai_la.model.ui.HomeItemModel;
import com.android.cai_lai_la.utils.SpaceItemDecoration;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class NavClassFragment extends Fragment {
    private static final String TAG = "NavClassFragment";
    // RecyclerView 具体内容展示的列表
    @BindView(R.id.class_recycler)
    RecyclerView recyclerView;
    HomeCategoryRecyclerAdapter adapter;

    Activity activity;
    Context context;
    List<HomeItemModel> list;



    public static NavClassFragment newInstance(){
        NavClassFragment fragment = new NavClassFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav_class, container, false);
        ButterKnife.bind(this, view);  // 自动绑定
        activity = getActivity();
        context = activity.getApplicationContext();
        initView();
        return view;
    }

    private void initView() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onBindViewHolder: 获取到所有分类信息");
                List<ProductClass> list = ProductClassController.list();
                activity.runOnUiThread(() -> {
                    // 初始化
                    // 设置布局方式
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
                    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            return 1;
                        }
                    });
                    recyclerView.setLayoutManager(gridLayoutManager);

                    // 设置adapter
                    HomeCategoryRecyclerAdapter adapter = new HomeCategoryRecyclerAdapter(context, activity, list);
                    recyclerView.addItemDecoration(new SpaceItemDecoration(50,50,50,50));
                    recyclerView.setAdapter(adapter);
                    Log.i(TAG, "onBindViewHolder: 商品分类设置完成");

                });
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
