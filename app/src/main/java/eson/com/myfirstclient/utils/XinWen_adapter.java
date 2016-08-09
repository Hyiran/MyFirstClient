package eson.com.myfirstclient.utils;

/**
 * Created by HP on 2016/8/6.
 */
public class XinWen_adapter {
    public final static int TYPE_putong = 0;
    public final static int TYPE_duotu = 1;
    public final static int TYPE_zhuanti = 2;
    public final static int TYPE_zhibo = 3;

    //判断item的类型
    public static int getType(String skipType) {
        if(skipType == null){
            return TYPE_putong;
        }
        if(skipType.equals("photoset")){
            return TYPE_duotu;
        }else if (skipType.equals("special")) {
            return TYPE_zhuanti;
        } else if (skipType.equals("live")) {
            return TYPE_zhibo;
        }
        return TYPE_putong;
    }

    public final static int toutiao = 0;
    public final static int yule = 1;
    public final static int tiyu = 2;
    public final static int caijing = 3;
    public final static int keji = 4;
    public final static int shishang = 5;
    public final static int lishi = 6;
    public final static int caipiao = 7;
    public final static int junshi = 8;
    public final static int youxi = 9;
    public final static int redian = 10;

    public static int getXinWenType(String daohangtitle) {
        if (daohangtitle.equals("热点")) {
            return redian;
        } else if (daohangtitle.equals("头条")) {
            return toutiao;
        } else if (daohangtitle.equals("娱乐")) {
            return yule;
        } else if (daohangtitle.equals("体育")) {
            return tiyu;
        } else if (daohangtitle.equals("财经")) {
            return caijing;
        } else if (daohangtitle.equals("科技")) {
            return keji;
        } else if (daohangtitle.equals("时尚")) {
            return shishang;
        } else if (daohangtitle.equals("历史")) {
            return lishi;
        } else if (daohangtitle.equals("彩票")) {
            return caipiao;
        } else if (daohangtitle.equals("军事")) {
            return junshi;
        } else if (daohangtitle.equals("游戏")) {
            return youxi;
        }
        // TODO: 2016/08/06
        return 0;
    }


}
