package com.example.shop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.bean.ChildBean;
import java.util.List;

/**
 * 子分类数据适配器
 */

public class FenChildAdapter extends RecyclerView.Adapter<FenChildAdapter.MyViewHodler>{
    Context context;
    List<ChildBean.DataBean> list;
    private OnItemClickListener mOnItemClickListener;

    public FenChildAdapter(Context context, List<ChildBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fen_child,null);
        MyViewHodler hodler = new MyViewHodler(view);
        hodler.iv = (ImageView) view.findViewById(R.id.iv);
        hodler.tvs = (TextView) view.findViewById(R.id.tvs);
        return hodler;
    }

    @Override
    public void onBindViewHolder(final MyViewHodler holder, int position) {
        //设置布局
        for (int i = 0;i<list.size();i++){
            String[] str = list.get(position).getImages().split("\\|");
            String s = str[i];
            Uri uri =  Uri.parse(s);
            holder.iv.setImageURI(uri);
        }
        holder.tvs.setText(list.get(position).getTitle());

        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvs;
        public MyViewHodler(View itemView) {
            super(itemView);
        }
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}