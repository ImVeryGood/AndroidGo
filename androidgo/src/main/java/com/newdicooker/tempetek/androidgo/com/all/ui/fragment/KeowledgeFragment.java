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
public class KeowledgeFragment extends Fragment {
    private View view;

    public KeowledgeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_keowledge2, container, false);

        return view;
    }



}
