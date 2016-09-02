package com.example.dllo.vmovie;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.RelativeLayout;

import android.widget.Toast;

import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.backstage.fragment.BackStageFragment;
import com.example.dllo.vmovie.series.SeriesFragment;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView clickDrawer, clickSearch;
    private RelativeLayout relativeDrawer, relativeDrawerHome, relativeDrawerSeries, relativeDrawerBehind;

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
        relativeDrawerHome.setOnClickListener(this);
        relativeDrawerSeries.setOnClickListener(this);
        relativeDrawerBehind.setOnClickListener(this);

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_drawer_homepage:
                replaceFragment(R.id.fragment_replace, new HomePagerFragment());
                drawerLayout.closeDrawers();
                break;
            case R.id.relative_drawer_series:
                replaceFragment(R.id.fragment_replace, new SeriesFragment());
                drawerLayout.closeDrawers();
                break;
            case R.id.relative_drawer_behind:
                replaceFragment(R.id.fragment_replace, new BackStageFragment());
                drawerLayout.closeDrawers();
                break;
        }
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