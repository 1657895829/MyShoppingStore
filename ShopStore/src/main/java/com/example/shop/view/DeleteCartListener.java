package com.example.shop.view;


import com.example.shop.bean.DeleteBean;

public interface DeleteCartListener {
    public void success(DeleteBean deleteBean);
    public void failure();
}
