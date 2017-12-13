package com.example.shop.model;

import com.example.shop.bean.SearchBean;
import com.example.shop.retrofit.GetDataInterface;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  搜索框model层实现类
 *  https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
 */
public class SearchModel {
    //获取数据方法
    public void getEditData(String edit, final SearchModelCallBack callBack){
        //使用Retrofit结合RxJava，okhttp封装类的单例模式,集合传参
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetDataInterface request = retrofit.create(GetDataInterface.class);
        final Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("keywords",edit);
        map.put("page","1");
        request.getEdit(map).enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean searchBean = response.body();
                callBack.success(searchBean);
                System.out.println("搜索model搜索数据："+searchBean.toString()+map.toString());
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {
                callBack.failure(new Exception(""));
            }
        });
    }
}
