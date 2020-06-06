package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.model.Product;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeRecommendRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    Activity activity;
    List<Product> list;
    LayoutInflater inflater;

    public HomeRecommendRecyclerAdapter(Context context, Activity activity, List<Product> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_recommend_product, parent, false);
        return new RecommendProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // TODO: 初始化数据
        // TODO: 暂时使用测试数据
        RecommendProductHolder holder = (RecommendProductHolder) viewHolder;
        Random random = new Random();
        if (random.nextInt() > 0){
            holder.imageView.setImageResource(R.drawable.product_default);
        } else{
            holder.imageView.setImageResource(R.drawable.product_default1);
        }
        holder.title.setText("大白菜");
        holder.price.setText("￥2000元");
        holder.keep.setText("剩余30件");
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RecommendProductHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_product_image)
        ImageView imageView;
        @BindView(R.id.home_product_title)
        TextView title;
        @BindView(R.id.home_product_price)
        TextView price;
        @BindView(R.id.home_product_keep)
        TextView keep;

        public RecommendProductHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
