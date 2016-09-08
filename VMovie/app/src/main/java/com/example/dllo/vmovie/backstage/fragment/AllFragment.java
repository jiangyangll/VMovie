package com.example.dllo.vmovie.backstage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.activity.AllDetailActivity;
import com.example.dllo.vmovie.backstage.adapter.AllAdapter;
import com.example.dllo.vmovie.backstage.adapter.AllAdapter.OnRecyclerItemClickListener;
import com.example.dllo.vmovie.backstage.adapter.BackStageTitleAdapter;
import com.example.dllo.vmovie.backstage.bean.AllBean;
import com.example.dllo.vmovie.backstage.bean.DataBean;
import com.example.dllo.vmovie.base.BaseFragment;
import com.example.dllo.vmovie.netutil.NetUtil;
import com.example.dllo.vmovie.okhttptool.NetTool;
import com.example.dllo.vmovie.okhttptool.OnHttpCallBack;

import java.util.ArrayList;


/**
 * Created by 朱麒颢 dllo on 16/8/30.
 * 年轻的战场
 */
public class AllFragment extends BaseFragment {
    private AllAdapter mAllAdapter;
    private RecyclerView mRecyclerView;
    public static final String KEY_POSITION = "KEY_POSITION";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager manager;
    private int lastVisibleItem;
    private int p = 1;
    private ArrayList<DataBean> mDataBeanArrayList;

    @Override
    protected int setLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    protected void initData() {
        mDataBeanArrayList = new ArrayList<>();
        mAllAdapter = new AllAdapter(getContext());
        manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        //添加布局动画
        LayoutAnimationController lac = new LayoutAnimationController(AnimationUtils.loadAnimation(getActivity(), R.anim.enter_anim));
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        mRecyclerView.setLayoutAnimation(lac);
        mRecyclerView.startLayoutAnimation();
        mAllAdapter.setListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, ViewHolder holder, int position, String postId) {
                Intent intent = new Intent(getActivity(), AllDetailActivity.class);
                String id = postId;
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        NetTool.getInstance().startRequest(NetUtil.T_BACKSTAGE_EVERY + 1 + NetUtil.BACKSTAGE_EVERY_SIZE + 10 + NetUtil.BACKSTAGE_EVERY_ID + BackStageTitleAdapter.getCateId(getPosition()), AllBean.class, new OnHttpCallBack<AllBean>() {
            @Override
            public void onSuccess(final AllBean response) {
                for (int i = 0; i < response.getData().size(); i++) {
                    DataBean dataBean = new DataBean();
                    dataBean.setTitle(response.getData().get(i).getTitle());
                    dataBean.setShare_num(response.getData().get(i).getShare_num());
                    dataBean.setLike_num(response.getData().get(i).getLike_num());
                    dataBean.setImage(response.getData().get(i).getImage());
                    dataBean.setPostid(response.getData().get(i).getPostid());
                    mDataBeanArrayList.add(dataBean);
                }
                mAllAdapter.setDataBeanArrayList(mDataBeanArrayList);
                mRecyclerView.setAdapter(mAllAdapter);
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mSwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        24, getResources().getDisplayMetrics()));
        //刷新的数据,设置OnRefreshListener监听器,同时实现里面的onRefresh()方法,在该方法中进行网络请求最新数据
        mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetTool.getInstance().startRequest(NetUtil.T_BACKSTAGE_EVERY + 1 + NetUtil.BACKSTAGE_EVERY_SIZE + 10 + NetUtil.BACKSTAGE_EVERY_ID + BackStageTitleAdapter.getCateId(getPosition()), AllBean.class, new OnHttpCallBack<AllBean>() {
                    @Override
                    public void onSuccess(AllBean response) {
                        ArrayList<DataBean> dataBeanArrayList = new ArrayList<>();
                        for (int i = 0; i < response.getData().size(); i++) {
                            DataBean dataBean = new DataBean();
                            dataBean.setTitle(response.getData().get(i).getTitle());
                            dataBean.setShare_num(response.getData().get(i).getShare_num());
                            dataBean.setLike_num(response.getData().get(i).getLike_num());
                            dataBean.setImage(response.getData().get(i).getImage());
                            dataBean.setPostid(response.getData().get(i).getPostid());
                            dataBeanArrayList.add(dataBean);
                        }
                        mAllAdapter.setDataBeanArrayList(dataBeanArrayList);
                        mSwipeRefreshLayout.setRefreshing(false);
                        p = 1;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }
        });

        //RecyclerView设置滑动监听
        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, final int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAllAdapter.getItemCount()) {
                    NetTool.getInstance().startRequest(NetUtil.T_BACKSTAGE_EVERY + (++p) + NetUtil.BACKSTAGE_EVERY_SIZE + 10 + NetUtil.BACKSTAGE_EVERY_ID + BackStageTitleAdapter.getCateId(getPosition()), AllBean.class, new OnHttpCallBack<AllBean>() {
                        @Override
                        public void onSuccess(final AllBean response) {
                            ArrayList<DataBean> dataBeanList = new ArrayList<>();
                            for (int i = 0; i < response.getData().size(); i++) {
                                DataBean dataBean = new DataBean();
                                dataBean.setTitle(response.getData().get(i).getTitle());
                                dataBean.setShare_num(response.getData().get(i).getShare_num());
                                dataBean.setLike_num(response.getData().get(i).getLike_num());
                                dataBean.setImage(response.getData().get(i).getImage());
                                dataBean.setPostid(response.getData().get(i).getPostid());
                                dataBeanList.add(dataBean);
                            }
                            mAllAdapter.addMoreItem(dataBeanList);
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

    private int getPosition() {
        int position;
        Bundle args = getArguments();
        if (args != null) {
            position = args.getInt(KEY_POSITION);
            return position;
        } else return 0;
    }
}
