package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.estefania.proyectofinalm4b.clases.Cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Inicio_Sesion extends AppCompatActivity {


    ArrayList<Cliente> datos =new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Button info = findViewById(R.id.botoniniciarsesion);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validacion();
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        Button info1 = findViewById(R.id.buttonEditar);
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), registro_main.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void manejaJson(JSONArray jsonArray){
        for (int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject=null;
            Cliente cliente=new Cliente();

            try {
                jsonObject=jsonArray.getJSONObject(i);

                cliente.setCli_correo(jsonObject.getString("cli_correo"));
                cliente.setCli_clave(jsonObject.getString("cli_clave"));
                datos.add(cliente);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        arrayAdapter.notifyDataSetChanged();
    }

    private void obtener_datos(){
        String url= "\"http://10.0.2.2:8080/api/CLIENTES";
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
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    public void validacion(){

        obtener_datos();

        String correo_ingresado=findViewById(R.id.txtcorreo).toString();
        String clave_ingresado=findViewById(R.id.txtclave).toString();

        for (int i=0; i<datos.size();i++){
            if (datos.get(i).getCli_clave().equals(clave_ingresado)&&datos.get(i).getCli_correo().equals(correo_ingresado)){
                Toast.makeText(getApplicationContext(),"ENCONTRADO CON EXITO",Toast.LENGTH_SHORT).show();
            }
        }
    }

}