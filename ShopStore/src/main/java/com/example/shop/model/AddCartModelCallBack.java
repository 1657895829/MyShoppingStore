package com.example.shop.model;

import com.example.shop.bean.AddCartBean;

/**
 * 加入购物车model层接口
 */
public interface AddCartModelCallBack {
    public void success(AddCartBean addCartBean);
    public void failure(Exception e);
}
