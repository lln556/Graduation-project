package com.android.cai_lai_la.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.SearchResultRecyclerAdapter;
import com.android.cai_lai_la.controller.ProductController;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ui.SearchResultItemModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {
    public static final String TAG = "search result activity";
    @BindView(R.id.search_result_recycler)
    RecyclerView recyclerView;

    List<SearchResultItemModel> list;
    String searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: 开启线程");
                initDate();
                SearchResultActivity.this.runOnUiThread(() -> {
                    Log.i(TAG, "run: 开启ui线程");
                    initView();
                });
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void initView() {
        Log.i(TAG, "initView: 初始化结果界面");
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }
        // 初始化 recycler
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        // 设置是否跨列
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (list.get(position).getType() == SearchResultItemModel.TYPE_SEARCH) {
                    return 2;
                } else if (list.get(position).getType() == SearchResultItemModel.TYPE_PRODUCT) {
                    return 1;
                }
                return 0;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SearchResultRecyclerAdapter(this, this, list, searchKey));
    }

    private void initDate() {
        Log.i(TAG, "initDate: 初始化数据");
        // 获取搜索的关键字
        Intent intent = getIntent();
        searchKey = intent.getStringExtra(SearchActivity.INTENT_KEY);
        // 添加 recyclerView 项
        list = new ArrayList<>();
        list.add(new SearchResultItemModel(SearchResultItemModel.TYPE_SEARCH));  // 添加搜索框
        List<Product> productList = ProductController.filter(searchKey);
        for (Product product :
                productList) {
            list.add(new SearchResultItemModel(SearchResultItemModel.TYPE_PRODUCT, product));
        }
    }
}