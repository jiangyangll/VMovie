package com.example.dllo.vmovie.setting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;

import java.io.File;

/**
 * Created by dllo on 16/9/7.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    //设置
    private ImageView backImg;
    private RelativeLayout clearCacheRL, settingPlayRL, versionUpdateRL, settingStorageRL;
    private Switch allowWIFISwitch;
    private TextView cacheTextView;
    private Button backBtn;

    private PopupWindow popupWindow;
    private TextView haveSDK, countPhoneSize, settingStorageContent;
    private Button phoneSizeBtn, sdkBtn;

    private WifiManager wifiManager;

    @Override
    public int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        backImg = (ImageView) findViewById(R.id.setting_back);
        backBtn = (Button) findViewById(R.id.setting_user_back);
        cacheTextView = (TextView) findViewById(R.id.setting_cache_textView);
        allowWIFISwitch = (Switch) findViewById(R.id.setting_allowWIFI_switch);
        clearCacheRL = (RelativeLayout) findViewById(R.id.setting_clear_cache_rl);
        settingPlayRL = (RelativeLayout) findViewById(R.id.setting_play_rl);
        versionUpdateRL = (RelativeLayout) findViewById(R.id.setting_version_update_rl);
        settingStorageRL = (RelativeLayout) findViewById(R.id.setting_storage_rl);

        settingStorageContent = (TextView) findViewById(R.id.setting_storage_content);
    }

    @Override
    protected void initData() {

        wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        /* 判断运行程序后的WiFi状态是否打开或打开中 */
        if (wifiManager.isWifiEnabled()) {
        /* 判断WiFi状态是否“已打开” */
            if (wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
        /* 若WiFi已打开，将选取项打勾 */
                allowWIFISwitch.setChecked(true);
        /* 更改选取项文字为关闭WiFi*/
            } else {
        /* 若WiFi未打开，将选取项勾选择消 */
                allowWIFISwitch.setChecked(false);
            }
        } else {
            allowWIFISwitch.setChecked(false);
        }

        //获取缓存大小
        try {
            String cacheSize = CacheManager.getTotalCacheSize(getApplicationContext());
            cacheTextView.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        backBtn.setOnClickListener(this);
        backImg.setOnClickListener(this);

        allowWIFISwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当选取项为取消选取状态
                if (!allowWIFISwitch.isChecked()) {
                    //尝试关闭Wi-Fi服务
                    try {
                        //判断WiFi状态是否为已打开
                        if (wifiManager.isWifiEnabled()) {
                            //关闭WiFi
                            if (wifiManager.setWifiEnabled(false)) {
                                Toast.makeText(SettingActivity.this, "关闭wifi", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SettingActivity.this, "开启wifi", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //WiFi状态不为已打开状态时
                        }
                    } catch (Exception e) {
                        Log.i("HIPPO", e.toString());
                        e.printStackTrace();
                    }
                } else if (allowWIFISwitch.isChecked()) {
                    //尝试打开Wi-Fi服务
                    try {
                        //确认WiFi服务是关闭且不在打开作业中
                        if (!wifiManager.isWifiEnabled() && wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
                            if (wifiManager.setWifiEnabled(true)) {
                                Toast.makeText(SettingActivity.this, "开启wifi", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SettingActivity.this, "关闭wifi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        Log.i("HIPPO", e.toString());
                        e.printStackTrace();
                    }
                }
            }
        });
        clearCacheRL.setOnClickListener(this);

        settingStorageRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUp(v);
            }
        });

        settingPlayRL.setOnClickListener(this);
        versionUpdateRL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_user_back:

                break;

            case R.id.setting_back:
                SettingActivity.this.finish();
                break;

            case R.id.setting_clear_cache_rl:
                CacheManager.clearAllCache(getApplicationContext());
                Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
                try {
                    cacheTextView.setText(CacheManager.getTotalCacheSize(getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.setting_play_rl:
                Intent intent = new Intent(SettingActivity.this, PlayActivity.class);
                startActivity(intent);
                break;

            case R.id.setting_version_update_rl:
                Toast.makeText(this, "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showPopUp(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.setting_item_popup, null);
        view.setBackgroundColor(Color.WHITE);
        haveSDK = (TextView) view.findViewById(R.id.decide_have_sdk);
        countPhoneSize = (TextView) view.findViewById(R.id.count_phone_size);
        phoneSizeBtn = (Button) view.findViewById(R.id.phone_size_btn);
        sdkBtn = (Button) view.findViewById(R.id.sdk_btn);

        popupWindow = new PopupWindow(view, 800, 500);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        v.getLocationOnScreen(location);

        popupWindow.showAtLocation(v, Gravity.CENTER, location[0], location[0]);

        if (ExistSDCard()) {
            haveSDK.setText("有");
        } else {
            haveSDK.setText("无");
        }

        String number = "剩余大小:" + getSDFreeSize() + "GB";
        countPhoneSize.setText(number);

        phoneSizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingStorageContent.setText("手机存储");
                popupWindow.dismiss();
            }
        });

        sdkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingStorageContent.setText("SD卡");
                popupWindow.dismiss();
            }
        });
    }

    private boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    public long getSDFreeSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSize();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocks();
        //返回SD卡空闲大小
        return (freeBlocks * blockSize) / 1024 / 1024 / 1024;//单位GB
    }
}
