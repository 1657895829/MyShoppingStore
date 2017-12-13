package com.example.shop.presenter;

import android.text.TextUtils;
import com.example.shop.bean.SearchBean;
import com.example.shop.model.SearchModel;
import com.example.shop.model.SearchModelCallBack;
import com.example.shop.view.SearchViewListener;

//搜索框p层
public class SearchPresenter {
    private SearchViewListener listener;
    private SearchModel searchModel;
    public SearchPresenter(SearchViewListener listener){
        this.listener = listener ;
        this.searchModel = new SearchModel();
    }

    //搜索数据
    public void getData(String edit){
        //判断输入为空情况下
        if (TextUtils.isEmpty(edit) || edit.length() == 0){
            listener.empty();
            return;
        }
        //判断输入错误的情况
        if(!edit.equals("笔记本") && !edit.equals("手机")){
            listener.falseEdit();
            return;
        }

        searchModel.getEditData(edit, new SearchModelCallBack() {
            @Override
            public void success(SearchBean searchBean) {
                listener.success(searchBean);
                System.out.println("搜索presenter搜索数据："+searchBean.toString());
            }

            @Override
            public void failure(Exception e) {
                listener.failure(e);
            }
        });

    }

    /**
     * 防止内存泄漏
     */
    public void detach(){
        listener = null;
    }
}
