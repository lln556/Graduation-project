package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.controller.ProductPicController;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ProductPic;
import com.android.cai_lai_la.model.ui.CartInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderConfirmAdapter extends BaseAdapter {
    Context context;
    Activity activity;
    private List<CartInfo> cartInfos;
    private List<Product> list;
    private LayoutInflater layoutInflater;

    public OrderConfirmAdapter(Context context, List<Product> list, List<CartInfo> cartInfos, Activity activity){
        this.context = context;
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.cartInfos = cartInfos;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.confirm_order_child,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product product = list.get(position);
        viewHolder.name.setText(product.getTitle());
        viewHolder.content.setText(product.getSubtitle());
        viewHolder.price.setText("?? "+product.getCurrentprice());
        viewHolder.num.setText(""+cartInfos.get(position).getNum());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RequestOptions options = new RequestOptions().error(R.drawable.test1).placeholder(R.drawable.test1);
                List<ProductPic> picList = ProductPicController.list(product.getPid());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(context)
                                .load(picList.get(0).getPidpath())
                                .apply(options)
                                .into(viewHolder.pic);
                    }
                });

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.item_chlid_image)
        ImageView pic;
        @BindView(R.id.item_chlid_name)
        TextView name;
        @BindView(R.id.item_chlid_content)
        TextView content;
        @BindView(R.id.item_chlid_money)
        TextView price;
        @BindView(R.id.order_num)
        TextView num;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
