package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

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
    private RecyclerView.LayoutManager manager;

    private BGABanner customBanner;
    private List<View> customViews = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (WrapRecyclerView) view.findViewById(R.id.new_recycler);
    }

    @Override
    protected void initData() {
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new NewAdapter(getContext());

        //轮播图头布局
        final View headView = LayoutInflater.from(getContext()).inflate(R.layout.wraprecycler_headview, null);
        customBanner = (BGABanner) headView.findViewById(R.id.banner_main_cube);

        //Banner轮播图实现
        customViews = getViews(6);
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
                            bundle.putParcelable("params",data);
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
        recyclerView.addHeaderView(headView);
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