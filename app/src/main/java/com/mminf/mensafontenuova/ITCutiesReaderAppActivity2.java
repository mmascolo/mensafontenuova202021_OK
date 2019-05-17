package com.mminf.mensafontenuova;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Main application activity.
 * <p>
 * Update: Downloading RSS data in an async task
 *
 * @author ITCuties
 */
public class ITCutiesReaderAppActivity2 extends Activity {

    // A reference to the local object
    private ITCutiesReaderAppActivity2 local;

    /**
     * This method creates main application view
     */
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set view

        setContentView(R.layout.activity_rss2);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        toolbar.setNavigationIcon(R.mipmap.ic_back); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        // Set reference to this activity
        local = this;

        GetRSSDataTask task = new GetRSSDataTask();

        // Start download RSS task
        task.execute("www.istitutopirandello.it/public/index.php?format=feed&amp;type=rss");

        // Debug the thread name
        Log.d("ITCRssReader", Thread.currentThread().getName());
    }

    private class GetRSSDataTask extends AsyncTask<String, Void, List<RssItem>> {
        @Override
        protected List<RssItem> doInBackground(String... urls) {

            // Debug the task thread name
            Log.d("ITCRssReader", Thread.currentThread().getName());

            try {
                // Create RSS reader
                RssReader rssReader = new RssReader(urls[0]);

                // Parse RSS, get items
                return rssReader.getItems();

            } catch (Exception e) {
                Log.e("ITCRssReader", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<RssItem> result) {

            // Get a ListView from main view
            ListView itcItems = (ListView) findViewById(R.id.listMainView);

            // Create a list adapter
            ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(local, android.R.layout.simple_list_item_1, result);
            // Set list adapter for the ListView
            itcItems.setAdapter(adapter);

            // Set list view item click listener
            itcItems.setOnItemClickListener(new ListListener(result, local));
        }
    }
}