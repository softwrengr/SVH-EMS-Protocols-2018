package com.eagledeveloper.svhems.ui.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.databinding.FragmentPdf2Binding;
import com.eagledeveloper.svhems.utilities.GeneralUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.model.LinkTapEvent;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;

public class PdfFragment extends Fragment {

    private LinkHandler linkHandler;
    private FragmentPdf2Binding binding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pdf2, container, false);
        setHasOptionsMenu(true);

        initUI();
        onback(binding.getRoot());

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initUI() {
        binding.pdfview.fromAsset("pdf.pdf")
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
                        binding.pageNumber.setText("Page " + String.valueOf(pageNum) + " of 168");
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
                        binding.progress.setVisibility(View.GONE);
                    }
                })
                .spacing(0)
                .load();

        binding.pdfview.zoomTo(1);

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

