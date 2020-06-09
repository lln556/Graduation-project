package com.android.cai_lai_la.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.cai_lai_la.MainActivity;
import com.android.cai_lai_la.R;
import com.android.cai_lai_la.activity.ProductDetailActivity;
import com.android.cai_lai_la.controller.ProductPicController;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ProductPic;
import com.android.cai_lai_la.model.ui.SearchResultItemModel;
import com.android.cai_lai_la.utils.LoadImageUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "search result adapter";
    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private String searchKey;
    private List<SearchResultItemModel> list;

    @Override
    public int getItemViewType(int position) {
        SearchResultItemModel searchResultItemModel = list.get(position);
        return searchResultItemModel.getType();
    }

    public SearchResultRecyclerAdapter(Context context, Activity activity, List<SearchResultItemModel> list, String searchKey) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        this.searchKey = searchKey;
        inflater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case SearchResultItemModel.TYPE_SEARCH:
                view = inflater.inflate(R.layout.item_search_title_head, parent, false);
                return new SearchItemHolder(view);
            case SearchResultItemModel.TYPE_PRODUCT:
                view = inflater.inflate(R.layout.item_search_product, parent, false);
                return new SearchProductHolder(view);
            default:
                return null;
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        SearchResultItemModel item = list.get(position);
        if (viewHolder instanceof SearchItemHolder){
            Log.i(TAG, "onBindViewHolder: 初始化顶部搜索框");
            // 顶部搜索框
            SearchItemHolder holder = (SearchItemHolder) viewHolder;
            // 返回按钮
            holder.backImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.startActivity(new Intent(context, MainActivity.class));
                }
            });
            // 搜索框
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.finish();
                }
            });
        } else if (viewHolder instanceof SearchProductHolder){
            Log.i(TAG, "onBindViewHolder: 初始化商品信息");
            // 商品按钮
            Product product = item.getProduct();
            SearchProductHolder holder = (SearchProductHolder) viewHolder;
            holder.title.setText(String.format("%s %s", product.getTitle(), product.getSubtitle()));
            holder.price.setText(String.format("￥%.2f", product.getCurrentprice()));
            holder.keep.setText(String.format("剩余%d件", product.getStorenum()));
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ProductDetailActivity.class);
                    intent.putExtra(ProductDetailActivity.INTENT_PRODUCT, product);
                    activity.startActivity(intent);
                }
            });
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    List<ProductPic> productPicList = ProductPicController.list(product.getPid());
                    LoadImageUtils.load(activity, context, productPicList, holder.imageView);
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SearchItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_result_back)
        ImageView backImage;
        @BindView(R.id.search_result_lin)
        LinearLayout linearLayout;
        public SearchItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class SearchProductHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_product_image)
        ImageView imageView;
        @BindView(R.id.home_product_title)
        TextView title;
        @BindView(R.id.home_product_price)
        TextView price;
        @BindView(R.id.home_product_keep)
        TextView keep;

        public SearchProductHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
