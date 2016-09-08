package com.example.dllo.vmovie.search;

import android.content.Context;

import com.example.dllo.vmovie.R;

/**
 * Created by dllo on 16/9/7.
 */
public class SearchAdapter extends CustomAdapter {
    public SearchAdapter(Context context, SearchBean bean, int layoutId) {
        super(context, bean, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setImageResource(R.id.item_search_iv_icon, bean.getData().get(position).getImage())
                .setText(R.id.item_search_tv_title, bean.getData().get(position).getTitle())
                .setText(R.id.item_search_tv_like, bean.getData().get(position).getLike_num())
                .setText(R.id.item_search_tv_share, bean.getData().get(position).getShare_num());
    }
}
