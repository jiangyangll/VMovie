package com.example.dllo.vmovie.backstage.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.adapter.AllAdapter;
import com.example.dllo.vmovie.backstage.adapter.BackStageTitleAdapter;
import com.example.dllo.vmovie.backstage.bean.AllBean;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by 朱麒颢 dllo on 16/8/30.
 * 年轻的战场
 */
public class AllFragment extends BaseFragment {
    private AllAdapter mAllAdapter;
    private ListView mListView;

    public static final String KEY_POSITION = "KEY_POSITION";

    @Override
    protected int setLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.listView);

    }

    @Override
    protected void initData() {
        mAllAdapter = new AllAdapter(getContext());
        NetTool.getInstance().startRequest(NetUtil.T_BACKSTAGE_EVERY + 1 + NetUtil.BACKSTAGE_EVERY_SIZE + 10 + NetUtil.BACKSTAGE_EVERY_ID + BackStageTitleAdapter.getId(getPosition()), AllBean.class, new OnHttpCallBack<AllBean>() {
            @Override
            public void onSuccess(AllBean response) {
                mAllAdapter.setAllBean(response);
                mListView.setAdapter(mAllAdapter);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
    private int getPosition() {
        int position;
        Bundle args = getArguments();
        if (args != null) {
            position = args.getInt(KEY_POSITION);
            return position;
        } else return 0;

    }
}
