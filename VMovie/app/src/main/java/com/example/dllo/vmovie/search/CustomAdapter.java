package com.example.dllo.vmovie.search;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by dllo on 16/9/7.
 */
public abstract class CustomAdapter extends BaseAdapter {

    protected Context mContext;
    protected SearchBean bean;
    protected int mLayoutId;

    public CustomAdapter(Context context, SearchBean searchBean, int layoutId) {
        mContext = context;
        bean = searchBean;
        mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return bean.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getHolder(mContext, convertView, mLayoutId, parent, position);
        convert(holder, position);
        return holder.getConvertView();
    }

    //get holder convert
    public abstract void convert(ViewHolder holder, int position);

}
