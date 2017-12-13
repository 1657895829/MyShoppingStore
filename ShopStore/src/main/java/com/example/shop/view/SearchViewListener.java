package com.example.shop.view;

import com.example.shop.bean.SearchBean;

/**
 * 搜索框view层接口
 */

public interface SearchViewListener {
    public void success(SearchBean searchBean); //请求成功数据
    public void failure(Exception e);           //请求数据失败

    public void empty();                        //判断是否为空
    public void falseEdit();                    //输入错误
}
