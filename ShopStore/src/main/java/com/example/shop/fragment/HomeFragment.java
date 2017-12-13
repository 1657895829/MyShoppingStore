package com.example.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shop.R;
import com.example.shop.activity.LoginActivity;
import com.example.shop.activity.SearchActivity;
import com.example.shop.adapter.HomeXRecyclerViewAdapter;
import com.example.shop.bean.DengluBean;
import com.example.shop.bean.ShouyeLunBoBean;
import com.example.shop.presenter.MyPresenter1;
import com.example.shop.view.LoginViewListener;
import com.example.shop.view.ViewCallBack1;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//首页fragment
public class HomeFragment extends Fragment implements ViewCallBack1,LoginViewListener {
    @Bind(R.id.homeRecycylerView)
    XRecyclerView homeRecycylerView;
    private HomeXRecyclerViewAdapter homeAdapter;
    private MyPresenter1 presenter1;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //调用P层
        homeAdapter = new HomeXRecyclerViewAdapter(getActivity(), getChildFragmentManager());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        homeRecycylerView.setLayoutManager(manager);
        presenter1 = new MyPresenter1(this);
        presenter1.getLunBo();//轮播图的展示

        //XRecyclerview的上拉下拉方法
        homeRecycylerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        homeAdapter.notifyDataSetChanged();
                        homeRecycylerView.refreshComplete();
                    }
                }, 888);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        homeAdapter.notifyDataSetChanged();
                        homeRecycylerView.loadMoreComplete();
                    }
                }, 888);
            }
        });
    }

    @Override
    public void success(ShouyeLunBoBean shouyeLunBoBean) {
        List<ShouyeLunBoBean.DataBean> listIMage = shouyeLunBoBean.getData();

        //添加数据
        homeAdapter.addTuijian(shouyeLunBoBean.getTuijian());
        homeAdapter.addMiaosha(shouyeLunBoBean.getMiaosha());

        //将集合传给适配器
        homeAdapter.addLunbo(listIMage);
        homeRecycylerView.setAdapter(homeAdapter);
    }

    @Override
    public void success(final DengluBean dengluBean) {

    }

    @Override
    public void failure(Exception e) {
        System.out.println("数据出错：" + e);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_sao, R.id.iv_sou,R.id.info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sao:
                //直接跳转到依赖包里面   开始扫描
                Intent intent1 = new Intent(getActivity(), CaptureActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_sou:
                //点击搜索  跳转至搜索页面
                Intent intent2 = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent2);
                break;
            case R.id.info:
                //点击消息  跳转至登录账号页面
                Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent3);
                break;
        }
    }

}
