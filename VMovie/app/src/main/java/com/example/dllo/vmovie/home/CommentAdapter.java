package com.example.dllo.vmovie.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dllo.vmovie.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


/**
 * Created by dllo on 16/9/1.
 */
public class CommentAdapter extends BaseExpandableListAdapter {

    private CommentBean bean;
    private Context context;

    public CommentAdapter(Context context) {
        this.context = context;
    }

    public void setBean(CommentBean bean) {
        this.bean = bean;
    }

    @Override
    public int getGroupCount() {
        return bean.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return bean.getData().get(groupPosition).getSubcomment().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return bean.getData().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return bean.getData().get(groupPosition).getSubcomment().get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_channel_comment, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.userNameTv.setText(bean.getData().get(groupPosition).getUserinfo().getUsername());

        String addGroupTime = bean.getData().get(groupPosition).getAddtime();

        long thisTime = System.currentTimeMillis();
        long a = Integer.valueOf(addGroupTime);
        long remainTime =  thisTime - a * 1000L;

//        Log.d("CommentAdapter", "remainTime:" + remainTime);

        Date date = new Date(remainTime);
        DateFormat dateFormat = new SimpleDateFormat("d天" +  "hh小时" + "前");
        String time = dateFormat.format(date);
        holder.addTimeTv.setText(time);

        holder.contentTv.setText(bean.getData().get(groupPosition).getContent());
        holder.countApproveTv.setText(bean.getData().get(groupPosition).getCount_approve());

        Glide.with(context).load(bean.getData().get(groupPosition).getUserinfo().getAvatar()).bitmapTransform(new CropCircleTransformation(context)).into(holder.avatarImg);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_channel_child_comment, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.childUserNameTv.setText(bean.getData().get(groupPosition).getSubcomment().get(childPosition).getUserinfo().getUsername());

        childViewHolder.childReplyUserNameTv.setText("回复" + bean.getData().get(groupPosition).getSubcomment().get(childPosition).getReply_userinfo().getUsername() + ":  " + bean.getData().get(groupPosition).getSubcomment().get(childPosition).getContent());


        childViewHolder.childCountApproveTv.setText(bean.getData().get(groupPosition).getSubcomment().get(childPosition).getCount_approve());

        Glide.with(context).load(bean.getData().get(groupPosition).getSubcomment().get(childPosition).getUserinfo().getAvatar()).bitmapTransform(new CropCircleTransformation(context)).into(childViewHolder.childAvatarImg);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class ViewHolder {
        private ImageView avatarImg;
        private TextView userNameTv, contentTv, addTimeTv, countApproveTv;

        public ViewHolder(View itemView) {
            avatarImg = (ImageView) itemView.findViewById(R.id.item_channel_comment_avatar);
            userNameTv = (TextView) itemView.findViewById(R.id.item_channel_comment_userName);
            addTimeTv = (TextView) itemView.findViewById(R.id.item_channel_comment_addTime);
            contentTv = (TextView) itemView.findViewById(R.id.item_channel_comment_content);
            countApproveTv = (TextView) itemView.findViewById(R.id.item_channel_comment_count_approve);
        }
    }

    class ChildViewHolder {
        private ImageView childAvatarImg;
        private TextView childUserNameTv, childAddTimeTv, childReplyUserNameTv, childCountApproveTv;

        public ChildViewHolder(View itemView) {
            childAvatarImg = (ImageView) itemView.findViewById(R.id.item_child_comment_avatarImg);
            childUserNameTv = (TextView) itemView.findViewById(R.id.item_child_comment_userName);
            childAddTimeTv = (TextView) itemView.findViewById(R.id.item_child_comment_addTime);
            childReplyUserNameTv = (TextView) itemView.findViewById(R.id.item_child_comment_reply_userName);
            childCountApproveTv = (TextView) itemView.findViewById(R.id.item_child_comment_count_approve);
        }
    }

}
