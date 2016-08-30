package com.example.dllo.vmovie.home;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by dllo on 16/8/30.
 */
public class ChannelFragment extends BaseFragment {

    //首页-频道
    private ChannelAdapter adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected int setLayout() {
        return R.layout.fragment_channel;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.channel_recycler);
    }

    @Override
    protected void initData() {

        adapter = new ChannelAdapter(getContext());
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        NetTool.getInstance().startRequest(NetUtil.CHANNEl, ChannelBean.class, new OnHttpCallBack<ChannelBean>() {
            @Override
            public void onSuccess(ChannelBean response) {
                adapter.setBean(response);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
