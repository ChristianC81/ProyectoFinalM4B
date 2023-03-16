package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.estefania.proyectofinalm4b.Adapters.Adapter;
import com.estefania.proyectofinalm4b.Adapters.Adapter_Compras;
import com.estefania.proyectofinalm4b.clases.producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class activity_compras extends AppCompatActivity {
     Adapter_Compras adapter_compras;

    JsonArrayRequest request ;
    RequestQueue requestQueue ;

    String JSON_URL = "http://10.0.2.2:8080/api/producto" ;

     Button btfinalizar;
    RecyclerView recyclerView;
    List<producto> productos;

    String nombre="";
    String descripcion="";
    String precio="";

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        recyclerView = findViewById(R.id.listprod2);
        //Llamar el adapter para que carge los datos
        obtenerCarrito();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        adapter_compras= new Adapter_Compras(getApplicationContext(), productos );
        recyclerView.setAdapter(adapter_compras);


        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            nombre = extras.getString("nombre");
            descripcion = extras.getString("descripcion");
            precio = extras.getString("precio");

        }



        ImageButton info2 = findViewById(R.id.imageButtonInicio4);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });



        ImageButton info3 = findViewById(R.id.imageButtonCompra4);
        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), activity_compras.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton info4 = findViewById(R.id.imageButtonPerfil4);
        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Perfil.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton info5 = findViewById(R.id.imageButtonBuscar4);
        info5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Busqueda.class);
                startActivityForResult(intent, 0);
            }
        });
    }
    public void obtenerCarrito(){
        requestQueue = Volley.newRequestQueue(activity_compras.this);
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter_compras.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse;" +error.getMessage());
            }
        });
        requestQueue.add(request) ;
    }
}