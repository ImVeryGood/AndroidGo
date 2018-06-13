package com.newdicooker.tempetek.androidgo.com.all.url;

/**
 * Created by SunPengCheng
 * on 2018/5/23
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class NetUrl {
    public static String basePath = "http://www.wanandroid.com/";

    /**
     * 首页列表
     *
     * @param page
     * @return
     */
    public static String homeListUrl(int page) {
        return basePath + "article/list/" + page + "/json";
    }

    /**
     * 首页banner图
     *
     * @return
     */
    public static String homeBanner() {
        return "http://www.wanandroid.com/banner/json";
    }

    /*热门搜索*/
    public static String hotSearch() {
        return basePath + "hotkey/json";
    }

    public static String getSearch(int page) {
        return basePath + "article/query/" + page + "/json";
    }

    /**
     * 收藏网址
     *
     * @return
     */
    public static String collectUrl() {
        return basePath + "lg/collect/addtool/json";
    }

    /**
     * 知识体系
     *
     * @return
     */
    public static String knowledgeUrl() {
        return basePath + "tree/json";
    }

    /**
     * 知识体系二级页面
     *
     * @param page
     * @param cid
     * @return
     */
    public static String knowledgeSecondUrl(int page, String cid) {
        return basePath + "article/list/" + page + "/json?cid=" + cid;
    }

    /**
     * 导航
     *
     * @return
     */
    public static String navgationUrl() {
        return basePath + "navi/json";
    }
}
