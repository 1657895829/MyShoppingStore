package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.activity.XiangQingActivity;
import com.example.shop.bean.ShouyeLunBoBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 热点页面适配器
 */
public class MineAdapter extends RecyclerView.Adapter<MineAdapter.HotViewHolder> {
    private List<ShouyeLunBoBean.MiaoshaBean.ListBeanX> list;
    private Context context;

    public MineAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<ShouyeLunBoBean.MiaoshaBean.ListBeanX> miaosha) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(miaosha);
        notifyDataSetChanged();
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.mine_adapter, null);
        return new HotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, final int position) {
        if (list.size() > 0) {
            if (list.get(position).getImages().contains("|")) {
                String[] split = list.get(position).getImages().split("\\|");
                holder.hot_image.setImageURI(split[0]);
                holder.title.setText(list.get(position).getTitle());
                holder.priceHot.setText("￥"+list.get(position).getPrice());

            } else {
                holder.hot_image.setImageURI(list.get(position).getImages());
                holder.title.setText(list.get(position).getTitle());
                holder.priceHot.setText("￥"+list.get(position).getPrice());
            }
        }

        //点击按钮的点击事件
        holder.btnHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传递商品数据
                Intent intent = new Intent(context, XiangQingActivity.class);
                intent.putExtra("pid", list.get(position).getPid() + "");
                intent.putExtra("images", list.get(position).getImages());
                intent.putExtra("bargainPrice", list.get(position).getBargainPrice() + "");
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("price", list.get(position).getPrice() + "");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class HotViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.hot_image)
        SimpleDraweeView hot_image;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.price_hot)
        TextView priceHot;
        @Bind(R.id.btn_hot)
        Button btnHot;
        public HotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

