package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.callback.OnClickAddCloseListenter;
import com.android.cai_lai_la.callback.OnClickListenterModel;
import com.android.cai_lai_la.controller.ProductPicController;
import com.android.cai_lai_la.model.Product;
import com.android.cai_lai_la.model.ProductPic;
import com.android.cai_lai_la.model.ui.CartInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListAdapter extends BaseAdapter {
    Context context;
    Activity activity;
    private List<CartInfo> cartInfos;
    private List<Product> list;
    private LayoutInflater layoutInflater;
    public CartListAdapter(Context context, List<Product> list, List<CartInfo> cartInfos, Activity activity){
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
            convertView=layoutInflater.inflate(R.layout.cart_child,null);
            viewHolder=new ViewHolder(convertView, position);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product product = list.get(position);
        viewHolder.name.setText(product.getTitle());
        viewHolder.content.setText(product.getSubtitle());
        viewHolder.checkBox.setChecked(cartInfos.get(position).ischeck());
        viewHolder.price.setText("¥ "+product.getCurrentprice());
        viewHolder.btnNum.setText(cartInfos.get(position).getNum()+"");
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
                                .into(viewHolder.imageView);
                    }
                });

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListenterModel.onItemClick(viewHolder.checkBox.isChecked(),v,position);
            }
        });
        return convertView;
    }


    class ViewHolder implements View.OnClickListener{
        private int position;

        @BindView(R.id.item_chlid_name)
        TextView name;
        @BindView(R.id.item_chlid_content)
        TextView content;
        @BindView(R.id.item_chlid_money)
        TextView price;
        @BindView(R.id.item_chlid_check)
        CheckBox checkBox;
        @BindView(R.id.btn_delete)
        Button button;
        @BindView(R.id.id_front)
        View front;
        @BindView(R.id.item_chlid_add)
        Button btnAdd;
        @BindView(R.id.item_chlid_close)
        Button btnClose;
        @BindView(R.id.item_chlid_num)
        Button btnNum;
        @BindView(R.id.item_chlid_image)
        ImageView imageView;

        public ViewHolder(View view, int position) {
            ButterKnife.bind(this, view);//其实就是根据我们自己提供的根布局来绑定控件
            this.position=position;
            btnAdd.setOnClickListener(this);
            btnClose.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_chlid_add:
                    onClickAddCloseListenter.onItemClick(v,2, position, Integer.valueOf(btnNum.getText().toString()));
                    break;
                case R.id.item_chlid_close:
                    onClickAddCloseListenter.onItemClick(v,1, position, Integer.valueOf(btnNum.getText().toString()));
                    break;
            }
        }
    }

    // CheckBox接口的方法
    private OnClickListenterModel onClickListenterModel = null;
    public void setOnClickListenterModel(OnClickListenterModel listener) {
        this.onClickListenterModel = listener;
    }

    // 数量接口的方法
    private OnClickAddCloseListenter onClickAddCloseListenter = null;
    public void setOnClickAddCloseListenter(OnClickAddCloseListenter listener) {
        this.onClickAddCloseListenter = listener;
    }
}
