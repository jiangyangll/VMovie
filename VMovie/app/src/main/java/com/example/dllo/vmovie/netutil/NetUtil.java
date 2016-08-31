package com.example.dllo.vmovie.netutil;

/**
 * Created by dllo on 16/8/30.
 * 网址
 */
public class NetUtil {

    //最新
    public static final String NEWEST = "http://app.vmoiver.com/apiv3/post/getPostByTab?p=1&size=20&tab=latest";
    //刷新的话,变p=1的值
    //最新轮播图
    public static final String NEWEST_RECYCLER = "http://app.vmoiver.com/apiv3/index/getBanner";
    // 轮播图详情拼接参数 判断轮播图app_banner_type种类，type=2 的参数是 app_banner_param:49843
    public static final String BannerDescriptionHead =  "http://app.vmoiver.com/";
    public static final String BannerDescriptionTail = "?qingapp=app_new";
    //频道
    public static final String CHANNEl = "http://app.vmoiver.com/apiv3/cate/getList";
    //系列
    public static final String SERIES_START = "http://app.vmoiver.com/apiv3/series/getList?page=1&size=10&p=1";
    //幕后标题
    public static final String BACKSTAGE
            = "http://app.vmoiver.com/apiv3/backstage/getCate";
    //幕后每一个fragment的前半部分 然后再拼接id
    /**
     * p=1&size=10&cateid=2   //全部
     * p=1&size=10&cateid=47  //电影自习室
     * p=1&size=10&cateid=53   //电影会客厅
     * p=1&size=10&cateid=4    //拍摄
     * p=1&size=10&cateid=26   //综述
     * p=1&size=10&cateid=30   //后期
     * p=1&size=10&cateid=31   //器材
     */
    //http://app.vmoiver.com/apiv3/backstage/getPostByCate?p=?&size=?&cateid=?
    public static final String BACKSTAGE_EVERY_ID = "&cateid=";
    public static final String BACKSTAGE_EVERY_SIZE = "&size=";
    public static final String T_BACKSTAGE_EVERY = "http://app.vmoiver.com/apiv3/backstage/getPostByCate?p=";

    //幕后详情   每一个幕后的详情  在如/postid=47774
    public static final String BACKSTAGE_DETAIL = "http://app.vmoiver.com/apiv3/post/view?postid=";
    //其中的评论  拼接id  /p=1&size=10&postid=47774&type=0
    public static final String COMMENT_LEFT = "http://app.vmoiver.com/apiv3/comment/getLists/p=1&size=10&postid=";
    public static final String COMMENT_RIGHT = "&type=0";

    //搜索  在kw=  之后加入搜索内容便可显示
    //http://app.vmoiver.com/apiv3/search/?kw=  啦啦啦啦啦啦啦啦啦啦 &p=1 搜索的结果
    public static final String SEARCH = "http://app.vmoiver.com/apiv3/search/?kw=";

    /**
     * 频道的详情
     */
    //热门
    public static final String CHANNEL_HOT = "http://app.vmoiver.com/apiv3/post/getPostByTab?p=1&tab=hot";
    //专题
    public static final String CHANNEL_SPECIAL = "http://app.vmoiver.com/apiv3/post/getPostByTab?p=1&tab=album";
//    public static final String SERIES_DETAIL="http://app.vmoiver.com/apiv3/series/getVideo/series_postid=1792";

    //创意以下
    //http://app.vmoiver.com/apiv3/post/getPostInCate?cateid=6&p=1
    //6创意  13广告  10运动   17剧情  16动画   11旅游   7励志   18音乐  12爱情  8搞笑
    public static final String CHANNEL_DETAIL_LEFT = "http://app.vmoiver.com/apiv3/post/getPostInCate?cateid=";
    public static final String CHANNEL_DETAIL_RIGHT = "&p=1";


    //视频详情
    //http://app.vmoiver.com/apiv3/series/getVideo?series_postid=1792&p=1
    public static final String VIDEO_LEFT = "http://app.vmoiver.com/apiv3/series/getVideo?series_postid=";
    public static final String VIDEO_RIGHT = "&p=1";

    //最新详情  频道中video也可通过拼接postid实现加载数据
    //http://app.vmoiver.com/apiv3/post/view?postid=49595
    //http://app.vmoiver.com/apiv3/post/view?postid=49595&p=1
    public static final String NEWEST_DETAIL_LEFT = "http://app.vmoiver.com/apiv3/post/view?postid=";
    public static final String NEWEST_DETAIL_RIGHT = "&p=1";
    //系列详情
    //http://app.vmoiver.com/apiv3/series/view?seriesid=45&p=1
    public static final String SERIES_DETAIL_LEFT = "http://app.vmoiver.com/apiv3/series/view?seriesid=";
    public static final String SERIES_DETAIL_RIGHT = "&p=1";

    //String url = "http://app.vmoiver.com/" + id + "?qingapp=app_new&v=android_5.0.4";
    //网页
    //http://app.vmoiver.com/49595?qingapp=app_new
    public static final String WEB_LEFT = "http://app.vmoiver.com/";
    public static final String WEB_RIGHT = "?qingapp=app_new";

    //频道的热门图片
    public static final String CHANNEL_HOT_PIC = "http://cs.vmoiver.com/Uploads/Activity/2016-04-26/571ed9b5d2e44.jpg";
    public static final String CHANNEL_SPECIAL_PIC = "http://cs.vmoiver.com/Uploads/Activity/2016-04-27/5720601258d7f.jpg";

}
