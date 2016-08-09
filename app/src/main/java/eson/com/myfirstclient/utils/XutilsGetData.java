package eson.com.myfirstclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import eson.com.myfirstclient.CostomProgressDialog.CustomProgressDialog;
import eson.com.myfirstclient.R;

/**
 * Created by HP on 2016/8/6.
 */
public class XutilsGetData {
    //数据存储的名称
    private String CONFIG = "config";

    private HttpUtils http;
    private CallBackHttp callBackHttp;
    //网络请求图片
    private HttpHandler<String> hand;
    private String data = null;
    private SharedPreferences sp;

    /**
     * 网络请求string数据
     * @param isprogressdialog
     * @param context
     * @param url
     * @param callback
     */
    public void xUtilsHttp(final Context context, final String url, CallBackHttp callback,final boolean isprogressdialog) {
        http = new HttpUtils();
        callBackHttp = callback;
        if(isprogressdialog){
            if(dialog == null){

            }
        }
        //打开子线程请求网络
        final CustomProgressDialog finalDialog = dialog;
        hand = http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            //开始请求调用的方法
            @Override
            public void onStart() {
                super.onStart();
            }

            //正在请求调用的方法
            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
            }

            //请求成功调用的方法
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                LogUtils.e("onSuccess",responseInfo + "");
                LogUtils.e("onSuccessurl",url + "");
                data = responseInfo.result;//获得网络请求的字符串
                LogUtils.e("onSuccess", data + "");
                callBackHttp.handleData(data);//接口回调函数
                savaData(context,url,data);//保存数据
                if(finalDialog  != null){
                    finalDialog.dismiss();
                }
            }

            //请求失败调用的方法
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    /**
     * 保存数据
     * @param context
     * @param key
     * @param data
     */
    private void savaData(Context context, String key, String data) {
        if(sp == null){
            sp = context.getSharedPreferences(CONFIG,Context.MODE_APPEND);
        }
        sp.edit().putString(key,data).commit();
    }

    public String getData(Context context, String url, Object o) {
        return null;
    }

    private static BitmapUtils utils;
    private static CallBackImage callbackimage;

    //网络请求图片
    static CustomProgressDialog dialog = null;

    public static void xUtilsImageiv(ImageView iv, String imageurl, Context context, boolean isprogressdialog) {
        //第一个参数为上下文 第二个参数为缓冲路径(如果不写也会缓存到默认路径)
        utils = new BitmapUtils(context,context.getFilesDir().getPath());//保存图片路径

        if(isprogressdialog){
            if(dialog == null ){
                dialog  = new CustomProgressDialog(context,"正在加载...", R.anim.donghua_frame);
            }
            dialog.show();
        }

        //第一个为放入的图片的控件，第二个为图片地址，config为显示方式，CallBack为回调可以自定义显示
        utils.display(iv, imageurl, new BitmapLoadCallBack<ImageView>() {
            @Override
            public void onLoadCompleted(ImageView imageView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                imageView.setImageBitmap(bitmap);

                if(dialog != null){
                    dialog.dismiss();
                }
            }

            @Override
            public void onLoadFailed(ImageView imageView, String s, Drawable drawable) {

            }
        });
    }


    //数据接口回调
    public interface CallBackHttp {
        void handleData(String data);
    }

    //图片接口回调
    public interface CallBackImage {
        void handleData(Bitmap bitmap);
    }
}
