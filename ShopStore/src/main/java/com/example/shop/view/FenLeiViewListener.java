package com.example.shop.view;

import com.example.shop.bean.ChildBean;
import com.example.shop.bean.LeftBean;
import com.example.shop.bean.RightBean;
import java.util.List;

/**
 * 分类页面的view接口
 */
public interface FenLeiViewListener {
    public void ShowLeftData(List<LeftBean.DataBean>  leftList);
    public void ShowRightData(List<RightBean.DataBean>  rightList);
    //子类数据成功
    public void ShowChildData(List<ChildBean.DataBean> childList);
}
