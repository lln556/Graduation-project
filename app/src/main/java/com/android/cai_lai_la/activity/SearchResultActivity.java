package com.android.cai_lai_la.activity;

import android.os.Bundle;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.SearchResultRecyclerAdapter;
import com.android.cai_lai_la.model.ui.SearchResultItemModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends AppCompatActivity {
    @BindView(R.id.search_result_recycler)
    RecyclerView recyclerView;

    List<SearchResultItemModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        initDate();
        initView();
    }

    private void initView(){
        // 初始化 recycler
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SearchResultRecyclerAdapter(this, this, list));
    }
    private void initDate(){
        list.add(new SearchResultItemModel(SearchResultItemModel.TYPE_SEARCH));  // 添加搜索框
        list.add(new SearchResultItemModel(SearchResultItemModel.TYPE_PRODUCT));  // 添加商品信息
    }
}