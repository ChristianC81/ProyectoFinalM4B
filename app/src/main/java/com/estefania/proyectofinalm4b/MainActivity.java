package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.estefania.proyectofinalm4b.clases.producto;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> datos=new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listProductos);
        arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, datos);
        listView.setAdapter(arrayAdapter);
        contenerDatos();

        ImageButton info2 = findViewById(R.id.imageButtonInicio2);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton info3 = findViewById(R.id.imageButtonCompra2);
        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), activity_compras.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton info4 = findViewById(R.id.imageButtonPerfil2);
        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Perfil.class);
                startActivityForResult(intent, 0);
            }
        });

        ImageButton info5 = findViewById(R.id.imageButtonBuscar);
        info5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Busqueda.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void contenerDatos(){
        String url= "http://10.0.2.2:8080/api/productos";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //obtenemos el json de respuesta
                        manejaJson(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //manejamos el error
                //Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void manejaJson(JSONArray jsonArray){
        for(int i=0; i< jsonArray.length(); i++){
            JSONObject jsonObject=null;
            producto publicacion = new producto();
            try {
                jsonObject = jsonArray.getJSONObject(i);
                publicacion.setProd_nombre(jsonObject.getString("prod_nombre"));//el nombre es como viene del json
                publicacion.setProd_tipo(jsonObject.getString("prod_tipo"));//el nombre es como viene del json
                publicacion.setProd_descripcion(jsonObject.getString("prod_descripcion"));//el nombre es como viene del json
                publicacion.setProd_preciounitario(jsonObject.getDouble("prod_preciounitario"));//el nombre es como viene del json
                datos.add(" Nombre:    "+publicacion.getProd_nombre()+" \n Tipo:  "+publicacion.getProd_tipo()+" \n Descripcion:           "+publicacion.getProd_descripcion()+" \n Precio:           "+publicacion.getProd_preciounitario());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        arrayAdapter.notifyDataSetChanged();
    }
}