package com.example.dllo.vmovie.backstage.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.adapter.BackStageTitleAdapter;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.backstage.bean.BackStageTitleBean;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by dllo on 16/8/30.
 */
public class BackStageFragment extends BaseFragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private BackStageTitleAdapter mTitleAdapter;

    //幕后
    @Override
    protected int setLayout() {
        return R.layout.fragment_backstage;
    }

    @Override
    protected void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);

    }

    @Override
    protected void initData() {
        //Fragment管理Fragment的时候,使用getChildFragmentManager()
        mTitleAdapter = new BackStageTitleAdapter(getChildFragmentManager());
        //用OK解析标题
        NetTool.getInstance().startRequest(NetUtil.BACKSTAGE, BackStageTitleBean.class, new OnHttpCallBack<BackStageTitleBean>() {
            @Override
            public void onSuccess(BackStageTitleBean response) {
                mTitleAdapter.setTitleBean(response);
                mViewPager.setAdapter(mTitleAdapter);
                mTabLayout.setupWithViewPager(mViewPager);

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setSelectedTabIndicatorColor(Color.BLACK);
    }
}
