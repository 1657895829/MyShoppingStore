package com.example.shop.model;

import com.example.shop.bean.DengluBean;

/**
 * 登录model层接口
 */

public interface LoginModelCallBack {
    public void success(DengluBean dengluBean);
    public void failure(Exception e);
}
