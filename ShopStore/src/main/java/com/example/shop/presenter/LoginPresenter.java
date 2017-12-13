package com.example.shop.presenter;

import com.example.shop.bean.DengluBean;
import com.example.shop.model.LoginModel;
import com.example.shop.model.LoginModelCallBack;
import com.example.shop.view.LoginViewListener;

/**
 * 登录Presenter 层
 */

public class LoginPresenter {
    LoginModel loginModel = new LoginModel();
    LoginViewListener loginViewListener;
    public LoginPresenter(LoginViewListener loginViewListener) {
        this.loginViewListener = loginViewListener;
    }

    public void getData(String denglu_zh, String denglu_pwd) {
        loginModel.getData(denglu_zh,denglu_pwd, new LoginModelCallBack() {
            @Override
            public void success(DengluBean dengluBean) {
                loginViewListener.success(dengluBean);
                System.out.println("登录p数据："+dengluBean.toString());
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }
}
