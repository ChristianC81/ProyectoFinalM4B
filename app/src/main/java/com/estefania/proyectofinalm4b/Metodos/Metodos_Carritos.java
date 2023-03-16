package com.estefania.proyectofinalm4b.Metodos;

import com.estefania.proyectofinalm4b.clases.Detalle_pedido;

import java.util.ArrayList;
import java.util.List;

public class Metodos_Carritos {

    private static final ArrayList<Detalle_pedido> detallePedidos = new ArrayList<>();

    //Método para agregar productos al carrito(bolsa)
    public static String agregarPlatillos(Detalle_pedido detallePedido) {
        int index = 0;
        boolean b = false;
        for (Detalle_pedido dp : detallePedidos) {
            if (dp.getProducto_agregar().getProd_id() == detallePedido.getProducto_agregar().getProd_id()) {
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

    //Método para eliminar un platillo del carrito(bolsa)
    public static void eliminar(final int idp) {
        Detalle_pedido dpE = null;
        for (Detalle_pedido dp : detallePedidos) {
            if (dp.getProducto_agregar().getProd_id() == idp) {
                dpE = dp;
                break;
            }
        }
        if (dpE != null) {
            detallePedidos.remove(dpE);
            System.out.println("Se elimino el platillo del detalle de pedido");
        }
    }

    //Método para conseguir los detalles del pedido
    public static ArrayList<Detalle_pedido> getDetallePedidos() {
        return detallePedidos;
    }

    //Método para limpiar
    public static void limpiar() {
        detallePedidos.clear();
    }

}
