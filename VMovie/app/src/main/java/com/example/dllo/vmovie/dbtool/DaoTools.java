package com.example.dllo.vmovie.dbtool;

import com.example.dllo.vmovie.VMovieApplication;
import com.example.dllo.vmovie.dbtool.SubScribeBeanDao.Properties;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/9/10.
 */
public class DaoTools {

    private static DaoTools ourInstance;
    private SubScribeBeanDao scribeBeanDao;
    private LikeBackStageBeanDao backStageBeanDao;
    private LikeFilmBeanDao filmBeanDao;

    public static DaoTools getInstance(){
        if (ourInstance == null) {
            synchronized (DaoTools.class){
                if (ourInstance == null) {
                    ourInstance = new DaoTools();
                }
            }
        }
        ourInstance.scribeBeanDao = VMovieApplication.getDaoSession().getSubScribeBeanDao();
        ourInstance.backStageBeanDao = VMovieApplication.getDaoSession().getLikeBackStageBeanDao();
        ourInstance.filmBeanDao = VMovieApplication.getDaoSession().getLikeFilmBeanDao();
        return ourInstance;
    }

    private DaoTools(){

    }

    public void insertSubScribe(SubScribeBean subScribeBean){
        scribeBeanDao.insert(subScribeBean);
    }

    public void deleteSubScribe(SubScribeBean subScribeBean){
        scribeBeanDao.delete(subScribeBean);
    }

    public void deleteSubScribeBySeriesId(String seriesId){
        org.greenrobot.greendao.query.QueryBuilder<SubScribeBean> queryBuilder = scribeBeanDao.queryBuilder();
        DeleteQuery<SubScribeBean> deleteQuery = queryBuilder.where(Properties.SeriesId.eq(seriesId)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }

    public List<SubScribeBean> getAllSubscribe(){
        List<SubScribeBean> list = scribeBeanDao.queryBuilder().list();
        return list;
    }

    public boolean isSava(String seriesId){
        QueryBuilder<SubScribeBean> queryBuilder = scribeBeanDao.queryBuilder().where(Properties.SeriesId.eq(seriesId));
        long size = queryBuilder.buildCount().count();
        return size > 0 ? true :false;
    }

    public boolean isSavaFilm(String title){
        QueryBuilder<LikeFilmBean> queryBuilder = filmBeanDao.queryBuilder()
                .where(LikeFilmBeanDao.Properties.Title.eq(title));
        long size = queryBuilder.buildCount().count();
        return size > 0 ? true :false;
    }

    public boolean isSavaBackStage(String title){
        QueryBuilder<LikeBackStageBean> queryBuilder = backStageBeanDao.queryBuilder()
                .where(LikeBackStageBeanDao.Properties.Title.eq(title));
        long size = queryBuilder.buildCount().count();
        return size > 0 ? true :false;
    }
}
