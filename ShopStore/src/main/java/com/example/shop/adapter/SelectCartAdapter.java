package com.example.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.bean.DeleteBean;
import com.example.shop.bean.SelectCartBean;
import com.example.shop.customview.CustomJiaJian;
import com.example.shop.presenter.DeleteCartPresenter;
import com.example.shop.util.ImageLoaderUtil;
import com.example.shop.view.DeleteCartListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车适配器
 */
public class SelectCartAdapter extends RecyclerView.Adapter<SelectCartAdapter.MyViewHolder> implements DeleteCartListener {
    private List<SelectCartBean.DataBean.ListBean> list;//大的集合
    Map<String,String> map = new HashMap<>();//装商家名字和id的集合
    Context context;
    //new出删除购物车数据的presenter
    DeleteCartPresenter deleteCartPresenter = new DeleteCartPresenter(this);

    public SelectCartAdapter(Context context) {
        this.context = context;
    }

    public void add(SelectCartBean selectCartBean) {
        //传进来的是bean对象
        if(list==null){
            list = new ArrayList<>();
        }
        //第一层遍历商家和商品
        for (SelectCartBean.DataBean shop:selectCartBean.getData()){
             //把商家的id和商家名添加到map里
            map.put(shop.getSellerid(),shop.getSellerName());
            //第二层遍历里面的商品
            for (int i=0;i<shop.getList().size();i++){
                //添加到list集合里
                list.add(shop.getList().get(i));
            }
        }
        //调用方法设置显示或隐藏商家名
        setFirst(list);
        notifyDataSetChanged();
    }


    /**
     * 设置数据源,控制是否显示商家
     * */
    private void setFirst(List<SelectCartBean.DataBean.ListBean> list) {

        if(list.size()>0){
            //如果是第一条数据就设置isFirst为1
            list.get(0).setIsFirst(1);
            //从第二条开始遍历
            for (int i=1;i<list.size();i++){
                //如果和前一个商品是同一家商店的
                if (list.get(i).getSellerid() == list.get(i-1).getSellerid()){
                    //设置成2不显示商铺
                    list.get(i).setIsFirst(2);
                }else{//设置成1显示商铺
                    list.get(i).setIsFirst(1);
                    //如果当前条目选中,把当前的商铺也选中
                    if (list.get(i).isItem_check()==true){
                        list.get(i).setShop_check(list.get(i).isItem_check());
                    }
                }
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.recy_cart_item,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        /**
         * 设置商铺的 shop_checkbox和商铺的名字 显示或隐藏
         * */
        if (list.get(position).getIsFirst() == 1) {
            //显示商家
            holder.shop_checkbox.setVisibility(View.VISIBLE);
            holder.shop_name.setVisibility(View.VISIBLE);
            //设置shop_checkbox的选中状态
            holder.shop_checkbox.setChecked(list.get(position).isShop_check());
            holder.shop_name.setText(map.get(String.valueOf(list.get(position).getSellerid())));
        } else {//2
            //隐藏商家
            holder.shop_name.setVisibility(View.GONE);
            holder.shop_checkbox.setVisibility(View.GONE);
        }
        //拆分images字段
        String[] split = list.get(position).getImages().split("\\|");
        //设置商品的图片
        ImageLoader.getInstance().displayImage(split[0], holder.item_face, ImageLoaderUtil.getDefaultOption());
        //控制商品的item_checkbox,,根据字段改变
        holder.item_checkbox.setChecked(list.get(position).isItem_check());
        holder.item_name.setText(list.get(position).getTitle());
        holder.item_desc.setText(list.get(position).getSubhead());
        holder.item_price.setText("￥" + list.get(position).getBargainPrice() + "");
        /**
         * 文字线条的显示样式：
         * getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
         */
        holder.item_yuanjia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中间横线（删除线）
        holder.item_yuanjia.setText("￥" + list.get(position).getPrice());

        //调用customjiajian里面的方法设置 加减号中间的数字
        holder.customJiaJian.setEditText(list.get(position).getNum());

        //商铺的shop_checkbox点击事件 ,控制商品的item_checkbox
        holder.shop_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先改变数据源中的shop_check
                list.get(position).setShop_check(holder.shop_checkbox.isChecked());

                for (int i = 0; i < list.size(); i++) {
                    //如果是同一家商铺的 都给成相同状态
                    if (list.get(position).getSellerid() == list.get(i).getSellerid()) {
                        //当前条目的选中状态 设置成 当前商铺的选中状态
                        list.get(i).setItem_check(holder.shop_checkbox.isChecked());
                    }
                }
                //刷新适配器
                notifyDataSetChanged();
                //调用求和的方法
                sum(list);
            }
        });

        //商品的item_checkbox点击事件,控制商铺的shop_checkbox
        holder.item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先改变数据源中的item_checkbox
                list.get(position).setItem_check(holder.item_checkbox.isChecked());

                //反向控制商铺的shop_checkbox
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.size(); j++) {
                        //如果两个商品是同一家店铺的 并且 这两个商品的item_checkbox选中状态不一样
                        if (list.get(i).getSellerid() == list.get(j).getSellerid() && !list.get(j).isItem_check()) {
                            //就把商铺的shop_checkbox改成false
                            list.get(i).setShop_check(false);
                            break;
                        } else {
                            //同一家商铺的商品 选中状态都一样,就把商铺shop_checkbox状态改成true
                            list.get(i).setShop_check(true);
                        }
                    }
                }

                //更新适配器
                notifyDataSetChanged();
                //调用求和的方法
                sum(list);
            }
        });

        //删除条目的点击事件,删除框点击 请求 删除接口
        holder.item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mvp操作 删除的接口,传pid
                int pid1 = list.get(position).getPid();
                String pid = String.valueOf(pid1);


                deleteCartPresenter.delete(pid);

                list.remove(position);//移除集合中的当前数据
                //删除完当前的条目 重新判断商铺的显示隐藏
                setFirst(list);

                //调用重新求和
                sum(list);
                notifyDataSetChanged();
            }
        });

        //加减号的监听,
        holder.customJiaJian.setCustomListener(new CustomJiaJian.CustomListener() {
            @Override
            public void jiajian(int count) {
                //改变数据源中的数量
                list.get(position).setNum(count);
                notifyDataSetChanged();
                sum(list);
            }

        });
    }



        /**
         * 计算总价的方法
         * */
        private void sum(List<SelectCartBean.DataBean.ListBean> list){
            int totalNum = 0;//初始的总价为0
            float totalMoney = 0.0f;
            boolean allCheck = true;
            for (int i=0;i<list.size();i++){
                //把 已经选中的 条目 计算价格
                if (list.get(i).isItem_check()){
                    totalNum += list.get(i).getNum();
                    totalMoney += list.get(i).getNum() * list.get(i).getPrice();
                }else{
                    //如果有个未选中,就标记为false
                    allCheck = false;
                }
            }

            //接口回调出去 把总价 总数量 和allcheck 传给view层
            updateListener.setTotal(totalMoney+"",totalNum+"",allCheck);
        }



    //view层调用这个方法, 点击quanxuan按钮的操作
    public void quanXuan(boolean checked) {
        for (int i=0;i<list.size();i++){
            list.get(i).setShop_check(checked);
            list.get(i).setItem_check(checked);

        }
        notifyDataSetChanged();
        sum(list);
    }
    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    //删除接口的回调
    @Override
    public void success(DeleteBean deleteBean) {
        Toast.makeText(context,deleteBean.getMsg(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure() {

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox shop_checkbox;
        private final TextView shop_name;
        private final CheckBox item_checkbox;
        private final TextView item_name;
        private final TextView item_yuanjia;
        private final TextView item_price;
        private final TextView item_desc;
        private final CustomJiaJian customJiaJian;
        private final ImageView item_delete;
        private final ImageView item_face;

        public MyViewHolder(View itemView) {
            super(itemView);
            shop_checkbox = (CheckBox) itemView.findViewById(R.id.shop_checkbox);
            shop_name = (TextView) itemView.findViewById(R.id.shop_name);
            item_checkbox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            item_price = (TextView) itemView.findViewById(R.id.item_price);
            item_yuanjia = (TextView) itemView.findViewById(R.id.item_yuanjia);
            item_desc = (TextView) itemView.findViewById(R.id.item_desc);
            customJiaJian = (CustomJiaJian) itemView.findViewById(R.id.custom_jiajian);
            item_delete = (ImageView) itemView.findViewById(R.id.item_delete);
            item_face = (ImageView) itemView.findViewById(R.id.item_face);
        }
    }

    UpdateListener updateListener;
    public void setUpdateListener(UpdateListener updateListener){
        this.updateListener = updateListener;
    }
    //接口
    public interface UpdateListener{
        public void setTotal(String total, String num, boolean allCheck);
    }
}
