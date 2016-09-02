package com.example.dllo.vmovie.backstage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;
import com.example.dllo.vmovie.backstage.bean.CommentDetailBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by 朱麒颢 dllo on 16/8/31.
 * 年轻的战场
 */
public class CommentDetailAdapter extends BaseExpandableListAdapter {
    private CommentDetailBean mBean;
    private Context mContext;
    private int time;

    public void setBean(CommentDetailBean bean) {
        mBean = bean;
    }

    public CommentDetailAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mBean.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mBean.getData().get(groupPosition).getSubcomment().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mBean.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mBean.getData().get(groupPosition).getSubcomment().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CommentDetailHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_comment_detail, parent, false);
            holder = new CommentDetailHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CommentDetailHolder) convertView.getTag();
        }
        holder.tv_username.setText(mBean.getData().get(groupPosition).getUserinfo().getUsername());
        //处理时间
        String addTime = mBean.getData().get(groupPosition).getAddtime();
        time = Integer.valueOf(addTime);
        Date date = new Date(time * 1000L);
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String newTime = format.format(date);
        holder.tv_addtime.setText(newTime);

        holder.tv_content.setText(mBean.getData().get(groupPosition).getContent());
        holder.tv_count_approve.setText(mBean.getData().get(groupPosition).getCount_approve() + "");
        //添加一个依赖(compile 'jp.wasabeef:glide-transformations:2.0.0'),然后再在下面加上一句即可实现圆形图片
        Glide.with(mContext).load(mBean.getData()
                .get(groupPosition).getUserinfo().
                        getAvatar()).bitmapTransform(new
                CropCircleTransformation(mContext)).
                into(holder.image_avatar);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ReplyDetailHolder replyHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_subcomment_reply, parent, false);
            replyHolder = new ReplyDetailHolder(convertView);
            convertView.setTag(replyHolder);
        } else {
            replyHolder = (ReplyDetailHolder) convertView.getTag();
        }
        replyHolder.tv_subcomment_username.setText(mBean.getData().get(groupPosition).getSubcomment().get(childPosition).getUserinfo().getUsername());
        //处理时间
        String addTime = mBean.getData().get(groupPosition).getSubcomment().get(childPosition).getAddtime();
        time = Integer.valueOf(addTime);
        Date date = new Date(time * 1000L);
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        String newTime = format.format(date);
        replyHolder.tv_subcomment_addtime.setText(newTime);

        replyHolder.tv_reply_username.setText("回复" + " " + mBean.getData().get(groupPosition).getSubcomment().get(childPosition).getReply_userinfo().getUsername() + ":   "
                + mBean.getData().get(groupPosition).getSubcomment().get(childPosition).getContent());
        replyHolder.tv_subcomment_count_approve.setText(mBean.getData().get(groupPosition).getSubcomment().get(childPosition).getCount_approve() + "");
        Glide.with(mContext).load(mBean.getData().get(groupPosition).getSubcomment().
                get(childPosition).getUserinfo().getAvatar()).
                bitmapTransform(new CropCircleTransformation(mContext)).into(replyHolder.image_subcomment_avatar);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public class CommentDetailHolder {
        private TextView tv_username, tv_addtime, tv_content, tv_count_approve;
        private ImageView image_avatar, image_comment_handle;

        public CommentDetailHolder(View view) {
            tv_username = (TextView) view.findViewById(R.id.tv_username);
            tv_addtime = (TextView) view.findViewById(R.id.tv_addtime);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            tv_count_approve = (TextView) view.findViewById(R.id.tv_count_approve);
            image_avatar = (ImageView) view.findViewById(R.id.image_avatar);
            image_comment_handle = (ImageView) view.findViewById(R.id.image_comment_handle);
        }
    }

    public class ReplyDetailHolder {
        private TextView tv_subcomment_username, tv_subcomment_addtime, tv_reply_username, tv_subcomment_count_approve;
        private ImageView image_subcomment_avatar, image_subcomment_handle;

        public ReplyDetailHolder(View view) {
            tv_subcomment_username = (TextView) view.findViewById(R.id.tv_subcomment_username);
            tv_subcomment_addtime = (TextView) view.findViewById(R.id.tv_subcomment_addtime);
            tv_reply_username = (TextView) view.findViewById(R.id.tv_reply_username);
            tv_subcomment_count_approve = (TextView) view.findViewById(R.id.tv_subcomment_count_approve);
            image_subcomment_avatar = (ImageView) view.findViewById(R.id.image_subcomment_avatar);
            image_subcomment_handle = (ImageView) view.findViewById(R.id.image_subcomment_handle);

        }
    }
}