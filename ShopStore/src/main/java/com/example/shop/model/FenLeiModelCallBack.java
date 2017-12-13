package com.example.shop.model;

import com.example.shop.bean.ChildBean;
import com.example.shop.bean.LeftBean;
import com.example.shop.bean.RightBean;
import java.util.List;

/**
 * 分类页面model层接口类
 */
public interface FenLeiModelCallBack {
    public void LeftCallBack(List<LeftBean.DataBean> leftCallBack );//z左侧成功
    public void RightCallBack(List<RightBean.DataBean> rightCallBack ); //右侧成功

    //子类数据成功
    public void ChildCallBack(List<ChildBean.DataBean> childCallBack);
}
