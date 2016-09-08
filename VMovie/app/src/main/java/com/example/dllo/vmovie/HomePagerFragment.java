package com.example.dllo.vmovie;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.customview.VerticalSwitchTextView;
import com.example.dllo.vmovie.home.ChannelFragment;
import com.example.dllo.vmovie.home.HomeFragmentPagerAdapter;
import com.example.dllo.vmovie.home.NewBean;
import com.example.dllo.vmovie.home.NewFragment;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

import java.util.ArrayList;

/**
 * Created by dllo on 16/8/30.
 */
public class HomePagerFragment extends BaseFragment {

    //首页
    //最新
    private TextView newTv;
    //频道
    private TextView channelTv;
    //实现Tab滑动效果
    private ViewPager mViewPager;

    //动画图片
    private ImageView cursor;

    //动画图片偏移量
    private int offset = 0;
    private int position_one;

    //动画图片宽度
    private int bmpW;

    //当前页卡编号
    private int currIndex = 0;

    //存放Fragment
    private ArrayList<Fragment> fragmentArrayList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void initView(View view) {
        //初始化TextView
        initTextView(view);
        //初始化ImageView
        initImageView(view);
        //初始化Fragment
        initFragment();
        //初始化ViewPager
        initViewPager(view);
    }

    @Override
    protected void initData() {

    }

    //初始化头标
    private void initTextView(View view) {

        //最新头标
        newTv = (TextView) view.findViewById(R.id.new_text);
        //频道头标
        channelTv = (TextView) view.findViewById(R.id.channel_text);

        //添加点击事件
        newTv.setOnClickListener(new MyOnClickListener(0));
        channelTv.setOnClickListener(new MyOnClickListener(1));
    }

    /**
     * 初始化页卡内容区
     */
    private void initViewPager(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.home_view_pager);
        mViewPager.setAdapter(new HomeFragmentPagerAdapter(getChildFragmentManager(), fragmentArrayList));

        //让ViewPager缓存2个页面
        mViewPager.setOffscreenPageLimit(2);

        //设置默认打开第一页
        mViewPager.setCurrentItem(0);

        //将顶部文字恢复默认值
        resetTextViewTextColor();
        newTv.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));

        //设置viewpager页面滑动监听事件
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * 初始化动画
     */
    private void initImageView(View view) {
        cursor = (ImageView) view.findViewById(R.id.cursor);
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 获取分辨率宽度
        int screenW = dm.widthPixels;

        bmpW = (screenW / 3);

        //设置动画图片宽度
        setBmpW(cursor, bmpW);
        offset = 0;

        //动画图片偏移量赋值
        position_one = (int) (screenW / 2.8);
    }

    /**
     * 初始化Fragment，并添加到ArrayList中
     */
    private void initFragment() {
        fragmentArrayList = new ArrayList<Fragment>();
        fragmentArrayList.add(new NewFragment());
        fragmentArrayList.add(new ChannelFragment());
    }

    /**
     * 头标点击监听
     *
     * @author weizhi
     * @version 1.0
     */
    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mViewPager.setCurrentItem(index);
        }
    }

    /**
     * 页卡切换监听
     *
     * @author weizhi
     * @version 1.0
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            Animation animation = null;
            switch (position) {

                //当前为页卡1
                case 0:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(position_one, 0, 0, 0);
                        resetTextViewTextColor();
                        newTv.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    break;

                //当前为页卡2
                case 1:
                    //从页卡1跳转转到页卡2
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, position_one, 0, 0);
                        resetTextViewTextColor();
                        channelTv.setTextColor(getResources().getColor(R.color.main_top_tab_color_2));
                    }
                    break;
            }
            currIndex = position;

            animation.setFillAfter(true);// true:图片停在动画结束位置
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 设置动画图片宽度
     *
     * @param mWidth
     */
    private void setBmpW(ImageView imageView, int mWidth) {
        ViewGroup.LayoutParams para;
        para = imageView.getLayoutParams();
        para.width = mWidth / 2;
        imageView.setLayoutParams(para);
    }

    /**
     * 将顶部文字恢复默认值
     */
    private void resetTextViewTextColor() {
        newTv.setTextColor(getResources().getColor(R.color.white));
        channelTv.setTextColor(getResources().getColor(R.color.white));
    }
}

