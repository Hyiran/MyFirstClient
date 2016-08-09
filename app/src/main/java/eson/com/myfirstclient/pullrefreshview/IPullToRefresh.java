package eson.com.myfirstclient.pullrefreshview;

import android.view.View;

/**
 * Created by HP on 2016/8/6.
 */
public interface IPullToRefresh<T extends View> {
    void setOnRefreshListener(PullToRefreshBase.OnRefreshListener<T> refreshListener);
}
