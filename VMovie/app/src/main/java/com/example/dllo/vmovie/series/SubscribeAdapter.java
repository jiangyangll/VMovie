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
import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class SubscribeAdapter extends BaseAdapter{

    private Context context;
    private List<SubscribeListBean> beanList;

    public SubscribeAdapter(Context context) {
        this.context = context;
    }

    public void setBeanList(List<SubscribeListBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_subscribe_video_list,parent,false);
            holder = new VideoHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (VideoHolder) convertView.getTag();
        }
        String title = beanList.get(position).getTitle();
        String number = beanList.get(position).getNumber();
        holder.tvTitle.setText("第" + number + "集:  " + title);
        holder.tvDate.setText(beanList.get(position).getAddtime());
        int time = Integer.parseInt(beanList.get(position).getDuration());
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        String durtion = format.format(date);
        holder.tvDuration.setText(durtion);
        Glide.with(context).load(beanList.get(position).getThumbnail()).into(holder.ivVideoImage);
        return convertView;
    }

    private class VideoHolder{
        private TextView tvTitle, tvDate, tvDuration;
        private ImageView ivVideoImage;
        public VideoHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_subscribe_video_list_title);
            tvDate = (TextView) view.findViewById(R.id.tv_subscribe_video_list_date);
            tvDuration = (TextView) view.findViewById(R.id.tv_subscribe_video_list_duration);
            ivVideoImage = (ImageView) view.findViewById(R.id.iv_subscribe_video_list_image);
        }
    }
}
