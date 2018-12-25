package com.eagledeveloper.svhems.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eagledeveloper.svhems.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PDFRenderFragment extends Fragment {


    public PDFRenderFragment() {
        // Required empty public constructor
    }

    private View parentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_pdfrender, container, false);

        return parentView;
    }

}
