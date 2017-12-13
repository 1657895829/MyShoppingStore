package com.example.shop.presenter;

import com.example.shop.bean.SelectCartBean;
import com.example.shop.model.SelectCartModel;
import com.example.shop.model.SelectCartModelCallBack;
import com.example.shop.view.SelectCartViewListener;

/**
 * 查询购物车p层
 */

public class SelectCartPresenter {
    SelectCartModel selectCartModel = new SelectCartModel();

    SelectCartViewListener cartViewListener;
    public SelectCartPresenter(SelectCartViewListener cartViewListener) {
        this.cartViewListener = cartViewListener;
    }
    //调用model 层的请求数据
    public void getData(){
        selectCartModel.getData(new SelectCartModelCallBack() {
            @Override
            public void success(SelectCartBean selectCartBean) {
                if(cartViewListener!=null) {
                    cartViewListener.success(selectCartBean);
                }
            }

            @Override
            public void failure() {
                if(cartViewListener!=null) {
                    cartViewListener.failure();
                }
            }
        });
    }

    /**
     * 防止内存泄露
     * */
    public void detach(){
        cartViewListener = null;
    }


}
