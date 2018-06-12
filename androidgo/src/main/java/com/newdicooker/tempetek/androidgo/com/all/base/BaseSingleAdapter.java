package com.newdicooker.tempetek.androidgo.com.all.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdicooker.tempetek.androidgo.com.all.bean.KnowledgeBean;

import java.util.List;

/**
 * Created by SunPengCheng
 * on 2018/6/11
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class BaseSingleAdapter extends BaseQuickAdapter<KnowledgeBean, BaseSingleAdapter.KnowledgeHolder> {
    public BaseSingleAdapter(@LayoutRes int layoutResId, @Nullable List<KnowledgeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(KnowledgeHolder helper, KnowledgeBean item) {

    }

    class KnowledgeHolder extends BaseViewHolder {
        public KnowledgeHolder(View itemView) {
            super(itemView);
        }
    }
}
