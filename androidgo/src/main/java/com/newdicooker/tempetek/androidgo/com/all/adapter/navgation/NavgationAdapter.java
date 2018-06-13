package com.newdicooker.tempetek.androidgo.com.all.adapter.navgation;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.bean.NavgationBean;
import com.newdicooker.tempetek.androidgo.com.all.utils.CommonUtils;
import com.newdicooker.tempetek.androidgo.com.all.utils.JudgeUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SunPengCheng
 * on 2018/6/13
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class NavgationAdapter extends BaseQuickAdapter<NavgationBean.DataBean, NavgationAdapter.NavgationHolder> {
    public NavgationAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    public void setNewData(@Nullable List<NavgationBean.DataBean> data) {
        super.setNewData(data);
    }

    @Override
    protected void convert(final NavgationHolder helper, NavgationBean.DataBean item) {
        helper.name.setText(item.getName());
        final List<NavgationBean.DataBean.ArticlesBean> mArticles = item.getArticles();
        helper.flowLayout.setAdapter(new TagAdapter<NavgationBean.DataBean.ArticlesBean>(mArticles) {
            @Override
            public View getView(FlowLayout parent, int position, NavgationBean.DataBean.ArticlesBean articlesBean) {
                TextView text = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_flow_item, helper.flowLayout, false);
                text.setText(articlesBean.getTitle());
                text.setTextColor(CommonUtils.randomColor());
                return text;
            }
        });
        helper.flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                JudgeUtils.judgeToWebActivity(parent.getContext(), mArticles.get(position).getLink());
                return false;
            }
        });
    }

    public class NavgationHolder extends BaseViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.flow_layout)
        TagFlowLayout flowLayout;

        public NavgationHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
