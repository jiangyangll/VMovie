package com.example.dllo.vmovie;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import android.view.MotionEvent;

import android.view.KeyEvent;

import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.RelativeLayout;

import android.widget.TextView;


import android.widget.Toast;


import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.backstage.fragment.BackStageFragment;
import com.example.dllo.vmovie.series.SeriesFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements OnTouchListener {

    private DrawerLayout drawerLayout;
    private ImageView clickDrawer, clickSearch;
    private RelativeLayout relativeDrawer, relativeDrawerHome, relativeDrawerSeries, relativeDrawerBehind;
    private ImageView ivHomeBlank, ivSeriesBlank, ivBehindBlank
            , ivHomepage, ivSeries, ivBehind;
    private TextView tvHomePage, tvSeries, tvBehind;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        relativeDrawer = (RelativeLayout) findViewById(R.id.relative_drawer);
        relativeDrawer.setAlpha(255);
        relativeDrawerHome = (RelativeLayout) findViewById(R.id.relative_drawer_homepage);
        relativeDrawerSeries = (RelativeLayout) findViewById(R.id.relative_drawer_series);
        relativeDrawerBehind = (RelativeLayout) findViewById(R.id.relative_drawer_behind);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.setAlpha(255);
        clickDrawer = (ImageView) findViewById(R.id.click_drawerAble);
        clickSearch = (ImageView) findViewById(R.id.click_search);

        ivHomeBlank = (ImageView) findViewById(R.id.iv_drawer_homepage_blank);
        ivSeriesBlank = (ImageView) findViewById(R.id.iv_drawer_series_blank);
        ivBehindBlank = (ImageView) findViewById(R.id.iv_drawer_behind_blank);
        ivHomepage = (ImageView) findViewById(R.id.iv_drawer_homepage);
        ivSeries = (ImageView) findViewById(R.id.iv_drawer_series);
        ivBehind = (ImageView) findViewById(R.id.iv_drawer_behind);
        tvHomePage = (TextView) findViewById(R.id.tv_drawer_homepage);
        tvSeries = (TextView) findViewById(R.id.tv_drawer_series);
        tvBehind = (TextView) findViewById(R.id.tv_drawer_behind);
        relativeDrawerHome.setOnTouchListener(this);
        relativeDrawerSeries.setOnTouchListener(this);
        relativeDrawerBehind.setOnTouchListener(this);
        relativeDrawerHome.setBackgroundColor(Color.DKGRAY);
        ivHomeBlank.setVisibility(View.VISIBLE);
        ivHomepage.setImageResource(R.mipmap.side_home);
        tvHomePage.setTextColor(Color.WHITE);
        replaceFragment(R.id.fragment_replace,new HomePagerFragment());

    }

    @Override
    protected void initData() {
        clickDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        clickSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
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
}
