package com.newdicooker.tempetek.androidgo.com.all.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newdicooker.tempetek.androidgo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {


    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the home_head_layout for this fragment
        return inflater.inflate(R.layout.fragment_project, container, false);
    }

}
