package com.golike.lookgank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author admin
 * @date 2018/2/7
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private  Context context = null;
    SearchData dataList;

    public NewAdapter(Context context, SearchData dataList) {
        this.dataList = dataList;
        this.context = context;
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
            holder.title.setText(searchData.desc);
            Glide.with(context)
                    .load(searchData.getUrl()+"?imageView2/0/w/720")
                    .into(holder.face_src);
        }
    }

    @Override
    public int getItemCount() {
        return this.dataList == null ? 0 : this.dataList.getResults().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.face_src)
        ImageView face_src;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //title = (TextView)itemView.findViewById(R.id.title);
        }
    }
}
