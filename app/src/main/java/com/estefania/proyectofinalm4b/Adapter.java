package com.estefania.proyectofinalm4b;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.estefania.proyectofinalm4b.clases.producto;

import org.w3c.dom.Text;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ////CONTEXT
        Context contexto;
        /////Botones declarados
        ImageButton btnimg2;
        ImageButton detalle;
        ////los textos
        TextView codigo;
        TextView nombre;
        TextView tipo;
        TextView descripcion;
        TextView stock;
        TextView precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            codigo = (TextView) itemView.findViewById(R.id.txtextra);
            nombre = (TextView) itemView.findViewById(R.id.txtprod_nombre);
            tipo = (TextView) itemView.findViewById(R.id.txtTipo);
            descripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
            stock = (TextView) itemView.findViewById(R.id.txtStock);
            precio = (TextView) itemView.findViewById(R.id.txtprod_preciounitario);
            /*nombre= itemView.findViewById(R.id.txtprod_nombre);*/

            btnimg2 = (ImageButton) itemView.findViewById(R.id.imgbtn2);
            detalle = (ImageButton) itemView.findViewById(R.id.imgbtndesc);

        }

        void setOnClickListeners(){
            detalle.setOnClickListener(this);
            btnimg2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imgbtndesc:
                    Intent intent1 = new Intent (contexto, detalle_producto.class);
                    intent1.putExtra("nombre", nombre.getText());
                    intent1.putExtra("precio", precio.getText());
                    /*intent1.putExtra("codigo", codigo.getText());
                    intent1.putExtra("tipo", tipo.getText());*/
                    contexto.startActivity(intent1);
                    break;
                case R.id.imgbtn2:
                    Intent intent2 = new Intent (contexto, activity_compras.class);
                    contexto.startActivity(intent2);
                    break;

            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind dhe data
        ///PONER LOS EVENTOS
        holder.setOnClickListeners();
        /*
        holder.tipo.setText(productos.get(position).getProd_nombre());
        holder.codigo.setText(productos.get(position).getProd_nombre());*/
        holder.descripcion.setText(productos.get(position).getProd_descripcion());
        holder.nombre.setText(productos.get(position).getProd_nombre());
        holder.precio.setText(String.valueOf(productos.get(position).getProd_preciounitario()));
    }
}
