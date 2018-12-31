package com.eagledeveloper.svhems.ui.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.eagledeveloper.svhems.DataModel;
import com.eagledeveloper.svhems.GridAdapter;
import com.eagledeveloper.svhems.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageScrollListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PDFFragment extends Fragment {
    View view;

    @BindView(R.id.pdfView)
    PDFView pdfView;

    @BindView(R.id.pdfView1)
    PDFView pdfView1;
    @BindView(R.id.pdfView2)
    PDFView pdfView2;
    @BindView(R.id.pdfView3)
    PDFView pdfView3;


    @BindView(R.id.tv_page_number)
    TextView tvPageNumber;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pdf, container, false);
        initUI();

        ButterKnife.bind(this, view);


        pdfView1.fromAsset("pdf.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableSwipe(true)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .load();
        pdfView2.fromAsset("pdf.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(false)
                .enableSwipe(false)
                .pages(3)
                .defaultPage(3)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
                        Toast.makeText(getActivity(), String .valueOf(pageHeight), Toast.LENGTH_SHORT).show();
                    }
                })
                .load();;

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initUI() {



        pdfView.fromAsset("pdf.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableSwipe(true)

                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .scrollHandle(new ScrollHandle() {
                    @Override
                    public void setScroll(float position) {

                    }

                    @Override
                    public void setupLayout(PDFView pdfView) {

                    }

                    @Override
                    public void destroyLayout() {

                    }

                    @Override
                    public void setPageNum(int pageNum) {

                        tvPageNumber.setText("page " + String.valueOf(pageNum));
                    }

                    @Override
                    public boolean shown() {
                        return false;
                    }

                    @Override
                    public void show() {

                    }

                    @Override
                    public void hide() {

                    }

                    @Override
                    public void hideDelayed() {

                    }
                })
                .enableAntialiasing(false) // improve rendering a little bit on low-res screens
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        Toast.makeText(getActivity(), String.valueOf(nbPages), Toast.LENGTH_SHORT).show();
                    }
                }).onRender(new OnRenderListener() {
            @Override
            public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {

                int w = Math.round(pageWidth);
                int h = Math.round(pageHeight);

                Bitmap bitmap = Bitmap.createBitmap(w, h,
                        Bitmap.Config.RGB_565);

                Toast.makeText(getActivity(), "    " + String.valueOf(nbPages), Toast.LENGTH_SHORT).show();
            }
        })
                .spacing(0)
                .load();


    }
}
