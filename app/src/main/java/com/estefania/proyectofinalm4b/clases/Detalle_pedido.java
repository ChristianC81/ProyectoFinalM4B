package com.estefania.proyectofinalm4b.clases;

public class Detalle_pedido {
private Integer deta_id;
private Integer deta_cantidad;
private Double deta_precio_total;
private Integer det_pro_id;

    public Detalle_pedido() {
    }

    public Integer getDeta_id() {
        return deta_id;
    }

    public void setDeta_id(Integer deta_id) {
        this.deta_id = deta_id;
    }

    public Integer getDeta_cantidad() {
        return deta_cantidad;
    }

    public void setDeta_cantidad(Integer deta_cantidad) {
        this.deta_cantidad = deta_cantidad;
    }

    public Double getDeta_precio_total() {
        return deta_precio_total;
    }

    public void setDeta_precio_total(Double deta_precio_total) {
        this.deta_precio_total = deta_precio_total;
    }

    public Integer getDet_pro_id() {
        return det_pro_id;
    }

    public void setDet_pro_id(Integer det_pro_id) {
        this.det_pro_id = det_pro_id;
    }
}
