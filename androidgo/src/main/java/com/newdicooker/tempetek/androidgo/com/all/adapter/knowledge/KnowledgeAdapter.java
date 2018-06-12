package com.newdicooker.tempetek.androidgo.com.all.adapter.knowledge;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.bean.KnowledgeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SunPengCheng
 * on 2018/6/11
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class KnowledgeAdapter extends BaseQuickAdapter<KnowledgeBean.DataBean, KnowledgeAdapter.KnowledgeHoler> {

    public KnowledgeAdapter() {
        super(R.layout.kenowlege_recycler_item);
    }

    @Override
    public void setNewData(@Nullable List<KnowledgeBean.DataBean> data) {
        super.setNewData(data);
    }

    @Override
    protected void convert(KnowledgeHoler helper, KnowledgeBean.DataBean item) {
        helper.name.setText(item.getName());
        StringBuilder content = new StringBuilder();
        for (KnowledgeBean.DataBean.ChildrenBean bean : item.getChildren()) {
            content.append(bean.getName()).append("  ");
        }
        helper.tag.setText(content.toString());
    }

    class KnowledgeHoler extends BaseViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tag)
        TextView tag;
        @BindView(R.id.enter)
        ImageView enter;

        public KnowledgeHoler(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
