package com.example.shop.view;

import com.example.shop.bean.ShouyeGridBean;

/**
 * 九宫格的接口
 */

public interface ViewCallBack2 {
    public void success(ShouyeGridBean shouyeGridBean);
    public void failure(Exception e);
}
