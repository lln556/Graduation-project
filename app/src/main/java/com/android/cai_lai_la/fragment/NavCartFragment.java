package com.android.cai_lai_la.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.CartListAdapter;
import com.android.cai_lai_la.controller.CartController;
import com.android.cai_lai_la.model.Product;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavCartFragment extends Fragment {
    private int uid = 1;//用户编号
    private Context mContext;

    @BindView(R.id.cart_listView)
    ListView listView;

    public NavCartFragment() {
        // Required empty public constructor
    }

    public static NavCartFragment newInstance(){
        NavCartFragment fragment = new NavCartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_nav_cart, container, false);
        ButterKnife.bind(this, view);  // 自动绑定
        initData();
        return view;
    }

    private void initData(){
        List<Product> productList = new ArrayList();
        productList = CartController.list(1);
        if(productList.size()==0){
            Toast.makeText(mContext, "商品不存在", Toast.LENGTH_SHORT).show();
        }
        else {
            CartListAdapter adapter = new CartListAdapter(mContext,productList);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}
