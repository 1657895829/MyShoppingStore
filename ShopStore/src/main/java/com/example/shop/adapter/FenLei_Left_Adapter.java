package com.example.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.bean.LeftBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 分类页面左边数据适配器
 */
public class FenLei_Left_Adapter extends RecyclerView.Adapter<FenLei_Left_Adapter.MyHolder> {
    private Context context;
    private List<LeftBean.DataBean> list;

    public FenLei_Left_Adapter(Context context, List<LeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fenlei_left, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        //加载布局
        holder.zuoMz.setText(list.get(position).getName());
        ImageLoader.getInstance().displayImage(list.get(position).getIcon(),holder.zuoTp);
        if (onItemClickListener != null){
            holder.zuoTp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    onItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 :list.size();
    }

    //设置条目点击的回调接口
    private OnItemClickListener onItemClickListener;
    public interface  OnItemClickListener{
        public void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.zuo_tp)
        ImageView zuoTp;
        @Bind(R.id.zuo_mz)
        TextView zuoMz;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
