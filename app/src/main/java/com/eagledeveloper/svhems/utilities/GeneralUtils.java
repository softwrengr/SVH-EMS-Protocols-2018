package com.eagledeveloper.svhems.utilities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.eagledeveloper.svhems.R;

/**
 * Created by eapple on 20/12/2018.
 */

public class GeneralUtils {


    public static Fragment connectFragment(Activity activity, Fragment fragment){
        ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        return fragment;
    }

    public static Fragment connectFragmentWithBack(Activity activity, Fragment fragment){
        ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("abc").commit();
        return fragment;
    }


}
