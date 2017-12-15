package com.example.shop.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shop.R;
import com.example.shop.bean.ZhuceBean;
import com.example.shop.presenter.RegPresenter;
import com.example.shop.view.RegViewListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册页面
public class RegActivity extends AppCompatActivity implements RegViewListener{
    @Bind(R.id.zhuce_cha)
    TextView zhuceCha;
    @Bind(R.id.zhuce_zh)
    EditText zhuceZh;
    @Bind(R.id.zhuce_pwd)
    EditText zhucePwd;
    @Bind(R.id.zhuce_btn)
    Button zhuceBtn;
    private RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        ButterKnife.bind(this);

        //调用p层
        presenter = new RegPresenter(this);
    }

    @OnClick({R.id.zhuce_cha, R.id.zhuce_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce_cha:
                finish();
                break;
            case R.id.zhuce_btn:
                //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
                if (!TextUtils.isEmpty(zhuceZh.getText().toString()) && !TextUtils.isEmpty(zhucePwd.getText().toString())) {

                    zhuceBtn.setBackgroundColor(Color.RED);        //信息都不为空时登录按钮为红色

                    // mvp请求注册的接口
                    presenter.getData(zhuceZh.getText().toString(),zhucePwd.getText().toString());

                } else {

                    zhuceBtn.setBackgroundColor(Color.LTGRAY);        //信息都为空时登录按钮为浅灰色

                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void success(ZhuceBean zhuceBean) {
        Toast.makeText(this, "注册结果：      "+zhuceBean.getMsg(), Toast.LENGTH_SHORT).show();

        if(zhuceBean.getMsg().equals("天呢！用户已注册")){
            try {
                Thread.sleep(2000);
                Toast.makeText(this, "即将跳转到登录页面", Toast.LENGTH_SHORT).show();
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, "注册失败！请检查网络"+ e, Toast.LENGTH_SHORT).show();
    }
}
