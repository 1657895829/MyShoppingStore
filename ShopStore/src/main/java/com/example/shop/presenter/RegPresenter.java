package com.example.shop.presenter;

import com.example.shop.bean.ZhuceBean;
import com.example.shop.model.RegModel;
import com.example.shop.model.RegModelCallBack;
import com.example.shop.view.RegViewListener;

/**
 * 注册Presenter 层
 */

public class RegPresenter {

    RegModel regModel = new RegModel();
    RegViewListener regViewListener;
    public RegPresenter(RegViewListener regViewListener) {
        this.regViewListener = regViewListener;
    }

    public void getData(String zhuce_zh, String zhuce_pwd) {
        regModel.getData(zhuce_zh,zhuce_pwd, new RegModelCallBack() {

            @Override
            public void success(ZhuceBean zhuceBean) {
                regViewListener.success(zhuceBean);
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }
}
