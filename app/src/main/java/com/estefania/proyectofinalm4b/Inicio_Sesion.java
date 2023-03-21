package com.estefania.proyectofinalm4b;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.estefania.proyectofinalm4b.clases.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Inicio_Sesion extends AppCompatActivity {


    ArrayList<Usuario> datos =new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        obtenerDatos();
        Button info = findViewById(R.id.botoniniciarsesion);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificar(v);
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

    private void manejaJson(@NonNull JSONArray jsonArray){
        for (int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject=null;
            Usuario publicacion=new Usuario();
            try {
                jsonObject=jsonArray.getJSONObject(i);
                publicacion.setUsur_clave(jsonObject.getString("usu_clave"));
                publicacion.setUsur_correo(jsonObject.getString("usu_correo"));
                datos.add(publicacion);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        //arrayAdapter.notifyDataSetChanged();
    }

    private void obtenerDatos(){
        String url="http://25.43.143.100:8080/api/usuarios";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //obtengo el json respuesta
                //MANEJAMOS EL JSON
                manejaJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //obtengo un error si es que se da
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    public void verificar(View v){
        Integer non=1;
        EditText co = findViewById(R.id.txtcorreo);
        EditText cla=findViewById(R.id.txtclave);

        String correox=co.getText().toString();
        String clavex=cla.getText().toString();
        for (int x=0;x< datos.size();x++){
            if (datos.get(x).getUsur_correo().equals(correox)&&datos.get(x).getUsur_clave().equals(clavex)){

                non=0;
            }
        }
        if (non==1){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "NO ENCONTRADO", Toast.LENGTH_SHORT);
            toast1.show();
        }else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "ENCONTRADO", Toast.LENGTH_SHORT);
            toast1.show();
            Intent intent = new Intent (v.getContext(), MainActivity.class);
            startActivityForResult(intent, 0);
        }

    }

}