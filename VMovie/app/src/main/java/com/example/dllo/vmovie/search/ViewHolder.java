package com.example.dllo.vmovie.search;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by dllo on 16/9/7.
 */
public class ViewHolder {

    private SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;
    private int mPosition;

    //初始化viewHolder
    public ViewHolder(Context context, int layoutId, ViewGroup parent, int position) {
        mContext = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mViews = new SparseArray<>();
        mPosition = position;
        mConvertView.setTag(this);
    }

    //获取viewHolder
    public static ViewHolder getHolder(Context context, View convertView, int layoutId, ViewGroup parent, int position) {
        if (convertView == null) {
            return new ViewHolder(context, layoutId, parent, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    //getView
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    //setText
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    //set url image
    public ViewHolder setImageResource(int viewId, String url) {
        ImageView iv = getView(viewId);
        Glide.with(mContext).load(url).into(iv);
        return this;
    }
}
