package com.example.prtakeaway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    String URL = ""; //variable con la url a la que nos conectamos
    public Retrofit retrofit; //variable para el retrofit
Button btnLogin;
EditText etUser, etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        etPass = findViewById(R.id.etPass);
        etUser = findViewById(R.id.etUser);

        //definimos lo que hace el boton de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cogemos los valores de los campos que ha rellenado el usuario
                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();
               // login(user, pass); //llamamos a la funcion
                loginPrueba();

                //Toast.makeText(LoginActivity.this, "User: "+user+" Pass: "+pass, Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("MisPreferenciasPrueba", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nombre", "nombre prueba");
        editor.putString("apellido","apellido prueba");
        editor.putInt("idCliente",7);
        editor.putString("contraseña","contraseña prueba");
        editor.putString("correo", "correo prueba");
        editor.putString("direccion", "direccion prueba");
        editor.putString("telefono","telefono prueba");

        editor.apply();
    }

    //funcion que comprobara si el usuario puede entrar
    public void login(String user, String pass){
        //llamamos a retrofit
        retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Usuario usuario = new Usuario(user, pass); //creamos un objeto usuario

        TiendaAPI tiendaAPI = retrofit.create(TiendaAPI.class);

        Call<RespuestaUsuario> call = tiendaAPI.login(usuario); //hacemos una llamada al retrofit

        call.enqueue(new Callback<RespuestaUsuario>() {
            @Override
            public void onResponse(Call<RespuestaUsuario> call, Response<RespuestaUsuario> response) {
                if (response.isSuccessful()){
                    RespuestaUsuario respuesta = response.body();
                    Log.d("prueba Response",respuesta.toString());

                    //usamos sharedPreferences para que sean variables globales
                    SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombre", respuesta.getNombre().toString());
                    editor.putString("apellido",respuesta.getApellido().toString());
                    editor.putInt("idCliente",respuesta.getIdUsuario());
                    editor.putString("contraseña",respuesta.getContrasena().toString());
                    editor.putString("correo", respuesta.getCorreo().toString());
                    editor.putString("direccion", respuesta.getDireccion().toString());
                    editor.putString("telefono", respuesta.getTelefono().toString());

                    editor.apply();

                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginActivity.this, "Usuario incorrecto :c", Toast.LENGTH_SHORT).show();                }
            }

            @Override
            public void onFailure(Call<RespuestaUsuario> call, Throwable t) {
                Log.d("error onFailure", "error onFailure "+t.getMessage()+" "+t+" "+ call);
            }
        });
    }

    public void loginPrueba(){

        String mail = etUser.getText().toString().trim();
        Pattern pattern = Patterns.EMAIL_ADDRESS;

        if(pattern.matcher(mail).matches()){
            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "El mail no es valido!", Toast.LENGTH_SHORT).show();

        }
    }
}