package com.example.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.adapter.SearchAdapter;
import com.example.shop.bean.SearchBean;
import com.example.shop.presenter.SearchPresenter;
import com.example.shop.view.SearchViewListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//搜索框页面：https://www.zhaoapi.cn/product/searchProducts?keywords=%E7%AC%94%E8%AE%B0%E6%9C%AC&page=1
public class SearchActivity extends AppCompatActivity implements SearchViewListener {
    @Bind(R.id.fanhui)
    ImageView fanhui;
    @Bind(R.id.search_edit)
    EditText searchEdit;
    @Bind(R.id.search_text)
    TextView searchText;
    @Bind(R.id.sousuo_recyview)
    RecyclerView sousuoRecyview;
    private SearchPresenter presenter;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        //实例化p层，获取数据
        presenter = new SearchPresenter(this);

        //设置适配器以及布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sousuoRecyview.setLayoutManager(manager);
        adapter = new SearchAdapter(SearchActivity.this);
    }

    @OnClick({R.id.fanhui, R.id.search_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui:   //点击返回结束当前页面
                Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.search_text: //点击搜索按钮
                presenter.getData(searchEdit.getText().toString());
                break;
        }
    }

    @Override
    public void success(SearchBean searchBean) {
        //添加数据
        if (searchBean != null){
            adapter.addData(searchBean.getData());
            sousuoRecyview.setAdapter(adapter);
        }
    }

    @Override
    public void failure(Exception e) {
        System.out.println("错误："+e);
    }

    @Override
    public void empty() {
        Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void falseEdit() {
        Toast.makeText(this, "请输入手机或笔记本", Toast.LENGTH_SHORT).show();
    }

    //取消绑定，防止内存泄露
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
