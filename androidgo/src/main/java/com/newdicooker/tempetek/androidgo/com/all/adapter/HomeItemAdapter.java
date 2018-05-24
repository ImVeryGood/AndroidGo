package com.newdicooker.tempetek.androidgo.com.all.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.bean.BannerBean;
import com.newdicooker.tempetek.androidgo.com.all.bean.HomeListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SunPengCheng
 * on 2018/5/23
 * 邮箱：13027699936@163.com.
 * version 2.0.4
 */

public class HomeItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context mContext;
    private List<BannerBean.DataBean> bannerList;
    private List<HomeListBean.DataBean.DatasBean> homeList;

    public HomeItemAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);

    }

    public void setBannerList(List<BannerBean.DataBean> bannerList) {
        this.bannerList = bannerList;
        notifyItemChanged(0);

    }

    public void setArticleList(List<HomeListBean.DataBean.DatasBean> homeList) {
        this.homeList = homeList;
        notifyItemChanged(1, getItemCount() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case 0:
                view = inflater.inflate(R.layout.home_item_banner, null);
                holder = new HeadViewHOlder(view);
                break;
            case 1:
                view = inflater.inflate(R.layout.home_recycler_item, null);
                holder = new ItemViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
                final HeadViewHOlder headHolder = (HeadViewHOlder) holder;
                if (bannerList != null) {
                    headHolder.banner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {

                        @Override
                        public LocalImageHolderView createHolder() {
                            return new LocalImageHolderView();
                        }
                    }, bannerList).startTurning(2000);
                    headHolder.banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            headHolder.bannerTitle.setText(bannerList.get(position).getTitle());
                            headHolder.currentPage.setText(position + 1 + "");
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                    headHolder.pageNum.setText("/" + bannerList.size());
                }
                break;
            case 1:
                ItemViewHolder viewHolder = (ItemViewHolder) holder;
                viewHolder.info.setText(homeList.get(position).getChapterName());
                viewHolder.title.setText(homeList.get(position).getTitle());
                viewHolder.userName.setText(homeList.get(position).getAuthor());
                viewHolder.date.setText(homeList.get(position).getNiceDate());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public int getItemCount() {
        return homeList == null ? 1 : homeList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

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

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeadViewHOlder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        ConvenientBanner banner;
        @BindView(R.id.banner_title)
        TextView bannerTitle;
        @BindView(R.id.current_page)
        TextView currentPage;
        @BindView(R.id.page_num)
        TextView pageNum;

        public HeadViewHOlder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
