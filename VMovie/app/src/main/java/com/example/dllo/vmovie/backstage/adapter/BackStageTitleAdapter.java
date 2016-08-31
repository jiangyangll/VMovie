package com.example.dllo.vmovie.backstage.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dllo.vmovie.backstage.bean.BackStageTitleBean;
import com.example.dllo.vmovie.backstage.fragment.AllFragment;

import java.util.ArrayList;

/**
 * Created by 朱麒颢 dllo on 16/8/30.
 * 年轻的战场
 */
public class BackStageTitleAdapter extends FragmentPagerAdapter {
    private static BackStageTitleBean mTitleBean;
    private ArrayList<String> titles;

    public BackStageTitleAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setTitleBean(BackStageTitleBean titleBean) {
        mTitleBean = titleBean;
        titles = new ArrayList<>();
        for (int i = 0; i < mTitleBean.getData().size(); i++) {
            titles.add(mTitleBean.getData().get(i).getCatename());
        }
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        args.putInt(AllFragment.KEY_POSITION,position);
        AllFragment allFragment = new AllFragment();
        allFragment.setArguments(args);
        return allFragment;
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public static String getCateId(int position){
        String id = mTitleBean.getData().get(position).getCateid();
        return id;
    }
}
