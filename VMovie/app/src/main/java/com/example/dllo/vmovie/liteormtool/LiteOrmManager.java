package com.example.dllo.vmovie.liteormtool;

import android.util.Log;

import com.example.dllo.vmovie.VMovieApplication;
import com.example.dllo.vmovie.like.LikeBackstageBean;
import com.example.dllo.vmovie.like.LikeFilmBean;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/9/6.
 */
public class LiteOrmManager {
    private static LiteOrmManager ourInstance;
    private LiteOrm liteOrm;

    public static LiteOrmManager getInstance() {
        if (ourInstance == null) {
            synchronized (LiteOrmManager.class) {
                if (ourInstance == null) {
                    ourInstance = new LiteOrmManager();
                }
            }
        }
        return ourInstance;
    }

    private LiteOrmManager() {
        liteOrm = LiteOrm.newSingleInstance(VMovieApplication.getContext(), "VMovie.db");
    }

    //插入一条数据
    public <T> void insert(T t) {
        liteOrm.save(t);
    }

    //插入一组数据
    public <T> void insertList(List<T> list) {
        liteOrm.save(list);
    }

    //删除一条数据
    public <T> void delete(T t) {
        liteOrm.delete(t);
    }

    //删除一组数据
    public <T> void deleteList(List<T> list) {
        liteOrm.delete(list);
    }

    //删除一个表
    public <T> void deleteClass(Class<T> tClass) {
        liteOrm.delete(tClass);
    }

    //删除数据库
    public void deleteDatabase() {
        liteOrm.deleteDatabase();
    }

    //查询
    public <T> List<T> queryAll(Class<T> tClass) {
        return liteOrm.query(tClass);
    }

    //指定查询
//    public <T> List<T> queryByWhere(Class<T> tClass, String field, Object[] value) {
//        return liteOrm.query(new QueryBuilder<T>(tClass).where(field + "?=", value));
//    }

    public <T> List<T> queryByWhere(Class<T> tClass, String field) {
        return liteOrm.query(new QueryBuilder<T>(tClass).where(field + "?="));
    }

//    public <T> List<T> queryByWhereTitle(Class<T> tClass, String field, T t) {
//        return liteOrm.query(new QueryBuilder<T>(tClass).where(field + "?=", t));
//    }

    //查询是否有值,有则返回true
    public <T> List<T> queryByWhereValue(Class<T> tClass, String field, T t) {
        Log.d("LiteOrmManager", field + " " + t + " ");
//        QueryBuilder<T> qb = new QueryBuilder<>(tClass).where(field + "?=", t);
//        long size = liteOrm.queryCount(qb);
//        Log.d("LiteOrmManager", "size:" + size);
        return liteOrm.query(new QueryBuilder<T>(tClass).where(field + "?=", t));
    }

    //查询  某字段 等于 Value的值
//    public <T> List<T> getQueryByWhereLength(Class<T> cla, String field, String[] value, int start, int length) {
//        return liteOrm.<T>query(new QueryBuilder(cla).where(field + "=?", value).limit(start, length));
//    }
}
