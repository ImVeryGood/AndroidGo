package com.newdicooker.tempetek.androidgo.com.all.helper;

import android.content.Context;
import android.content.Intent;

import com.newdicooker.tempetek.androidgo.com.all.ui.activity.SearchResultActivity;
import com.newdicooker.tempetek.androidgo.com.all.ui.activity.WebActivity;
import com.newdicooker.tempetek.androidgo.com.all.ui.activity.knowledge.KnowledgeActivity;

/**
 * Created by SunPengCheng
 * on 2018/6/7
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class JudgeUtils {
    public static void judjeToSearchResult(Context mActivity, String srarchText) {
        Intent intent = new Intent(mActivity, SearchResultActivity.class);
        intent.putExtra("k", srarchText);
        mActivity.startActivity(intent);
    }

    public static void judgeToWebActivity(Context mContext, String link) {
        Intent intent = new Intent(mContext, WebActivity.class);
        intent.putExtra("url", link);
        mContext.startActivity(intent);
    }

    public static void judgeToKnowledge(Context mActivity, String titile, String childString) {
        Intent intent = new Intent(mActivity, KnowledgeActivity.class);
        intent.putExtra("title", titile);
        intent.putExtra("tabString", childString);
        mActivity.startActivity(intent);
    }
}
