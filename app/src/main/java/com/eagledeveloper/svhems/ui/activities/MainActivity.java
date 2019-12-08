package com.eagledeveloper.svhems.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.ui.fragments.GriViewFragment;
import com.eagledeveloper.svhems.ui.fragments.HomeFragment;
import com.eagledeveloper.svhems.ui.fragments.PdfFragment;
import com.eagledeveloper.svhems.utilities.GeneralUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GeneralUtils.connectFragment(this, new HomeFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grid_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection


        if (item.getItemId() == android.R.id.home) {

        }
        switch (item.getItemId()) {
            case R.id.action_grid_view:

                GeneralUtils.connectFragmentWithBack(this, new GriViewFragment());

                break;
            case R.id.action_one_page_view:

                GeneralUtils.connectFragmentWithBack(this, new PdfFragment());


            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

}