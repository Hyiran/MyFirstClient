package eson.com.myfirstclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

import eson.com.myfirstclient.activitys.MainActivity;

/**
 * Created by HP on 2016/8/2.
 */
public class CommonUtil {

    //判断是否有网络
    public static boolean isNetWork(Context context) {
        //得到网络的管理者
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 获取时间
     * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
