package com.example.dllo.vmovie.home;

import android.content.Intent;
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
            public void onSuccess(final ChannelBean response) {
                adapter.setBean(response);
                recyclerView.setAdapter(adapter);

                adapter.setListener(new ChannelAdapter.OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(View view, ChannelAdapter.ChannelHolder newHolder, int position) {
                        Intent intent = new Intent(getContext(), ChannelDetailActivity.class);
                        String cateName = null;
                        int cateId;
                        String url = null;

                        if (position == 0) {
                            cateName = "热门";
                            url = NetUtil.CHANNEL_HOT;
                        } else if (position == 1) {
                            cateName = "专题";
                            url = NetUtil.CHANNEL_SPECIAL;
                        } else {
                            cateName = response.getData().get(position - 2).getCatename();
                            cateId = Integer.valueOf(response.getData().get(position - 2).getCateid());
                            url = NetUtil.CHANNEL_DETAIL_LEFT + cateId + NetUtil.CHANNEL_DETAIL_RIGHT;
                        }
                        intent.putExtra("catename", cateName);
                        intent.putExtra("url",url);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
}