package com.example.shop.model;

import com.example.shop.bean.ChildBean;
import com.example.shop.bean.LeftBean;
import com.example.shop.bean.RightBean;
import com.example.shop.okhttp.OkHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

/**
 * 分类页面model层实现类
 */
public class FenLeiModel {
    //左边请求接口获取分类数据
    public void getLeftModel(final FenLeiModelCallBack callBack){
        OkHttp.getAsync("https://www.zhaoapi.cn/product/getCatagory", new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                System.out.println("错误："+e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                //获取返回数据
                LeftBean leftBean = new Gson().fromJson(result, LeftBean.class);
                List<LeftBean.DataBean> data = leftBean.getData();
                callBack.LeftCallBack(data);
                System.out.println("Model层左边数据成功返回"+data.toString());
            }
        });
    }

    //右边请求接口获取分类数据,根据cid页数获取数据
    public void getRightModel(int count,final FenLeiModelCallBack callBack){
        OkHttp.getAsync("https://www.zhaoapi.cn/product/getProductCatagory?cid="+count, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                System.out.println("错误："+e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                //获取返回数据
                RightBean rightBean = new Gson().fromJson(result, RightBean.class);
                List<RightBean.DataBean> data = rightBean.getData();
                callBack.RightCallBack(data);
                System.out.println("Model层右边数据成功返回"+data.toString());
            }
        });
    }

    //子分类下面的列表
    public void getChildModel(int count, final FenLeiModelCallBack callBack){
        OkHttp.getAsync("https://www.zhaoapi.cn/product/getProducts?pscid=" + count, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                System.out.println("错误："+e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                //获取返回数据
                ChildBean childBean = new Gson().fromJson(result, ChildBean.class);
                List<ChildBean.DataBean> data = childBean.getData();
                callBack.ChildCallBack(data);
                System.out.println("子分类数据成功返回"+data.toString());
            }
        });
    }
}
