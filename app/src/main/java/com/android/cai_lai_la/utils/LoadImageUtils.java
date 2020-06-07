package com.android.cai_lai_la.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.model.ProductPic;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class LoadImageUtils {
    public static final String TAG = "utils-loadImage";

    /**
     * 加载图片
     * @param src 支持 bitmap、drawable、url、file、int
     * @param imageView 载入的位置
     * @param defaultImage 默认图片
     */
    public static void load(Activity activity, Context context, Object src, ImageView imageView, int defaultImage) {
        activity.runOnUiThread(() -> {
            Log.i(TAG, "load: 开始加载图片到" + imageView.getId());
            Glide.with(context)
                    .load(src)
                    .apply(new RequestOptions().error(defaultImage))
                    .apply(new RequestOptions().placeholder(defaultImage))
                    .into(imageView);
        });
    }

    /**
     *  直接加载内容
     */
    public static void load(Activity activity, Context context, Object src, ImageView imageView){
        load(activity, context, src, imageView, R.drawable.product_default);
    }

    /**
     * 加载 picList 中第一个图片
     */
    public static void load(Activity activity, Context context, List<ProductPic> picList, ImageView imageView, int defaultImage){
        if (picList.size() > 0){
            String url = picList.get(0).getPidpath();
            Log.i(TAG, "load: 列表size>0，加载第一个图片资源：" + url);
            load(activity, context, url, imageView);
        } else{
            Log.i(TAG, "load: 列表size=0，加载默认资源id=" + defaultImage);
            load(activity, context,defaultImage, imageView);
        }
    }
    public static void load(Activity activity, Context context, List<ProductPic> picList, ImageView imageView){
        load(activity, context, picList, imageView, R.drawable.product_default1);
    }
}
