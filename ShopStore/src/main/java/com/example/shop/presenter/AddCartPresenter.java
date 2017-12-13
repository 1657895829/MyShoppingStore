package com.example.shop.presenter;

import com.example.shop.bean.AddCartBean;
import com.example.shop.model.AddCartModel;
import com.example.shop.model.AddCartModelCallBack;
import com.example.shop.view.AddCartViewListener;

/**
 * 加入购物车 p 层
 */

public class AddCartPresenter {
    private AddCartViewListener viewListener;
    private AddCartModel model;

    public AddCartPresenter(AddCartViewListener viewListener) {
        this.viewListener = viewListener;
        this.model = new AddCartModel();
    }

    public void getData(String pid){
        model.getData(pid, new AddCartModelCallBack() {
            @Override
            public void success(AddCartBean addCartBean) {
                viewListener.success(addCartBean);
            }

            @Override
            public void failure(Exception e) {
                viewListener.failure(e);
            }
        });
    }
}
