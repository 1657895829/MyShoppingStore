package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.activity.WebUrlActivity;
import com.example.shop.bean.SearchBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索框适配器
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{
    private Context context;
    private List<SearchBean.DataBean> list;
    public SearchAdapter(Context context) {
        this.context = context;
    }

    //添加数据的方法
    public void addData(List<SearchBean.DataBean> lists){
        if (this.list == null){
            this.list = new ArrayList<>();
        }
        list.clear();
        list.addAll(lists);
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.search_item,null);
        SearchViewHolder tuijianViewHolder = new SearchViewHolder(view);
        return tuijianViewHolder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        if(list.size()>0) {
            if (list.get(position).getImages().contains("|")){
                String[] split = list.get(position).getImages().split("\\|");
                holder.search_image.setImageURI(split[0]);
                holder.search_text.setText(list.get(position).getTitle());
            }else {
                holder.search_image.setImageURI(list.get(position).getImages());
                holder.search_text.setText(list.get(position).getTitle());
            }
        }

        //条目的点击事件
        holder.search_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将url传过去 访问详情页面
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("url",list.get(position).getDetailUrl());
                context.startActivity(intent);
            }
        });
        holder.search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将url传过去 访问详情页面
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("url",list.get(position).getDetailUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size() ;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView search_image;
        private final TextView search_text;
        public SearchViewHolder(View itemView) {
            super(itemView);
            search_image = (SimpleDraweeView) itemView.findViewById(R.id.search_image);
            search_text = (TextView) itemView.findViewById(R.id.search_text);
        }
    }
}
