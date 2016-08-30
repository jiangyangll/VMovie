package com.example.dllo.vmovie.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.netutil.NetUtil;

/**
 * Created by dllo on 16/8/30.
 */
public class ChannelAdapter extends RecyclerView.Adapter {

    //首页-最新
    private ChannelBean bean;
    private Context context;
    private OnRecyclerItemClickListener listener;

    public ChannelAdapter(Context context) {
        this.context = context;
    }

    public void setBean(ChannelBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChannelHolder(LayoutInflater.from(context).inflate(R.layout.item_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChannelHolder channelHolder = (ChannelHolder) holder;

        if (position == 0) {
            channelHolder.cateNameShow.setText("热门");
            Glide.with(context).load(NetUtil.CHANNEL_HOT_PIC).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(channelHolder.imageView);

        } else if (position == 1) {
            channelHolder.cateNameShow.setText("专题");
            Glide.with(context).load(NetUtil.CHANNEL_SPECIAL_PIC).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(channelHolder.imageView);

        } else {
            channelHolder.cateNameShow.setText(bean.getData().get(position - 2).getCatename());
            Glide.with(context).load(bean.getData().get(position - 2).getIcon()).placeholder(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).into(channelHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return bean.getData().size() + 2;
    }

    public class ChannelHolder extends RecyclerView.ViewHolder {
        private TextView cateNameShow;
        private ImageView imageView;

        public ChannelHolder(View itemView) {
            super(itemView);
            cateNameShow = (TextView) itemView.findViewById(R.id.channel_cateName);
            imageView = (ImageView) itemView.findViewById(R.id.channel_image);
        }
    }

    public interface OnRecyclerItemClickListener {
        void onItemClick(View view, ChannelHolder newHolder, int position);
    }

}
