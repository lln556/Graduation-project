package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.model.ui.HomeBannerInfoModel;
import com.android.cai_lai_la.model.ui.HomeItemModel;
import com.bumptech.glide.Glide;
import com.stx.xhb.androidx.XBanner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * home 界面使用 RecyclerView的当时，但是同时需要添加集中类型的 item
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "home recycler";
    private Context context;
    private Activity activity;
    private List<HomeItemModel> list;
    private LayoutInflater inflater;  // 用于在 onCreateViewHolder 时，寻找不同的布局文件

    public HomeRecyclerAdapter(Context context, Activity activity, List<HomeItemModel> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 创建 view，并将 view 绑定到 holder
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HomeItemModel.TYPE_CAROUSEL:
                view = inflater.inflate(R.layout.item_home_carousel, parent, false);
                return new CarouselHolder(view);
            case HomeItemModel.TYPE_CATEGORY:
                view = inflater.inflate(R.layout.item_home_category, parent, false);
                return new CategoryHolder(view);
            case HomeItemModel.TYPE_RECOMMEND:
                view = inflater.inflate(R.layout.item_home_recommend, parent, false);
                return new RecommendHolder(view);
            default:
                view = inflater.inflate(R.layout.item_home_footer, parent, false);
                return new FooterHolder(view);
        }
    }

    /**
     * 渲染数据
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        HomeItemModel bean = list.get(position);
        if (viewHolder instanceof CarouselHolder){
            // 轮播图
            Log.i(TAG, "onBindViewHolder: 添加轮播图");
            CarouselHolder holder = (CarouselHolder) viewHolder;
            // 设置资源
            List<HomeBannerInfoModel> bannerInfoList = new ArrayList<>();
            bannerInfoList.add(new HomeBannerInfoModel(R.drawable.bander1, "各种生鲜任你采购！"));
            bannerInfoList.add(new HomeBannerInfoModel(R.drawable.bander2, "绿色健康，快乐好生活！"));
            bannerInfoList.add(new HomeBannerInfoModel(R.drawable.bander3, "健康享受每一天！"));
            bannerInfoList.add(new HomeBannerInfoModel(R.drawable.bander4, "缤纷水果，快乐每一天！"));
            holder.xBanner.setBannerData(bannerInfoList);
            // 加载图片
            holder.xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context)
                            .load(((HomeBannerInfoModel)model).getImageId())
                            .into((ImageView)view);
                }
            });

        } else if (viewHolder instanceof CategoryHolder){
            // 分类图
        } else if (viewHolder instanceof RecommendHolder){
            // 推荐商品布局
        }

    }

    /**
     * 获取指定位置 view 的类型。这个步骤是使用多种类型的 view 的关键
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    /*
    ViewHolder 内部类
     */

    /**
     * 轮播图
     */
    class CarouselHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.xbanner)
        XBanner xBanner;
        public CarouselHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 分类栏
     */
    class CategoryHolder extends RecyclerView.ViewHolder {
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 推荐商品
     */
    class RecommendHolder extends RecyclerView.ViewHolder {

        public RecommendHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 默认
     */
    class FooterHolder extends RecyclerView.ViewHolder{

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
