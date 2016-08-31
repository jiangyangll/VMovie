package com.example.dllo.vmovie.customview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by dllo on 16/8/31.
 */
public class RecyclerWrapAdapter extends RecyclerView.Adapter implements WrapAdapter {

    private RecyclerView.Adapter mAdapter;

    private ArrayList<View> mHeaderViews;

    private ArrayList<View> mFootViews;
    static final ArrayList<View> EMPTY_INFO_LIST = new ArrayList<View>();
    private int mCurrentPosition;

    public RecyclerWrapAdapter(ArrayList<View> mHeaderViews, ArrayList<View> mFootViews, RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
        if (mHeaderViews == null) {
            this.mHeaderViews = EMPTY_INFO_LIST;
        } else {
            this.mHeaderViews = mHeaderViews;
        }
        if (mFootViews == null) {
            this.mFootViews = EMPTY_INFO_LIST;
        } else {
            this.mFootViews = mFootViews;
        }
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerView.INVALID_TYPE) {
            return new HeaderViewHolder(mHeaderViews.get(0));
        } else if (viewType == RecyclerView.INVALID_TYPE - 1) {
            return new HeaderViewHolder(mFootViews.get(0));
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //如果头部不为空，那么我们就要先添加头部，所以我们只要
        //把前面几个position给头部，当position小于头部总数的时候，
        //我们返回头部view。再判断原Adapter 的 count 与当前 position
        // 的差值来比较，是调用原 Adapter 的 getView 方法，还是获取 footView
        // 的 view。
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
                return;
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getHeadersCount() + getFootersCount() + mAdapter.getItemCount();
        } else {
            return getHeadersCount() + getFootersCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        //增加两个类型
        //RecyclerView.INVALID_TYPE 添加头部
        //RecyclerView.INVALID_TYPE-1 添加尾部
        //如果头部不为空，那么我们就要先添加头部，所以我们只要
        //把前面几个position给头部，当position小于头部总数的时候，
        //我们返回头部类型。再判断原Adapter 的 count 与当前 position的差值来比较，
        // 是调用原 Adapter 的 类型，还是获取 footView的类型。
        mCurrentPosition = position;
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return RecyclerView.INVALID_TYPE;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.getItemViewType(adjPosition);
            }
        }
        return RecyclerView.INVALID_TYPE - 1;
    }

    @Override
    public RecyclerView.Adapter getWrappedAdapter() {
        return mAdapter;
    }

    private static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
