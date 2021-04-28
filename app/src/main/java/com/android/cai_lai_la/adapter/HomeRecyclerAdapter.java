package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.controller.HistoryBrowserController;
import com.android.cai_lai_la.controller.ProductClassController;
import com.android.cai_lai_la.controller.ProductController;
import com.android.cai_lai_la.model.HistoryBrowser;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ProductClass;
import com.android.cai_lai_la.model.ui.HomeBannerInfoModel;
import com.android.cai_lai_la.model.ui.HomeItemModel;
import com.bumptech.glide.Glide;
import com.stx.xhb.androidx.XBanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * home 界面使用 RecyclerView的当时，但是同时需要添加集中类型的 item
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "HomeRecyclerAdapter";
    private Context context;
    private Activity activity;
    private List<HomeItemModel> list;
    private LayoutInflater inflater;  // 用于在 onCreateViewHolder 时，寻找不同的布局文件
    private int uid;  //用户编号

    public HomeRecyclerAdapter(Context context, Activity activity, List<HomeItemModel> list, int uid) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        this.uid = uid;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        HomeItemModel homeItemModel = list.get(position);
        if (homeItemModel.getType() == HomeItemModel.TYPE_CAROUSEL || homeItemModel.getType() == HomeItemModel.TYPE_CATEGORY) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
                params.setFullSpan(true);
            }
        }
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
            case HomeItemModel.TYPE_RECOMMEND_DIVIDE:
                view = inflater.inflate(R.layout.item_home_recommend_di, parent, false);
                return new DivideHolder(view);
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
        if (viewHolder instanceof CarouselHolder) {
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
                            .load(((HomeBannerInfoModel) model).getImageId())
                            .into((ImageView) view);
                }
            });

        } else if (viewHolder instanceof CategoryHolder) {
            // 分类图
            CategoryHolder holder = (CategoryHolder) viewHolder;
            // 设置数据
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "onBindViewHolder: 获取到所有分类信息");
                    List<ProductClass> list = ProductClassController.list();
                    activity.runOnUiThread(() -> {
                        // 初始化
                        // 设置布局方式
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
                        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                            @Override
                            public int getSpanSize(int position) {
                                return 1;
                            }
                        });
                        holder.categoryPage.setLayoutManager(gridLayoutManager);
                        // 设置adapter
                        holder.categoryPage.setAdapter(new HomeCategoryRecyclerAdapter(context, activity, list));
                        Log.i(TAG, "onBindViewHolder: 商品分类设置完成");

                    });
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        } else if (viewHolder instanceof RecommendHolder) {
            Log.i(TAG, "onBindViewHolder: 创建推荐商品信息");
            // 推荐商品布局
            RecommendHolder holder = (RecommendHolder) viewHolder;
            // 设置数据
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    List<HistoryBrowser> list = HistoryBrowserController.list(uid);   //获取用户浏览记录
                    List<Product> alllist = ProductController.list();
                    List<Product> result = new ArrayList<>();
                    for(int i = 0; i < alllist.size(); ++i){
                        for(int j = 0; j < list.size(); ++j){
                            int a = alllist.get(i).getPid();
                            int b = list.get(j).getPid();
                            if(a == b){
                                result.add(alllist.get(i));
                            }
                        }
                    }
                    if(result.size() == 0){    //如果用户没有浏览过任何商品，则自动展示系统热门商品
                        Collections.shuffle(alllist);
                        activity.runOnUiThread(() -> {
                            // 设置布局
                            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            holder.recyclerView.setLayoutManager(layoutManager);
                            // 设置adapter
                            HomeRecommendRecyclerAdapter homeRecommendRecyclerAdapter = new HomeRecommendRecyclerAdapter(context, activity, alllist);
                            holder.recyclerView.setAdapter(homeRecommendRecyclerAdapter);
                        });
                    }
                    else{     //根据用户的浏览记录推荐用户喜爱的商品
                        Collections.shuffle(result);  // 随机打乱
                        activity.runOnUiThread(() -> {
                            // 设置布局
                            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            holder.recyclerView.setLayoutManager(layoutManager);
                            // 设置adapter
                            HomeRecommendRecyclerAdapter homeRecommendRecyclerAdapter = new HomeRecommendRecyclerAdapter(context, activity, result);
                            holder.recyclerView.setAdapter(homeRecommendRecyclerAdapter);
                        });
                    }

                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }

    }

    /**
     * 获取指定位置 view 的类型。这个步骤是使用多种类型的 view 的关键
     *
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
        @BindView(R.id.home_category_recycler)
        RecyclerView categoryPage;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 推荐商品
     */
    class RecommendHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.home_recommend_recycler)
        RecyclerView recyclerView;

        public RecommendHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 默认
     */
    class FooterHolder extends RecyclerView.ViewHolder {

        public FooterHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class DivideHolder extends RecyclerView.ViewHolder {
        public DivideHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
