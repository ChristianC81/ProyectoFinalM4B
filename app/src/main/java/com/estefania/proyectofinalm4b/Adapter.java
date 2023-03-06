package com.estefania.proyectofinalm4b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.estefania.proyectofinalm4b.clases.producto;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context contexto;

    List<producto> productos;
    LayoutInflater inflater;
    public Adapter(Context contexto, List<producto> productos) {
        this.productos = productos;
        this.contexto = contexto;


    }



    @Override
    public int getItemCount() {
        int a ;

        if(productos != null && !productos.isEmpty()) {

            a = productos.size();
        }
        else {

            a = 0;

        }

        return a;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = inflater. from(parent.getContext()).inflate(R.layout.activity_lista_prod, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind dhe data
        holder.nombre.setText(productos.get(position).getProd_nombre());
       holder.precio.setText(String.valueOf(productos.get(position).getProd_preciounitario()));
       /* double precioDouble = Double.parseDouble(productos.get(position).getProd_preciounitario());
        holder.precio.setText(String.valueOf(precioDouble));*/

        /*notifyDataSetChanged();*/

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView nombre,precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre= itemView.findViewById(R.id.txtprod_nombre);
           precio= itemView.findViewById(R.id.txtprod_preciounitario);



        }
    }



}
