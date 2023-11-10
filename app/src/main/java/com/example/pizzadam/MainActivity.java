package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);
    }

    public void realizarAcceso(View v){

        String textoUsuario = usuario.getText().toString();
        String textoContrasena = contrasena.getText().toString();

        if (Servicio.getInstance().comprobarAcceso(textoUsuario, textoContrasena)){
            startActivity(new Intent(MainActivity.this,MenuActivity.class));
        }
        else{
            Toast.makeText(this, "Usuario y contraseña no válidos.",Toast.LENGTH_LONG).show();
        }
    }

}