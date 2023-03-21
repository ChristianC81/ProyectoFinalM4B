package com.estefania.proyectofinalm4b;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.estefania.proyectofinalm4b.clases.producto;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context contexto;
    private Context mContext;
    private RequestQueue requestQueue;
    String JSON_URL = "http://25.43.143.100:8080/api/productos";
    List<producto> productos;
    LayoutInflater inflater;
    List<producto> listaOriginal;

    public Adapter(Context contexto, List<producto> productos) {
        this.productos = productos;
        this.listaOriginal = new ArrayList<>();
        listaOriginal.addAll(productos);
        this.contexto = contexto;
        mContext=contexto;
        requestQueue= Volley.newRequestQueue(mContext);

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
        TextView id;
        Spinner cantidad;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contexto = itemView.getContext();
            descripcion = (TextView) itemView.findViewById(R.id.textViewdescripcion);
            nombre = (TextView) itemView.findViewById(R.id.txtprod_nombre);
            precio = (TextView) itemView.findViewById(R.id.txtprod_preciounitario);
            codigo = (TextView) itemView.findViewById(R.id.textViewcodigo);
            tipo = (TextView) itemView.findViewById(R.id.textViewtipo);
            stock = (TextView) itemView.findViewById(R.id.textViewstock);
            id=(TextView)itemView.findViewById(R.id.txt_id_prod);
            cantidad=(Spinner)itemView.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(mContext,R.array.cantidades, android.R.layout.simple_spinner_item);
            cantidad.setAdapter(adapter);

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
                    String det_pro_id=id.getText().toString();
                    String deta_cantidad=cantidad.getSelectedItem().toString();
                    String precio_unitario=precio.getText().toString();
                    Double precio_u=Double.parseDouble(precio_unitario);
                    Integer canti=Integer.parseInt(deta_cantidad);
                    Double resultado=canti*precio_u;
                    String deta_precio_total=String.valueOf(resultado);
                    Integer dett=1;

                    try {

                        enviarSolicitudDetalle(det_pro_id,deta_cantidad,deta_precio_total,dett);
                        // Toast.makeText(contexto, "Añadido al carrito de compras "+dett, Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
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
        holder.id.setText(String.valueOf(productos.get(position).getProd_id()));
        /*notifyDataSetChanged();*/
    }


    //METODO PARA BUSCAR
    public void filter(final String txtbuscar) {
        if (txtbuscar.length() == 0) {
            productos.clear();
            productos.addAll(listaOriginal);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                List<producto> collect = productos.stream()
                        .filter(i -> i.getProd_nombre().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());
                productos.clear();
                productos.addAll(collect);
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



    private void enviarSolicitudDetalle(String det_pro_id, String deta_cantidad, String deta_precio_total,Integer pedido_id) throws JSONException {

        String url = "http://10.0.2.2:8080/api/detalle/create";

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("det_prod_id",det_pro_id);
        jsonObject.put("pedido_id",pedido_id);
        jsonObject.put("deta_cantidad",deta_cantidad);
        jsonObject.put("deta_precio_total", deta_precio_total);


        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                response -> {
                    // Aquí manejas la respuesta exitosa de la API REST
                    System.out.println("EXITOSAMENTE");
                },
                error -> {
                    // Aquí manejas la respuesta de error de la API REST
                    System.out.println("VALIOOOO");
                }
        );

        requestQueue.add(jsonObjectRequest);
        String a=jsonObject.toString();
        System.out.println(a);


    }
}
