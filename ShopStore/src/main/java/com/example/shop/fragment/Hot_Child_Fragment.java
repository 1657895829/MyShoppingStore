package com.example.shop.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.adapter.HotChildAdapter;
import com.example.shop.bean.NewsBean;
import com.example.shop.util.NetConnectionUtil;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 接收传值的Fragment
 */
public class Hot_Child_Fragment extends Fragment {
    private List<NewsBean.ResultBean.DataBean> list = new ArrayList<>();
    @Bind(R.id.hot_child_XRecyclerView)
    XRecyclerView hotChildXRecyclerView;
    private HotChildAdapter adapter;
    private Handler handler = new Handler();
    private String encode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.hot_child_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取传递的标题数据，把获取的标题转码实现
        Bundle bundle = getArguments();
        String data = bundle.getString("name", "top");
        encode = URLEncoder.encode(data);

        //判断网络状态，异步加载数据
        if (NetConnectionUtil.isNetConnectioned(getActivity())){
            getData(data);
        }else{
            NetConnectionUtil.setNetConnectionWork(getActivity());
        }

        //XRecyclerview的上拉下拉方法
        hotChildXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        getData(encode);
                        adapter.notifyDataSetChanged();
                        hotChildXRecyclerView.refreshComplete();
                    }
                }, 888);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        getData(encode);
                        adapter.notifyDataSetChanged();
                        hotChildXRecyclerView.loadMoreComplete();
                    }
                }, 888);
            }
        });
    }

    //请求网络数据的方法
    public void getData(final String encode) {
        //使用OKhttp请求网络数据
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                // http://v.juhe.cn/toutiao/index?type=top&key=2f092bd9ce76c0257052d6d3c93c11b4
                .url("http://v.juhe.cn/toutiao/index?type="+encode+"&key=2f092bd9ce76c0257052d6d3c93c11b4")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(), "数据出错："+e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                System.out.println("返回：" + result);

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //子线程内成功的回调
                        NewsBean bean = new Gson().fromJson(result, NewsBean.class);
                        list = bean.getResult().getData();
                        System.out.println("标题：" + list.get(0).getTitle());

                        //设置布局管理器以及布局适配器
                        if (adapter == null) {
                            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            hotChildXRecyclerView.setLayoutManager(manager);
                            adapter = new HotChildAdapter(getActivity(),list);
                            hotChildXRecyclerView.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
