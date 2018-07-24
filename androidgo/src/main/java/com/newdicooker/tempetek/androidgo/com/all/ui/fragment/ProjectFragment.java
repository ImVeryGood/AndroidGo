package com.newdicooker.tempetek.androidgo.com.all.ui.fragment;


import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.newdicooker.tempetek.androidgo.R;
import com.newdicooker.tempetek.androidgo.com.all.base.BaseFragment;
import com.wx.ovalimageview.RoundImageView;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends BaseFragment {


    @BindView(R.id.head_bg)
    ImageView headBg;
    @BindView(R.id.avatar)
    RoundImageView avatar;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView() {
        Glide.with(this).load(R.mipmap.picture)
                .bitmapTransform(new BlurTransformation(getContext(), 25), new CenterCrop(getContext()))
                .into(headBg);

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

}
