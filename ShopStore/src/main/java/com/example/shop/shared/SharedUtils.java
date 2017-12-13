package com.example.shop.shared;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
/**
 * 在当前页面保存和获取数据，用于应用页面的进入
 */
public class SharedUtils {
    static SharedPreferences preferences;
    static String	File = "data";

    //保存数据的方法
    public static void savaBooleanData(Context context, String key,boolean value){
        if (preferences == null) {
            preferences = context.getSharedPreferences(File, Context.MODE_PRIVATE);
        }

        //获取编辑器对象
        Editor editor = preferences.edit();
        editor.putBoolean(key, value).commit();
    }

    //获取数据的方法
    public static boolean getBooleanData(Context context, String key,boolean defValue){
        if (preferences == null) {
            preferences = context.getSharedPreferences(File, Context.MODE_PRIVATE);
        }
        return preferences.getBoolean(key, defValue);
    }
}
