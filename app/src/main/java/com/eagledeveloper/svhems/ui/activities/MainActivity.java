package com.eagledeveloper.svhems.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.ui.fragments.GriViewFragment;
import com.eagledeveloper.svhems.ui.fragments.HomeFragment;
import com.eagledeveloper.svhems.ui.fragments.PDFFragment;
import com.eagledeveloper.svhems.utilities.GeneralUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeneralUtils.connectFragment(this, new GriViewFragment());
    }
}
