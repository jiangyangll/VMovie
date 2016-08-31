package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.base.BaseActivity;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

/**
 * Created by dllo on 16/8/31.
 */
public class ChannelDetailActivity extends BaseActivity {

    //频道-点击后进入各个子频道
    private ImageView returnImg;
    private TextView headTv;
    private RecyclerView recyclerView;
    private ChannelDetailAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_channel_detail;
    }

    @Override
    protected void initView() {
        returnImg = (ImageView) findViewById(R.id.channel_detail_return);
        headTv = (TextView) findViewById(R.id.channel_detail_head);
        recyclerView = (RecyclerView) findViewById(R.id.channel_detail_recyclerView);
    }

    @Override
    protected void initData() {

        adapter = new ChannelDetailAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelDetailActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        String cateName = intent.getStringExtra("catename");
        String url = intent.getStringExtra("url");
        headTv.setText(cateName);

        NetTool.getInstance().startRequest(url, ChannelDetailBean.class, new OnHttpCallBack<ChannelDetailBean>() {
            @Override
            public void onSuccess(ChannelDetailBean response) {
                adapter.setBean(response);
                recyclerView.setAdapter(adapter);

                adapter.setListener(new ChannelDetailAdapter.OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(View view, ChannelDetailAdapter.ChannelDetailHolder detailHolder, int position) {
                        Intent skipIntent = new Intent(ChannelDetailActivity.this,ChannelContentActivity.class);
                        startActivity(skipIntent);
                    }
                });

            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }
}
