package com.android.cai_lai_la.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.cai_lai_la.model.ui.SearchResultItemModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    Activity activity;
    LayoutInflater inflater;

    public SearchResultRecyclerAdapter(Context context, Activity activity, List<SearchResultItemModel> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        inflater = activity.getLayoutInflater();
    }

    List<SearchResultItemModel> list;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
