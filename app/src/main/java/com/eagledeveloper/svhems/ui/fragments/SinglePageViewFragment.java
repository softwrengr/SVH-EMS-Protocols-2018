package com.eagledeveloper.svhems.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eagledeveloper.svhems.R;
import com.eagledeveloper.svhems.databinding.FragmentPdfBinding;
import com.eagledeveloper.svhems.utilities.GeneralUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;



public class SinglePageViewFragment extends Fragment {

    private FragmentPdfBinding binding;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pdf, container, false);
        setHasOptionsMenu(true);

        initUI();
        onback(binding.getRoot());

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initUI() {

        int pageNumber = Integer.parseInt(GeneralUtils.getSharedPreferences(getActivity()).getString("page", ""));
        binding.pdfView.fromAsset("pdf.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .enableSwipe(true)
                .defaultPage(pageNumber)
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

                        binding.tvPageNumber.setText("Page " + String.valueOf(pageNum) + " of 168");
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
                        binding.ivProgress.setVisibility(View.GONE);
                    }
                })
                .spacing(0)
                .load();

        binding.pdfView.zoomTo(1);


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
                    GeneralUtils.connectFragment(getActivity(), new GriViewFragment());
                    return true;
                }
                return false;
            }
        });

    }

}
