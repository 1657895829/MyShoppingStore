package com.example.shop.view;

import com.example.shop.bean.ZhuceBean;

/**
 * 注册view层
 */

public interface RegViewListener {
    public void success(ZhuceBean zhuceBean);
    public void failure(Exception e);
}
