package com.example.dllo.vmovie.backstage.adapter;

import android.content.Context;
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
import com.example.dllo.vmovie.backstage.bean.DataBean;

import java.util.ArrayList;


/**
 * Created by 朱麒颢 dllo on 16/8/30.
 * 年轻的战场
 */
public class AllAdapter extends Adapter<AllAdapter.AllHolder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<DataBean> mDataBeanArrayList;
    private OnRecyclerItemClickListener mListener;

    public void setDataBeanArrayList(ArrayList<DataBean> dataBeanArrayList) {
        mDataBeanArrayList = dataBeanArrayList;
        notifyDataSetChanged();
    }

    public void addMoreItem(ArrayList<DataBean> dataBeanArrayList) {
        mDataBeanArrayList.addAll(dataBeanArrayList);
        notifyDataSetChanged();
    }

    public AllAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public AllHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_all, parent, false);
        AllHolder holder = new AllHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AllHolder holder, final int position) {
        holder.tv_title.setText(mDataBeanArrayList.get(position).getTitle());
        holder.tv_share_num.setText(mDataBeanArrayList.get(position).getShare_num());
        holder.tv_like_num.setText(mDataBeanArrayList.get(position).getLike_num());
        Glide.with(mContext).load(mDataBeanArrayList.get(position).getImage()).into(holder.image);
        if (mListener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickPosition = holder.getAdapterPosition();
                    String postId = mDataBeanArrayList.get(clickPosition).getPostid();
                    mListener.onItemClick(v, holder, clickPosition, postId);
                }
            });
        }
    }

    public void setListener(OnRecyclerItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mDataBeanArrayList.size();
    }

    public class AllHolder extends ViewHolder {
        private ImageView image;
        private TextView tv_title, tv_share_num, tv_like_num;

        public AllHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_share_num = (TextView) itemView.findViewById(R.id.tv_share_num);
            tv_like_num = (TextView) itemView.findViewById(R.id.tv_like_num);
        }
    }

    public interface OnRecyclerItemClickListener {
        void onItemClick(View view, ViewHolder holder, int position, String postId);

    }
}
