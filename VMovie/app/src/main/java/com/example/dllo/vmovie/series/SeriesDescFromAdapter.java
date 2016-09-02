package com.example.dllo.vmovie.series;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.series.SeriesDescFromAdapter.FromHolder;

/**
 * Created by dllo on 16/9/2.
 */
public class SeriesDescFromAdapter extends Adapter<FromHolder>{

    private SeriesDescBean seriesDescBean;
    private Context context;

    public SeriesDescFromAdapter(Context context) {
        this.context = context;
    }

    public void setSeriesDescBean(SeriesDescBean seriesDescBean) {
        this.seriesDescBean = seriesDescBean;
        notifyDataSetChanged();
    }

    @Override
    public FromHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FromHolder(LayoutInflater.from(context).inflate(R.layout.item_series_description_from,parent,false));
    }

    @Override
    public void onBindViewHolder(FromHolder holder, int position) {
        holder.tvFrom.setText(seriesDescBean.getData().getPosts().get(position).getFrom_to());
    }

    @Override
    public int getItemCount() {
        return seriesDescBean.getData().getPosts().size();
    }

    public class FromHolder extends ViewHolder{
        private TextView tvFrom;
        public FromHolder(View itemView) {
            super(itemView);
            tvFrom = (TextView) itemView.findViewById(R.id.tv_series_description_from_item);
        }
    }
}
