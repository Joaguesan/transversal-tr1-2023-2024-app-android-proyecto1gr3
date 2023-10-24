package com.example.prtakeaway;

public class DetallePedido {
    private int IDDetalle;
    private int IDPedido;
    private int IDProducto;
    private int Cantidad;

    public DetallePedido(int IDDetalle, int IDPedido, int IDProducto, int Cantidad) {
        this.IDDetalle = IDDetalle;
        this.IDPedido = IDPedido;
        this.IDProducto = IDProducto;
        this.Cantidad = Cantidad;
    }

    public int getIDDetalle() {
        return IDDetalle;
    }

    public void setIDDetalle(int IDDetalle) {
        this.IDDetalle = IDDetalle;
    }

    public int getIDPedido() {
        return IDPedido;
    }

    public void setIDPedido(int IDPedido) {
        this.IDPedido = IDPedido;
    }

    public int getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(int IDProducto) {
        this.IDProducto = IDProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    }