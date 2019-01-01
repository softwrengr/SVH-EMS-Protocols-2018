package com.eagledeveloper.svhems.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.utilities.GeneralUtils;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;

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

    private void initUI() {
        ButterKnife.bind(this, view);

        ivKaufCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("kauf");

            }
        });

        ivGuardnerCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("guard");
            }
        });

        ivEmergencyCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("emergency");
            }
        });

        btnEmsProtocols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.connectFragmentWithBack(getActivity(), new PdfFragment());
            }
        });
    }


    private void dialPhone(String strContactNo) {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                "tel", strContactNo, null));
        startActivity(phoneIntent);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.clear();
    }

    private void showDialog(final String string) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_layout);
        TextView tvCall,tvClose;
        tvCall = dialog.findViewById(R.id.dialog_call);
        tvClose = dialog.findViewById(R.id.dialog_close);
        tvCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(string.equals("kauf")){
                    dialPhone(getResources().getString(R.string.phone_one));
                }
                else if(string.equals("guard")){
                    dialPhone(String.valueOf(getResources().getString(R.string.phone_two)));
                }
                else {
                    dialPhone(String.valueOf(getResources().getString(R.string.phone_three)));
                }

            }
        });

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
