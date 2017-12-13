package com.example.shop.presenter;

import com.example.shop.bean.ShouyeLunBoBean;
import com.example.shop.model.ModelCallBack1;
import com.example.shop.model.MyModel1;
import com.example.shop.view.ViewCallBack1;

/**
 *
 */

public class MyPresenter1 {

    MyModel1 myModel1 = new MyModel1();
    ViewCallBack1 viewCallBack1;

    public MyPresenter1(ViewCallBack1 viewCallBack1) {
        this.viewCallBack1 = viewCallBack1;
    }


    //请求轮播图数据
    public void getLunBo(){
       //调用model层的方法 请求网络数据
        myModel1.getLunBo(new ModelCallBack1() {
            @Override
            public void success(ShouyeLunBoBean shouyeLunBoBean) {
                viewCallBack1.success(shouyeLunBoBean);
            }

            @Override
            public void failure(Exception e) {
                viewCallBack1.failure(e);
            }
        });
    }
}
