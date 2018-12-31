package com.eagledeveloper.svhems.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.eagledeveloper.svhems.R;

/**
 * Created by eapple on 20/12/2018.
 */

public class GeneralUtils {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public static Fragment connectFragment(Activity activity, Fragment fragment){
        ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        return fragment;
    }

    public static Fragment connectFragmentWithBack(Activity activity, Fragment fragment){
        ((AppCompatActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack("abc").commit();
        return fragment;
    }


    public static SharedPreferences.Editor putStringValueInEditor(Context context, String key, String value) {
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        editor.putString(key, value).commit();
        return editor;
    }


    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("pdf", 0);
    }

}
