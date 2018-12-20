package com.eagledeveloper.svhems.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {
    View view;
    @BindView(R.id.btn_ems_protocols)
    Button btnEmsProtocols;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        btnEmsProtocols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new PDFFragment());
            }
        });
    }
}
