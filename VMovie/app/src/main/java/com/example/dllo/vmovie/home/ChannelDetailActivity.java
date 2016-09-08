package com.example.dllo.vmovie.home;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
    private SwipeRefreshLayout swipeRefreshLayout;

    private int lastVisibleItem;

    private LinearLayoutManager linearLayoutManager;

    @Override
    public int setLayout() {
        return R.layout.activity_channel_detail;
    }

    @Override
    protected void initView() {
        returnImg = (ImageView) findViewById(R.id.channel_detail_return);
        headTv = (TextView) findViewById(R.id.channel_detail_head);
        recyclerView = (RecyclerView) findViewById(R.id.channel_detail_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.channel_detail_swipeRefresh);
    }

    @Override
    protected void initData() {

        adapter = new ChannelDetailAdapter(this);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                24, getResources().getDisplayMetrics()));

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //添加分隔线
        recyclerView.addItemDecoration(new RecyclerAdvanceDecoration(this, OrientationHelper.VERTICAL));

        returnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelDetailActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        String cateName = intent.getStringExtra("catename");
        String url = intent.getStringExtra("url");
        final String loadUrl = intent.getStringExtra("loadUrl");
        headTv.setText(cateName);

        NetTool.getInstance().startRequest(url, ChannelDetailBean.class, new OnHttpCallBack<ChannelDetailBean>() {
            @Override
            public void onSuccess(final ChannelDetailBean response) {
                adapter.setBean(response);
                recyclerView.setAdapter(adapter);

                adapter.setListener(new ChannelDetailAdapter.OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(View view, ChannelDetailAdapter.ChannelDetailHolder detailHolder, int position) {
                        Intent skipIntent = new Intent(ChannelDetailActivity.this, ChannelContentActivity.class);
                        skipIntent.putExtra("postId", response.getData().get(position).getPostid());
                        skipIntent.putExtra("shareNumber", response.getData().get(position).getShare_num());
                        skipIntent.putExtra("likeNumber", response.getData().get(position).getLike_num());
                        startActivity(skipIntent);
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
                NetTool.getInstance().startRequest("http://app.vmoiver.com/apiv3/post/getPostByTab?p=1&size=20&tab=latest", ChannelDetailBean.class, new OnHttpCallBack<ChannelDetailBean>() {
                    @Override
                    public void onSuccess(ChannelDetailBean response) {
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

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {
                    adapter.changeMoreStatus(ChannelDetailAdapter.LOADING_MORE);
                    NetTool.getInstance().startRequest(loadUrl + ChannelDetailAdapter.countP, ChannelDetailBean.class, new OnHttpCallBack<ChannelDetailBean>() {
                        @Override
                        public void onSuccess(ChannelDetailBean response) {
                            adapter.addMoreItem(response);
                            adapter.notifyDataSetChanged();
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
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }
}
