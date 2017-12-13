package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.activity.WebUrlActivity;
import com.example.shop.bean.ShouyeLunBoBean;

import java.util.List;

/**
 * 首页图标适配器
 */

public class MyGridAdapter extends BaseAdapter{
    private List<String> list;
    private List<Integer> imgList;
    private Context context;
    private List<ShouyeLunBoBean.TuijianBean.ListBean> data;

    public MyGridAdapter(List<String> list, List<Integer> imgList, Context context, List<ShouyeLunBoBean.TuijianBean.ListBean> data) {
        this.list = list;
        this.imgList = imgList;
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.fragment_grid, null);
            holder.iv_grid = (ImageView) convertView.findViewById(R.id.iv_grid);
            holder.tv_grid = (TextView) convertView.findViewById(R.id.tv_grid);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_grid.setText(list.get(position));
        holder.iv_grid.setImageResource(imgList.get(position));

        holder.tv_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击图标跳转页面
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("url", data.get(position).getDetailUrl());
                context.startActivity(intent);
            }
        });
        holder.iv_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击图标跳转页面
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("url", data.get(position).getDetailUrl());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView iv_grid;
        TextView tv_grid;
    }

}
