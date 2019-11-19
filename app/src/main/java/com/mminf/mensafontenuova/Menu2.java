package com.mminf.mensafontenuova;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Menu2 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
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



    public static String menu = "";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_1, container, false);



        OkHttpHandler downloadFilesTask = new OkHttpHandler(getContext());

        downloadFilesTask.execute();

        Log.e("main", menu);


        String[] menu_cibo = menu.split(",");




        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> giorno = new ArrayList<>();
        ArrayList<String> primo = new ArrayList<>();
        ArrayList<String> secondo = new ArrayList<>();
        ArrayList<String> contorno = new ArrayList<>();
        ArrayList<String> dolce = new ArrayList<>();
        ArrayList<String> news = new ArrayList<>();


        String day_appo;
        int aa = menu_cibo.length;


        for (int index = 1; index < aa-1; index++)
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
    news.add(day[6]);





    day_appo ="";
}





        // set up the RecyclerView
        RecyclerView recyclerView = v.findViewById(R.id.rvAnimals);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);


        adapter = new MyRecyclerViewAdapter(getActivity(),data,giorno,primo,secondo,contorno,dolce,news);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Date d = new Date();
        String date = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        String mese = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String anno = new SimpleDateFormat("YYYY", Locale.getDefault()).format(new Date());

        int posizione = Integer.parseInt(date);


        Log.e("mese", mese);
        if ((mese.equals("10")) && (anno.equals("2019"))) {
            posizione = ((posizione) + 30);
        }
        if ((mese.equals("11")) && (anno.equals("2019"))) {
            posizione = ((posizione) + 61);
        }
        if ((mese.equals("12")) && (anno.equals("2019"))) {
            posizione = ((posizione) + 91);
        }
        if ((mese.equals("01")) && (anno.equals("2020"))) {
            posizione = ((posizione) + 122);
        }
        if ((mese.equals("02")) && (anno.equals("2020"))) {
            posizione = ((posizione) + 154);
        }
        if ((mese.equals("03")) && (anno.equals("2020"))) {
            posizione = ((posizione) + 182);
        }
        if ((mese.equals("04")) && (anno.equals("2020"))) {
            posizione = ((posizione) + 213);
        }
        if ((mese.equals("05")) && (anno.equals("2020"))) {
            posizione = ((posizione) + 243);
        }
        if ((mese.equals("06")) && (anno.equals("2020"))) {
            posizione = ((posizione) + 274);
        }


        if (posizione < 0) {
            posizione = 0;
        }









        recyclerView.getLayoutManager().scrollToPosition(posizione - 1);


        return v;
    }






    private MyRecyclerViewAdapter adapter;




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


class OkHttpHandler extends AsyncTask<String, Void, String> {
    public ProgressDialog dialog;
    OkHttpClient client = new OkHttpClient();
    private WeakReference<Context> contextRef;

    public OkHttpHandler(Context context) {
        contextRef = new WeakReference<>(context);
    }

    public void scrivi_str(String campo, String valore) {
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(contextRef.get());
        mPreferences_agg.edit().putString(campo, valore).commit();
    }

    protected void onPreExecute() {

        Log.e("contex", contextRef.get().toString());


        //dialog
        dialog = new ProgressDialog(contextRef.get());
        dialog.setTitle("Download menù");
        dialog.setMessage("caricamento, attendere prego");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();


        Response responses = null;

        // Reset the response code
        Request request = new Request.Builder()
                .url("https://www.mminf.cloud/menu2.txt")
                .build();

        try {
            Response response = client.newCall(request).execute();
            Log.e("connesso", "connesso e scaricato");
            return response.body().string();


        } catch (Exception e) {
            Log.e("conn", "NOT OK");
            e.printStackTrace();

        }
        return null;
        }

    @Override
    protected void onPostExecute(String result) {


        try {
            if (result != null && result.length() > 0) {


                Menu2.menu = result;

                Log.e("menu", result);

                dialog.dismiss();

                Toast.makeText(contextRef.get(),result , Toast.LENGTH_SHORT).show();
                Menu2.menu = result;

            }
        } catch (Exception e) {

        }

    }







}


















