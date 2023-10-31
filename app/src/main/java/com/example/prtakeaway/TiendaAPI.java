package com.example.prtakeaway;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TiendaAPI {
    @POST("/login")
        Call<RespuestaUsuario> login(@Body Usuario usuario);

    @GET("/getProducts")
        Call<List<Productos.Producto>> getProductos();
    @GET("/getOrderC")
    Call<List<Pedidos.Pedido>> getPedidos();

    @POST("/enviarPedido")
    Call<Void> enviarPedido(@Body Pedidos.Pedido pedido);
}
