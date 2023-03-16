package com.estefania.proyectofinalm4b.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.estefania.proyectofinalm4b.Interfaces_Comunicacion.Comunicacion;
import com.estefania.proyectofinalm4b.R;
import com.estefania.proyectofinalm4b.activity_compras;
import com.estefania.proyectofinalm4b.clases.Detalle_pedido;

import java.util.List;
import java.util.Locale;

public class Adapter_Compras extends RecyclerView.Adapter<Adapter_Compras.ViewHolder> {

    private Context contexto;
     List<Detalle_pedido> detalles;
     Comunicacion c;


    public Adapter_Compras( List<Detalle_pedido> detalles, Context contexto ){
        this.detalles = detalles;
        this.c = c;
        this.contexto=contexto;



    }

    @NonNull
    @Override
    public Adapter_Compras.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_productos_carrito, parent, false);
        return new ViewHolder(view , this.c);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_Compras.ViewHolder holder, int position) {
        holder.setItem(this.detalles.get(position));


    }

    @Override
    public int getItemCount() {

        return detalles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView precio;
        TextView nombre;
        TextView descripcion;
        private final Comunicacion c;
        ImageView imgPlatilloDC, btnDecrease, btnAdd, btnEliminarPCarrito;
        EditText edtCantidad;


        TextView stock;
        public ViewHolder(@NonNull View itemView, Comunicacion c) {
            super(itemView);
            this.c = c;
            //imgPlatilloDC = imgPlatilloDC;
            this.btnEliminarPCarrito = itemView.findViewById(R.id.botoneliminar);
            this.btnAdd = itemView.findViewById(R.id.botoninvrementar);
            this.btnDecrease = itemView.findViewById(R.id.botondisminuir);
            //this.edtCantidad = itemView.findViewById(R.id.cantidadesperada);
            contexto = itemView.getContext();
            descripcion = (TextView) itemView.findViewById(R.id.txt_descrip1);
            nombre = (TextView) itemView.findViewById(R.id.txt_nombreproducto);
            precio = (TextView) itemView.findViewById(R.id.txt_precio_u);

        }
        public void setItem(final Detalle_pedido dp) {
            this.nombre.setText(dp.getProducto_agregar().getProd_nombre());
            this.precio.setText(String.format(Locale.ENGLISH, "S/%.2f", dp.getProducto_agregar().getProd_preciounitario()));
            int cant = dp.getDeta_cantidad();
            this.edtCantidad.setText(Integer.toString(cant));

            //-------------Actualizar Cantidad del Carrito-------------------------
            btnAdd.setOnClickListener(v -> {
                if (dp.getDeta_cantidad() != dp.getProducto_agregar().getStock()) {//Si el valor todavía no llega al estock, puede seguir agregando
                    dp.addOne();
                    Adapter_Compras.this.notifyDataSetChanged();
                }
            });
            btnDecrease.setOnClickListener(v -> {
                if (dp.getDeta_cantidad() != 1) {
                    dp.removeOne();
                    Adapter_Compras.this.notifyDataSetChanged();
                }
            });
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


    }
}
