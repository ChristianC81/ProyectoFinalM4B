package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class detalle_producto extends AppCompatActivity {

    String nombre="";
    String codigo="";
    String tipo="";
    String descripcion="";
    String stock="";
    String precio="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        //////PONER LOS DATOS EN LA VISTA


        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            nombre = extras.getString("nombre");
            codigo = extras.getString("prod_codigo");
            precio = extras.getString("prod_precio");
            descripcion = extras.getString("prod_descripcion");
        }

        TextView txtNombre = (TextView) findViewById(R.id.txtNombreProducto);
        TextView txttipo = (TextView) findViewById(R.id.txtTipo);
        TextView txtdescripcion = (TextView) findViewById(R.id.txtDetalle);
        TextView txtcod = (TextView) findViewById(R.id.txtProdCod);

        txtNombre.setText(nombre);
        txtdescripcion.setText(descripcion);
        txtcod.setText(codigo);

        ///////FIN DEL PROCESEISHON

        ImageButton info2 = findViewById(R.id.imageButtonInicio4);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Inicio_Sesion.class);
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
}