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
 * Created by dllo on 16/8/30.
 */
public class NewAdapter extends RecyclerView.Adapter {

    //首页-最新
    private NewBean bean;
    private Context context;
    private OnRecyclerItemClickListener listener;

    public NewAdapter(Context context) {
        this.context = context;
    }

    public void setBean(NewBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewHolder(LayoutInflater.from(context).inflate(R.layout.item_new,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewHolder newHolder = (NewHolder) holder;
        newHolder.titleShow.setText(bean.getData().get(position).getTitle());
        newHolder.cateNameShow.setText(bean.getData().get(position).getCates().get(0).getCatename());

        //处理
        String publish = bean.getData().get(position).getDuration();
        int a = Integer.valueOf(publish);
//        Date date = new Date(1472141100000L);
        Date date = new Date(a * 1000L);
        DateFormat format = new SimpleDateFormat("mm′ss″");
        String time = format.format(date);
        newHolder.publishTimeShow.setText(time);

        Glide.with(context).load(bean.getData().get(position).getImage()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(newHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return bean.getData().size();
    }

    public class NewHolder extends RecyclerView.ViewHolder {
        private TextView titleShow, cateNameShow, publishTimeShow;
        private ImageView imageView;

        public NewHolder(View itemView) {
            super(itemView);
            titleShow = (TextView) itemView.findViewById(R.id.new_title);
            cateNameShow = (TextView) itemView.findViewById(R.id.new_cateName);
            publishTimeShow = (TextView) itemView.findViewById(R.id.new_publishTime);
            imageView = (ImageView) itemView.findViewById(R.id.new_image);
        }
    }

    public interface OnRecyclerItemClickListener{
        void onItemClick(View view, NewHolder newHolder, int position);
    }
}