package com.newdicooker.tempetek.androidgo.com.all.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.bean.HomeListBean;
import com.newdicooker.tempetek.androidgo.com.all.inter.ItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SunPengCheng
 * on 2018/6/7
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class SearchArticleAdapter extends RecyclerView.Adapter<SearchArticleAdapter.SearchViewHolder> {
    private Context mContex;
    private LayoutInflater inflater;
    private List<HomeListBean.DataBean.DatasBean> list;
    private ItemClickListener itemClickListener;


    public SearchArticleAdapter(Context mContex) {
        this.mContex = mContex;
        inflater = LayoutInflater.from(mContex);
    }

    public void onClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setArticleDate(List<HomeListBean.DataBean.DatasBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.home_recycler_item, null);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        holder.info.setText(list.get(position).getChapterName());
        holder.title.setText(list.get(position).getTitle());
        holder.userName.setText(list.get(position).getAuthor());
        holder.date.setText(list.get(position).getNiceDate());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.setOnItemClickListener(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.info)
        TextView info;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.card_view)
        CardView cardView;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
