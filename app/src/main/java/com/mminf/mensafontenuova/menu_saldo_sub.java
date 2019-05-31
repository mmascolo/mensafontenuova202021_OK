package com.mminf.mensafontenuova;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


public class menu_saldo_sub extends Fragment {


    public void scrivi_str(String campo, String valore) {
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(getContext());
        mPreferences_agg.edit().putString(campo, valore).commit();
    }


    public void scrivi_int(String campo, int valore) {
        SharedPreferences mPreferences3 = PreferenceManager.getDefaultSharedPreferences(getContext());
        mPreferences3.edit().putInt(campo, valore).commit();
    }

    public int leggi_int(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getContext());
        int valore = 0;
        return mPreferences_leg.getInt(campo, valore);
    }

    public String leggi_str(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getContext());
        String valore = "";
        return mPreferences_leg.getString(campo, valore);
    }

    public String leggi_sito(String campo, String valore) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getContext());
        return mPreferences_leg.getString(campo, valore);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.menu_saldo_sub, container, false);
        String cale = leggi_str("calendario");

        final WebView myWebView3 = v.findViewById(R.id.WEBCAL);
        WebSettings webSettings = myWebView3.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView3.getSettings().setDomStorageEnabled(true);
        myWebView3.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        String username_sito = leggi_str("username");
        String password_sito = leggi_str("password");

        String url = "https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre";
        final String js = "javascript: document.getElementById('txtUsername').value='" + username_sito + "';" + "document.getElementById('txtPassword').value='" + password_sito + "';" + "document.getElementById('btnOK').click()";
        final String js2 = "(function ()  {var table = document.getElementById('tblChildrenList'); row = table.rows[" + cale + "]; col = row.cells[1]; return col.innerHTML;})();";
        final String js4 = "(function () {var div=';';var ret;ret ='';var table = document.getElementById('tblMainTbl');for (var i = 1, row; row = table.rows[i]; i++) {ret = row.cells[0].innerText + div+ row.cells[1].innerText+div +row.cells[2].innerText+div+ row.cells[3].innerText+div +row.cells[4].innerText+div+ret ;}return ret;})();";


        final String aaa;

        myWebView3.loadUrl(url);
        myWebView3.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {


                if (url.contains("fontenuopre")) {
                    Log.e("fontenuovapre", url);
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {


                        }

                    });
                }


                if (url.contains("login")) {
                    Log.d("login", url);

                }


                if (url.contains("PWM_ChildrenList.aspx")) {
                    Log.e("estrai href ", url);

                    view.evaluateJavascript(js2, new ValueCallback<String>() {


                        @Override
                        public void onReceiveValue(String aaaa) {
                            Log.e("estratta href ", Html.fromHtml((String) aaaa).toString());
                            String add = "https://www.schoolesuite.it/default1/PWM_Details.aspx" + Html.fromHtml((String) aaaa.substring(aaaa.lastIndexOf("?"), aaaa.lastIndexOf("=") + 1).toString());
                            Log.e("add", add);

                            myWebView3.loadUrl(add);


                        }
                    });


                }
                if (url.contains("PWM_Details.aspx")) {
                    Log.d("details", url);
                    Log.d("***JS4***", js4);
                    view.evaluateJavascript(js4, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String a) {

                            Log.e("STRINGA", a);
                            a = a.substring(1);

                            String[] separated = a.split(";");


                            MaterialCalendarView calendario = (MaterialCalendarView) getView().findViewById(R.id.calendarView);


                            List<CalendarDay> list = new ArrayList<CalendarDay>();
                            List<CalendarDay> lista_soldi = new ArrayList<CalendarDay>();

                            Calendar calendar = Calendar.getInstance();

                            if (a.equals("null")) {
                            } else {

                                for (int i = 0; i < separated.length - 4; i = i + 5) {

                                    Log.e("i", Integer.toString(i));


                                    Log.e("!!!!!!!!", separated[i]);
                                    String giorno = separated[i].substring(0, 2);
                                    Log.e("giorno", giorno);

                                    String mese = separated[i].substring(3, 5);
                                    Log.e("mese", mese);

                                    String anno = separated[i].substring(6, 10);
                                    Log.e("anno", anno);

                                    if (separated[i + 2].equals("SERVIZIO MENSA")) {

                                        CalendarDay calendarDay = CalendarDay.from(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
                                        list.add(calendarDay);


                                    } else {

                                    }


                                    CalendarDay calendarDay = CalendarDay.from(Integer.parseInt(anno), Integer.parseInt(mese), Integer.parseInt(giorno));
                                    list.add(calendarDay);


                                    Log.e("11111111", separated[i + 1]);


                                    Log.e("33333333", separated[i + 3]);
                                    Log.e("44444444", separated[i + 4]);
                                }


                                TextView incorso = (TextView) getView().findViewById(R.id.incorso);


                                calendario.addDecorator(new

                                        EventDecorator(Color.RED, list));
                                incorso.setVisibility(View.INVISIBLE);
                                calendario.setVisibility(View.VISIBLE);
                            }



                        }


                    });


                }
            }
        });





        return v;


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Calendario presenze");


    }


    public class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(15, color));



        }


    }


}
