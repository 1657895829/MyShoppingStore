package com.example.shop.presenter;

import com.example.shop.bean.ChildBean;
import com.example.shop.bean.LeftBean;
import com.example.shop.bean.RightBean;
import com.example.shop.model.FenLeiModel;
import com.example.shop.model.FenLeiModelCallBack;
import com.example.shop.view.FenLeiViewListener;
import java.util.List;

/**
 * 分类页面presenter层
 */

public class FenLeiPresenter {
    private FenLeiViewListener fenLeiView;
    private FenLeiModel fenLeiModel;

    public FenLeiPresenter(FenLeiViewListener fenLeiView) {
        this.fenLeiView = fenLeiView;
        this.fenLeiModel = new FenLeiModel();
    }

    //获取左边数据的方法
    public void getLeftData(){
        fenLeiModel.getLeftModel(new FenLeiModelCallBack() {
            @Override
            public void LeftCallBack(List<LeftBean.DataBean> leftCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowLeftData(leftCallBack);
                    System.out.println("Presenter层左边数据成功返回"+leftCallBack.toString());
                }
            }

            @Override
            public void RightCallBack(List<RightBean.DataBean> rightCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowRightData(rightCallBack);
                    System.out.println("Presenter层右边数据成功返回"+rightCallBack.toString());
                }
            }

            @Override
            public void ChildCallBack(List<ChildBean.DataBean> childCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowChildData(childCallBack);
                }
            }
        });
    }

    //获取右边数据的方法
    public void getRightData(int count){
        fenLeiModel.getRightModel(count, new FenLeiModelCallBack() {
            @Override
            public void LeftCallBack(List<LeftBean.DataBean> leftCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowLeftData(leftCallBack);
                    System.out.println("Presenter层左边数据成功返回"+leftCallBack.toString());
                }
            }

            @Override
            public void RightCallBack(List<RightBean.DataBean> rightCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowRightData(rightCallBack);
                    System.out.println("Presenter层右边数据成功返回"+rightCallBack.toString());
                }
            }

            @Override
            public void ChildCallBack(List<ChildBean.DataBean> childCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowChildData(childCallBack);
                }
            }
        });
    }

    //获取右边数据的方法
    public void getChildData(int count){
        fenLeiModel.getChildModel(count, new FenLeiModelCallBack() {
            @Override
            public void LeftCallBack(List<LeftBean.DataBean> leftCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowLeftData(leftCallBack);
                    System.out.println("Presenter层左边数据成功返回"+leftCallBack.toString());
                }
            }

            @Override
            public void RightCallBack(List<RightBean.DataBean> rightCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowRightData(rightCallBack);
                    System.out.println("Presenter层右边数据成功返回"+rightCallBack.toString());
                }
            }

            @Override
            public void ChildCallBack(List<ChildBean.DataBean> childCallBack) {
                if (fenLeiView != null){
                    fenLeiView.ShowChildData(childCallBack);
                    System.out.println("Presenter层子分类数据成功返回"+childCallBack.toString());
                }
            }
        });
    }

    //取消绑定，防止内存泄漏
    public void detach(){
        fenLeiView = null;
    }
}
