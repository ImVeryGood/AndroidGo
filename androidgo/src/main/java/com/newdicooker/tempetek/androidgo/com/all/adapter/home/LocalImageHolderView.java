package com.newdicooker.tempetek.androidgo.com.all.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.newdicooker.tempetek.androidgo.com.all.bean.BannerBean;

/**
 * Created by Spc
 * on 2016/12/8.
 */

public class LocalImageHolderView implements Holder<BannerBean.DataBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerBean.DataBean data) {
        Glide.with(context).load(data.getImagePath()).into(imageView);
    }


}
