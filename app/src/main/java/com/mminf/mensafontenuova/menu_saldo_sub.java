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
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPreferences_agg.edit().putString(campo, valore).commit();
    }


    public void scrivi_int(String campo, int valore) {
        SharedPreferences mPreferences3 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mPreferences3.edit().putInt(campo, valore).commit();
    }

    public int leggi_int(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int valore = 0;
        return mPreferences_leg.getInt(campo, valore);
    }

    public String leggi_str(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String valore = "";
        return mPreferences_leg.getString(campo, valore);
    }

    public String leggi_sito(String campo, String valore) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return mPreferences_leg.getString(campo, valore);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.menu_saldo_sub, container, false);


        final WebView myWebView3 = v.findViewById(R.id.WEBCAL);
        WebSettings webSettings = myWebView3.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView3.getSettings().setDomStorageEnabled(true);
        myWebView3.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        String username_sito = leggi_str("username");
        String password_sito = leggi_str("password");

        String url = "https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre";
        final String js = "javascript: document.getElementById('txtUsername').value='" + username_sito + "';" + "document.getElementById('txtPassword').value='" + password_sito + "';" + "document.getElementById('btnOK').click()";
        final String js2 = "(function ()  {var table = document.getElementById('tblChildrenList'); row = table.rows[1]; col = row.cells[1]; return col.innerHTML;})();";
        final String js4 = "{var div=';';var ret;ret =';';var table = document.getElementById('tblMainTbl');for (var i = 1, row; row = table.rows[i]; i++) {col = row.cells[0]; ret = ret + '|' + row.cells[0].innerText + div+ row.cells[1].innerText+div +row.cells[2].innerText+div+ row.cells[3].innerText+div +row.cells[4].innerText ;}return ret;})();";


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

                            String add = "https://www.schoolesuite.it/default1/" + Html.fromHtml((String) aaaa.substring(70, 154)).toString();
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

                            Log.e("***********S*********", a);
                            String[] separated = a.split(";");

                            if (a.equals("null")) {
                            } else {
                                Log.e("cal1", separated[0]);
                                Log.e("cal1", separated[1]);
                                Log.e("cal1", separated[2]);


                                MaterialCalendarView calendario = (MaterialCalendarView) getView().findViewById(R.id.calendarView);


                                List<CalendarDay> list = new ArrayList<CalendarDay>();

                                Calendar calendar = Calendar.getInstance();

                                CalendarDay calendarDay = CalendarDay.from(2019, 05, 27);

                                list.add(calendarDay);


                                calendario.setDateSelected(CalendarDay.from(2019, 05, 27), true);
                                calendario.addDecorator(new

                                        EventDecorator(Color.RED, list));
                                calendario.setDateSelected(CalendarDay.from(2019, 05, 22), true);
                            }


                        }


                    });


                }
            }
        });


        String DATE = leggi_str("calendario");

        Log.e("calendario", DATE);




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
            view.addSpan(new DotSpan(5, color));
        }
    }


}
