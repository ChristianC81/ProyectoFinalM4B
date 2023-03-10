package com.estefania.proyectofinalm4b;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.estefania.proyectofinalm4b.clases.producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context contexto;
    String JSON_URL = "http://192.168.18.52:8080/api/productos" ;
    List<producto> productos;
    LayoutInflater inflater;
    List<producto> listaOriginal;

    public Adapter(Context contexto, List<producto> productos) {
        this.productos = productos;
        this.listaOriginal = new ArrayList<>();
        listaOriginal.addAll(productos);
        this.contexto = contexto;



    }



    @Override
    public int getItemCount() {
        if (productos == null) {
            return 0;
        } else {
            return productos.size();
        }
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

    //METODO PARA BUSCAR
    public void filter(final String txtbuscar) {
        if (txtbuscar.length() == 0) {
            productos.clear();
            productos.addAll(listaOriginal);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<producto> collect = productos.stream()
                        .filter(i -> i.getProd_nombre().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());
                productos.clear();
                productos.addAll(collect);
            }
            else {
                productos.clear();
                for (producto i : listaOriginal) {
                    if (i.getProd_nombre().toLowerCase().contains(txtbuscar)) {
                        productos.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }




}
