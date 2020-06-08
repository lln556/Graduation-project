package com.android.cai_lai_la.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.cai_lai_la.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

public class SearchActivity extends AppCompatActivity {
    public static final String INTENT_KEY = "search_key";
    public static final String TAG = "home activity";
    @BindView(R.id.search_view)
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();  // 初始化view
        initData();  // 初始化数据
    }

    private void initView(){
        // 取消工具栏
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }
        // 设置返回键
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
        // 设置搜索
        searchView.setOnClickSearch(new ICallBack() {
            @SuppressLint("ShowToast")
            @Override
            public void SearchAciton(String string) {
                Log.i(TAG, "SearchAciton: 搜索->" + string);
                Toast.makeText(SearchActivity.this, string, Toast.LENGTH_LONG);
                Intent intent = new Intent(SearchActivity.this, SearchResultActivity.class);
                intent.putExtra(INTENT_KEY, string);
                startActivity(intent);
            }
        });
    }
    private void initData(){

    }
}