package com.eagledeveloper.svhems.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.utilities.GeneralUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {
    View view;
    @BindView(R.id.btn_ems_protocols)
    Button btnEmsProtocols;
    @BindView(R.id.dr_kauf_call)
    ImageView ivKaufCall;
    @BindView(R.id.dr_guardner_call)
    ImageView ivGuardnerCall;
    @BindView(R.id.emergency_call)
    ImageView ivEmergencyCall;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        initUI();
        return view;
    }

    private void initUI(){
        ButterKnife.bind(this,view);

        ivKaufCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dialPhone(getResources().getString(R.string.phone_one));
            }
        });

        ivGuardnerCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhone(getResources().getString(R.string.phone_two));
            }
        });

        ivEmergencyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhone(getResources().getString(R.string.phone_three));
            }
        });

        btnEmsProtocols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(),new SinglePageViewFragment());
            }
        });
    }


    private void dialPhone(String strContactNo){
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                "tel", strContactNo, null));
        startActivity(phoneIntent);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
       menu.clear();
    }

}
