package com.example.dllo.vmovie.like;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.liteormtool.LiteOrmManager;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/6.
 */
public class LikeActivity extends BaseActivity {

    //喜欢页面
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private ImageView backImg;
    private LikeFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_like;
    }

    @Override
    protected void initView() {
        tabLayout = (TabLayout) findViewById(R.id.activity_like_tabLayout);
        viewPager = (ViewPager) findViewById(R.id.activity_like_viewPager);
        backImg = (ImageView) findViewById(R.id.activity_like_back);
    }

    @Override
    protected void initData() {
        initFragment();

        fragmentPagerAdapter = new LikeFragmentPagerAdapter(getSupportFragmentManager());
        fragmentPagerAdapter.setFragments(fragments);
        viewPager.setAdapter(fragmentPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.GRAY, Color.WHITE);

        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LikeActivity.this.finish();
            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LikeFilmFragment());
        fragments.add(new LikeBackstageFragment());
    }
}