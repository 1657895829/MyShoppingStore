package com.example.shop.presenter;

import com.example.shop.bean.ShouyeGridBean;
import com.example.shop.model.ModelCallBack2;
import com.example.shop.model.MyModel2;
import com.example.shop.view.ViewCallBack2;

/**
 *
 */

public class MyPresenter2 {

    MyModel2 myModel2 = new MyModel2();
    ViewCallBack2 viewCallBack2;

    public MyPresenter2(ViewCallBack2 viewCallBack2) {
        this.viewCallBack2 = viewCallBack2;
    }


    //请求轮播图数据
    public void getGrid(){
       //调用model层的方法 请求网络数据
        myModel2.getGrid(new ModelCallBack2() {

            @Override
            public void success(ShouyeGridBean shouyeGridBean) {
                viewCallBack2.success(shouyeGridBean);
            }

            @Override
            public void failure(Exception e) {
                viewCallBack2.failure(e);
            }
        });
    }
}
