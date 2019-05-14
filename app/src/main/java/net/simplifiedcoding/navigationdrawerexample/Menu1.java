package net.simplifiedcoding.navigationdrawerexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xml.sax.helpers.DefaultHandler;





/**
 * Created by Belal on 18/09/16.
 */


public class Menu1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments


        View v = inflater.inflate(R.layout.fragment_menu_1, container, false);

        View innerView = v.findViewById(R.id.text1);
        final TextView emailTextView = (TextView) innerView;

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                    RssParser parser;
                    parser = new RssParser("https://www.icsandropertinifontenuova.edu.it/rss.xml");
                    Log.i("LOG", "Description: " + parser.getItem(3).description); //4th item's description

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();







        return v;



    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Rss");
    }
}
