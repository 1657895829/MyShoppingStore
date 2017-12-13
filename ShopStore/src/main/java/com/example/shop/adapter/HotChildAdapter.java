package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.activity.WebUrlActivity;
import com.example.shop.bean.NewsBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

//RecyclerView展示数据适配器
public class HotChildAdapter extends RecyclerView.Adapter<HotChildAdapter.MyViewHolder> {
    private Context context;
    private List<NewsBean.ResultBean.DataBean> list;

    public HotChildAdapter(Context context,List<NewsBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //设置参数数据
        holder.draweeView.setImageURI(list.get(position).getThumbnail_pic_s());
        holder.title.setText(list.get(position).getTitle());

        //条目点击事件
        holder.draweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至webview页面显示详情
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至webview页面显示详情
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.draweeView)
        SimpleDraweeView draweeView;
        @Bind(R.id.title)
        TextView title;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
