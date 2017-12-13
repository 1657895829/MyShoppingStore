package com.example.shop.model;

import com.example.shop.bean.AddCartBean;
import com.example.shop.retrofit.GetDataInterface;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 加入购物车的model层
 */
public class AddCartModel {
    /**
     * https://www.zhaoapi.cn/product/addCart
     * "uid": 1650
     * "token": "2FC3EF31EA25696D2715A971ADE38DE1"
     * "pid":57
     * @param pid
     * @param callBack
     */
    public void getData(String pid, final AddCartModelCallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataInterface request = retrofit.create(GetDataInterface.class);
        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","1650");
        map.put("token","2FC3EF31EA25696D2715A971ADE38DE1");
        map.put("pid",pid);
        request.addCart(map).enqueue(new Callback<AddCartBean>() {
            @Override
            public void onResponse(Call<AddCartBean> call, Response<AddCartBean> response) {
                AddCartBean addCartBean = response.body();
                callBack.success(addCartBean);
            }

            @Override
            public void onFailure(Call<AddCartBean> call, Throwable t) {
                callBack.failure(new Exception());
            }
        });
    }

}
