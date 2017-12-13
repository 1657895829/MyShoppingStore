package com.example.shop;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.example.shop.activity.HomeActivity;
import com.example.shop.shared.SharedUtils;
import java.util.Timer;
import java.util.TimerTask;
//初始页面
public class MainActivity extends Activity {
    private TextView timetext;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Intent intent1 = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent1);
                    finish();
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timetext = (TextView) findViewById(R.id.time);      //查找控件

        //调用工具类判断保存的布尔值
        boolean b = SharedUtils.getBooleanData(MainActivity.this, "flag", false);

        if (b) {	//已经进入过，现在是第二次，进入fragment页面
            handler.sendEmptyMessageDelayed(0, 0);
        }else{		//现在是第一次，进入导航页面
            SharedUtils.savaBooleanData(MainActivity.this, "flag", true);
            TimeAppIntent();
        }
    }

    int time = 3;//首先声明时间初始值：3秒
    //设置定时器任务，更新时间,3秒后跳转页面
    public void TimeAppIntent(){
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                //发送消息，更新时间
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // 为TextView控件赋值
                        timetext.setText(time+"  秒");
                        time--;

                        //当时间为0秒时，执行跳转功能
                        if (time == 0) {
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();            //跳转后销毁当前页面
                        }
                    }
                });
            }
        },1000, 1000);
    }
}
