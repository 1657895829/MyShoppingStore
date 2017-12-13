package com.example.shop.model;

import com.example.shop.bean.SelectCartBean;
import com.example.shop.retrofit.GetDataInterface;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 查询购物车model层
 */

public class SelectCartModel {

    public void getData(final SelectCartModelCallBack selectCartModelCallBack) {
        //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
        // https://www.zhaoapi.cn/product/addCart
        //"uid": 1650,
        // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataInterface service = retrofit.create(GetDataInterface.class);

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");

        service.selectCart(map).enqueue(new Callback<SelectCartBean>() {
            @Override
            public void onResponse(Call<SelectCartBean> call, Response<SelectCartBean> response) {
                SelectCartBean selectCartBean = response.body();
                selectCartModelCallBack.success(selectCartBean);
            }

            @Override
            public void onFailure(Call<SelectCartBean> call, Throwable t) {
                selectCartModelCallBack.failure();
            }
        });
    }
}
