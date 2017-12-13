package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.activity.XiangQingActivity;
import com.example.shop.bean.ShouyeGridBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 *
 */
public class Recy02Adapter extends RecyclerView.Adapter<Recy02Adapter.GridViewHolder>{

    Context context;
    List<ShouyeGridBean.DataBean> listGridDa;
    public Recy02Adapter(Context context, List<ShouyeGridBean.DataBean> listGridDa) {
        this.listGridDa = listGridDa;
        this.context = context;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shouye_recy_02layout,null);
        GridViewHolder gridViewHolder = new GridViewHolder(view);
        return gridViewHolder;
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        if(listGridDa.size()>0) {
            ImageLoader.getInstance().displayImage(listGridDa.get(position).getIcon(), holder.recy02_image);
            holder.recy02_text.setText(listGridDa.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class GridViewHolder extends RecyclerView.ViewHolder {

        private final ImageView recy02_image;
        private final TextView recy02_text;

        public GridViewHolder(View itemView) {
            super(itemView);
            recy02_image = (ImageView) itemView.findViewById(R.id.recy02_image);
            recy02_text = (TextView) itemView.findViewById(R.id.recy02_text);
        }
    }
}
