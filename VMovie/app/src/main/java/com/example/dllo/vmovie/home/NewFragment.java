package com.example.dllo.vmovie.home;

import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        NetTool.getInstance().startRequest("http://app.vmoiver.com/apiv3/index/getBanner", CarouselBean.class, new OnHttpCallBack<CarouselBean>() {
            @Override
            public void onSuccess(CarouselBean response) {
                for (int i = 0; i < response.getData().size(); i++) {
                    Log.d("MainActivity", response.getData().get(i).getImage());
                    SimpleDraweeView simpleDraweeView;
                    simpleDraweeView = (SimpleDraweeView) customViews.get(i);
                    simpleDraweeView.setImageURI(Uri.parse(response.getData().get(i).getImage()));
                }
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        NetTool.getInstance().startRequest(NetUtil.NEWEST, NewBean.class, new OnHttpCallBack<NewBean>() {
            @Override
            public void onSuccess(NewBean response) {
                adapter.setBean(response);
                recyclerView.setAdapter(adapter);
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