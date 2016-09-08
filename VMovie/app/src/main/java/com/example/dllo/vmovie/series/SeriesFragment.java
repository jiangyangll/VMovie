package com.example.dllo.vmovie.series;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/8/30.
 */
public class SeriesFragment extends BaseFragment {

    private RecyclerView recyclerSeries;
    private SeriesAdapter seriesAdapter;
    private SwipeRefreshLayout refreshLayout;
    private List<DataBean> dataBeanList;
    private int lastVisibleItem;
    private LinearLayoutManager manager;
    private int p = 1;

    ///系列

    @Override
    protected int setLayout() {
        return R.layout.fragment_series;
    }

    @Override
    protected void initView(View view) {
        recyclerSeries = (RecyclerView) view.findViewById(R.id.recycler_series);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_series);
        refreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        refreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.BLUE,Color.GREEN);
    }

    @Override
    protected void initData() {
        seriesAdapter = new SeriesAdapter(getContext());
        dataBeanList = new ArrayList<>();
        manager = new LinearLayoutManager(getContext());
        recyclerSeries.setLayoutManager(manager);

        NetTool.getInstance().startRequest(NetUtil.SERIES_START, SeriesBean.class, new OnHttpCallBack<SeriesBean>() {
            @Override
            public void onSuccess(SeriesBean response) {
                // 待定
                refreshData(response);
                seriesAdapter.setDataBeanList(dataBeanList);

                recyclerSeries.setAdapter(seriesAdapter);

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetTool.getInstance().startRequest(NetUtil.SERIES_START, SeriesBean.class, new OnHttpCallBack<SeriesBean>() {
                    @Override
                    public void onSuccess(SeriesBean response) {

                        List<DataBean> dataBeanList = new ArrayList<>();
                        for (int i = 0; i < response.getData().size(); i++) {
                            DataBean dataBean = new DataBean();
                            dataBean.setApp_image(response.getData().get(i).getApp_image());
                            dataBean.setContent(response.getData().get(i).getContent());
                            dataBean.setFollower_num(response.getData().get(i).getFollower_num());
                            dataBean.setImage(response.getData().get(i).getImage());
                            dataBean.setIs_end(response.getData().get(i).getIs_end());
                            dataBean.setIsfollow(response.getData().get(i).getIsfollow());
                            dataBean.setSeriesid(response.getData().get(i).getSeriesid());
                            dataBean.setTitle(response.getData().get(i).getTitle());
                            dataBean.setUpdate_to(response.getData().get(i).getUpdate_to());
                            dataBean.setWeekly(response.getData().get(i).getWeekly());
                            dataBeanList.add(dataBean);
                        }
                        seriesAdapter.setDataBeanList(dataBeanList);

                        refreshLayout.setRefreshing(false);
                        p = 1;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }
        });


        recyclerSeries.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == seriesAdapter.getItemCount()) {
                    NetTool.getInstance().startRequest(NetUtil.SERIES_REFRESH + (++p), SeriesBean.class, new OnHttpCallBack<SeriesBean>() {
                        @Override
                        public void onSuccess(SeriesBean response) {
                            List<DataBean> dataBeanList = new ArrayList<>();
                            for (int i = 0; i < response.getData().size(); i++) {
                                DataBean dataBean = new DataBean();
                                dataBean.setApp_image(response.getData().get(i).getApp_image());
                                dataBean.setContent(response.getData().get(i).getContent());
                                dataBean.setFollower_num(response.getData().get(i).getFollower_num());
                                dataBean.setImage(response.getData().get(i).getImage());
                                dataBean.setIs_end(response.getData().get(i).getIs_end());
                                dataBean.setIsfollow(response.getData().get(i).getIsfollow());
                                dataBean.setSeriesid(response.getData().get(i).getSeriesid());
                                dataBean.setTitle(response.getData().get(i).getTitle());
                                dataBean.setUpdate_to(response.getData().get(i).getUpdate_to());
                                dataBean.setWeekly(response.getData().get(i).getWeekly());
                                dataBeanList.add(dataBean);
                            }
                            seriesAdapter.addDataList(dataBeanList);
                            recyclerSeries.setLayoutManager(manager);
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = manager.findLastVisibleItemPosition();
            }
        });
    }

    private void refreshData(SeriesBean seriesBean){
        for (int i = 0; i < seriesBean.getData().size(); i++) {
            DataBean dataBean = new DataBean();
            dataBean.setApp_image(seriesBean.getData().get(i).getApp_image());
            dataBean.setContent(seriesBean.getData().get(i).getContent());
            dataBean.setFollower_num(seriesBean.getData().get(i).getFollower_num());
            dataBean.setImage(seriesBean.getData().get(i).getImage());
            dataBean.setIs_end(seriesBean.getData().get(i).getIs_end());
            dataBean.setIsfollow(seriesBean.getData().get(i).getIsfollow());
            dataBean.setSeriesid(seriesBean.getData().get(i).getSeriesid());
            dataBean.setTitle(seriesBean.getData().get(i).getTitle());
            dataBean.setUpdate_to(seriesBean.getData().get(i).getUpdate_to());
            dataBean.setWeekly(seriesBean.getData().get(i).getWeekly());
            dataBeanList.add(dataBean);
        }
    }
}
