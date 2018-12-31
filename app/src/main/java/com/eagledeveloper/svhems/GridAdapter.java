package com.eagledeveloper.svhems;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.util.List;

public class GridAdapter extends ArrayAdapter {

    private List<DataModel> dataModels;
    private Context context;
    private int layoutResourceId;


    public GridAdapter(Context context, int layoutResourceId, List<DataModel> dataModels) {
        super(context, layoutResourceId);
        this.dataModels = dataModels;
        this.context = context;
        this.layoutResourceId = layoutResourceId;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;


        if (row == null) {
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();

            holder.pdfView = row.findViewById(R.id.pdfView);


            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();

        }

        final DataModel dataModel = dataModels.get(position);
        Log.d("pdf",String.valueOf(dataModel.getPage_id()));

        return row;
    }


    @Override
    public int getCount() {
        return dataModels.size();
    }

    static class ViewHolder {
        PDFView pdfView;

    }
}

