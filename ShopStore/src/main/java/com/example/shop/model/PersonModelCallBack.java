package com.example.shop.model;

import com.example.shop.bean.PersonInfoBean;

/**
 * 个人中心
 * MVP的三层编写步骤：
 * 1. 先写model层接口类，进行数据的存取
 */

public interface PersonModelCallBack {
    void success(PersonInfoBean personBean);
    void failed(Throwable code);

}
