package com.example.shop.view;

import com.example.shop.bean.DengluBean;

/**
 * 登录view层
 */

public interface LoginViewListener {
    public void success(DengluBean dengluBean);
    public void failure(Exception e);
}
