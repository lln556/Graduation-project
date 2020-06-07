package com.android.cai_lai_la.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.cai_lai_la.R;
import com.android.cai_lai_la.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartListAdapter extends BaseAdapter {
    private List<Product> list;
    private LayoutInflater layoutInflater;
    public CartListAdapter(Context context, List<Product> list){
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
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
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Product product = list.get(position);
        viewHolder.name.setText(product.getTitle());
        viewHolder.content.setText(product.getSubtitle());
        viewHolder.price.setText("¥ "+product.getCurrentprice());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item_chlid_name)
        TextView name;
        @BindView(R.id.item_chlid_content)
        TextView content;
        @BindView(R.id.item_chlid_money)
        TextView price;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);//其实就是根据我们自己提供的根布局来绑定控件
        }
    }
}
