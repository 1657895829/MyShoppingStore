package com.example.shop.model;


import com.example.shop.bean.ShouyeLunBoBean;

/**
 * model层轮播图的接口
 */

public interface ModelCallBack1 {

    public void success(ShouyeLunBoBean shouyeLunBoBean);
    public void failure(Exception e);
}
