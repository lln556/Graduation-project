package com.android.cai_lai_la.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.cai_lai_la.R;
import com.android.cai_lai_la.adapter.CartListAdapter;
import com.android.cai_lai_la.callback.OnClickAddCloseListenter;
import com.android.cai_lai_la.callback.OnClickListenterModel;
import com.android.cai_lai_la.controller.CartController;
import com.android.cai_lai_la.model.Cart;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ui.CartInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavCartFragment extends Fragment {
    private boolean isGetData = false;
    private int uid = 1;  //用户编号
    private Context mContext;
    CartInfo cartInfo;  //购物车信息
    double price;
    int num;

    @BindView(R.id.cart_listView)
    ListView listView;
    @BindView(R.id.cart_num)
    TextView cartNum;
    @BindView(R.id.cart_money)
    TextView cartMoney;
    @BindView(R.id.cart_shopp_moular)
    Button cartShoppMoular;


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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            initData();
            isGetData = true;
            //相当于Fragment的onResume，为true时，Fragment已经可见
        } else {
            isGetData = false;
            // 相当于Fragment的onPause，为false时，Fragment不可见
        }
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
                List<CartInfo> cartInfos = new ArrayList();

                for (int i = 0; i < data.size(); i++) {
                    CartInfo c1 = new CartInfo();
                    c1.setCurrentprice(data.get(i).getCurrentprice());
                    cartInfos.add(c1);
                }

                if (data.size() == 0) {
                    Toast.makeText(mContext, "商品不存在", Toast.LENGTH_SHORT).show();
                } else {
                    CartListAdapter adapter = new CartListAdapter(mContext, data, cartInfos , getActivity());
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    /**
                     * 单选
                     */
                    adapter.setOnClickListenterModel(new OnClickListenterModel() {
                        @Override
                        public void onItemClick(boolean isFlang, View view, int position) {
                            cartInfos.get(position).setIscheck(isFlang);
                            showCommodityCalculation(cartInfos);
                        }
                    });


                    /**
                     * 数量增加和减少
                     */
                    adapter.setOnClickAddCloseListenter(new OnClickAddCloseListenter() {
                        @Override
                        public void onItemClick(View view, int index, int position, int num) {
                            if (index == 1) {
                                if (num > 1) {
                                    CartInfo cartinfoClose = (CartInfo) cartInfos.get(position);
                                    cartinfoClose.setNum((num - 1));
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                CartInfo cartinfoAdd = (CartInfo) cartInfos.get(position);
                                cartinfoAdd.setNum((num + 1));
                                adapter.notifyDataSetChanged();
                            }
                            showCommodityCalculation(cartInfos);
                        }
                    });
                    showCommodityCalculation(cartInfos);
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


    private void showCommodityCalculation(List<CartInfo> cartInfos) {
        price=0;
        num=0;
        for (int i = 0; i < cartInfos.size(); i++) {
            if (cartInfos.get(i).ischeck()){
                price+=Double.valueOf((cartInfos.get(i).getNum() * Double.valueOf(cartInfos.get(i).getCurrentprice())));
                num++;
            }
        }
        if (price==0.0){
            cartNum.setText("共0件商品");
            cartMoney.setText("¥ 0.0");
            return;
        }
        try {
            String money=String.valueOf(price);
            cartNum.setText("共"+num+"件商品");
            if (money.substring(money.indexOf("."),money.length()).length()>2){
                cartMoney.setText("¥ "+money.substring(0,(money.indexOf(".")+3)));
                return;
            }
            cartMoney.setText("¥ "+money.substring(0,(money.indexOf(".")+2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.cart_shopp_moular)
    public void onClick() {
        Toast.makeText(mContext,"提交订单:  "+cartMoney.getText().toString()+"元",Toast.LENGTH_LONG).show();
    }
}
