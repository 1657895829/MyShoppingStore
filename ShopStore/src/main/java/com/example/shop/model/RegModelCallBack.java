package com.example.shop.model;

import com.example.shop.bean.ZhuceBean;

/**
 * 注册model层接口
 */

public interface RegModelCallBack {
    public void success(ZhuceBean dengluBean);
    public void failure(Exception e);
}
