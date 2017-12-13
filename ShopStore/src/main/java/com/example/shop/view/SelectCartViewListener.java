package com.example.shop.view;

import com.example.shop.bean.SelectCartBean;

public interface SelectCartViewListener {
    public void success(SelectCartBean selectCartBean);
    public void failure();

}
