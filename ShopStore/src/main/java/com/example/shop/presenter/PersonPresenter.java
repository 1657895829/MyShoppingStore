package com.example.shop.presenter;

import com.example.shop.bean.PersonInfoBean;
import com.example.shop.model.PersonModel;
import com.example.shop.model.PersonModelCallBack;
import com.example.shop.view.PersonView;

/**
 * 个人p层
 */
public class PersonPresenter {
    PersonModel personModel = new PersonModel();
    PersonView personView;
    public PersonPresenter(PersonView personView) {
        this.personView = personView;
    }

    public void getData(int uid) {
        personModel.getPersonData(uid, new PersonModelCallBack() {
            @Override
            public void success(PersonInfoBean personInfoBean) {
                personView.sucess(personInfoBean);
                System.out.println("个人p数据："+personInfoBean.toString());
            }

            @Override
            public void failed(Throwable code) {
                System.out.println("个人p错误："+code);
            }
        });
    }
}
