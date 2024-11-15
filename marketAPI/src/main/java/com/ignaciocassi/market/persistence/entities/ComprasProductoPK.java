package com.ignaciocassi.market.persistence.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
//Clave privada compuesta
public class ComprasProductoPK implements Serializable {

    @Column(name="id_compra")
    private Integer idCompra;

    @Column(name="id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
}
