package com.example.dllo.vmovie.like;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dllo on 16/9/6.
 */
public class LikeFilmAdapter extends BaseAdapter {

    private Context context;
    private List<LikeFilmBean> bean;

    public void setBean(List<LikeFilmBean> bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public LikeFilmAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_like_film, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(bean.get(position).getTitle());

        Date date = new Date(Integer.valueOf(bean.get(position).getDuration()) * 1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String thisTime = simpleDateFormat.format(date);
        holder.duration.setText(thisTime);

        holder.shareNum.setText(bean.get(position).getShareNum());
        holder.ratingNum.setText(bean.get(position).getRatingNum());
        Glide.with(context).load(bean.get(position).getImgUrl()).into(holder.imageView);

        String a = bean.get(position).getRatingNum();
        float ratingNumber = Float.valueOf(a) / 2;
        holder.ratingBar.setRating(ratingNumber);
        return convertView;
    }

    class ViewHolder {
        private TextView title, duration, shareNum, ratingNum;
        private ImageView imageView;
        private RatingBar ratingBar;

        public ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.item_like_film_title);
            duration = (TextView) itemView.findViewById(R.id.item_like_film_duration);
            shareNum = (TextView) itemView.findViewById(R.id.item_like_film_share_num);
            ratingNum = (TextView) itemView.findViewById(R.id.item_like_film_rating_num);
            imageView = (ImageView) itemView.findViewById(R.id.item_like_film_image);
            ratingBar = (RatingBar) itemView.findViewById(R.id.item_like_film_rating);
        }
    }

}
