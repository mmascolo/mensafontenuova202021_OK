package com.mminf.mensafontenuova;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu4 extends Fragment {
    private WebView webVieweb;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment_menu_3, container, false);

        webVieweb = v.findViewById(R.id.webView1);
        webVieweb.loadUrl("https://www.icsdefilippo.gov.it");

        // Enable Javascript
        WebSettings webSettings = webVieweb.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webVieweb.setWebViewClient(new WebViewClient());


        return v;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("I.C.S. De Filippo");


    }
}