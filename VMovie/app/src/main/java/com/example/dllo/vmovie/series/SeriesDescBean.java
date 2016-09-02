package com.example.dllo.vmovie.series;

import java.util.List;

/**
 * Created by dllo on 16/9/1.
 */
public class SeriesDescBean {

    private String status;
    private String msg;

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String seriesid;
        private String title;
        private String image;
        private String content;
        private String weekly;
        private String count_follow;
        private String isfollow;
        private String share_link;
        private String is_end;
        private String update_to;
        private String tag_name;
        private String post_num_per_seg;
        private List<PostsBean> posts;

        public String getSeriesid() {
            return seriesid;
        }

        public void setSeriesid(String seriesid) {
            this.seriesid = seriesid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getWeekly() {
            return weekly;
        }

        public void setWeekly(String weekly) {
            this.weekly = weekly;
        }

        public String getCount_follow() {
            return count_follow;
        }

        public void setCount_follow(String count_follow) {
            this.count_follow = count_follow;
        }

        public String getIsfollow() {
            return isfollow;
        }

        public void setIsfollow(String isfollow) {
            this.isfollow = isfollow;
        }

        public String getShare_link() {
            return share_link;
        }

        public void setShare_link(String share_link) {
            this.share_link = share_link;
        }

        public String getIs_end() {
            return is_end;
        }

        public void setIs_end(String is_end) {
            this.is_end = is_end;
        }

        public String getUpdate_to() {
            return update_to;
        }

        public void setUpdate_to(String update_to) {
            this.update_to = update_to;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getPost_num_per_seg() {
            return post_num_per_seg;
        }

        public void setPost_num_per_seg(String post_num_per_seg) {
            this.post_num_per_seg = post_num_per_seg;
        }

        public List<PostsBean> getPosts() {
            return posts;
        }

        public void setPosts(List<PostsBean> posts) {
            this.posts = posts;
        }

        public static class PostsBean {
            private String from_to;
            private List<ListBean> list;

            public String getFrom_to() {
                return from_to;
            }

            public void setFrom_to(String from_to) {
                this.from_to = from_to;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String series_postid;
                private String number;
                private String title;
                private String addtime;
                private String duration;
                private String thumbnail;
                private String source_link;

                public String getSeries_postid() {
                    return series_postid;
                }

                public void setSeries_postid(String series_postid) {
                    this.series_postid = series_postid;
                }

                public String getNumber() {
                    return number;
                }

                public void setNumber(String number) {
                    this.number = number;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getAddtime() {
                    return addtime;
                }

                public void setAddtime(String addtime) {
                    this.addtime = addtime;
                }

                public String getDuration() {
                    return duration;
                }

                public void setDuration(String duration) {
                    this.duration = duration;
                }

                public String getThumbnail() {
                    return thumbnail;
                }

                public void setThumbnail(String thumbnail) {
                    this.thumbnail = thumbnail;
                }

                public String getSource_link() {
                    return source_link;
                }

                public void setSource_link(String source_link) {
                    this.source_link = source_link;
                }
            }
        }
    }
}
