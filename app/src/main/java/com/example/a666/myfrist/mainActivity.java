package com.example.a666.myfrist;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.a666.myfrist.Heart.HeartActivity;
import com.example.a666.myfrist.Weather.MainsActivity;
import com.example.a666.myfrist.Weather.WeatherActivity;
import com.example.a666.myfrist.adapter.mainAdapter;


public class mainActivity extends Activity {

	/** 声明GridView 该控件类似ListView */
	private GridView gv_home;
	/** 存储手机防盗密码的sp */
	private SharedPreferences msharedPreferences;
	/** 设备管理 员 */
	private DevicePolicyManager policyManager;
	/** 申请权限 */
	private ComponentName componentName;
	private long mExitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 初始化布局
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        msharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        // 初始化GridView
        gv_home = (GridView) findViewById(R.id.gv_home);
        gv_home.setAdapter(new mainAdapter(mainActivity.this));
        // 设置条目的点击事件
        gv_home.setOnItemClickListener(new OnItemClickListener() {
            // parent代表gridView,view代表每个条目的view对象,postion代表每个条目的位置
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {

                    case 0:// 手机杀毒
                        //startActivity(VirusScanActivity.class);
                        break;
                    case 1:// 缓存清理
                        //startActivity(CacheClearListActivity.class);
                        break;

                    case 2: // 流量统计
                        //startActivity(TrafficMonitoringActivity.class);
                        break;
                    case 3: // 高级工具
                        //startActivity(AdvancedToolsActivity.class);
                        break;
                    case 4: // 设置中心
                        //startActivity(SettingsActivity.class);
                        break;
                    case 5://心电图
                        startActivity(HeartActivity.class);
                        break;
					case 6://天气提示
						startActivity(WeatherActivity.class);
						break;
                }
            }
        });
    }

	/**
	 * 开启新的activity不关闭自己
	 * 
	 * @param cls
	 *            新的activity的字节码
	 */
	public void startActivity(Class<?> cls) {
		Intent intent = new Intent(mainActivity.this, cls);
		startActivity(intent);
	}

	/***
	 * 按两次返回键退出程序
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
