package com.example.shop.model;

import com.example.shop.bean.ShouyeGridBean;

/**
 * model层九宫格的接口
 */
public interface ModelCallBack2 {

    public void success(ShouyeGridBean shouyeGridBean);
    public void failure(Exception e);
}
