package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}