package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.model.ProductPic;
import com.android.cai_lai_la.utils.LoadImageUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    Activity activity;
    List<ProductPic> list;
    LayoutInflater inflater;

    public ProductDetailRecyclerAdapter(Context context, Activity activity, List<ProductPic> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product_detail_pic, parent, false);
        return new ProductPicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ProductPicHolder holder = (ProductPicHolder) viewHolder;
        ProductPic productPic = list.get(position);
        LoadImageUtils.load(activity, context, productPic.getPidpath(), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ProductPicHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_detail_pic)
        ImageView imageView;
        public ProductPicHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
