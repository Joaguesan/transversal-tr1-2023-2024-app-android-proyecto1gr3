package com.example.prtakeaway;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pedidos {
    private List<Pedido> pedidos;

    public List<Pedido> getPedidos() {
        return pedidos;
    }


    public static class Pedido {
        @SerializedName("IDPedido")
        private int IDPedido;
        @SerializedName("IDCliente")
        private int IDCliente;
        @SerializedName("FechaPedido")
        private String FechaPedido;
        @SerializedName("NombreProducto")
        private String NombreProducto;
        @SerializedName("PrecioUnitario")
        private double PrecioUnitario;
        @SerializedName("Estado")
        private String Estado;
        @SerializedName("Comentario")
        private String Comentario;
        @SerializedName("Cantidad")
        private int Cantidad;

        public int getIDPedido() {
            return IDPedido;
        }

        public int getIDCliente() {
            return IDCliente;
        }

        public String getFechaPedido() {
            return FechaPedido;
        }


        public double getTotal() {
            return PrecioUnitario;
        }

        public String getNombreProducto() {
            return NombreProducto;
        }

        public double getPrecioUnitario() {
            return PrecioUnitario;
        }

        public int getCantidad() {
            return Cantidad;
        }

        public String getEstado() {
            return Estado;
        }


        public String getComentario() {
            return Comentario;
        }
    }
}


