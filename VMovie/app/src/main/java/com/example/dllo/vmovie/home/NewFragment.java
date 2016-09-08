package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.customview.WrapRecyclerView;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by dllo on 16/8/30.
 */
public class NewFragment extends BaseFragment {

    //最新
    private WrapRecyclerView recyclerView;
    private NewAdapter adapter;
    private LinearLayoutManager manager;

    private BGABanner customBanner;
    private List<View> customViews = new ArrayList<>();

    private SwipeRefreshLayout swipeRefreshLayout;
    private int lastVisibleItem;

    @Override
    protected int setLayout() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (WrapRecyclerView) view.findViewById(R.id.new_recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.new_swipeRefresh);
    }

    @Override
    protected void initData() {
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new NewAdapter(getContext());

        recyclerView.addItemDecoration(new RecyclerAdvanceDecoration(getContext(), OrientationHelper.VERTICAL));

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        //轮播图头布局
        final View headView = LayoutInflater.from(getContext()).inflate(R.layout.wraprecycler_headview, null);
        customBanner = (BGABanner) headView.findViewById(R.id.banner_main_cube);
        recyclerView.addHeaderView(headView);
        //Banner轮播图实现

        customViews = getViews(7);

        customBanner.setViews(customViews);

        NetTool.getInstance().startRequest(NetUtil.NEWEST_RECYCLER, CarouselBean.class, new OnHttpCallBack<CarouselBean>() {
            @Override
            public void onSuccess(final CarouselBean response) {
                for (int i = 0; i < response.getData().size(); i++) {
                    SimpleDraweeView simpleDraweeView;
                    simpleDraweeView = (SimpleDraweeView) customViews.get(i);
                    simpleDraweeView.setImageURI(Uri.parse(response.getData().get(i).getImage()));

                    String type = response.getData().get(i).getExtra_data().getApp_banner_type();
                    String params = response.getData().get(i).getExtra_data().getApp_banner_param();
                    final CarouselData data = new CarouselData();
                    data.setType(type);
                    data.setParams(params);

                    simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), CarouselActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("params", data);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        NetTool.getInstance().startRequest(NetUtil.NEWEST, NewBean.class, new OnHttpCallBack<NewBean>() {
            @Override
            public void onSuccess(final NewBean response) {
                adapter.setBean(response);
                recyclerView.setAdapter(adapter);

                adapter.setListener(new NewAdapter.OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(View view, NewAdapter.NewHolder newHolder, int position) {
                        Intent intent = new Intent(getContext(), NewDetail.class);
                        intent.putExtra("postId", response.getData().get(position - 1).getPostid());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetTool.getInstance().startRequest(NetUtil.NEWEST, NewBean.class, new OnHttpCallBack<NewBean>() {
                    @Override
                    public void onSuccess(NewBean response) {
                        adapter.addItem(response);
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
            }
        });

        //RecyclerView滑动监听
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ( lastVisibleItem  == adapter.getItemCount()) {
                    NetTool.getInstance().startRequest("http://app.vmoiver.com/apiv3/post/getPostByTab?p=" + NewAdapter.countP + "&size=20&tab=latest", NewBean.class, new OnHttpCallBack<NewBean>() {
                        @Override
                        public void onSuccess(NewBean response) {

                            Log.d("NewFragment", "---->+++++++++");
                            adapter.addMoreItem(response);
                            adapter.notifyDataSetChanged();
                            //recyclerView.setAdapter(adapter);
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

    private List<View> getViews(int count) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.view_banner, null);
            views.add(view);
        }
        return views;
    }
}