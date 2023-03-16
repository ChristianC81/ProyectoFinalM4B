package com.estefania.proyectofinalm4b.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estefania.proyectofinalm4b.R;
import com.estefania.proyectofinalm4b.clases.producto;
import com.estefania.proyectofinalm4b.detalle_producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context contexto;
    String JSON_URL = "http://192.168.18.52:8080/api/productos";
    List<producto> productos;
    LayoutInflater inflater;
    List<producto> listaOriginal;

    public Adapter(Context contexto, List<producto> productos) {
        this.productos = productos;
        this.contexto = contexto;
        this.listaOriginal = new ArrayList<>();
        this.listaOriginal.addAll(productos);

    }

    @Override
    public int getItemCount() {
        int a;
        if (productos != null && !productos.isEmpty()) {
            a = productos.size();
        } else {
            a = 0;
        }
        return a;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_lista_prod, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ////CONTEXT
        Context contexto;
        /////Botones declarados
        ImageButton btnimg2;
        ImageButton detalle;
        ////los textos
        TextView precio;
        TextView nombre;
        TextView descripcion;
        TextView tipo;
        TextView stock;
        TextView codigo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            descripcion = (TextView) itemView.findViewById(R.id.textViewdescripcion);
            nombre = (TextView) itemView.findViewById(R.id.txtprod_nombre);
            precio = (TextView) itemView.findViewById(R.id.txtprod_preciounitario);
            codigo = (TextView) itemView.findViewById(R.id.textViewcodigo);
            tipo = (TextView) itemView.findViewById(R.id.textViewtipo);
            stock = (TextView) itemView.findViewById(R.id.textViewstock);


            btnimg2 = (ImageButton) itemView.findViewById(R.id.imgbtn2);
            detalle = (ImageButton) itemView.findViewById(R.id.btnimgDetalle);

        }

        void setOnClickListeners() {
            detalle.setOnClickListener(this);
            btnimg2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnimgDetalle:
                    Intent intent1 = new Intent(contexto, detalle_producto.class);
                    intent1.putExtra("nombre", nombre.getText());
                    intent1.putExtra("precio", precio.getText());
                    intent1.putExtra("descripcion", descripcion.getText());
                    intent1.putExtra("codigo", codigo.getText());
                    intent1.putExtra("tipo", tipo.getText());
                    intent1.putExtra("stock", stock.getText());
                    contexto.startActivity(intent1);
                    break;
                case R.id.imgbtn2:
                    Toast.makeText(contexto, "Añadido al carrito de compras", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ///PONER LOS EVENTOS
        holder.setOnClickListeners();
        holder.descripcion.setText(productos.get(position).getProd_descripcion());
        holder.nombre.setText(productos.get(position).getProd_nombre());
        holder.precio.setText(String.valueOf(productos.get(position).getProd_preciounitario()));
        holder.stock.setText(String.valueOf(productos.get(position).getStock()));
        holder.codigo.setText(productos.get(position).getProd_codigo());
        holder.tipo.setText(productos.get(position).getProd_tipo());
        /*notifyDataSetChanged();*/
    }


    //METODO PARA BUSCAR
    public void filter(final String txtbuscar) {

        if (txtbuscar.length() == 0) {
            productos.clear();
            productos.addAll(productos);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<producto> productos1 = productos.stream()
                        .filter(i -> i.getProd_nombre().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaOriginal.clear();
                productos.addAll(productos1);
            } else {
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
