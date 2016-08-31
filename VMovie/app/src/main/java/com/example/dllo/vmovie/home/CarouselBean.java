package com.example.dllo.vmovie.home;

import java.util.List;

/**
 * Created by dllo on 16/8/31.
 */
public class CarouselBean {


    /**
     * status : 0
     * msg : OK
     * data : [{"bannerid":"1129","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/08/30/57c592ed711cc.jpg","description":"","userid":"962838","addtime":"1472565998","uptime":"1472565998","orderid":"6","cateid":"0","count_click":"489","status":"1","start_time":"1472572800","end_time":"1472659140","extra":"{\"app_banner_type\":\"1\",\"app_banner_param\":\"http:\\/\\/www.vmovier.com\\/fan14?inner_app=1\"}","extra_data":{"app_banner_type":"1","app_banner_param":"http://www.vmovier.com/fan14?inner_app=1"}},{"bannerid":"1109","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/08/29/57c450315ed9a.jpg","description":"","userid":"8","addtime":"1472483378","uptime":"1472483378","orderid":"5","cateid":"0","count_click":"3060","status":"1","start_time":"1471872060","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"49873\"}","extra_data":{"app_banner_type":"2","app_banner_param":"49873","is_album":"1"}},{"bannerid":"1126","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/08/29/57c3ee265a8be.jpg","description":"","userid":"927555","addtime":"1472458279","uptime":"1472458279","orderid":"4","cateid":"0","count_click":"793","status":"1","start_time":"1472458278","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"49907\"}","extra_data":{"app_banner_type":"2","app_banner_param":"49907","is_album":"0"}},{"bannerid":"1116","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/08/25/57beceedec160.jpg","description":"","userid":"927555","addtime":"1472122607","uptime":"1472122607","orderid":"3","cateid":"0","count_click":"1893","status":"1","start_time":"1472122605","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"49910\"}","extra_data":{"app_banner_type":"2","app_banner_param":"49910","is_album":"0"}},{"bannerid":"1112","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/08/24/57bd737cb1650.jpg","description":"","userid":"919390","addtime":"1472033661","uptime":"1472033661","orderid":"2","cateid":"0","count_click":"960","status":"1","start_time":"1472033660","end_time":"0","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"49924\"}","extra_data":{"app_banner_type":"2","app_banner_param":"49924","is_album":"0"}},{"bannerid":"1122","type":"7","object_id":"0","title":"","url":"/","image":"http://cs.vmoiver.com/Uploads/Banner/2016/08/26/57bff7f237921.jpg","description":"","userid":"2","addtime":"1472198683","uptime":"1472198683","orderid":"1","cateid":"0","count_click":"1294","status":"1","start_time":"1472198400","end_time":"1474898400","extra":"{\"app_banner_type\":\"2\",\"app_banner_param\":\"49936\"}","extra_data":{"app_banner_type":"2","app_banner_param":"49936","is_album":"1"}}]
     */

    private String status;
    private String msg;
    /**
     * bannerid : 1129
     * type : 7
     * object_id : 0
     * title :
     * url : /
     * image : http://cs.vmoiver.com/Uploads/Banner/2016/08/30/57c592ed711cc.jpg
     * description :
     * userid : 962838
     * addtime : 1472565998
     * uptime : 1472565998
     * orderid : 6
     * cateid : 0
     * count_click : 489
     * status : 1
     * start_time : 1472572800
     * end_time : 1472659140
     * extra : {"app_banner_type":"1","app_banner_param":"http:\/\/www.vmovier.com\/fan14?inner_app=1"}
     * extra_data : {"app_banner_type":"1","app_banner_param":"http://www.vmovier.com/fan14?inner_app=1"}
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String bannerid;
        private String type;
        private String object_id;
        private String title;
        private String url;
        private String image;
        private String description;
        private String userid;
        private String addtime;
        private String uptime;
        private String orderid;
        private String cateid;
        private String count_click;
        private String status;
        private String start_time;
        private String end_time;
        private String extra;
        /**
         * app_banner_type : 1
         * app_banner_param : http://www.vmovier.com/fan14?inner_app=1
         */

        private ExtraDataBean extra_data;

        public String getBannerid() {
            return bannerid;
        }

        public void setBannerid(String bannerid) {
            this.bannerid = bannerid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getObject_id() {
            return object_id;
        }

        public void setObject_id(String object_id) {
            this.object_id = object_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getCateid() {
            return cateid;
        }

        public void setCateid(String cateid) {
            this.cateid = cateid;
        }

        public String getCount_click() {
            return count_click;
        }

        public void setCount_click(String count_click) {
            this.count_click = count_click;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public ExtraDataBean getExtra_data() {
            return extra_data;
        }

        public void setExtra_data(ExtraDataBean extra_data) {
            this.extra_data = extra_data;
        }

        public static class ExtraDataBean {
            private String app_banner_type;
            private String app_banner_param;

            public String getApp_banner_type() {
                return app_banner_type;
            }

            public void setApp_banner_type(String app_banner_type) {
                this.app_banner_type = app_banner_type;
            }

            public String getApp_banner_param() {
                return app_banner_param;
            }

            public void setApp_banner_param(String app_banner_param) {
                this.app_banner_param = app_banner_param;
            }
        }
    }
}
