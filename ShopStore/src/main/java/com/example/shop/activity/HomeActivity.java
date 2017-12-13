package com.example.shop.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import com.example.shop.R;
import com.example.shop.fragment.FenClassFragment;
import com.example.shop.fragment.HomeFragment;
import com.example.shop.fragment.HotFragment;
import com.example.shop.fragment.MineFragment;
import com.example.shop.fragment.ShoppingBikeFragment;
import com.hjm.bottomtabbar.BottomTabBar;
import butterknife.Bind;
import butterknife.ButterKnife;

//5个Fragment页面分类显示
public class HomeActivity extends AppCompatActivity {
    @Bind(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //初始化Fragment
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)      //图片大小
                .setFontSize(12)                       //字体大小
                .setTabPadding(4, 6, 10)//选项卡的间距
                .setChangeColor(Color.RED, Color.BLACK)     //选项卡的选择颜色
                .addTabItem("首页", R.mipmap.icon_home, HomeFragment.class)
                .addTabItem("分类", R.mipmap.icon_discover, FenClassFragment.class)
                .addTabItem("热点", R.mipmap.icon_hot, HotFragment.class)
                .addTabItem("购物车", R.mipmap.icon_cart, ShoppingBikeFragment.class)
                .addTabItem("我的", R.mipmap.icon_user, MineFragment.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);
                    }
                });
    }

}
