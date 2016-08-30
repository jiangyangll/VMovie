package com.example.dllo.vmovie;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.fragment.BackStageFragment;
import com.example.dllo.vmovie.fragment.HomePagerFragment;
import com.example.dllo.vmovie.fragment.SeriesFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout drawerLayout;
    private ImageView clickDrawer, clickSearch;
    private LinearLayout homePager, series, backstage;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        homePager = (LinearLayout) findViewById(R.id.home_page);
        series = (LinearLayout) findViewById(R.id.series);
        backstage = (LinearLayout) findViewById(R.id.backstage);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.setAlpha(30f);
        clickDrawer = (ImageView) findViewById(R.id.click_drawerAble);
        clickSearch = (ImageView) findViewById(R.id.click_search);
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

        homePager.setOnClickListener(this);
        series.setOnClickListener(this);
        backstage.setOnClickListener(this);
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
            case R.id.home_page:
                replaceFragment(R.id.fragment_replace, new HomePagerFragment());
                drawerLayout.closeDrawers();
                break;
            case R.id.series:
                replaceFragment(R.id.fragment_replace, new SeriesFragment());
                drawerLayout.closeDrawers();
                break;
            case R.id.backstage:
                replaceFragment(R.id.fragment_replace, new BackStageFragment());
                drawerLayout.closeDrawers();
                break;
        }
    }

}

