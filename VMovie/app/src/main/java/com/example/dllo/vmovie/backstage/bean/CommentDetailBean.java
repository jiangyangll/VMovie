package com.example.dllo.vmovie.backstage.bean;

import java.util.List;

/**
 * Created by 朱麒颢 dllo on 16/8/31.
 * 年轻的战场
 */
public class CommentDetailBean {
    private String status;
    private String msg;
    private String total_num;
    /**
     * commentid : 290895
     * isrecommend : 0
     * count_approve : 18
     * has_approve : 0
     * content : 钱不是万能的，钱是万达的
     * addtime : 1472483892
     * userinfo : {"userid":"810480","username":"goddog","avatar":"http://cs.vmoiver.com/Uploads/avatar/2016/07/09/57808637b858d_80.jpeg","isadmin":"0","is_xpc_author":"0"}
     * reply_username :
     * reply_user_isadmin :
     * reply_userinfo : []
     * subcomment : [{"commentid":"291069","isrecommend":"0","count_approve":"0","has_approve":"0","content":"66666666","addtime":"1472523392","userinfo":{"userid":"909144","username":"好运哥哥","avatar":"http://cs.vmoiver.com/Uploads/avatar/2016/04/12/570c92fd097bb_80.jpeg","isadmin":"0","is_xpc_author":"0"},"reply_username":"goddog","reply_user_isadmin":"0","reply_userinfo":{"userid":"810480","username":"goddog","avatar":"http://cs.vmoiver.com/Uploads/avatar/2016/07/09/57808637b858d_80.jpeg","isadmin":"0","is_xpc_author":"0"}},{"commentid":"291370","isrecommend":"0","count_approve":"0","has_approve":"0","content":"[赞][赞]","addtime":"1472563781","userinfo":{"userid":"963205","username":"小妖精_2","avatar":"http://cs.vmoiver.com/Uploads/avatar/2016/06/17/57641d77412b0_80.jpeg","isadmin":"0","is_xpc_author":"0"},"reply_username":"goddog","reply_user_isadmin":"0","reply_userinfo":{"userid":"810480","username":"goddog","avatar":"http://cs.vmoiver.com/Uploads/avatar/2016/07/09/57808637b858d_80.jpeg","isadmin":"0","is_xpc_author":"0"}}]
     */

    private List<DataBean> data;

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

    public String getTotal_num() {
        return total_num;
    }

    public void setTotal_num(String total_num) {
        this.total_num = total_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String commentid;
        private String isrecommend;
        private String count_approve;
        private String has_approve;
        private String content;
        private String addtime;
        /**
         * userid : 810480
         * username : goddog
         * avatar : http://cs.vmoiver.com/Uploads/avatar/2016/07/09/57808637b858d_80.jpeg
         * isadmin : 0
         * is_xpc_author : 0
         */

        private UserinfoBean userinfo;
        private String reply_username;
        private String reply_user_isadmin;
        private List<?> reply_userinfo;
        /**
         * commentid : 291069
         * isrecommend : 0
         * count_approve : 0
         * has_approve : 0
         * content : 66666666
         * addtime : 1472523392
         * userinfo : {"userid":"909144","username":"好运哥哥","avatar":"http://cs.vmoiver.com/Uploads/avatar/2016/04/12/570c92fd097bb_80.jpeg","isadmin":"0","is_xpc_author":"0"}
         * reply_username : goddog
         * reply_user_isadmin : 0
         * reply_userinfo : {"userid":"810480","username":"goddog","avatar":"http://cs.vmoiver.com/Uploads/avatar/2016/07/09/57808637b858d_80.jpeg","isadmin":"0","is_xpc_author":"0"}
         */

        private List<SubcommentBean> subcomment;

        public String getCommentid() {
            return commentid;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }

        public String getIsrecommend() {
            return isrecommend;
        }

        public void setIsrecommend(String isrecommend) {
            this.isrecommend = isrecommend;
        }

        public String getCount_approve() {
            return count_approve;
        }

        public void setCount_approve(String count_approve) {
            this.count_approve = count_approve;
        }

        public String getHas_approve() {
            return has_approve;
        }

        public void setHas_approve(String has_approve) {
            this.has_approve = has_approve;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public String getReply_username() {
            return reply_username;
        }

        public void setReply_username(String reply_username) {
            this.reply_username = reply_username;
        }

        public String getReply_user_isadmin() {
            return reply_user_isadmin;
        }

        public void setReply_user_isadmin(String reply_user_isadmin) {
            this.reply_user_isadmin = reply_user_isadmin;
        }

        public List<?> getReply_userinfo() {
            return reply_userinfo;
        }

        public void setReply_userinfo(List<?> reply_userinfo) {
            this.reply_userinfo = reply_userinfo;
        }

        public List<SubcommentBean> getSubcomment() {
            return subcomment;
        }

        public void setSubcomment(List<SubcommentBean> subcomment) {
            this.subcomment = subcomment;
        }

        public static class UserinfoBean {
            private String userid;
            private String username;
            private String avatar;
            private String isadmin;
            private String is_xpc_author;

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getIsadmin() {
                return isadmin;
            }

            public void setIsadmin(String isadmin) {
                this.isadmin = isadmin;
            }

            public String getIs_xpc_author() {
                return is_xpc_author;
            }

            public void setIs_xpc_author(String is_xpc_author) {
                this.is_xpc_author = is_xpc_author;
            }
        }

        public static class SubcommentBean {
            private String commentid;
            private String isrecommend;
            private String count_approve;
            private String has_approve;
            private String content;
            private String addtime;
            /**
             * userid : 909144
             * username : 好运哥哥
             * avatar : http://cs.vmoiver.com/Uploads/avatar/2016/04/12/570c92fd097bb_80.jpeg
             * isadmin : 0
             * is_xpc_author : 0
             */

            private UserinfoBean userinfo;
            private String reply_username;
            private String reply_user_isadmin;
            /**
             * userid : 810480
             * username : goddog
             * avatar : http://cs.vmoiver.com/Uploads/avatar/2016/07/09/57808637b858d_80.jpeg
             * isadmin : 0
             * is_xpc_author : 0
             */

            private ReplyUserinfoBean reply_userinfo;

            public String getCommentid() {
                return commentid;
            }

            public void setCommentid(String commentid) {
                this.commentid = commentid;
            }

            public String getIsrecommend() {
                return isrecommend;
            }

            public void setIsrecommend(String isrecommend) {
                this.isrecommend = isrecommend;
            }

            public String getCount_approve() {
                return count_approve;
            }

            public void setCount_approve(String count_approve) {
                this.count_approve = count_approve;
            }

            public String getHas_approve() {
                return has_approve;
            }

            public void setHas_approve(String has_approve) {
                this.has_approve = has_approve;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public UserinfoBean getUserinfo() {
                return userinfo;
            }

            public void setUserinfo(UserinfoBean userinfo) {
                this.userinfo = userinfo;
            }

            public String getReply_username() {
                return reply_username;
            }

            public void setReply_username(String reply_username) {
                this.reply_username = reply_username;
            }

            public String getReply_user_isadmin() {
                return reply_user_isadmin;
            }

            public void setReply_user_isadmin(String reply_user_isadmin) {
                this.reply_user_isadmin = reply_user_isadmin;
            }

            public ReplyUserinfoBean getReply_userinfo() {
                return reply_userinfo;
            }

            public void setReply_userinfo(ReplyUserinfoBean reply_userinfo) {
                this.reply_userinfo = reply_userinfo;
            }

            public static class UserinfoBean {
                private String userid;
                private String username;
                private String avatar;
                private String isadmin;
                private String is_xpc_author;

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getIsadmin() {
                    return isadmin;
                }

                public void setIsadmin(String isadmin) {
                    this.isadmin = isadmin;
                }

                public String getIs_xpc_author() {
                    return is_xpc_author;
                }

                public void setIs_xpc_author(String is_xpc_author) {
                    this.is_xpc_author = is_xpc_author;
                }
            }

            public static class ReplyUserinfoBean {
                private String userid;
                private String username;
                private String avatar;
                private String isadmin;
                private String is_xpc_author;

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getIsadmin() {
                    return isadmin;
                }

                public void setIsadmin(String isadmin) {
                    this.isadmin = isadmin;
                }

                public String getIs_xpc_author() {
                    return is_xpc_author;
                }

                public void setIs_xpc_author(String is_xpc_author) {
                    this.is_xpc_author = is_xpc_author;
                }
            }
        }
    }

}
