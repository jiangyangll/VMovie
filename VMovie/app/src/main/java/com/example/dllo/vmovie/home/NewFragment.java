package com.example.dllo.vmovie.home;

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
public class NewFragment extends BaseFragment {

    //最新
    private RecyclerView recyclerView;
    private NewAdapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected int setLayout() {
        return R.layout.fragment_new;
    }

    @Override
    protected void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.new_recycler);
    }

    @Override
    protected void initData() {
        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new NewAdapter(getContext());
        
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
    }
}