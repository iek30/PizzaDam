package com.example.pizzadam;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);
        setTema();
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

    public void setTema(){
        SharedPreferences preferencias = getSharedPreferences("archivoGuardado", Context.MODE_PRIVATE);
        String estado = preferencias.getString("tema","");
        ConstraintLayout fondo = findViewById(R.id.constraint);
        CardView card = findViewById(R.id.card);
        if (estado.equals("ON")) {
            fondo.setBackgroundColor(Color.parseColor("#FF0000"));
            card.setBackgroundColor(Color.parseColor("#FF0000"));
            fondo.setBackgroundTintList(null);
        }
        else {
            fondo.setBackgroundColor(Color.parseColor("#FF0000"));
        }
    }

    public void borrarTodo(View v){
        usuario.setText("");
        contrasena.setText("");
    }

}