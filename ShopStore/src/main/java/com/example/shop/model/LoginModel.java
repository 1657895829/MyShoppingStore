package com.example.shop.model;

import com.example.shop.bean.DengluBean;
import com.example.shop.retrofit.GetDataInterface;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 登录model层
 */

public class LoginModel {
    public void getData(String denglu_zh, String denglu_pwd, final LoginModelCallBack loginModelCallBack) {
        //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetDataInterface service = retrofit.create(GetDataInterface.class);

        Map<String,String> map = new HashMap<>();
        map.put("mobile",denglu_zh);
        map.put("password",denglu_pwd);
        service.denglu(map).enqueue(new Callback<DengluBean>() {

            @Override
            public void onResponse(Call<DengluBean> call, Response<DengluBean> response) {
                DengluBean dengluBean = response.body();
                loginModelCallBack.success(dengluBean);
                System.out.println("登录m数据："+dengluBean.toString());
            }

            @Override
            public void onFailure(Call<DengluBean> call, Throwable t) {
                loginModelCallBack.failure(new Exception());
            }
        });
    }
}
