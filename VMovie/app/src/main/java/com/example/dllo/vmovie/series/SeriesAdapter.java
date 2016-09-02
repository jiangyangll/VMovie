package com.example.dllo.vmovie.series;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.series.SeriesAdapter.SeriesHolder;

/**
 * Created by dllo on 16/8/30.
 */
public class SeriesAdapter extends Adapter<SeriesHolder>{

    private Context context;
    private SeriesBean seriesBean;

    public SeriesAdapter(Context context) {
        this.context = context;
    }

    public void setSeriesBean(SeriesBean seriesBean) {
        this.seriesBean = seriesBean;
        notifyDataSetChanged();
    }


    @Override
    public SeriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeriesHolder(LayoutInflater.from(context).inflate(R.layout.item_series,parent,false));
    }

    @Override
    public void onBindViewHolder(SeriesHolder holder, final int position) {
        holder.tvTitle.setText(seriesBean.getData().get(position).getTitle());
        holder.tvDes.setText("已更新至" + seriesBean.getData().get(position).getUpdate_to() + "集"
        + "     " + seriesBean.getData().get(position).getFollower_num() + "人已订阅");
        holder.tvContent.setText(seriesBean.getData().get(position).getContent());
        Glide.with(context).load(seriesBean.getData().get(position).getImage()).into(holder.ivShow);
        holder.ivShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SeriesDesActivity.class);
                intent.putExtra("seriesId",seriesBean.getData().get(position).getSeriesid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesBean.getData().size();
    }

    public class SeriesHolder extends ViewHolder{
        private TextView tvTitle, tvDes, tvContent;
        private ImageView ivShow;
        public SeriesHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_series_item_title);
            tvDes = (TextView) itemView.findViewById(R.id.tv_series_item_des);
            tvContent = (TextView) itemView.findViewById(R.id.tv_series_item_content);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_series_item_pic);
        }
    }
}
