package com.example.shop.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.ZhiFuBao.PayDemoActivity;
import com.example.shop.adapter.SelectCartAdapter;
import com.example.shop.bean.SelectCartBean;
import com.example.shop.presenter.SelectCartPresenter;
import com.example.shop.view.SelectCartViewListener;

//购物车fragment
public class ShoppingBikeFragment extends Fragment implements SelectCartViewListener{
    private RecyclerView recyclerView;
    private TextView total_price;
    private TextView total_num;
    private CheckBox quanxuan;
    private TextView pay;
    private SelectCartPresenter selectCartPresenter;
    private SelectCartAdapter selectCartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gouwuche,container,false);
        //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_View);
        total_price = (TextView) view.findViewById(R.id.total_price);
        total_num = (TextView) view.findViewById(R.id.total_num);
        quanxuan = (CheckBox) view.findViewById(R.id.quanxuan);
        pay = (TextView)view.findViewById(R.id.pay);


        quanxuan.setTag(1);//1为不选中
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        selectCartAdapter = new SelectCartAdapter(getActivity());
        selectCartPresenter = new SelectCartPresenter(this);

        //调用presenter里面的请求数据的方法
        selectCartPresenter.getData();

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(selectCartAdapter);
        selectCartAdapter.notifyDataSetChanged();

        //调用recyAdapter里面的接口,设置 全选按钮 总价 总数量
        selectCartAdapter.setUpdateListener(new SelectCartAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置ui的改变
                total_num.setText("共"+num+"件商品");//总数量
                total_price.setText("总价：¥ "+total);//总价
                if(allCheck){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }
                quanxuan.setChecked(allCheck);
            }
        });

        //这里只做ui更改, 点击全选按钮,,调到adapter里面操作
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter里面的方法 ,,把当前quanxuan状态传递过去

                int tag = (int) quanxuan.getTag();
                if(tag==1){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }

                selectCartAdapter.quanXuan(quanxuan.isChecked());
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(total_price.getText().toString()) > 1){
                    //点击进行  第三方支付宝 支付
                    Intent intent = new Intent(getActivity(), PayDemoActivity.class);
                    startActivity(intent);
                }

                Toast.makeText(getActivity(),"共需支付："+total_price.getText().toString()+" 元",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    @Override
    public void success(SelectCartBean selectCartBean) {

        //将数据传给适配器
        if(selectCartBean!=null) {
            selectCartAdapter.add(selectCartBean);
            selectCartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failure() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        selectCartPresenter.detach();
    }

}
