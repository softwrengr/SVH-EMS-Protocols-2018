package com.eagledeveloper.svhems.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.databinding.FragmentHomeBinding;
import com.eagledeveloper.svhems.utilities.GeneralUtils;



public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        initUI();
        return binding.getRoot();
    }

    private void initUI() {

        binding.drKaufCall.setOnClickListener(v ->
                showDialog("kauf"));

        binding.drGuardnerCall.setOnClickListener(v ->
                showDialog("guard"));

        binding.emergencyCall.setOnClickListener(v ->
                showDialog("emergency"));

        binding.btnEmsProtocols.setOnClickListener(v ->
                GeneralUtils.connectFragmentWithBack(getActivity(), new PdfFragment()));
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
