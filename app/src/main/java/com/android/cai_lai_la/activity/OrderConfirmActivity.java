package com.android.cai_lai_la.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.OrderConfirmAdapter;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ui.CartInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderConfirmActivity extends AppCompatActivity {
    public static final String INTENT_PRODUCT = "productlist";
    public static final String INTENT_CARTINFO = "cartinfolist";
    private List<CartInfo> cartInfos;
    private List<Product> list;

    @BindView(R.id.order_list)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        //仅去掉标题栏，系统状态栏还是会显示
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        initData();
        initView();
    }

    private void initData(){
        Intent intent = getIntent();
        list = (List<Product>) intent.getSerializableExtra(INTENT_PRODUCT);
        cartInfos = (List<CartInfo>) intent.getSerializableExtra(INTENT_CARTINFO);
    }

    private void initView(){
        for (int i = 0; i < list.size(); i++) {
            CartInfo c1 = new CartInfo();
            c1.setCurrentprice(list.get(i).getCurrentprice());
            cartInfos.add(c1);
        }
        if (list.size() == 0) {
            Toast.makeText(this, "商品不存在", Toast.LENGTH_SHORT).show();
        } else {
            OrderConfirmAdapter adapter = new OrderConfirmAdapter(this, list, cartInfos, this);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}