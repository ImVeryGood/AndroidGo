package com.newdicooker.tempetek.androidgo.com.all.url;

/**
 * Created by SunPengCheng
 * on 2018/5/23
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class NetUrl {
    /**
     * 首页列表
     *
     * @param page
     * @return
     */
    public static String homeListUrl(int page) {
        return "http://www.wanandroid.com/article/list/" + page + "/json";
    }

    /**
     * 首页banner图
     *
     * @return
     */
    public static String homeBanner() {
        return "http://www.wanandroid.com/banner/json";
    }
}
