package com.example.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.bean.DengluBean;
import com.example.shop.presenter.LoginPresenter;
import com.example.shop.view.LoginViewListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登录页面
public class LoginActivity extends AppCompatActivity implements LoginViewListener {
    @Bind(R.id.denglu_zh)
    EditText dengluZh;
    @Bind(R.id.denglu_pwd)
    EditText dengluPwd;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu);
        ButterKnife.bind(this);

        //调用p层
        presenter = new LoginPresenter(this);
    }

    @OnClick({R.id.denglu_cha,R.id.zhuce,R.id.denglu_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.denglu_cha:
                //返回到之前的fragment
                finish();
                break;
            case R.id.zhuce:
                // 手机快速注册按钮，跳转至注册页面
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;

            case R.id.denglu_btn:
                System.out.println("点击登录");
                Toast.makeText(this, "点击登录", Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(dengluZh.getText().toString()) && !TextUtils.isEmpty(dengluPwd.getText().toString())) {
                    //如果都不为空,请求接口
                    presenter.getData(dengluZh.getText().toString(),dengluPwd.getText().toString());
                } else {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void success(DengluBean dengluBean) {
        Toast.makeText(this, "登录结果：" + dengluBean.getMsg(), Toast.LENGTH_SHORT).show();

        if(dengluBean.getMsg().equals("登录成功")){
            try {
                Thread.sleep(1000);
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, "登录失败！请检查登录信息" + e, Toast.LENGTH_SHORT).show();
    }
}
