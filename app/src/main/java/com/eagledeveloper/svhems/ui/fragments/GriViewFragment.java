package com.eagledeveloper.svhems.ui.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.eagledeveloper.svhems.model.DataModel;
import com.eagledeveloper.svhems.adapter.GridAdapter;
import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.utilities.GeneralUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GriViewFragment extends Fragment {

    private View parentView;
    @BindView(R.id.gridview_images)
    GridView gridView;
    ArrayList<DataModel> list;
    GridAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_gri_view, container, false);

        setHasOptionsMenu(true);
        ButterKnife.bind(this, parentView);
        initUI();
        onback(parentView);
        return parentView;
    }

    private void initUI() {
        list = new ArrayList<>();
        adapter = new GridAdapter(getActivity(), list);
        gridView.setAdapter(adapter);

        DataModel dataModel = new DataModel();
        for (int i = 1; i <= 146; i++) {
            dataModel.setPage_id(i);
            list.add(dataModel);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_grid_view);
        item.setVisible(false);
    }

    private void onback(View view) {
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    GeneralUtils.connectFragment(getActivity(), new PdfFragment());
                    return true;
                }
                return false;
            }
        });

    }


}
