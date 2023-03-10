package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class registro_main extends AppCompatActivity {

        private ProgressDialog ProgressDialog;
        private Object Task;
        private Object AuthResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_main);

        EditText Tcedula = findViewById(R.id.txtV_Identificacion);
        EditText Tnombre = findViewById(R.id.txtV_Nombres);
        EditText Tcorreo = findViewById(R.id.txtV_correo);
        EditText Tclave = findViewById(R.id.txtV_clave);
        EditText Tdireccion = findViewById(R.id.txtV_direccion);
        EditText Ttelefono = findViewById(R.id.txtV_telefono);

        Button info = findViewById(R.id.bt_volverInicio);
        Button regis = findViewById(R.id.bt_registrar);

        RequestQueue queue = Volley.newRequestQueue(this);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cedula_usu = Tcedula.getText().toString();
                String nombre_usu = Tnombre.getText().toString();
                String correo_usu = Tcorreo.getText().toString();
                String clave_usu = Tclave.getText().toString();
                String direccion_usu = Tdireccion.getText().toString();
                String telefono_usu = Ttelefono.getText().toString();

                try {
                    enviarSolicitudRegistroUsuario(cedula_usu, nombre_usu, correo_usu, clave_usu, direccion_usu, telefono_usu);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //mensajeVolverInicio(v);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Inicio_Sesion.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    private void enviarSolicitudRegistroUsuario(String identificacion, String nombre, String correo, String clave, String direccion, String telefono) throws JSONException {
        String url = "http://10.0.2.2:8080/api/usuarios";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("usu_identificacion", identificacion);
        jsonObject.put("usu_nombre", nombre);
        jsonObject.put("usu_correo", correo);
        jsonObject.put("usu_clave", clave);
        jsonObject.put("usu_direccion", direccion);
        jsonObject.put("usu_telefono", telefono);
        jsonObject.put("usu_rol", "Usuario");

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //ProgressDialog.setMessage("Realizando registro...");
        //ProgressDialog.show();

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

        //ProgressDialog.dismiss();
    }
}