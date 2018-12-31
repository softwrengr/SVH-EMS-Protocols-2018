package com.eagledeveloper.svhems;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eagledeveloper.svhems.ui.fragments.SinglePageFragment;
import com.eagledeveloper.svhems.utilities.GeneralUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    ArrayList<DataModel> clientsDetailsModels;
    Context context;
    private LayoutInflater layoutInflater;
    MyViewHolder viewHolder = null;

    public GridAdapter(Context context, ArrayList<DataModel> clientsDetailsModels) {
        this.clientsDetailsModels=clientsDetailsModels;
        this.context=context;
        if (context!=null)
        {
            this.layoutInflater=LayoutInflater.from(context);

        }
    }

    @Override
    public int getCount() {
        if (clientsDetailsModels!=null) return clientsDetailsModels.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(clientsDetailsModels != null && clientsDetailsModels.size() > position) return  clientsDetailsModels.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        final DataModel model=clientsDetailsModels.get(position);
        if(clientsDetailsModels != null && clientsDetailsModels.size() > position) return  clientsDetailsModels.size();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DataModel model = clientsDetailsModels.get(position);

        viewHolder = new MyViewHolder();
        convertView=layoutInflater.inflate(R.layout.custom_layout,parent,false);
        viewHolder.pdfView = convertView.findViewById(R.id.gv_pdf);
        viewHolder.layout = convertView.findViewById(R.id.layout);

        viewHolder.pdfView.fromAsset("pdf.pdf")
                .pages(position)
                .defaultPage(1)
                .enableAnnotationRendering(true)
                .password(null)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                    }
                })
                .load();

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putStringValueInEditor(context,"page",String.valueOf(model.getPage_id()));
                GeneralUtils.connectFragmentWithBack((Activity) context,new SinglePageFragment());
            }
        });

        convertView.setTag(viewHolder);
        return convertView;
    }


    private class MyViewHolder  {
        LinearLayout layout;
        PDFView pdfView;
    }
}
