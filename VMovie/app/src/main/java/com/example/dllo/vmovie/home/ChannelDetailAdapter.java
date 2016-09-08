package com.example.dllo.vmovie.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/8/31.
 */
public class ChannelDetailAdapter extends RecyclerView.Adapter {

    //首页-频道-点击进入各个专题
    private ChannelDetailBean bean;
    private Context context;
    private OnRecyclerItemClickListener listener;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //上拉加载更多状态-默认为0
    private int load_more_status = 0;
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //顶部FootView

    public static int countP;

    public ChannelDetailAdapter(Context context) {
        this.context = context;
    }

    public void setBean(ChannelDetailBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //进行判断显示类型，来创建返回不同的View
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_channel_detail, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            ChannelDetailHolder channelDetailHolder = new ChannelDetailHolder(view);
            return channelDetailHolder;
        } else if (viewType == TYPE_FOOTER) {
            View foot_view = LayoutInflater.from(context).inflate(R.layout.recycler_load_more_layout, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            FootViewHolder footViewHolder = new FootViewHolder(foot_view);
            return footViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ChannelDetailHolder) {
            ((ChannelDetailHolder) holder).titleShow.setText(bean.getData().get(position).getTitle());
            ((ChannelDetailHolder) holder).cateNameShow.setText(bean.getData().get(position).getCates().get(0).getCatename());
            String publish = bean.getData().get(position).getDuration();

            //处理时间
            int a = Integer.valueOf(publish);
            Date date = new Date(a * 1000L);
            DateFormat format = new SimpleDateFormat("mm′ss″");
            String time = format.format(date);
            ((ChannelDetailHolder) holder).duration.setText(time);
            Glide.with(context).load(bean.getData().get(position).getImage()).into(((ChannelDetailHolder) holder).imageView);
            holder.itemView.setTag(position);

            if (listener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int clickPosition = ((ChannelDetailHolder) holder).getAdapterPosition();

                        listener.onItemClick(v, ((ChannelDetailHolder) holder), clickPosition);
                    }
                });
            }

        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footViewHolder.foot_view_item_tv.setText("上拉加载更多...");
                    break;
                case LOADING_MORE:
                    footViewHolder.foot_view_item_tv.setText("正在加载更多数据...");
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        countP = bean.getData().size() / 10 + 1;
        Log.d("ChannelDetailAdapter", "countP:" + countP);
        return bean.getData().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("ChannelDetailAdapter", "position:" + position);
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class ChannelDetailHolder extends RecyclerView.ViewHolder {
        private TextView titleShow, cateNameShow, duration;
        private ImageView imageView;

        public ChannelDetailHolder(View itemView) {
            super(itemView);
            titleShow = (TextView) itemView.findViewById(R.id.item_channel_detail_title);
            cateNameShow = (TextView) itemView.findViewById(R.id.item_channel_detail_cateName);
            duration = (TextView) itemView.findViewById(R.id.item_channel_detail_duration);
            imageView = (ImageView) itemView.findViewById(R.id.item_channel_detail_image);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        private TextView foot_view_item_tv;

        public FootViewHolder(View view) {
            super(view);
            foot_view_item_tv = (TextView) view.findViewById(R.id.foot_view_item_tv);
        }
    }

    public void addItem(ChannelDetailBean bean) {
        notifyDataSetChanged();
    }

    public void addMoreItem(ChannelDetailBean bean) {
        for (int i = 0; i < bean.getData().size(); i++) {
            this.bean.getData().add(bean.getData().get(i));
        }
        notifyDataSetChanged();
    }

    public void changeMoreStatus(int status) {
        load_more_status = status;
        notifyDataSetChanged();
    }

    public interface OnRecyclerItemClickListener {
        void onItemClick(View view, ChannelDetailHolder detailHolder, int position);
    }
}
