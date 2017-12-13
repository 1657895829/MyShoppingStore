package com.example.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.shop.R;
import com.example.shop.activity.SearchActivity;
import com.example.shop.activity.WebUrlActivity;
import com.example.shop.adapter.FenLei_Left_Adapter;
import com.example.shop.adapter.FenLei_Right_Adapter;
import com.example.shop.bean.ChildBean;
import com.example.shop.bean.LeftBean;
import com.example.shop.bean.RightBean;
import com.example.shop.presenter.FenLeiPresenter;
import com.example.shop.util.DividerGridltemDecoration;
import com.example.shop.util.Glide_ImageLoader_Util;
import com.example.shop.view.FenLeiViewListener;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//分类fragment
public class FenClassFragment extends Fragment implements FenLeiViewListener {
    @Bind(R.id.fl_zuorv)
    RecyclerView flZuorv;
    @Bind(R.id.fl_yourv)
    ExpandableListView flYourv;
    @Bind(R.id.fl_sys)
    ImageView flSys;
    @Bind(R.id.banner)
    Banner banner;
    private FenLeiPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = View.inflate(getActivity(), R.layout.fenclass_fragment, null);
        ButterKnife.bind(this, view);

        //调用P层,显示数据
        presenter = new FenLeiPresenter(this);
        presenter.getChildData(1);
        presenter.getRightData(1);
        presenter.getLeftData();

        //点击扫描
        flSys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接跳转到依赖包里面   开始扫描
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    //左侧请求
    @Override
    public void ShowLeftData(final List<LeftBean.DataBean> leftList) {
        //设置左边数据线性布局管理器以及适配器
        flZuorv.setLayoutManager(new LinearLayoutManager(getActivity()));
        FenLei_Left_Adapter leftAdapter = new FenLei_Left_Adapter(getActivity(), leftList);
        flZuorv.setAdapter(leftAdapter);

        //分割线
        flZuorv.addItemDecoration(new DividerGridltemDecoration(getActivity()));

        //回调调用条目点击事件
        leftAdapter.setOnItemClickListener(new FenLei_Left_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //设置数据页数
                int cid = leftList.get(position).getCid();
                presenter.getRightData(cid);
            }
        });
    }

    @Override
    public void ShowRightData(List<RightBean.DataBean> rightList) {
        //设置右边数据线性布局管理器以及适配器
        FenLei_Right_Adapter rightAdapter = new FenLei_Right_Adapter(getActivity(), rightList);
        flYourv.setAdapter(rightAdapter);

        //父级列表默认全部展开
        int count = flYourv.getCount();
        for (int i = 0; i < count; i++) {
            flYourv.expandGroup(i);
        }
    }

    @Override
    public void ShowChildData(final List<ChildBean.DataBean> childList) {
        //设置无限轮播图
        List<String> listLunbo = new ArrayList<>();
        for (int i = 0; i < childList.size(); i++) {
            //加载图片
            if (childList.size() > 0) {
                if (childList.get(i).getImages().contains("|")) {
                    String[] split = childList.get(i).getImages().split("\\|");
                    for (int y = 0; i < split.length; i++) {
                        listLunbo.add(split[i]);
                    }
                } else {
                    listLunbo.add(childList.get(i).getImages());
                }
            }
        }

        //设置图片自动轮播
        banner.isAutoPlay(true);
        //将图片集合放入banner中，加载图片
        banner.setImages(listLunbo);
        //设置图片每 3 秒切换
        banner.setDelayTime(3000);
        //加载图片
        banner.setImageLoader(new Glide_ImageLoader_Util());

        //banner页面的点击事件
        for (int i = 0; i < childList.size(); i++) {
            final int position = i;
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(getActivity(), WebUrlActivity.class);
                    intent.putExtra("url", childList.get(position).getDetailUrl());
                    startActivity(intent);
                }
            });
        }
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //重写p层解绑的方法，防止内存泄露
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    //搜索跳转
    @OnClick(R.id.edit_query)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        getActivity().startActivity(intent);
    }
}
