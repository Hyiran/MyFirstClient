package eson.com.myfirstclient.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 2016/8/6.
 */
public class XinWenJson {

    public static XinWen_toutiao getdata(String data, int type) {

        try {
            XinWen_toutiao toutiao = new XinWen_toutiao();
            JSONObject object = new JSONObject(data);

            LogUtils.e("xinwenjsonobject",object + "");
            JSONArray array = null;
            switch (type){
                case XinWen_adapter.redian:
                    array = object.getJSONArray("T1429173762551");//热点
                    break;
                case XinWen_adapter.toutiao:
                    array = object.getJSONArray("T1348647853363");//头条
                    break;
                case XinWen_adapter.yule:
                    array = object.getJSONArray("T1348648517839");
                    break;
                case XinWen_adapter.tiyu:
                    array = object.getJSONArray("T1348649079062");
                    break;
                case XinWen_adapter.caijing:
                    array = object.getJSONArray("T1348648756099");
                    break;
                case XinWen_adapter.keji:
                    array = object.getJSONArray("T1348649580692");
                    break;
                case XinWen_adapter.shishang:
                    array = object.getJSONArray("T1348650593803");
                    break;
                case XinWen_adapter.lishi:
                    array = object.getJSONArray("T1368497029546");
                    break;
                case XinWen_adapter.caipiao:
                    array = object.getJSONArray("T1356600029035");
                    break;
                case XinWen_adapter.junshi:
                    array=object.getJSONArray("T1348648141035");//军事
                    break;
                case XinWen_adapter.youxi:
                    array=object.getJSONArray("T1348654151579");//游戏
                    break;
            }
            LogUtils.e("xinwenjsonarray----"," " + array);

            List<XinWen_toutiao.T1348647853363Entity> list =  new ArrayList<>();

            for (int i = 0; i < array.length();i++){
                XinWen_toutiao.T1348647853363Entity t1348647853363Entity = new XinWen_toutiao.T1348647853363Entity();
                JSONObject arrayobj = array.getJSONObject(i);

                if(!arrayobj.isNull("skipID")){
                    String skipId = arrayobj.getString("skipID");
                    t1348647853363Entity.setSkipID(skipId);
                };
                if(!arrayobj.isNull("replyCount")){
                    int replyCount = arrayobj.getInt("replyCount");
                    t1348647853363Entity.setReplyCount(replyCount);
                }
                if(!arrayobj.isNull("skipType")){
                    String skipType = arrayobj.getString("skipType");
                    t1348647853363Entity.setSkipType(skipType);
                }
                if(!arrayobj.isNull("title")){
                    String title = arrayobj.getString("title");
                    t1348647853363Entity.setTitle(title);
                }
                if(!arrayobj.isNull("digest")){
                    String digest = arrayobj.getString("digest");
                    t1348647853363Entity.setDigest(digest);
                }
                if(!arrayobj.isNull("priority")){
                    int priority = arrayobj.getInt("priority");
                    t1348647853363Entity.setPriority(priority);
                }
                if(!arrayobj.isNull("imgsrc")){
                    String imgsrc = arrayobj.getString("imgsrc");
                    t1348647853363Entity.setImgsrc(imgsrc);
                }
                if(!arrayobj.isNull("url")){
                    String url = arrayobj.getString("url");
                    t1348647853363Entity.setUrl(url);
                }

                if(!arrayobj.isNull("imgextra")){
                    JSONArray imgextraArray = arrayobj.getJSONArray("imgextra");
                    LogUtils.e("xinwenjsonimagetraArray",imgextraArray + "");
                    List<XinWen_toutiao.T1348647853363Entity.ImgextraEntity> listimagestra = new ArrayList<>();

                    for (int j = 0; j < imgextraArray.length();j++){
                        XinWen_toutiao.T1348647853363Entity.ImgextraEntity imgextraEntity = new XinWen_toutiao.T1348647853363Entity.ImgextraEntity();

                        JSONObject imagestra = imgextraArray.getJSONObject(j);
                        String imagesra = imagestra.getString("imgsrc");
                        imgextraEntity.setImgsrc(imagesra);
                        listimagestra.add(imgextraEntity);
                    }
                    t1348647853363Entity.setImgextra(listimagestra);
                }

                if(!arrayobj.isNull("ads")){
                    JSONArray adsArray = arrayobj.getJSONArray("ads");
                    List<XinWen_toutiao.T1348647853363Entity.AdsEnity> adsEnities = new ArrayList<>();

                    for(int k = 0; k < adsArray.length(); k++){
                        LogUtils.e("xinwenjsonads","xinwenadssentity");
                        XinWen_toutiao.T1348647853363Entity.AdsEnity ads =  new XinWen_toutiao.T1348647853363Entity.AdsEnity();

                        JSONObject adsoje = adsArray.getJSONObject(k);
                        String adstitle = adsoje.getString("title");
                        String adsurl = adsoje.getString("url");
                        LogUtils.e("xinwenjsonadsurl"," " + adsurl);
                        String adstag = adsoje.getString("tag");
                        String adsimgsrc = adsoje.getString("imgsrc");

                        ads.setTitle(adstitle);
                        ads.setUrl(adsurl);
                        ads.setTag(adstag);
                        ads.setImgsrc(adsimgsrc);
                        adsEnities.add(ads);
                    }
                    t1348647853363Entity.setAds(adsEnities);
                }
                list.add(t1348647853363Entity);
            }
            toutiao.setT1348647853363(list);
            LogUtils.e("xinwen_json","=====" + list.get(0).getTitle());

            for(int i = 0; i < toutiao.getT1348647853363().size();i++){
                LogUtils.e("xinwenjson",i + "=====" + toutiao.getT1348647853363().get(i).getTitle());
            }
            LogUtils.e("xinwenjson","=====" + toutiao.getT1348647853363());
            return toutiao;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.e("xinwenjson","==================");
        return null;
    }
}
