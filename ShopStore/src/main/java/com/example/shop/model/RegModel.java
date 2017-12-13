package com.example.shop.model;

import com.example.shop.bean.ZhuceBean;
import com.example.shop.retrofit.GetDataInterface;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 注册model层
 */

public class RegModel {
    public void getData(String zhuce_zh, String zhuce_pwd, final RegModelCallBack regModelCallBack) {
    //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataInterface service = retrofit.create(GetDataInterface.class);

        Map<String,String> map = new HashMap<>();
        map.put("mobile",zhuce_zh);
        map.put("password",zhuce_pwd);
        service.zhuce(map).enqueue(new Callback<ZhuceBean>() {

            @Override
            public void onResponse(Call<ZhuceBean> call, Response<ZhuceBean> response) {
                ZhuceBean zhuceBean = response.body();
                regModelCallBack.success(zhuceBean);
            }

            @Override
            public void onFailure(Call<ZhuceBean> call, Throwable t) {
                regModelCallBack.failure(new Exception());
            }
        });
    }

}