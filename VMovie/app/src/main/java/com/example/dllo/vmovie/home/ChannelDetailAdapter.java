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
        return new ChannelDetailHolder(LayoutInflater.from(context).inflate(R.layout.item_channel_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ChannelDetailHolder detailHolder = (ChannelDetailHolder) holder;
        detailHolder.titleShow.setText(bean.getData().get(position).getTitle());
        detailHolder.cateNameShow.setText(bean.getData().get(position).getCates().get(0).getCatename());

        //处理时间
        String publish = bean.getData().get(position).getDuration();

        int a = Integer.valueOf(publish);
        Date date = new Date(a * 1000L);
        DateFormat format = new SimpleDateFormat("mm′ss″");
        String time = format.format(date);
        detailHolder.duration.setText(time);

        Glide.with(context).load(bean.getData().get(position).getImage()).into(detailHolder.imageView);

        if (listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int clickPosition = detailHolder.getAdapterPosition();

                    listener.onItemClick(v,detailHolder,clickPosition);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return bean.getData().size();
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

    public interface OnRecyclerItemClickListener{
        void onItemClick(View view, ChannelDetailHolder detailHolder, int position);
    }
}
