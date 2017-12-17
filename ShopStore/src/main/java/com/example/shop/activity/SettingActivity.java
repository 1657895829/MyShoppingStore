package com.example.shop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.shop.MainActivity;
import com.example.shop.R;
import com.example.shop.util.ActivityManager;
import java.io.File;
import butterknife.ButterKnife;
import butterknife.OnClick;

//设置页面
public class SettingActivity extends AppCompatActivity {
//    @Bind(R.id.mobile)
//    TextView mobile;
    private SharedPreferences config;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        config = getSharedPreferences("config", 0);
        edit = config.edit();

        //将当前的Activity添加到ActivityManager中
        ActivityManager.getInstance().add(this);

        //接收传值,设置电话号码
        Intent intent = getIntent();
        String tel = intent.getStringExtra("username");
        //mobile.setText(tel);
    }

    @OnClick({R.id.back, R.id.outLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:   //点击叉号，返回上一页面
                finish();
                break;
            case R.id.outLogin: //退出登录

                //logout(view);

                //将uid存值为null
                edit.putString("uid", null).commit();

                finish();
                break;
        }
    }


    //"退出登录"button的回调方法,退出回到主页面，不是 我的fragment
    public void logout(View view) {
        //1、将保存在sp中的数据删除
        SharedPreferences sp = this.getSharedPreferences("config", 0);
        sp.edit().clear().commit();//清除数据必须要提交:提交以后，文件仍存在，只是文件中的数据被清除了
        //2、将本地保存的图片的file删除
        File filesDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//判断sd卡是否挂载
            //路径1：storage/sdcard/Android/data/包名/files
            filesDir = this.getExternalFilesDir("");
        } else {//手机内部存储
            //路径：data/data/包名/files
            filesDir = this.getFilesDir();
        }

        //3、销毁所有的Activity
        this.removeAll();
        //4、重新进入首页面
        goToActivity(MainActivity.class, null);
    }

    //启动新的Activity
    public void goToActivity(Class Activity, Bundle bundle) {
        Intent intent = new Intent(this, Activity);
        if (bundle != null && bundle.size() != 0) {
            intent.putExtra("data", bundle);
        }
        startActivity(intent);
    }

    //销毁当前的Activity
    public void removeCurrentActivity() {
        ActivityManager.getInstance().removeCurrent();
    }

    //销毁所有的Activity
    public void removeAll() {
        ActivityManager.getInstance().removeAll();
    }
}

