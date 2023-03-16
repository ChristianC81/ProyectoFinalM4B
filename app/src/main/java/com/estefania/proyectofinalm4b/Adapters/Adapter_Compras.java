package com.estefania.proyectofinalm4b.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estefania.proyectofinalm4b.Interfaces_Comunicacion.Comunicacion;
import com.estefania.proyectofinalm4b.R;
import com.estefania.proyectofinalm4b.activity_compras;
import com.estefania.proyectofinalm4b.clases.producto;
import com.estefania.proyectofinalm4b.detalle_producto;

import java.util.List;

public class Adapter_Compras extends RecyclerView.Adapter<Adapter_Compras.ViewHolder> {

    private Context contexto;
    List<producto> productos;
    Comunicacion carrito;
    ImageButton imgbtn2;
    LayoutInflater inflater;
    Button btnAgregarCarro;

    public Adapter_Compras(Context contexto, List<producto> productos){
        this.productos = productos;
        this.contexto = contexto;

        this.btnAgregarCarro=btnAgregarCarro;
    }

    @NonNull
    @Override
    public Adapter_Compras.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_productos_carrito, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Compras.ViewHolder holder, int position) {
        holder.setOnClickListeners();
        holder.descripcion.setText(productos.get(position).getProd_descripcion());
        holder.nombre.setText(productos.get(position).getProd_nombre());
        holder.precio.setText(String.valueOf(productos.get(position).getProd_preciounitario()));
        //holder.stock.setText(String.valueOf(productos.get(position).getStock()));


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


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView precio;
        TextView nombre;
        TextView descripcion;

        TextView stock;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contexto = itemView.getContext();
            descripcion = (TextView) itemView.findViewById(R.id.txt_descrip1);
            nombre = (TextView) itemView.findViewById(R.id.txt_nombreproducto);
            precio = (TextView) itemView.findViewById(R.id.txt_precio_u);



            imgbtn2 = (ImageButton) itemView.findViewById(R.id.imgbtn2);
            btnAgregarCarro = (Button) itemView.findViewById(R.id.btnAgregarCarro);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAgregarCarro:
                    Intent intent1 = new Intent(contexto,activity_compras.class);
                    intent1.putExtra("nombre", nombre.getText());
                    intent1.putExtra("precio", precio.getText());
                    intent1.putExtra("descripcion", descripcion.getText());
                    intent1.putExtra("stock", stock.getText());
                    contexto.startActivity(intent1);
                    break;
                case R.id.imgbtn2:
                    Toast.makeText(contexto, "Añadido al carrito de compras", Toast.LENGTH_SHORT).show();

                    break;
            }

        }

        public void setOnClickListeners() {
            imgbtn2.setOnClickListener(this);
            btnAgregarCarro.setOnClickListener(this);
        }
    }
}
