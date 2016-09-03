package com.example.dllo.vmovie.series;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/9/2.
 */
public class SeriesDescVideoListAdapter extends BaseAdapter {

    private Context context;
    private SeriesDescBean seriesDescBean;
    private int fromPosition;
    private int clickFromPosition;
    private int clickPosition;

    public SeriesDescVideoListAdapter(Context context) {
        this.context = context;
    }

    public void setSeriesDescBean(SeriesDescBean seriesDescBean) {
        this.seriesDescBean = seriesDescBean;
        notifyDataSetChanged();
    }

    public void setFromPosition(int fromPosition) {
        this.fromPosition = fromPosition;
        notifyDataSetChanged();
    }

    public void setClickPosition(int clickPosition) {
        this.clickPosition = clickPosition;
        notifyDataSetChanged();
    }

    public void setClickFromPosition(int clickFromPosition) {
        this.clickFromPosition = clickFromPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return seriesDescBean.getData().getPosts().get(fromPosition).getList().size();
    }

    @Override
    public Object getItem(int position) {
        return seriesDescBean.getData().getPosts().get(fromPosition).getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_series_description_video_list,parent,false);
            holder = new VideoHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (VideoHolder) convertView.getTag();
        }
        String number = seriesDescBean.getData().getPosts().get(fromPosition).getList().get(position).getNumber();
        String title = seriesDescBean.getData().getPosts().get(fromPosition).getList().get(position).getTitle();
        holder.tvTitle.setText("第" + number + "集:  " + title);
        holder.tvDate.setText(seriesDescBean.getData().getPosts().get(fromPosition).getList().get(position).getAddtime());
        int time = Integer.parseInt(seriesDescBean.getData().getPosts().get(fromPosition).getList().get(position).getDuration());
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String durtion = format.format(date);
        holder.tvDuration.setText(durtion);
        Glide.with(context).load(seriesDescBean.getData().getPosts().get(fromPosition).getList().get(position).getThumbnail()).into(holder.ivVideoImage);
        if (clickFromPosition == fromPosition) {
            if (clickPosition == position) {
                holder.tvPlayState.setVisibility(View.VISIBLE);
            }else {
                holder.tvPlayState.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    private class VideoHolder{
        private TextView tvTitle, tvDate, tvDuration, tvPlayState;
        private ImageView ivVideoImage;
        public VideoHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_series_description_video_list_title);
            tvDate = (TextView) view.findViewById(R.id.tv_series_description_video_list_date);
            tvDuration = (TextView) view.findViewById(R.id.tv_series_description_video_list_duration);
            tvPlayState = (TextView) view.findViewById(R.id.tv_series_description_video_list_play_state);
            ivVideoImage = (ImageView) view.findViewById(R.id.iv_series_description_video_list_image);
        }
    }
}
