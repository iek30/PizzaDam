package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, contrasena;
    private ConexionBD usuarioDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = findViewById(R.id.usuario);
        contrasena = findViewById(R.id.contrasena);
        setTema();
        usuarioDBHelper = new ConexionBD(this,"Pizzeria",null,3);
        usuarioDBHelper.abrir();
    }

    private class AutenticarUsuarioTask extends AsyncTask<Void, Void, Boolean> {
        TextView usuarioT = findViewById(R.id.usuario);
        TextView contrasenaT = findViewById(R.id.contrasena);
        String usuario = usuarioT.getText().toString().trim();
        String contrasena = contrasenaT.getText().toString().trim();
        @Override
        protected Boolean doInBackground(Void... voids) {
            boolean autenticado = false;
            if (usuarioDBHelper.obtenerUsuarioPorCredenciales(usuario, contrasena)) autenticado=true;
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

    public void cambiarACrearCuenta(View v){
        try {
            TextView usuarioT = findViewById(R.id.usuario);
            TextView contrasenaT = findViewById(R.id.contrasena);
            String usuario = usuarioT.getText().toString().trim();
            String contrasena = contrasenaT.getText().toString().trim();
            usuarioDBHelper.insertarPersona(usuario, contrasena);
            Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}