package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : JinBiao
 *     CSDN : http://my.csdn.net/DT235201314
 *     time   : 2017/06/12
 *     desc   : SharedPreferences数据存储协助类
 *              map键值对形式保存文件
 *     version: 1.0
 * </pre>
 */

public class SharedHelper {
    private Context mContext;

    public SharedHelper() {
    }

    public SharedHelper(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 定义一个保存数据的方法
     * @param username
     * @param passwd
     */
    public void save(String username, String passwd) {
        SharedPreferences sp = mContext.getSharedPreferences("my_sp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("passwd", passwd);
        editor.commit();
        Toast.makeText(mContext, "信息已写入SharedPreference中", Toast.LENGTH_SHORT).show();
    }

    /**
     * 定义一个读取SP文件的方法
     * @return
     */
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = mContext.getSharedPreferences("my_sp", Context.MODE_PRIVATE);
        data.put("username", sp.getString("username", ""));
        data.put("passwd", sp.getString("passwd", ""));
        return data;
    }
}
