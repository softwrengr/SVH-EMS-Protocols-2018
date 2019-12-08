package com.eagledeveloper.svhems.ui.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.utilities.GeneralUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PdfFragment extends Fragment {
    View view;

    @BindView(R.id.pdfview)
    PDFView pdfView;

    @BindView(R.id.page_number)
    TextView tvPageNumber;

    @BindView(R.id.progress)
    ImageView ivProgress;

    private LinkHandler linkHandler;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_pdf2, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        initUI();
        onback(view);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initUI() {
        pdfView.fromAsset("pdf.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableSwipe(true)
                .defaultPage(0)
                .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                .password(null)
                .linkHandler( linkHandler = new LinkHandler() {
                    @Override
                    public void handleLinkEvent(LinkTapEvent event) {
                        Log.d("show",String.valueOf(event.getMappedLinkRect()));
                    }
                })
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

                        tvPageNumber.setText("Page " + String.valueOf(pageNum) + " of 146");
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
                        ivProgress.setVisibility(View.GONE);
                    }
                })
                .spacing(0)
                .load();

        pdfView.zoomTo(1);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_one_page_view);
        item.setVisible(false);
    }


    private void onback(View view) {
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    GeneralUtils.connectFragment(getActivity(), new HomeFragment());
                    return true;
                }
                return false;
            }
        });

    }


}

