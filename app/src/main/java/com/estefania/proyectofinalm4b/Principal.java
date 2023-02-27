package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Button info = findViewById(R.id.botoniniciarsesion);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Inicio_Sesion.class);
                startActivityForResult(intent, 0);
            }
        });

        Button infor = findViewById(R.id.button4);
        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), registro_main.class);
                startActivityForResult(intent, 0);
            }
        });

    }
}