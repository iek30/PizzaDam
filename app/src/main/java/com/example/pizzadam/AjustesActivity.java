package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        comprobarEstado();
    }

    public void salir(View v){
        finish();
    }

    public void comprobarEstado(){
        SharedPreferences preferencias = getSharedPreferences("archivoGuardado", Context.MODE_PRIVATE);
        String estado = preferencias.getString("tema","");
        Switch tema = findViewById(R.id.switch2);
        if (estado.equals("ON")) {
            tema.setChecked(true);
        }
        else tema.setChecked(false);

    }

    public void guardarCambios(View v){
        Switch tema = findViewById(R.id.switch2);
        if (tema.isChecked()) {
            SharedPreferences preferencias = getSharedPreferences("archivoGuardado", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = preferencias.edit();
            edit.putString("tema","ON");
            edit.commit();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        else {
            SharedPreferences preferencias = getSharedPreferences("archivoGuardado", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = preferencias.edit();
            edit.putString("tema","OFF");
            edit.commit();
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        finish();
    }
}