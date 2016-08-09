package eson.com.myfirstclient.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import eson.com.myfirstclient.BroadCastReceiver.MyReceiver;
import eson.com.myfirstclient.R;
import eson.com.myfirstclient.content_frament.ReDianFrament;
import eson.com.myfirstclient.content_frament.SheZhiFrament;
import eson.com.myfirstclient.content_frament.ShiTingFrament;
import eson.com.myfirstclient.content_frament.XinWenFrament;
import eson.com.myfirstclient.content_frament.YueDuFrament;
import eson.com.myfirstclient.utils.CommonUtil;
import eson.com.myfirstclient.viewpager.ContentViewPager;

public class MainActivity extends AppCompatActivity {
    private ContentViewPager contentViewPager;
    private RadioGroup contentradiogroup;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkNetState();//检查网络状态
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        myReceiver = new MyReceiver();
        registerReceiver(myReceiver, intentFilter);

        initdata();//填充数据
        initview();//填充布局
    }

    private List<Fragment> content_list = null;

    private void initdata(  ) {
        content_list  = new ArrayList<>();
        content_list.add(new XinWenFrament());
        content_list.add(new ReDianFrament());
        content_list.add(new ShiTingFrament());
        content_list.add(new YueDuFrament());
        content_list.add(new SheZhiFrament());
    }

    private void initview() {
        if(content_list == null){
            return;
        }
        contentViewPager = (ContentViewPager) findViewById(R.id.content_viewpager);
        contentradiogroup = (RadioGroup) findViewById(R.id.content_radiogroup);

        //预加载一页
        contentViewPager.setOffscreenPageLimit(2);
        contentViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return content_list.get(position);
            }

            @Override
            public int getCount() {
                return content_list.size();
            }
        });
        contentradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_xinwen:
                        contentViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_redian:
                        contentViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_shiting:
                        contentViewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_yuedu:
                        contentViewPager.setCurrentItem(3);
                        break;
                    case R.id.rb_shezhi:
                        contentViewPager.setCurrentItem(4);
                        break;
                }
            }
        });
        contentradiogroup.check(R.id.rb_xinwen);
    }

    /**
     * 检查网络是连接
     */
    private void checkNetState() {
        if(!CommonUtil.isNetWork(this)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("网络状态提醒");
            builder.setMessage("网络状态不可用，是否打开网络设置？");
            builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (Build.VERSION.SDK_INT > 10) {
                        startActivity(new Intent(Settings.ACTION_SETTINGS));
                    } else {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                }
            });
            builder.create().show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    private long timeMills;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - timeMills) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                timeMills = System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return  super.onKeyDown(keyCode,event);
    }
}
