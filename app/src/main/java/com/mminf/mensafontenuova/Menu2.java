package com.mminf.mensafontenuova;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu2<aa> extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    private MyRecyclerViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments

        View v = inflater.inflate(R.layout.fragment_menu_1, container, false);
        // data to populate the RecyclerView with
        String[] menu_cibo = {
                "01/04/2019;LUNEDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione","02/04/2019;MARTEDI;Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione","03/04/2019;MERCOLEDI;Pasta al burro;Arrosto di tacchino;Carote;Dessert","04/04/2019;GIOVEDI;Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione","05/04/2019;VENERDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione","06/04/2019;SABATO; ; ; ; ;","07/04/2019;DOMENICA; ; ; ; ;","08/04/2019;LUNEDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione","09/04/2019;MARTEDI;Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione","10/04/2019;MERCOLEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione","11/04/2019;GIOVEDI;Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta","12/04/2019;VENERDI;Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione","13/04/2019;SABATO; ; ; ; ;","14/04/2019;DOMENICA; ; ; ; ;","15/04/2019;LUNEDI;Pasta al pesto;Bresaola;Fagiolini;Frutta di  stagione","16/04/2019;MARTEDI;Pasta al  pomodoro;Coscio pollo forno;Carote filangee;Gelato","17/04/2019;MERCOLEDI;Risotto primavera;Bocconcini di Merluzzo;bieta;Succo di frutta","18/04/2019;GIOVEDI;Gnocchetti di semola al pomodoro;Arrosto di vitellone;pomodori/finocchi;Frutta di stagione","19/04/2019;VENERDI;Pasta olio e parmigiano;Mozzarella o formaggio;insalata;Frutta di stagione","20/04/2019;SABATO; ; ; ; ;","21/04/2019;DOMENICA; ; ; ; ;","22/04/2019;LUNEDI; ; ; ; ;","23/04/2019;MARTEDI; ; ; ; ;","24/04/2019;MERCOLEDI; ; ; ; ;","25/04/2019;GIOVEDI; ; ; ; ;","26/04/2019;VENERDI; ; ; ; ;","27/04/2019;SABATO; ; ; ; ;","28/04/2019;DOMENICA; ; ; ; ;","29/04/2019;LUNEDI;Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione","30/04/2019;MARTEDI;Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione","01/05/2019;MERCOLEDI;1*Tagliatelle al pomodoro;Polpette Vitellone al forno;Bieta all’agro;Frutta di stagione","02/05/2019;GIOVEDI;Pasta e zucchine;Prosciutto cotto;Insalata;Frutta di stagione","03/05/2019;VENERDI;Risotto al pomodoro;Frittata;Fagiolini all’agro;Dessert","04/05/2019;SABATO; ; ; ; ; ;","05/05/2019;DOMENICA; ; ; ; ;","06/05/2019;LUNEDI;Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione","07/05/2019;MARTEDI;Fettuccine al ragù;Mozzarella/Formaggio;Pomodori/finocchi;Frutta di stagione","08/05/2019;MERCOLEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Gelato","09/05/2019;GIOVEDI; Risotto con piselli;Fesa di tacchino;Carote;Frutta di stagione","10/05/2019;VENERDI; Pasta al pomodoro;Polpette Vitellone;Piselli al vapore;Frutta di stagione","11/05/2019;SABATO; ; ; ; ;","12/05/2019;DOMENICA; ; ; ; ;","13/05/2019;LUNEDI;Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione","14/05/2019;MARTEDI;Pasta al burro;Arrosto di tacchino;Carote;Dessert","15/05/2019;MERCOLEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione","16/05/2019;GIOVEDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione","17/05/2019;VENERDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione","18/05/2019;SABATO; ; ; ; ;","19/05/2019;DOMENICA; ; ; ; ;","20/05/2019;LUNEDI; Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione","21/05/2019;MARTEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione","22/05/2019;MERCOLEDI; Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta","23/05/2019;GIOVEDI; Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione","24/05/2019;VENERDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione","25/05/2019;SABATO; ; ; ; ;","26/05/2019;DOMENICA; ; ; ; ;","27/05/2019;LUNEDI;Pasta al  pomodoro;Coscio pollo forno;Carote filangee;Gelato","28/05/2019;MARTEDI;;Risotto primavera;Bocconcini di Merluzzo;bieta;Succo di frutta","29/05/2019;MERCOLEDI;Gnocchetti di semola al pomodoro;Arrosto di vitellone;pomodori/finocchi;Frutta di stagione","30/05/2019;GIOVEDI;Pasta olio e parmigiano;Mozzarella o formaggio;insalata;Frutta di stagione","31/05/2019;VENERDI;Pasta al pesto;Bresaola;Fagiolini;Frutta di  stagione","01/06/2019;SABATO; ; ; ; ;","02/06/2019;DOMENICA; ; ; ; ;","03/06/2019;LUNEDI; Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione","04/06/2019;MARTEDI;Tagliatelle al pomodoro;Polpette Vitellone al forno;Bieta all’agro;Frutta di stagione","05/06/2019;MERCOLEDI; Pasta e zucchine;Prosciutto cotto;Insalata;Frutta di stagione","06/06/2019;GIOVEDI; Risotto al pomodoro;Frittata;Fagiolini all’agro;Dessert","07/06/2019;VENERDI; Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione","08/06/2019;SABATO; ; ; ; ;","09/06/2019;DOMENICA; ; ; ; ;","10/06/2019;LUNEDI;Fettuccine al ragù;Mozzarella/Formaggio;Pomodori/finocchi;Frutta di stagione","11/06/2019;MARTEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Gelato","12/06/2019;MERCOLEDI; Risotto con piselli;Fesa di tacchino;Carote;Frutta di stagione","13/06/2019;GIOVEDI; Pasta al pomodoro;Polpette Vitellone;Piselli al vapore;Frutta di stagione","14/06/2019;VENERDI;Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione","15/06/2019;SABATO; ; ; ; ;","16/06/2019;DOMENICA; ; ; ; ;","17/06/2019;LUNEDI; Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione","18/06/2019;MARTEDI;Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione","19/06/2019;MERCOLEDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione","20/06/2019;GIOVEDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione","21/06/2019;VENERDI; Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione","22/06/2019;SABATO; ; ; ; ;","23/06/2019;DOMENICA; ; ; ; ;","24/06/2019;LUNEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione","25/06/2019;MARTEDI;Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta","26/06/2019;MERCOLEDI; Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione","27/06/2019;GIOVEDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione","28/06/2019;VENERDI;Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione","29/06/2019;SABATO; ; ; ; ;","30/06/2019;DOMENICA; ; ; ; ;",
       };


        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> giorno = new ArrayList<>();
        ArrayList<String> primo = new ArrayList<>();
        ArrayList<String> secondo = new ArrayList<>();
        ArrayList<String> contorno = new ArrayList<>();
        ArrayList<String> dolce = new ArrayList<>();


String day_appo;
        int aa = menu_cibo.length;




for (int index= 0;index < aa; index++)
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



/*

 int aa = menu_cibo.length;
        System.out.println(aa);



String myArray[] ={};




        ArrayList<List<String>> arrayList = new ArrayList<List<String>>();
        ArrayList<String> arrayList2 = new ArrayList<String>();

        for (String aString : menu_cibo) {
            arrayList.add(Arrays.asList(aString.split(";")));
        }

        System.out.println(arrayList);

         myArray = arrayList.toArray(myArray);

        System.out.println(myArray);

         /*

        for (String aString : menu_cibo) {
            System.out.println(arrayList.get(i));

            arrayList2.add(Arrays.asList(arrayList.get(i).spl1(",")));

        }


/*
data.add(day[0]);
giorno.add(day[1]);
primo.add(day[2]);
secondo.add(day[3]);
contorno.add(day[4]);
dolce.add(day[5]);
 */



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

        int posizione = Integer.parseInt(date);


        Log.e("mese", mese);
        if (mese.equals("05")) {posizione= posizione+30;}

        if (mese.equals("06")) {posizione= posizione+61;}







        recyclerView.getLayoutManager().scrollToPosition(posizione - 1);


        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Menù Mensa Fonte Nuova");


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }

}



