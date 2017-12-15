package com.example.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.shop.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//热点fragment
public class HotFragment extends Fragment {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.hot_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置标题
        list = new ArrayList<>();
        list.add("头条");
        list.add("社会");
        list.add("国内");
        list.add("国际");
        list.add("娱乐");
        list.add("军事");
        list.add("财经");
        list.add("时尚");
        list.add("科技");
        list.add("体育");
        viewPager.setOffscreenPageLimit(list.size());

        //设置使用ViewPager+Fragment显示新闻列表数据布局的适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            //得到当前页的标题,,,也就是设置当前页面显示的标题是tabLayout对应的标题
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                //在这个位置对比一下标题是什么,,,然后返回对应的fragment
                Hot_Child_Fragment hot_child_fragment = new Hot_Child_Fragment();

                //判断所选的标题，进行传值显示（下面注释的代码可简写为下面一行代码）
                Bundle bundle = new Bundle();
                bundle.putString("name", list.get(position));
                if (list.get(position).equals("头条")) {
                    bundle.putString("name", "top");
                } else if (list.get(position).equals("社会")) {
                    bundle.putString("name", "shehui");
                } else if (list.get(position).equals("国内")) {
                    bundle.putString("name", "guonei");
                } else if (list.get(position).equals("国际")) {
                    bundle.putString("name", "guoji");
                } else if (list.get(position).equals("娱乐")) {
                    bundle.putString("name", "yule");
                } else if (list.get(position).equals("军事")) {
                    bundle.putString("name", "junshi");
                } else if (list.get(position).equals("科技")) {
                    bundle.putString("name", "keji");
                } else if (list.get(position).equals("财经")) {
                    bundle.putString("name", "caijing");
                } else if (list.get(position).equals("时尚")) {
                    bundle.putString("name", "shishang");
                } else if (list.get(position).equals("体育")) {
                    bundle.putString("name", "tiyu");
                }
                hot_child_fragment.setArguments(bundle);
                return hot_child_fragment;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        //TabLyout要与ViewPager关联显示
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //扫描二维码
    @OnClick(R.id.fl_sys)
    public void onViewClicked() {
        //直接跳转到依赖包里面   开始扫描
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivity(intent);
    }
}
