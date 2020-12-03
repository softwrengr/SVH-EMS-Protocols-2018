package com.eagledeveloper.svhems.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.model.DataModel;
import com.eagledeveloper.svhems.ui.fragments.SinglePageViewFragment;
import com.eagledeveloper.svhems.utilities.GeneralUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private ArrayList<DataModel> clientsDetailsModels;
    private Context context;
    private LayoutInflater layoutInflater;
    private MyViewHolder viewHolder = null;

    public GridAdapter(Context context, ArrayList<DataModel> clientsDetailsModels) {
        this.clientsDetailsModels = clientsDetailsModels;
        this.context = context;
        if (context != null) {
            this.layoutInflater = LayoutInflater.from(context);

        }
    }

    @Override
    public int getCount() {
        if (clientsDetailsModels != null) return clientsDetailsModels.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (clientsDetailsModels != null && clientsDetailsModels.size() > position)
            return clientsDetailsModels.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        final DataModel model = clientsDetailsModels.get(position);
        if (clientsDetailsModels != null && clientsDetailsModels.size() > position)
            return clientsDetailsModels.size();
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final DataModel model = clientsDetailsModels.get(position);

        viewHolder = new MyViewHolder();
        convertView = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        convertView.setLayoutParams(new GridView.LayoutParams(params));

        viewHolder.pdfView = convertView.findViewById(R.id.gv_pdf);
        viewHolder.tvPage = convertView.findViewById(R.id.tv_page);
        viewHolder.tvPageNo = convertView.findViewById(R.id.tv_page_no);

        final View finalConvertView = convertView;
        viewHolder.pdfView.fromAsset("pdf.pdf")
                .pages(position)
                .defaultPage(position)
                .swipeHorizontal(false)
                .enableDoubletap(false)
                .enableSwipe(false)
                .enableAnnotationRendering(false)
                .password(null)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                        viewHolder.ivProgress = finalConvertView.findViewById(R.id.iv_progress);
                        viewHolder.ivProgress.setVisibility(View.GONE);

                    }
                })
                .load();

        viewHolder.tvPageNo.setText("page "+String.valueOf(position+1));
        viewHolder.tvPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeneralUtils.putStringValueInEditor(context, "page", String.valueOf(position));
                GeneralUtils.connectFragmentWithBack((Activity) context, new SinglePageViewFragment());

            }
        });

        convertView.setTag(viewHolder);
        return convertView;
    }


    private class MyViewHolder {
        ImageView ivProgress;
        PDFView pdfView;
        TextView tvPage,tvPageNo;
    }
}
