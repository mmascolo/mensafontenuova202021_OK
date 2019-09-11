package com.mminf.mensafontenuova;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu2<aa> extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    OkHttpClient client = new OkHttpClient();

    public static String readToString(String targetURL) throws IOException {
        URL url = new URL(targetURL);
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(url.openStream()));

        StringBuilder stringBuilder = new StringBuilder();

        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append(System.lineSeparator());
        }

        bufferedReader.close();
        return stringBuilder.toString().trim();
    }

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

    public void SITO() {
        Request request = new Request.Builder()
                .url("https://www.mminf.com/menu.txt")
                .build();

        try (Response response = client.newCall(request).execute()) {
            scrivi_str("menu", response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View v = inflater.inflate(R.layout.fragment_menu_1, container, false);
        // data to populate//
        ////        GetExample example = new GetExample();
        ////        String response = null;
        ////        try {
        ////            response = example.run("https://www.mminf.cloud/menu.txt");
        ////        } catch (IOException e) {
        ////            e.printStackTrace();
        ////        }
        ////        System.out.println(response); the RecyclerView with

        final Request request = new Request.Builder()
                .url("https://www.mminf.cloud/menu.txt")
                .build();

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        return null;
                    }
                    scrivi_str("menu", response.body().string());
                    return null;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {

                }
            }
        };


        scrivi_str("menu", "");

        asyncTask.execute();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String menu = leggi_str("menu");


        String[] menu_cibo = menu.split(",");


//     String[] menu_cibo = {
//               "01/04/2019;LUNEDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione", "02/04/2019;MARTEDI;Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione", "03/04/2019;MERCOLEDI;Pasta al burro;Arrosto di tacchino;Carote;Dessert", "04/04/2019;GIOVEDI;Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione", "05/04/2019;VENERDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione", "06/04/2019;SABATO; ; ; ; ;", "07/04/2019;DOMENICA; ; ; ; ;", "08/04/2019;LUNEDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione", "09/04/2019;MARTEDI;Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione", "10/04/2019;MERCOLEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione", "11/04/2019;GIOVEDI;Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta", "12/04/2019;VENERDI;Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione", "13/04/2019;SABATO; ; ; ; ;", "14/04/2019;DOMENICA; ; ; ; ;", "15/04/2019;LUNEDI;Pasta al pesto;Bresaola;Fagiolini;Frutta di  stagione", "16/04/2019;MARTEDI;Pasta al  pomodoro;Coscio pollo forno;Carote filangee;Gelato", "17/04/2019;MERCOLEDI;Risotto primavera;Bocconcini di Merluzzo;bieta;Succo di frutta", "18/04/2019;GIOVEDI;Gnocchetti di semola al pomodoro;Arrosto di vitellone;pomodori/finocchi;Frutta di stagione", "19/04/2019;VENERDI;Pasta olio e parmigiano;Mozzarella o formaggio;insalata;Frutta di stagione", "20/04/2019;SABATO; ; ; ; ;", "21/04/2019;DOMENICA; ; ; ; ;", "22/04/2019;LUNEDI; ; ; ; ;", "23/04/2019;MARTEDI; ; ; ; ;", "24/04/2019;MERCOLEDI; ; ; ; ;", "25/04/2019;GIOVEDI; ; ; ; ;", "26/04/2019;VENERDI; ; ; ; ;", "27/04/2019;SABATO; ; ; ; ;", "28/04/2019;DOMENICA; ; ; ; ;", "29/04/2019;LUNEDI;Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione", "30/04/2019;MARTEDI;Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione", "01/05/2019;MERCOLEDI;1*Tagliatelle al pomodoro;Polpette Vitellone al forno;Bieta all’agro;Frutta di stagione", "02/05/2019;GIOVEDI;Pasta e zucchine;Prosciutto cotto;Insalata;Frutta di stagione", "03/05/2019;VENERDI;Risotto al pomodoro;Frittata;Fagiolini all’agro;Dessert", "04/05/2019;SABATO; ; ; ; ; ;", "05/05/2019;DOMENICA; ; ; ; ;", "06/05/2019;LUNEDI;Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione", "07/05/2019;MARTEDI;Fettuccine al ragù;Mozzarella/Formaggio;Pomodori/finocchi;Frutta di stagione", "08/05/2019;MERCOLEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Gelato", "09/05/2019;GIOVEDI; Risotto con piselli;Fesa di tacchino;Carote;Frutta di stagione", "10/05/2019;VENERDI; Pasta al pomodoro;Polpette Vitellone;Piselli al vapore;Frutta di stagione", "11/05/2019;SABATO; ; ; ; ;", "12/05/2019;DOMENICA; ; ; ; ;", "13/05/2019;LUNEDI;Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione", "14/05/2019;MARTEDI;Pasta al burro;Arrosto di tacchino;Carote;Dessert", "15/05/2019;MERCOLEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione", "16/05/2019;GIOVEDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione", "17/05/2019;VENERDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione", "18/05/2019;SABATO; ; ; ; ;", "19/05/2019;DOMENICA; ; ; ; ;", "20/05/2019;LUNEDI; Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione", "21/05/2019;MARTEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione", "22/05/2019;MERCOLEDI; Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta", "23/05/2019;GIOVEDI; Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione", "24/05/2019;VENERDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione", "25/05/2019;SABATO; ; ; ; ;", "26/05/2019;DOMENICA; ; ; ; ;", "27/05/2019;LUNEDI;Pasta al  pomodoro;Coscio pollo forno;Carote filangee;Gelato", "28/05/2019;MARTEDI;;Risotto primavera;Bocconcini di Merluzzo;bieta;Succo di frutta", "29/05/2019;MERCOLEDI;Gnocchetti di semola al pomodoro;Arrosto di vitellone;pomodori/finocchi;Frutta di stagione", "30/05/2019;GIOVEDI;Pasta olio e parmigiano;Mozzarella o formaggio;insalata;Frutta di stagione", "31/05/2019;VENERDI;Pasta al pesto;Bresaola;Fagiolini;Frutta di  stagione", "01/06/2019;SABATO; ; ; ; ;", "02/06/2019;DOMENICA; ; ; ; ;", "03/06/2019;LUNEDI; Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione", "04/06/2019;MARTEDI;Tagliatelle al pomodoro;Polpette Vitellone al forno;Bieta all’agro;Frutta di stagione", "05/06/2019;MERCOLEDI; Pasta e zucchine;Prosciutto cotto;Insalata;Frutta di stagione", "06/06/2019;GIOVEDI; Risotto al pomodoro;Frittata;Fagiolini all’agro;Dessert", "07/06/2019;VENERDI; Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione", "08/06/2019;SABATO; ; ; ; ;", "09/06/2019;DOMENICA; ; ; ; ;", "10/06/2019;LUNEDI;Fettuccine al ragù;Mozzarella/Formaggio;Pomodori/finocchi;Frutta di stagione", "11/06/2019;MARTEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Gelato", "12/06/2019;MERCOLEDI; Risotto con piselli;Fesa di tacchino;Carote;Frutta di stagione", "13/06/2019;GIOVEDI; Pasta al pomodoro;Polpette Vitellone;Piselli al vapore;Frutta di stagione", "14/06/2019;VENERDI;Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione", "15/06/2019;SABATO; ; ; ; ;", "16/06/2019;DOMENICA; ; ; ; ;", "17/06/2019;LUNEDI; Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione", "18/06/2019;MARTEDI;Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione", "19/06/2019;MERCOLEDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione", "20/06/2019;GIOVEDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione", "21/06/2019;VENERDI; Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione", "22/06/2019;SABATO; ; ; ; ;", "23/06/2019;DOMENICA; ; ; ; ;", "24/06/2019;LUNEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione", "25/06/2019;MARTEDI;Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta", "26/06/2019;MERCOLEDI; Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione", "27/06/2019;GIOVEDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione", "28/06/2019;VENERDI;Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione", "29/06/2019;SABATO; ; ; ; ;", "30/06/2019;DOMENICA; ; ; ; ;"
//      };


        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> giorno = new ArrayList<>();
        ArrayList<String> primo = new ArrayList<>();
        ArrayList<String> secondo = new ArrayList<>();
        ArrayList<String> contorno = new ArrayList<>();
        ArrayList<String> dolce = new ArrayList<>();


String day_appo;
        int aa = menu_cibo.length;


        for (int index = 1; index < aa; index++)
{
    day_appo = menu_cibo[index];

    System.out.println(day_appo);
   String[] day =   day_appo.split(";");

    data.add(day[0]);
    giorno.add(day[1]);
    primo.add(day[2]);
    secondo.add(day[3]);
    contorno.add(day[4]);
    dolce.add(day[5]);





    day_appo ="";
}




        // set up the RecyclerView
        RecyclerView recyclerView = v.findViewById(R.id.rvAnimals);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);


        adapter = new MyRecyclerViewAdapter(getActivity(),data,giorno,primo,secondo,contorno,dolce);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Date d = new Date();
        String date = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        String mese = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String anno = new SimpleDateFormat("aaaa", Locale.getDefault()).format(new Date());

        int posizione = Integer.parseInt(date);


        Log.e("mese", mese);
        if ((mese == "10") && (anno == "2019")) {
            posizione = ((posizione) + 30);
        }
        if ((mese == "11") && (anno == "2019")) {
            posizione = ((posizione) + 61);
        }
        if ((mese == "12") && (anno == "2019")) {
            posizione = ((posizione) + 91);
        }
        if ((mese == "01") && (anno == "2020")) {
            posizione = ((posizione) + 122);
        }
        if ((mese == "02") && (anno == "2020")) {
            posizione = ((posizione) + 154);
        }
        if ((mese == "03") && (anno == "2020")) {
            posizione = ((posizione) + 182);
        }
        if ((mese == "04") && (anno == "2020")) {
            posizione = ((posizione) + 213);
        }
        if ((mese == "05") && (anno == "2020")) {
            posizione = ((posizione) + 243);
        }
        if ((mese == "06") && (anno == "2020")) {
            posizione = ((posizione) + 274);
        }


        if (posizione < 0) {
            posizione = 0;
        }









        recyclerView.getLayoutManager().scrollToPosition(posizione - 1);


        return v;
    }


    private MyRecyclerViewAdapter adapter;

    private class Connection extends AsyncTask {

        @Override
        protected Object doInBackground(Object... arg0) {
            SITO();
            return null;
        }

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menù Mensa Fonte Nuova");


    }

    @Override
    public void onItemClick(View view, int position) {
        //  Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

}


class OkHttpPost {

    OkHttpClient client = new OkHttpClient();

    String post(String url) throws IOException {

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}

class GetExample {
    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}








