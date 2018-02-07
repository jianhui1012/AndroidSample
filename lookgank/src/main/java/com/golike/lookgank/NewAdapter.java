package com.golike.lookgank;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author admin
 * @date 2018/2/7
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    SearchData dataList = new SearchData();

    public NewAdapter(SearchData dataList) {
        this.dataList = dataList;
    }

    public NewAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchData.GANK searchData = this.dataList.getResults().get(position);
        if (searchData != null) {
            holder.title.setText(position+"");
        }
    }

    @Override
    public int getItemCount() {
        return this.dataList.getResults() != null ? this.dataList.getResults().size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
