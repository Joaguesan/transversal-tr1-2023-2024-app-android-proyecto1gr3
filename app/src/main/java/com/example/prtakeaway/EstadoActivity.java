package com.example.prtakeaway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class EstadoActivity extends AppCompatActivity implements CustomAdapter.OnPedidoItemClickListener{
    //private Socket mSocket;
    List<Pedidos.Pedido> pedidos = new ArrayList<>();
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);
        //mSocket.connect();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CustomAdapter(pedidos); // data es tu conjunto de datos
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TiendaAPI tiendaAPI = retrofit.create(TiendaAPI.class);
        SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        Call<List<Pedidos.Pedido>> call = tiendaAPI.getPedidos();
        call.enqueue(new Callback<List<Pedidos.Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedidos.Pedido>> call, Response<List<Pedidos.Pedido>> response) {
                if (response.isSuccessful()) {
                    pedidos = response.body();
                    String id = sharedPreferences.getString("id","");
                    pedidos.removeIf(pedido -> pedido.getEstado().equals("Tancades"));
                    pedidos.removeIf(pedido -> pedido.getIDCliente()!=Integer.parseInt(id));
                    adapter.actualizarPedidos(pedidos);
                }else{
                    Log.d("FALLO","fallo");
                }
            }

            @Override
            public void onFailure(Call<List<Pedidos.Pedido>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                Log.d("FALLO2", ""+t);
            }

        });
    }
    @Override
    public void onPedidoItemClick(Pedidos.Pedido pedido) {
        adapter = new CustomAdapter(this);
        recyclerView.setAdapter(adapter);
        mostrarDialogoDetallesPedido(pedido);
    }
    public void mostrarDialogoDetallesPedido(Pedidos.Pedido pedido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Detalles del Pedido");
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_detalles_pedido, null);
        builder.setView(dialogView);

        // Encuentra las TextViews por sus IDs
        TextView textViewNumeroPedido = dialogView.findViewById(R.id.textViewNumeroPedido);
        TextView textViewFechaPedido = dialogView.findViewById(R.id.textViewFechaPedido);
        TextView textViewComida = dialogView.findViewById(R.id.comida);

        // Establece el texto en las TextViews con la información del pedido
        textViewNumeroPedido.setText("Número de Pedido: " + String.valueOf(pedido.getIDPedido()));
        textViewFechaPedido.setText("Fecha del Pedido: " + pedido.getFechaPedido());
        textViewComida.setText(pedido.getNombreProducto());

        // Agrega más TextViews y establece su texto según los detalles del pedido

        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    /*private static final String ULR1 = "http://10.0.2.2:3000/";

    {
        try {
            mSocket = IO.socket(ULR1);
        } catch (URISyntaxException e) {
            Log.d("TAG","Fallo socket");
        }
    }
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String username;
                    String message;
                    try {
                        username = data.getString("username");
                        message = data.getString("message");
                    } catch (JSONException e) {
                        return;
                    }
                    // add the message to view
                    addMessage(username);
                }
                private void addMessage(String message) {
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(message);
                }
            });
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        //mSocket.off("new message", onNewMessage);
    }*/

}