package com.example.dllo.vmovie.series;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
public class SeriesFragment extends BaseFragment {

    private RecyclerView recyclerSeries;
    private SeriesAdapter seriesAdapter;
    private SwipeRefreshLayout refreshLayout;

    ///系列

    @Override
    protected int setLayout() {
        return R.layout.fragment_series;
    }

    @Override
    protected void initView(View view) {
        recyclerSeries = (RecyclerView) view.findViewById(R.id.recycler_series);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_series);
    }

    @Override
    protected void initData() {
        seriesAdapter = new SeriesAdapter(getContext());
        NetTool.getInstance().startRequest(NetUtil.SERIES_START, SeriesBean.class, new OnHttpCallBack<SeriesBean>() {
            @Override
            public void onSuccess(SeriesBean response) {
                seriesAdapter.setSeriesBean(response);
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerSeries.setLayoutManager(manager);
                recyclerSeries.setAdapter(seriesAdapter);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
