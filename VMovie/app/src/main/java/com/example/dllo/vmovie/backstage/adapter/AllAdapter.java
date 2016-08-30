package com.example.dllo.vmovie.backstage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.bean.AllBean;

/**
 * Created by 朱麒颢 dllo on 16/8/30.
 * 年轻的战场
 */
public class AllAdapter extends BaseAdapter {
    private AllBean mAllBean;
    private Context mContext;

    public AllAdapter(Context context) {
        mContext = context;
    }

    public void setAllBean(AllBean allBean) {
        mAllBean = allBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mAllBean.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return mAllBean.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AllHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_all,parent,false);
            holder = new AllHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (AllHolder) convertView.getTag();
        }
        holder.tv_title.setText(mAllBean.getData().get(position).getTitle());
        holder.tv_share_num.setText(mAllBean.getData().get(position).getShare_num());
        holder.tv_like_num.setText(mAllBean.getData().get(position).getLike_num());
        Glide.with(mContext).load(mAllBean.getData().
                get(position).getImage()).into(holder.image);
        return convertView;
    }

   public class AllHolder{
       private ImageView image;
       private TextView tv_title,tv_share_num,tv_like_num;
       public AllHolder(View view) {
           image = (ImageView) view.findViewById(R.id.image);
           tv_title = (TextView) view.findViewById(R.id.tv_title);
           tv_share_num = (TextView) view.findViewById(R.id.tv_share_num);
           tv_like_num = (TextView) view.findViewById(R.id.tv_like_num);
       }
   }
}
