package com.mminf.mensafontenuova;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu7 extends Fragment {


    int contatore;
    private boolean connessioneok = false;
    private SharedPreferences mPreferences;
    private String username;
    private String password;

    public void scrivi_str(String campo, String valore) {
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPreferences_agg.edit().putString(campo, valore).commit();
    }

    public String leggi_str(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String valore = "";
        return mPreferences_leg.getString(campo, valore);
    }

    public int leggi_int(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int valore = 0;
        return mPreferences_leg.getInt(campo, valore);
    }

    void scrivi_int(String campo, int valore) {
        SharedPreferences mPreferences3 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPreferences3.edit().putInt(campo, valore).commit();
    }


    public synchronized void connesso(String username_site, String password_site) {


        WebView myWebView = getView().findViewById(R.id.WEB2);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        String url = "https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre";
        final String js = "javascript: document.getElementById('txtUsername').value='" + username_site + "';" + "document.getElementById('txtPassword').value='" + password_site + "';" + "document.getElementById('btnOK').click()";
        myWebView.loadUrl(url);

        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                Log.e("html", url);
                if (url.contains("fontenuopre")) {
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            Log.e("html", s);
                        }
                    });
                }
                if (url.contains("login")) {

                    connessioneok = false;
                    scrivi_str("connesso", "NO");
                    TextView txtconnesso = getView().findViewById(R.id.textView10);
                    txtconnesso.setText("Dati errati,verificare user/pass sul sito");

                }
                if (url.contains("PWM_ChildrenList.aspx")) {
                    connessioneok = true;
                    scrivi_str("connesso", "ok");
                    TextView txtconnesso = getView().findViewById(R.id.textView10);
                    txtconnesso.setText("Credenziali corrette");
                    Log.e("html", leggi_str("connesso").toString());

                }

            }
        });


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        final View v = inflater.inflate(R.layout.fragment_menu_7, container, false);


        EditText n_user = v.findViewById(R.id.editText);
        EditText n_password = v.findViewById(R.id.editText2);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        /* WebView myWebView =  findViewById(R.id.WEB);*/


//
//        if (leggi_str("radio1").equals("1")) {
//            rad1.setChecked(true);
//            rad2.setChecked(false);
//            rad3.setChecked(false);
//        }
//        if (leggi_str("radio1").equals("2")) {
//            rad1.setChecked(false);
//            rad2.setChecked(true);
//            rad3.setChecked(false);
//        }
//
//        if (leggi_str("radio1").equals("3")) {
//            rad1.setChecked(false);
//            rad2.setChecked(false);
//            rad3.setChecked(true);
//        }


        Button newGameButton = (Button) v.findViewById(R.id.button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                scrivi_str("connesso", "Test connessione in corso");
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                EditText n_user = getView().findViewById(R.id.editText);
                EditText n_password = getView().findViewById(R.id.editText2);
                TextView txtconnesso = getView().findViewById(R.id.textView10);
                txtconnesso.setText("Credenziali: " + leggi_str("connesso"));
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                scrivi_str("username", n_user.getText().toString());
                scrivi_str("password", n_password.getText().toString());
                scrivi_str("utenza", "SI");


                connesso(n_user.getText().toString(), n_password.getText().toString());
                Log.e("prima", leggi_str("connesso"));
                txtconnesso.setText("Credenziali: " + leggi_str("connesso"));


            }
        });


        username = leggi_str("username");
        password = leggi_str("password");
        n_user.setText(username);
        n_password.setText(password);

        return v;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Impostazioni");


    }
}