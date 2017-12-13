package com.example.shop.util;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
/**
 * 判断网络连接的工具类
 */
public class NetConnectionUtil {
    /**
     * 判断是否有网络连接的方法
     */
    public  static boolean isNetConnectioned(Context context){
        //1. 获取网络连接对象
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //2. 获取NetworkInfo对象,获取网络连接的信息
        NetworkInfo info = manager.getActiveNetworkInfo();

        //3. 信息不为空时，就代表网络可用
        if (info != null){
            return info.isAvailable();
        }
        return false;
    }

    /**
     * 网络无连接时跳转页面，弹出对话框进行网络的设置
     */
    public static void setNetConnectionWork(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络加载错误！");
        builder.setMessage("网络连接不可用，是否设置网络？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //跳转到系统的设置网络的界面
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                context.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消",null);
        builder.show();
    }
}
