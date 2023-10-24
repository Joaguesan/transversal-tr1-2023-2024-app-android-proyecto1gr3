package com.example.prtakeaway;


public class Pedido {
    private int IDPedido;
    private int IDCliente;
    private String FechaPedido;
    private double Total;
    private String Estado;
    private String Comentario;

    public Pedido(int IDPedido, int IDCliente, String FechaPedido, double Total, String Estado, String Comentario) {
        this.IDPedido = IDPedido;
        this.IDCliente = IDCliente;
        this.FechaPedido = FechaPedido;
        this.Total = Total;
        this.Estado = Estado;
        this.Comentario = Comentario;
    }

    public int getIDPedido() {
        return IDPedido;
    }

    public void setIDPedido(int IDPedido) {
        this.IDPedido = IDPedido;
    }

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }

    public String getFechaPedido() {
        return FechaPedido;
    }

    public void setFechaPedido(String FechaPedido) {
        this.FechaPedido = FechaPedido;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }
}
