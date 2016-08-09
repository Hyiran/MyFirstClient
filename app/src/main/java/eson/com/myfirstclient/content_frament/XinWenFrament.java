package eson.com.myfirstclient.content_frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;
import eson.com.myfirstclient.R;

/**
 * Created by HP on 2016/8/2.
 */
public class XinWenFrament extends Fragment {
    @Nullable
    private View contentView;
    private List<Fragment> xinwen_framentlist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initdata();
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        if(contentView == null){
            contentView = initview(inflater);
        }
        return  contentView;
    }


    private void initdata() {
        xinwen_framentlist = new ArrayList<>();

        TouTiaoFrament toutiao = new TouTiaoFrament();
        Bundle bundletoutiao = new Bundle();
        bundletoutiao.putString("xinwendaohang", "头条");
        toutiao.setArguments(bundletoutiao);
        xinwen_framentlist.add(toutiao);

        TouTiaoFrament yule = new TouTiaoFrament();
        Bundle bundleyule = new Bundle();
        bundleyule.putString("xinwendaohang", "娱乐");
        yule.setArguments(bundleyule);
        xinwen_framentlist.add(yule);

        TouTiaoFrament tiyu = new TouTiaoFrament();
        Bundle bundletiyu = new Bundle();
        bundletiyu.putString("xinwendaohang","体育");
        tiyu.setArguments(bundletiyu);
        xinwen_framentlist.add(tiyu);

        TouTiaoFrament caijing = new TouTiaoFrament();
        Bundle bundlecaijing = new Bundle();
        bundletiyu.putString("xinwendaohang","财经");
        tiyu.setArguments(bundlecaijing);
        xinwen_framentlist.add(caijing);

        TouTiaoFrament keji = new TouTiaoFrament();
        Bundle bundlekeji = new Bundle();
        bundletiyu.putString("xinwendaohang", "科技");
        tiyu.setArguments(bundlekeji);
        xinwen_framentlist.add(keji);

        TouTiaoFrament shishang = new TouTiaoFrament();
        Bundle bundleshishang = new Bundle();
        bundletiyu.putString("xinwendaohang","时尚");
        tiyu.setArguments(bundleshishang);
        xinwen_framentlist.add(shishang);

        TouTiaoFrament lishi = new TouTiaoFrament();
        Bundle bundlelishi = new Bundle();
        bundletiyu.putString("xinwendaohang","历史");
        tiyu.setArguments(bundlelishi);
        xinwen_framentlist.add(lishi);

        TouTiaoFrament caipiao = new TouTiaoFrament();
        Bundle bundlecaipiao = new Bundle();
        bundletiyu.putString("xinwendaohang","彩票");
        tiyu.setArguments(bundlecaipiao);
        xinwen_framentlist.add(caipiao);

        TouTiaoFrament junshi = new TouTiaoFrament();
        Bundle bundlejunshi = new Bundle();
        bundletiyu.putString("xinwendaohang","军事");
        tiyu.setArguments(bundlejunshi);
        xinwen_framentlist.add(junshi);

        TouTiaoFrament youxi = new TouTiaoFrament();
        Bundle bundleyouxi = new Bundle();
        bundletiyu.putString("xinwendaohang","游戏");
        tiyu.setArguments(bundleyouxi);
        xinwen_framentlist.add(youxi);
    }

    private ImageView btn_right;
    private View xuanfu_view;

    //初始化控件
    private View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.xinwen_fragement,null,false);

        //加载悬浮窗布局
        xuanfu_view = View.inflate(getActivity(),R.layout.popwindow_view,null);
        //点击该图标弹出悬浮窗
        btn_right = (ImageView) view.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //新闻导航栏控件
        final RadioGroup xinwen_Rradio = (RadioGroup) view.findViewById(R.id.xinwen_radiogroup);
        final ViewPager xinwen_viewpage = (ViewPager) view.findViewById(R.id.xinwen_viewpager);
        final HorizontalScrollView xinwen_scrollview = (HorizontalScrollView) view.findViewById(R.id.xinwen_scroll);
        final TextView xinwen_indicator = (TextView) view.findViewById(R.id.xinwen_indicator);

        //新闻页面的adapter
        XinWenFramentAdapter framentAdapter = new XinWenFramentAdapter(getActivity().getSupportFragmentManager());
        xinwen_viewpage.setAdapter(framentAdapter);
        xinwen_viewpage.setOffscreenPageLimit(2);
        xinwen_Rradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.xinwen_rb1:
                        xinwen_viewpage.setCurrentItem(0);
                        break;
                    case R.id.xinwen_rb2:
                        xinwen_viewpage.setCurrentItem(1);
                        break;
                    case R.id.xinwen_rb3:
                        xinwen_viewpage.setCurrentItem(2);
                        break;
                    case R.id.xinwen_rb4:
                        xinwen_viewpage.setCurrentItem(3);
                        break;
                    case R.id.xinwen_rb5:
                        xinwen_viewpage.setCurrentItem(4);
                        break;
                    case R.id.xinwen_rb6:
                        xinwen_viewpage.setCurrentItem(5);
                        break;
                    case R.id.xinwen_rb7:
                        xinwen_viewpage.setCurrentItem(6);
                        break;
                    case R.id.xinwen_rb8:
                        xinwen_viewpage.setCurrentItem(7);
                        break;
                    case R.id.xinwen_rb9:
                        xinwen_viewpage.setCurrentItem(8);
                        break;
                    case R.id.xinwen_rb10:
                        xinwen_viewpage.setCurrentItem(9);
                        break;
                }
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }

    //请求网络
    private void getData(final String url){
        if(url != null){
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpRequest.HttpMethod.GET,url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {

                }

                @Override
                public void onFailure(HttpException e, String s) {

                }
            });
        }
    }

    private class XinWenFramentAdapter extends FragmentPagerAdapter {
        public XinWenFramentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return xinwen_framentlist.get(position);
        }

        @Override
        public int getCount() {
            return xinwen_framentlist.size();
        }
    }
}
