package com.example.shop.model;

import com.example.shop.bean.SelectCartBean;

public interface SelectCartModelCallBack {
    public void success(SelectCartBean selectCartBean);
    public void failure();

}
