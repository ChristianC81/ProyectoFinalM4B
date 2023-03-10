package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class registro_main extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Handler handler;

    EditText Tcedula, Tnombre, Tcorreo, Tclave, TverifiClave, Tdireccion, Ttelefono;
    CheckBox Tterminos;
    Button info, regis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_main);

        Tcedula = findViewById(R.id.txtV_Identificacion);
        Tnombre = findViewById(R.id.txtV_Nombres);
        Tcorreo = findViewById(R.id.txtV_correo);
        Tclave = findViewById(R.id.txtV_clave);
        TverifiClave = findViewById(R.id.txtV_claveRepetir);
        Tdireccion = findViewById(R.id.txtV_direccion);
        Ttelefono = findViewById(R.id.txtV_telefono);
        Tterminos = findViewById(R.id.check_terminos);

        info = findViewById(R.id.bt_volverInicio);
        regis = findViewById(R.id.bt_registrar);

        RequestQueue queue = Volley.newRequestQueue(this);

        Tcedula.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String textced = Tcedula.getText().toString().trim();
                    if (textced.isEmpty()) {
                        Tcedula.setError("Este campo es requerido");
                    } else if (!validarCedula(textced)) {
                        Tcedula.setError("Cédula inválida");
                    }
                }
            }
        });

        Tcorreo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String textCorr = Tcorreo.getText().toString().trim();
                    if (textCorr.isEmpty()) {
                        Tcorreo.setError("Este campo es requerido");
                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(textCorr).matches()) {
                        Tcorreo.setError("Correo inválido");
                    }
                }
            }
        });

        Ttelefono.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String texttel = Ttelefono.getText().toString().trim();
                    if (texttel.isEmpty()) {
                        Ttelefono.setError("Este campo es requerido");
                    } else if (!validarTelefonoMovil(texttel)) {
                        Ttelefono.setError("Correo inválido");
                    }
                }
            }
        });
/*
        TverifiClave.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String text = TverifiClave.getText().toString().trim();
                    if (text.isEmpty()) {
                        TverifiClave.setError("Este campo es requerido");
                    } else if (!(text == Tclave.getText().toString())) {
                        TverifiClave.setError("La contraseña no coincide");
                    }
                }
            }
        });
*/
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(registro_main.this);
                progressDialog.setMessage("Registrando...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String cedula_usu = Tcedula.getText().toString();
                String nombre_usu = Tnombre.getText().toString();
                String correo_usu = Tcorreo.getText().toString();
                String clave_usu = Tclave.getText().toString();
                String direccion_usu = Tdireccion.getText().toString();
                String telefono_usu = Ttelefono.getText().toString();

                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean resultado = true;

                        progressDialog.dismiss();//Ocultar el resultado

                        //if(validacionesAll(cedula_usu, nombre_usu, correo_usu, clave_usu, direccion_usu, telefono_usu)){
                            if(Tterminos.isChecked()){
                                try {
                                    enviarSolicitudRegistroUsuario(cedula_usu, nombre_usu, correo_usu, clave_usu, direccion_usu, telefono_usu);
                                    Toast.makeText(registro_main.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                    Tterminos.setTextColor(Color.BLACK);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                Tterminos.setTextColor(Color.RED);
                                Toast.makeText(getApplicationContext(), "Los terminos y condiciones de la aplicacion no han sido aceptados", Toast.LENGTH_LONG).show();
                            }
                        //} else {
                          //  Toast.makeText(getApplicationContext(), "Compruebe si no hay errores en los campos", Toast.LENGTH_LONG).show();
                        //}
                    }
                }, 2300); // Simular un proceso que tarda 2.3 segundos

                //mensajeVolverInicio(v);
            }
        });

/*
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String text = editText.getText().toString().trim();
                    if (text.isEmpty()) {
                        editText.setError("Este campo es requerido");
                    } else {
                        // realizar validación adicional aquí
                    }
                }
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String text = editText.getText().toString().trim();
                    if (text.isEmpty()) {
                        editText.setError("Este campo es requerido");
                    } else {
                        // realizar validación adicional aquí
                    }
                }
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String text = editText.getText().toString().trim();
                    if (text.isEmpty()) {
                        editText.setError("Este campo es requerido");
                    } else {
                        // realizar validación adicional aquí
                    }
                }
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    String text = editText.getText().toString().trim();
                    if (text.isEmpty()) {
                        editText.setError("Este campo es requerido");
                    } else {
                        // realizar validación adicional aquí
                    }
                }
            }
        });*/

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

    }

    private boolean validacionesAll(String identificacion, String nombre, String correo, String clave, String direccion, String telefono){
        boolean EsValido = true;

        // Validación del correo electrónico
        if (TextUtils.isEmpty(correo) || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            EsValido = false;
        }

        // Validación de la cédula
        if (TextUtils.isEmpty(identificacion) || validarCedula(identificacion)) {
            EsValido = false;
        }

        //Validacion de telefono
        if (TextUtils.isEmpty(telefono) || validarTelefonoMovil(telefono)) {
            EsValido = false;
        }

        //Validacion de nombre
        if (TextUtils.isEmpty(nombre) || validarNombre(nombre)) {
            EsValido = false;
        }

        //Validacion de direccion
        if (TextUtils.isEmpty(direccion) || validarDireccion(direccion)) {
            EsValido = false;
        }

        //Validacion de clave
    /*    if (TextUtils.isEmpty(clave) || validarContrasena(clave) || clave == TverifiClave.getText().toString()) {
            EsValido = false;
        }*/
        return EsValido;
    }


    public static boolean validarCedula(String cedula) {
        // Verificar que la cadena de entrada tenga 10 caracteres
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        // Verificar que la cédula contenga solo dígitos numéricos
        try {
            Long.parseLong(cedula);
        } catch (NumberFormatException e) {
            return false;
        }

        // Obtener el último dígito de la cédula (dígito de control)
        int digitoControl = Character.getNumericValue(cedula.charAt(9));

        // Sumar los primeros 9 dígitos de la cédula
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            suma += digito;
        }

        // Calcular el dígito de control esperado
        int digitoControlEsperado = (10 - (suma % 10)) % 10;

        // Verificar que el dígito de control sea correcto
        return digitoControl == digitoControlEsperado;
    }

    public static boolean validarTelefonoMovil(String telefono) {
        // Verificar que la cadena de entrada tenga 10 caracteres
        if (telefono == null || telefono.length() != 10) {
            return false;
        }

        // Verificar que el número comience con el prefijo "09"
        if (!telefono.startsWith("09")) {
            return false;
        }

        // Verificar que la cadena contenga solo dígitos numéricos
        try {
            Long.parseLong(telefono);
        } catch (NumberFormatException e) {
            return false;
        }

        // Si todas las verificaciones pasaron, el número es válido
        return true;
    }

    public static boolean validarNombre(String nombre) {
        // Permitir letras mayúsculas y minúsculas, espacios, guiones y apóstrofes
        String regex = "^[a-zA-Z\\s\\-\\']+$";
        return nombre.matches(regex);
    }

    public static boolean validarDireccion(String direccion) {
        // Permitir letras y números, espacios, guiones y puntos
        String regex = "^[a-zA-Z0-9\\s\\-\\.]+$";
        return direccion.matches(regex);
    }

    public static boolean validarContrasena(String contrasena) {
        // La contraseña debe tener al menos una letra y al menos un número
        String regex = "^(?=.*[A-Za-z])(?=.*\\d).+$";
        return contrasena.matches(regex);
    }
}