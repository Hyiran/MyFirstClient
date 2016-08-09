package eson.com.myfirstclient.pullrefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import java.util.jar.Attributes;


import android.R;


/**
 * 这个类实现了ListView下拉刷新，加载更多和滑到底部自动加载更多
 * @author admin
 * @since 2016年8月6日 17:14:47
 */
public class PullToRefreshListView extends PullToRefreshBase<ListView> implements OnScrollListener {
    /**
     * ListView
     */
    private ListView mListView;
    /**
     * 用于滑到底部自动加载Footer
     */
    private LoadingLayout mLoadingLayout;
    /**
     * 滚动的监听器
     */
    private OnScrollListener mScrollListener;

    /**
     * 构造方法
     *
     */
    public PullToRefreshListView(Context context){
        this(context,null);
    }
    /**
     * 构造方法
     * @param context context
     * @param attrs attrs
     */
    public PullToRefreshListView(Context context,Attributes attrs){
        this(context,attrs,0);
    }

    /**
     * 构造方法
     * @param context
     * @param attrs
     * @param defStyle
     */
    public PullToRefreshListView(Context context, Attributes attrs, int defStyle) {
        super(context,attrs);
        setPullLoadEnable(false);
    }

    protected ListView createRefreshableView(Context context,AttributeSet attrs){
        ListView listView = new ListView(context, attrs);
        listView.setId(R.id.list);
        mListView = listView;

        android.view.ViewGroup.LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        mListView.setLayoutParams(params);
        listView.setOnScrollListener(this);

        return listView;
    }

    /**
     * 设置是否有更多的数据标志
     * @param hasMoreData
     */
    public void setHasMoreData(boolean hasMoreData){
        if(!hasMoreData){

        }
    }
    private void setPullLoadEnable(boolean b) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

}
