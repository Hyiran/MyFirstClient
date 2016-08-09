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
public class SheZhiFrament extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View contentview;

    @Nullable
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        try {
            if(contentview == null){
                contentview = initview(inflater);
            }
            return contentview;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private View initview(LayoutInflater inflater) {
        final View view = inflater.inflate(R.layout.hgz_activity_main_fragment,null,false);
        return view;
    }
}
