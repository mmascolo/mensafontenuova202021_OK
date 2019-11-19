package com.mminf.mensafontenuova;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {


    private List<String> mdata;
    private List<String> mgiorno;
    private List<String> mprimo;
    private List<String> msecondo;
    private List<String> mcontorno;
    private List<String> mdolce;






    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<String> data, List<String> giorno, List<String> primo, List<String> secondo, List<String> contorno, List<String> dolce, ArrayList<String> news) {
        this.mInflater = LayoutInflater.from(context);
        this.mdata = data;
        this.mgiorno = giorno;
        this.mprimo = primo;
        this.msecondo = secondo;
        this.mcontorno = contorno;
        this.mdolce = dolce;



    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String data = mdata.get(position);
        String giorno = mgiorno.get(position);
        String primo = mprimo.get(position);
        String secondo = msecondo.get(position);
        String contorno = mcontorno.get(position);
        String dolce = mdolce.get(position);

        holder.data.setText(data);
        holder.giorno.setText(giorno);

        holder.primo.setText(primo);
        holder.secondo.setText(secondo);
        holder.contorno.setText(contorno);
        holder.dolce.setText(dolce);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mprimo.size();
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mprimo.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView data;
        TextView giorno;
        TextView primo;
        TextView secondo;
        TextView contorno;
        TextView dolce;







        ViewHolder(View itemView) {
            super(itemView);

        data = itemView.findViewById(R.id.data);
        giorno = itemView.findViewById(R.id.giorno);
        primo = itemView.findViewById(R.id.primo);
        secondo = itemView.findViewById(R.id.secondo);
        contorno = itemView.findViewById(R.id.contorno);
        dolce = itemView.findViewById(R.id.dolce);




          itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //  if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}