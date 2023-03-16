package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.estefania.proyectofinalm4b.Adapters.Adapter_Compras;
import com.estefania.proyectofinalm4b.Interfaces_Comunicacion.Comunicacion;
import com.estefania.proyectofinalm4b.Metodos.Metodos_Carritos;
import com.estefania.proyectofinalm4b.clases.Detalle_pedido;
import com.estefania.proyectofinalm4b.clases.producto;

import java.util.List;

public class activity_compras extends AppCompatActivity {
    Adapter_Compras adapter1;
    RecyclerView productos_carrito;
    List<Detalle_pedido> detalles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);
        init();
        initAdapter();

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
    private void init (){
        productos_carrito = findViewById(R.id.listprod2);
        //btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra);

    }
    private void initAdapter() {
        adapter1 = new Adapter_Compras(Metodos_Carritos.getDetallePedidos(), getApplicationContext());
        productos_carrito.setLayoutManager(new LinearLayoutManager(this));
        productos_carrito.setAdapter(adapter1);
    }
    public void eliminarDetalle(int idP) {
        Metodos_Carritos.eliminar(idP);
        this.adapter1.notifyDataSetChanged();
    }
}