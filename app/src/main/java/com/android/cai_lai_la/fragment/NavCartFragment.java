package com.android.cai_lai_la.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;


import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.CartListAdapter;
import com.android.cai_lai_la.callback.OnClickAddCloseListenter;
import com.android.cai_lai_la.controller.CartController;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ui.CartInfo;

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
    CartInfo cartInfo;

    @BindView(R.id.cart_listView)
    ListView listView;


    public NavCartFragment() {
        // Required empty public constructor
    }

    public static NavCartFragment newInstance() {
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
        View view = inflater.inflate(R.layout.fragment_nav_cart, container, false);
        ButterKnife.bind(this, view);  // 自动绑定
        initData();
        return view;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                ArrayList arrayList = msg.getData().getParcelableArrayList("data");
                List<Product> data = (List<Product>) arrayList.get(0);
                List cartInfos=new ArrayList();
                for (int i = 0; i <= data.size()-1;i++){
                    cartInfos.add(new CartInfo());
                }

                if (data.size() == 0) {
                    Toast.makeText(mContext, "商品不存在", Toast.LENGTH_SHORT).show();
                } else {
                    CartListAdapter adapter = new CartListAdapter(mContext, data, cartInfos);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.setOnClickAddCloseListenter(new OnClickAddCloseListenter() {
                        @Override
                        public void onItemClick(View view, int index, int position,int num) {
                            if (index==1){
                                if (num>1) {
                                    CartInfo cartinfoClose = (CartInfo)cartInfos.get(position);
                                    cartinfoClose.setNum((num - 1));
                                    adapter.notifyDataSetChanged();
                                }
                            }else {
                                CartInfo cartinfoAdd = (CartInfo)cartInfos.get(position);
                                cartinfoAdd.setNum((num + 1));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });


                }
            }
        }
    };
    private void initData() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                List<Product> productList = new ArrayList();
                productList = CartController.list(uid);

                ArrayList arrayList = new ArrayList();//用于传递list的集合
                arrayList.add(productList);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("data", arrayList);
                Message msg = new Message();
                msg.what = 0;
                msg.setData(bundle);
                handler.sendMessage(msg);
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }


}
