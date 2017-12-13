package com.example.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.shop.R;
import com.example.shop.bean.RightBean;
import com.example.shop.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

/**
 * 分类页面右边二级列表条目数据适配器
 */
public class Set_FenLei_Child_Adapter extends RecyclerView.Adapter<Set_FenLei_Child_Adapter.ViewHodler> {
    private Context context;
    private List<RightBean.DataBean> list;
    private int i, i1;

    public Set_FenLei_Child_Adapter(Context context, List<RightBean.DataBean> list, int i, int i1) {
        this.context = context;
        this.list = list;
        this.i = i;
        this.i1 = i1;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.fen_set_child, null);
        ViewHodler hodler = new ViewHodler(v);

        return new ViewHodler(v);
    }

    @Override
    public void onBindViewHolder(final ViewHodler holder, int position) {
        //加载数据视图
        String[] split = list.get(i).getList().get(position).getIcon().split("\\|");
        ImageLoader.getInstance().displayImage(split[0],holder.img_child, ImageLoaderUtil.getDefaultOption());
        holder.tv_child.setText(list.get(i).getList().get(position).getName());

        //点击条目
        if (onItemClickListener != null){
            holder.img_child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition();                    // 1
                    onItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.get(i).getList().size();
    }

    //设置条目点击的回调接口
    private FenLei_Left_Adapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(FenLei_Left_Adapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private ImageView img_child;
        private TextView tv_child;
        public ViewHodler(View itemView) {
            super(itemView);
           img_child = (ImageView) itemView.findViewById(R.id.img_child);
           tv_child = (TextView) itemView.findViewById(R.id.tv_child);
        }
    }
}
