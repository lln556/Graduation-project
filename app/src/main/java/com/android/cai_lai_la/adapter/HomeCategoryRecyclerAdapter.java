package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.model.ProductClass;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeCategoryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "home category";
    private Context context;
    private Activity activity;
    private List<ProductClass> list;

    public HomeCategoryRecyclerAdapter(Context context, Activity activity, List<ProductClass> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    private LayoutInflater inflater;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_category_item, parent, false);
        return new CategoryItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CategoryItemHolder holder = (CategoryItemHolder) viewHolder;
        ProductClass productClass = list.get(position);
        Log.i(TAG, "onBindViewHolder: 分类信息为 " +productClass.getClassname());

        // 加载图片
        RequestOptions requestOptions= new RequestOptions()
                .placeholder(R.drawable.ic_cate_default)
                .error(R.drawable.ic_cate_default)
                .override(200)
                .fitCenter()
                ;
        Glide.with(context)
                .load(productClass.getClasspic())
                .apply(requestOptions)
                .into(holder.imageView);
        holder.textView.setText(productClass.getClassname());
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    static class CategoryItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_category_item_image)
        ImageView imageView;
        @BindView(R.id.home_category_item_text)
        TextView textView;
        public CategoryItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
