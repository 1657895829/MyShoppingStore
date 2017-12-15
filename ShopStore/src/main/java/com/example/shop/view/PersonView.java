package com.example.shop.view;

import com.example.shop.bean.PersonInfoBean;

/**
 * 个人页面
 * MVP的三层编写步骤：
 * 2. View 层 数据展示类
 * View – 用户界面：View通常是指Activity、Fragment或者某个View控件，它含有一个Presenter成员变量。
   通常View需要实现一个逻辑接口，将View上的操作转交给Presenter进行实现，最后，Presenter 调用View逻辑接口将结果返回给View元素。
 */
public interface PersonView {

    void failed(int code);
    void sucess(PersonInfoBean personInfoBean);

}
