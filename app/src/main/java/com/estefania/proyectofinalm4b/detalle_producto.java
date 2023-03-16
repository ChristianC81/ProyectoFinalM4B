package com.estefania.proyectofinalm4b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.estefania.proyectofinalm4b.Adapters.Adapter_Compras;
import com.estefania.proyectofinalm4b.clases.producto;

import java.util.ArrayList;
import java.util.List;

public class detalle_producto extends AppCompatActivity {

    String nombre="";
    String descripcion="";
    String precio="";
    String codigo="";
    String tipo="";
    String stock="";  //
    producto producto1;
    List<producto> detallePedidos = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        //////PONER LOS DATOS EN LA VISTA
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            nombre = extras.getString("nombre");
            descripcion = extras.getString("descripcion");
            precio = extras.getString("precio");
            codigo = extras.getString("codigo");
            tipo = extras.getString("tipo");
            stock = extras.getString("stock");
        }

        TextView txtNombre = (TextView) findViewById(R.id.txtNombreProducto);
        TextView txtdescripcion = (TextView) findViewById(R.id.txtDetalle);
        TextView txtprecio = (TextView) findViewById(R.id.txtPrecio);
        TextView txttipo = (TextView) findViewById(R.id.txtTipo);
        TextView txtcod = (TextView) findViewById(R.id.txtProdCod);
        TextView txtstock = (TextView) findViewById(R.id.txtStock);


        txtNombre.setText(nombre);
        txtdescripcion.setText(descripcion);
        txtprecio.setText("$  "+precio);
        txtcod.setText(codigo);
        txttipo.setText(tipo);
        txtstock.setText(stock);



        ///////FIN DEL PROCESEISHON

        ImageButton info2 = findViewById(R.id.imageButtonInicio4);
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        Button infor6 = findViewById(R.id.btnAgregarCarro);
        infor6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), activity_compras.class);
                producto detallePedido = new producto();
                detallePedido.getProd_nombre();
                detallePedido.getProd_descripcion();
                detallePedido.getProd_preciounitario();
                agregarPlatillos(detallePedido);

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

    //Metodo para agregar los procutos al carrito
    public  String agregarPlatillos(producto detallePedido) {
        int index = 0;
        boolean b = false;
        for (producto dp : detallePedidos) {
            if (dp.getProd_id() == detallePedido.getProd_id()) {
                detallePedidos.set(index, detallePedido);
                b = true;
                return "El platillo ha sido agregado al carrito, se actualizará la cantidad";
            }
            index++;
        }
        if (!b) {
            detallePedidos.add(detallePedido);
            return "El platillo ha sido agregado al carrito con éxito";
        }
        return ". . . . ";
    }
}