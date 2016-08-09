package eson.com.myfirstclient.pullrefreshview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import java.util.jar.Attributes;

/**
 * Created by HP on 2016/8/6.
 */
public abstract class PullToRefreshBase<T extends View>extends LinearLayout implements IPullToRefresh<T>{




    /**
     * 定义了下拉刷新和上拉刷新加载更多的接口
     */
    public interface OnRefreshListener<V extends View>{
        /**
         * 下拉松手后会被调用
         * @param refreshView 刷新的view
         */
        void onPullDownToRefresh(final PullToRefreshBase<V> refreshView);

        /**
         * 加载更多时被调用或上拉时被调用
         * @param refreshView
         */
        void onPullUpToRefresh(final PullToRefreshBase<V> refreshView);
    }
    /**上拉加载是否可用*/
    private boolean mPullLoadEnabled = false;
    /**判断滑动到底部加载是否可用*/
    private boolean mScrollLoadEnabled = false;
    /**下拉刷新和加载更多的监听器 */
    private OnRefreshListener<T> mRefreshListener;
    /**下拉刷新的布局 */
    private LoadingLayout mHeaderLayout;
    /**上拉加载更多的布局*/
    private LoadingLayout mFooterLayout;

    public PullToRefreshBase(Context context, Attributes attrs) {
        super(context);
    }

    public void setPullLoadEnabled(boolean pullLoadEnabled) {
        mPullLoadEnabled = pullLoadEnabled;
    }

    public void setScrollLoadEnabled(boolean scrollLoadEnaled) {
        mPullLoadEnabled = scrollLoadEnaled;
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener<T> refreshListener) {
        mRefreshListener = refreshListener;
    }

    public void setLastUpdatedLabel(CharSequence label) {
        if(null != mHeaderLayout){
            mHeaderLayout.setLastUpdateLabel(label);
        }
    }
}
