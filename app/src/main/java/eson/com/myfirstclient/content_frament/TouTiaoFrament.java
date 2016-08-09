package eson.com.myfirstclient.content_frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import eson.com.myfirstclient.CostomAdapter.XinWenBaseAdapter;
import eson.com.myfirstclient.R;
import eson.com.myfirstclient.pullrefreshview.PullToRefreshBase;
import eson.com.myfirstclient.pullrefreshview.PullToRefreshListView;
import eson.com.myfirstclient.utils.CommonUtil;
import eson.com.myfirstclient.utils.LogUtils;
import eson.com.myfirstclient.utils.XinWenJson;
import eson.com.myfirstclient.utils.XinWenURL;
import eson.com.myfirstclient.utils.XinWen_adapter;
import eson.com.myfirstclient.utils.XinWen_toutiao;
import eson.com.myfirstclient.utils.XutilsGetData;

/**
 * Created by HP on 2016/8/6.
 */
public class TouTiaoFrament extends Fragment {
    private String url = null;//第一页url
    private View view;
    private XinWenURL xinWenURL = new XinWenURL();
    private int daohangtype;
    private XinWenBaseAdapter toutiao_adapter;
    private List<XinWen_toutiao.T1348647853363Entity> toutiao_list = new ArrayList<XinWen_toutiao.T1348647853363Entity>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        LogUtils.e("bundle", bundle + "");
        String daohangtitle = bundle.getString("xinwendaohang");
        daohangtype = XinWen_adapter.getXinWenType(daohangtitle);
        LogUtils.e("bundle", "==" + daohangtype);
        url = geturl();
    }

    private String geturl() {
        String url = null;
        switch (daohangtype) {
            case XinWen_adapter.toutiao:
                url = xinWenURL.getToutiao();//头条url
                break;

        }
        return url;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            if (view == null) {
                view = initview(inflater);
            }
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private PullToRefreshListView toutiao_lv;

    private View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.xinwen_toutiaoframent, null, false);
        toutiao_lv = (PullToRefreshListView) view.findViewById(R.id.xinwen_toutiao_lv);

        getdata(url, true);

        toutiao_lv.setPullLoadEnabled(false);//上拉加载，屏蔽
        toutiao_lv.setScrollLoadEnabled(false); //设置滚动加载可用

        //设置上拉下拉的监听事件
        toutiao_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉刷新，重新获取数据，填充listview
                getdata(url, true);//刷新数据
                String stringDate = CommonUtil.getStringDate();//下拉刷新时获取当前的刷新时间
                toutiao_lv.setLastUpdatedLabel(stringDate); //将时间添加到刷新的表头
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                xinWenURL.setStartPage(xinWenURL.getStartPage() + 20);
                //默认选择头条栏目
                String urlfen = geturl();//分页
                LogUtils.e("toutiao", "url:" + urlfen);
                //上拉加载
                getdata(urlfen, false); //加载数据
            }
        });

        return view;
    }

    private XutilsGetData xutilsGetData = new XutilsGetData();

    //网络请求获得数据 refresh：true为刷新数据，false为加载数据 存储根据url保存数据
    private void getdata(String url, final boolean refresh) {
        if (CommonUtil.isNetWork(getActivity())) {
            //然后网络请求刷新数据
            xutilsGetData.xUtilsHttp(getActivity(), url, new XutilsGetData.CallBackHttp() {

                @Override
                public void handleData(String data) {
                    LogUtils.e("xinwenactivity==data==", data + "");
                    getdata(data, refresh);
                }
            }, true);
        } else {
            String data = xutilsGetData.getData(getActivity(), url, null);
            //判断本地数据是否存在，如果没有网络请求
            if (data != null) {
                getshowdata(data, refresh);
            }
        }
    }

    boolean isrefresh = true;

    //显示数据， 或者分页加载数据
    private void getshowdata(String data, boolean refresh) {
        if (refresh) {
            toutiao_list.clear();
        }
        XinWen_toutiao toutiao_object = XinWenJson.getdata(data, daohangtype);//传入类型和数据
        LogUtils.e("toutiao_object", "" + toutiao_object);
        toutiao_list.addAll(toutiao_object.getT1348647853363());

        //填充并刷新数据
        toutiao_adapter.setToutiao_list(toutiao_list);
    }

    public void setArguments(Bundle bundletoutiao) {
    }
}
