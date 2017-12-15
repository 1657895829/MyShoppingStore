package com.example.shop.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.bean.DengluBean;
import com.example.shop.bean.PersonInfoBean;
import com.example.shop.presenter.LoginPresenter;
import com.example.shop.presenter.PersonPresenter;
import com.example.shop.view.LoginViewListener;
import com.example.shop.view.PersonView;
import org.greenrobot.eventbus.EventBus;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//登录页面
public class LoginActivity extends AppCompatActivity implements LoginViewListener,PersonView {
    @Bind(R.id.denglu_zh)
    EditText dengluZh;
    @Bind(R.id.denglu_pwd)
    EditText dengluPwd;
    @Bind(R.id.denglu_btn)
    Button dengluBtn;
    private LoginPresenter presenter;
    private PersonPresenter dataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_lu);
        ButterKnife.bind(this);

        //调用p层
        presenter = new LoginPresenter(this);

        //从presenter调用个人中心的接口
        dataPresenter = new PersonPresenter(this);
        dataPresenter.getData(92);
    }

    @OnClick({R.id.denglu_cha, R.id.zhuce, R.id.denglu_btn})
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

                Toast.makeText(this, "点击登录", Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(dengluZh.getText().toString()) && !TextUtils.isEmpty(dengluPwd.getText().toString())) {

                    dengluBtn.setBackgroundColor(Color.RED);        //信息都不为空时登录按钮为红色

                    //如果都不为空,请求接口
                    presenter.getData(dengluZh.getText().toString(), dengluPwd.getText().toString());
                } else {

                    dengluBtn.setBackgroundColor(Color.LTGRAY);        //信息都为空时登录按钮为浅灰色

                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void success(DengluBean dengluBean) {
        Toast.makeText(this, "登录结果：" + dengluBean.getMsg(), Toast.LENGTH_SHORT).show();

        if (dengluBean.getMsg().equals("登录成功")) {

            //eventbus传值 登录信息
            EventBus.getDefault().post(dengluBean);
            finish();

        }
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, "登录失败！请检查登录信息" + e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failed(int code) {
        Toast.makeText(LoginActivity.this,"个人数据错误："+code,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sucess(PersonInfoBean personInfoBean) {
        //Toast.makeText(getActivity(),"个人数据："+personInfoBean.getMsg()+"            "+personInfoBean.toString(),Toast.LENGTH_SHORT).show();

//        //eventbus传值 个人数据
//        EventBus.getDefault().post(personInfoBean);
//        finish();

    }
}
