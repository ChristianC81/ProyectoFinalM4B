package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.estefania.proyectofinalm4b.Adapters.Adapter;
import com.estefania.proyectofinalm4b.clases.producto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Busqueda extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //DECLARAR LISTVIEW DE PRODUCTOS
    JsonArrayRequest request ;
     RequestQueue requestQueue ;
     RecyclerView listaproductos;
    List<producto> productos;
    //List<producto> listaOriginal;
    Adapter adapter;
    String JSON_URL = "http://10.0.2.2:8080/api/producto" ;
    SearchView txtbuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        listaproductos =findViewById(R.id.listprod);
        productos=new ArrayList<>();
        obtenerProductos();
        listaproductos.setHasFixedSize(true);
        listaproductos.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        adapter= new Adapter(getApplicationContext(), productos );
        listaproductos.setAdapter(adapter);

        //METODO PARA LA BUSQUEDA
        txtbuscar=findViewById(R.id.txtbuscar);

        //onclik activo
        txtbuscar.setOnQueryTextListener(this);

        /*listaproductos.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));*/

        //Jason
        ImageButton info = findViewById(R.id.imageButtonInicio2);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton info1 = findViewById(R.id.imageButtonCompra2);
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), activity_compras.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton info2 = findViewById(R.id.imageButtonPerfil2);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Perfil.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void obtenerProductos(){
        requestQueue = Volley.newRequestQueue(Busqueda.this);
        request = new JsonArrayRequest( Request.Method.GET,JSON_URL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0 ; i < response.length(); i++ ) {

                    try {
                        JSONObject productoObject = response.getJSONObject(i) ;
                        producto productos1 = new producto() ;
                        productos1.setProd_nombre(productoObject.getString("prod_nombre").toString());
                        productos1.setProd_preciounitario(productoObject.getDouble("prod_preciounitario"));
                        productos1.setProd_descripcion(productoObject.getString("prod_descripcion").toString());
                        productos1.setProd_tipo(productoObject.getString("prod_tipo").toString());
                        productos1.setStock(productoObject.getInt("prod_stock"));
                        productos1.setProd_codigo(productoObject.getString("prod_codigo").toString());
                        productos1.setProd_id(productoObject.getInt("prod_id"));
                        productos.add(productos1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse;" +error.getMessage());
            }
        });
        requestQueue.add(request) ;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}