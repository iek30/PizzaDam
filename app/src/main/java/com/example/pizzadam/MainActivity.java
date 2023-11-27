package com.example.pizzadam;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;
    private UsuarioDBHelper usuarioDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);
        setTema();
        usuarioDBHelper = new UsuarioDBHelper(this);
        try {
            usuarioDBHelper.abrir();
            usuarioDBHelper.agregarUsuario("iek", "123");
            usuarioDBHelper.cerrar();
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Error al agregar usuario de ejemplo", Toast.LENGTH_SHORT).show();
        }
    }

    private class AutenticarUsuarioTask extends AsyncTask<Void, Void, Boolean> {
        TextView usuarioT = findViewById(R.id.usuario);
        TextView contrasenaT = findViewById(R.id.contrasena);
        String usuario = usuarioT.getText().toString().trim();
        String contrasena = contrasenaT.getText().toString().trim();
        @Override
        protected Boolean doInBackground(Void... voids) {
            usuarioDBHelper.abrir();
            Cursor cursor = usuarioDBHelper.obtenerUsuarioPorCredenciales(usuario, contrasena);
            boolean autenticado = cursor != null && cursor.moveToFirst();
            if (cursor != null) {
                cursor.close();
            }
            usuarioDBHelper.cerrar();
            return autenticado;
        }

        @Override
        protected void onPostExecute(Boolean autenticado) {
            if (autenticado) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void realizarAcceso(View v){
        new AutenticarUsuarioTask().execute();
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