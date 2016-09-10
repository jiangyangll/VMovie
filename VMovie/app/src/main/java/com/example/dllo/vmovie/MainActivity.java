package com.example.dllo.vmovie;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.Gravity;

import android.view.MotionEvent;

import android.view.KeyEvent;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.widget.RelativeLayout;

import android.widget.TextView;


import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.backstage.fragment.BackStageFragment;
import com.example.dllo.vmovie.like.LikeActivity;
import com.example.dllo.vmovie.search.SearchActivity;
import com.example.dllo.vmovie.series.SeriesFragment;
import com.example.dllo.vmovie.series.SubscribeActivity;
import com.example.dllo.vmovie.setting.SettingActivity;

import java.util.Timer;
import java.util.TimerTask;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MainActivity extends BaseActivity implements OnTouchListener, OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView clickDrawer, clickSearch;

    private RelativeLayout relativeDrawerLike;
    private ImageView ivSetting;

    private RelativeLayout relativeDrawer, relativeDrawerHome, relativeDrawerSeries, relativeDrawerBehind;
    private ImageView ivHomeBlank, ivSeriesBlank, ivBehindBlank, ivHomepage, ivSeries, ivBehind, ivDrawerLogin, ivSideClose
            , ivDrawerSubscribe;
    private TextView tvHomePage, tvSeries, tvBehind, tvClickLogin;
    private AlertDialog mDialog;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        relativeDrawer = (RelativeLayout) findViewById(R.id.relative_drawer);

        relativeDrawerHome = (RelativeLayout) findViewById(R.id.relative_drawer_homepage);
        relativeDrawerSeries = (RelativeLayout) findViewById(R.id.relative_drawer_series);
        relativeDrawerBehind = (RelativeLayout) findViewById(R.id.relative_drawer_behind);

        relativeDrawerLike = (RelativeLayout) findViewById(R.id.like_drawer_relative);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.setAlpha(255);
        clickDrawer = (ImageView) findViewById(R.id.click_drawerAble);
        clickSearch = (ImageView) findViewById(R.id.click_search);

        ivSetting = (ImageView) findViewById(R.id.iv_drawer_setting);
        ivHomeBlank = (ImageView) findViewById(R.id.iv_drawer_homepage_blank);
        ivSeriesBlank = (ImageView) findViewById(R.id.iv_drawer_series_blank);
        ivBehindBlank = (ImageView) findViewById(R.id.iv_drawer_behind_blank);
        ivHomepage = (ImageView) findViewById(R.id.iv_drawer_homepage);
        ivSeries = (ImageView) findViewById(R.id.iv_drawer_series);
        ivBehind = (ImageView) findViewById(R.id.iv_drawer_behind);
        tvHomePage = (TextView) findViewById(R.id.tv_drawer_homepage);
        tvSeries = (TextView) findViewById(R.id.tv_drawer_series);
        tvBehind = (TextView) findViewById(R.id.tv_drawer_behind);
        ivSideClose = (ImageView) findViewById(R.id.iv_side_close);
        ivDrawerSubscribe = (ImageView) findViewById(R.id.iv_drawer_my_subscribe);
        ivDrawerSubscribe.setOnClickListener(this);
        ivSideClose.setOnClickListener(this);

        ivDrawerLogin = (ImageView) findViewById(R.id.iv_drawer_head_icon);
        tvClickLogin = (TextView) findViewById(R.id.tv_drawer_click_login);
        ivDrawerLogin.setOnClickListener(this);
        relativeDrawerHome.setOnTouchListener(this);
        relativeDrawerSeries.setOnTouchListener(this);
        relativeDrawerBehind.setOnTouchListener(this);
        relativeDrawerHome.setBackgroundColor(Color.DKGRAY);
        ivHomeBlank.setVisibility(View.VISIBLE);
        ivHomepage.setImageResource(R.mipmap.side_home);
        tvHomePage.setTextColor(Color.WHITE);
        replaceFragment(R.id.fragment_replace, new HomePagerFragment());

    }

    @Override
    protected void initData() {
        mDialog = createDialog();
        clickDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
                Animation scale = AnimationUtils.loadAnimation(MainActivity.this, R.anim.expand_anim);
                ivSideClose.startAnimation(scale);
            }
        });

        clickSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        relativeDrawerLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LikeActivity.class);
                startActivity(intent);
            }
        });

        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        WindowManager windowManager = this.getWindowManager();
        int drawerWidth = windowManager.getDefaultDisplay().getWidth();
        int drawerHeight = windowManager.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams params = relativeDrawer.getLayoutParams();
        params.width = drawerWidth;
        params.height = drawerHeight;
        relativeDrawer.setLayoutParams(params);

        replaceFragment(R.id.fragment_replace, new HomePagerFragment());
    }

    public void replaceFragment(int id, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (v.getId()) {
            case R.id.relative_drawer_homepage:
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        relativeDrawerHome.setBackgroundColor(Color.DKGRAY);
                        ivHomeBlank.setVisibility(View.VISIBLE);
                        ivHomepage.setImageResource(R.mipmap.side_home);
                        tvHomePage.setTextColor(Color.WHITE);
                        break;
                    case MotionEvent.ACTION_UP:
                        relativeDrawerSeries.setBackgroundColor(0);
                        ivSeriesBlank.setVisibility(View.GONE);
                        ivSeries.setImageResource(R.mipmap.side_series_);
                        tvSeries.setTextColor(Color.LTGRAY);

                        relativeDrawerBehind.setBackgroundColor(0);
                        ivBehindBlank.setVisibility(View.GONE);
                        ivBehind.setImageResource(R.mipmap.side_behind_);
                        tvBehind.setTextColor(Color.LTGRAY);
                        replaceFragment(R.id.fragment_replace, new HomePagerFragment());
                        drawerLayout.closeDrawers();
                        break;
                }
                break;
            case R.id.relative_drawer_series:
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        relativeDrawerSeries.setBackgroundColor(Color.DKGRAY);
                        ivSeriesBlank.setVisibility(View.VISIBLE);
                        ivSeries.setImageResource(R.mipmap.side_series);
                        tvSeries.setTextColor(Color.WHITE);
                        break;
                    case MotionEvent.ACTION_UP:
                        relativeDrawerHome.setBackgroundColor(0);
                        ivHomeBlank.setVisibility(View.GONE);
                        ivHomepage.setImageResource(R.mipmap.side_home_);
                        tvHomePage.setTextColor(Color.LTGRAY);

                        relativeDrawerBehind.setBackgroundColor(0);
                        ivBehindBlank.setVisibility(View.GONE);
                        ivBehind.setImageResource(R.mipmap.side_behind_);
                        tvBehind.setTextColor(Color.LTGRAY);
                        replaceFragment(R.id.fragment_replace, new SeriesFragment());
                        drawerLayout.closeDrawers();
                        break;
                }
                break;
            case R.id.relative_drawer_behind:
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        relativeDrawerBehind.setBackgroundColor(Color.DKGRAY);
                        ivBehindBlank.setVisibility(View.VISIBLE);
                        ivBehind.setImageResource(R.mipmap.side_behind);
                        tvBehind.setTextColor(Color.WHITE);
                        break;
                    case MotionEvent.ACTION_UP:
                        relativeDrawerHome.setBackgroundColor(0);
                        ivHomeBlank.setVisibility(View.GONE);
                        ivHomepage.setImageResource(R.mipmap.side_home_);
                        tvHomePage.setTextColor(Color.LTGRAY);

                        relativeDrawerSeries.setBackgroundColor(0);
                        ivSeriesBlank.setVisibility(View.GONE);
                        ivSeries.setImageResource(R.mipmap.side_series_);
                        tvSeries.setTextColor(Color.LTGRAY);
                        replaceFragment(R.id.fragment_replace, new BackStageFragment());
                        drawerLayout.closeDrawers();
                        break;
                }
        }
        return false;
    }

    //菜单、返回键响应
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    //双击退出函数
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }


            Handler mHandler = new Handler(new Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            drawerLayout.closeDrawer(relativeDrawer);
            return false;
        }
    });


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_drawer_head_icon:
                if (VMovieApplication.LOGIN_STATE == true) {
                    mDialog.show();
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, 100);
                }
                break;
            case R.id.iv_side_close:
                Animation scale = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_anim);
                scale.setFillAfter(true);
                ivSideClose.startAnimation(scale);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(0);
                    }
                }).start();
                break;
            case R.id.iv_drawer_my_subscribe:
                Intent intent = new Intent(MainActivity.this, SubscribeActivity.class);
                startActivity(intent);
                break;
        }
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定退出登录?");
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                VMovieApplication.LOGIN_STATE = false;
                ivDrawerLogin.setImageResource(R.mipmap.default_avatar);
                tvClickLogin.setText("点击登录");
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        return dialog;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            tvClickLogin.setText(data.getStringExtra("userName"));
            Glide.with(this).load(data.getStringExtra("headIcon")).bitmapTransform(new
                    CropCircleTransformation(this)).into(ivDrawerLogin);
        } else if (requestCode == 100 && resultCode == 300) {
            tvClickLogin.setText(data.getStringExtra("userName"));
            Glide.with(this).load(data.getStringExtra("headIcon")).bitmapTransform(new
                    CropCircleTransformation(this)).into(ivDrawerLogin);
        }
    }


}
