package eson.com.myfirstclient.content_frament;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import eson.com.myfirstclient.R;

/**
 * Created by HP on 2016/8/2.
 */
public class ShiTingFrament extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    private View contentview;

    @Nullable
    public View OnCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        try {
            if(contentview == null){
                contentview = initview(inflater);
            }
            return  contentview;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //初始化控件
    private View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.shiting_framement,null,false);
        return view;
    }
}
