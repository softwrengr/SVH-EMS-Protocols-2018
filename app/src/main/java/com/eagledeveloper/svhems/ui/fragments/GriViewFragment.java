package com.eagledeveloper.svhems.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.eagledeveloper.svhems.DataModel;
import com.eagledeveloper.svhems.GridAdapter;
import com.eagledeveloper.svhems.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GriViewFragment extends Fragment {

    private View parentView;
    @BindView(R.id.gridview_images)
    GridView gridView;
    List<DataModel> list;


    public GriViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_gri_view, container, false);


        ButterKnife.bind(this, parentView);

        List<DataModel> list = new ArrayList<>();

        DataModel dataModel = new DataModel();
        for (int i = 1; i <= 146; i++) {


            Log.d("id_pages",String.valueOf(i));
            dataModel.setPage_id(i);
            list.add(dataModel);

        }

        gridView.setAdapter(new GridAdapter(getActivity(), R.layout.custom_layout, list));



        return parentView;
    }

}
