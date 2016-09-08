package com.example.dllo.vmovie.like;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;

import java.util.List;

/**
 * Created by dllo on 16/9/6.
 */
public class LikeBackstageAdapter extends BaseAdapter {

    private Context context;
    private List<LikeBackstageBean> bean;

    public LikeBackstageAdapter(Context context) {
        this.context = context;
    }

    public void setBean(List<LikeBackstageBean> bean) {
        this.bean = bean;
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_like_backstage, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(bean.get(position).getTitle());
        holder.likeNumber.setText(bean.get(position).getLikeNum());
        holder.shareNumber.setText(bean.get(position).getShareNum());
        Glide.with(context).load(bean.get(position).getImageUrl()).into(holder.imageView);
        return convertView;
    }

    class ViewHolder {

        private TextView title, likeNumber, shareNumber;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.item_like_backstage_title);
            likeNumber = (TextView) itemView.findViewById(R.id.item_like_backstage_like_num);
            shareNumber = (TextView) itemView.findViewById(R.id.item_like_backstage_share_num);
            imageView = (ImageView) itemView.findViewById(R.id.item_like_backstage_image);
        }
    }
}
