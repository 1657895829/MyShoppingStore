package com.example.shop.model;

import com.example.shop.bean.DeleteBean;

public interface DeleteCartModelCallBack {
    public void success(DeleteBean deleteBean);
    public void failure();
}
