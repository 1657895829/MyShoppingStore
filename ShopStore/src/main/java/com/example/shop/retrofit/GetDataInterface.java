package com.example.shop.retrofit;

import com.example.shop.bean.AddCartBean;
import com.example.shop.bean.DeleteBean;
import com.example.shop.bean.DengluBean;
import com.example.shop.bean.PersonInfoBean;
import com.example.shop.bean.SearchBean;
import com.example.shop.bean.SelectCartBean;
import com.example.shop.bean.ZhuceBean;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 网络接口数据的请求类
 */
public interface GetDataInterface {
    //搜索 笔记本 手机的接口,集合传参
    //https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
    @GET("/product/searchProducts")
    Call<SearchBean> getEdit(@QueryMap Map<String,String> map);

     /*
     * 加入购物车
     *  https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
     *  uid": 1650
     *  "token": "2FC3EF31EA25696D2715A971ADE38DE1"
     */
    @GET("product/addCart")
    Call<AddCartBean> addCart(@QueryMap Map<String,String> map);

    //https://www.zhaoapi.cn/product/getCarts?source=android&uid=1650&token=2FC3EF31EA25696D2715A971ADE38DE1
    //uid": 1650,
    // "token": "2FC3EF31EA25696D2715A971ADE38DE1",
    @GET("product/getCarts")
    Call<SelectCartBean> selectCart(@QueryMap Map<String,String> map);

    //删除
    //https://www.zhaoapi.cn/product/deleteCart?uid=1650&pid=58
    @GET("/product/deleteCart")
    Call<DeleteBean> deleteCart(@QueryMap Map<String,String> map);

    //注册的接口
    //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
    @FormUrlEncoded
    @POST("/user/reg")
    Call<ZhuceBean> zhuce(@FieldMap Map<String,String> map);

    //登录的接口
    //https://www.zhaoapi.cn/user/login?mobile=15810680959&password=123456
    @FormUrlEncoded
    @POST("/user/login")
    Call<DengluBean> denglu(@FieldMap Map<String,String> map);

    //个人中心接口：
    //https://www.zhaoapi.cn/user/getUserInfo?uid=71
    @GET("/user/getUserInfo")
    Observable<PersonInfoBean> person(@Query("uid") int uid);

}
