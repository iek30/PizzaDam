package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PropiedadesActivity extends AppCompatActivity {

    private Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propiedades);

        Intent i = getIntent();

        if (i.hasExtra("pizza")){
            pizza = (Pizza) i.getSerializableExtra("pizza");

            ImageView imagen = findViewById(R.id.imagen);
            imagen.setImageResource(pizza.getImgId());

            TextView nombre = findViewById(R.id.idNombre);
            nombre.setText(pizza.getName());

            Button btn = findViewById(R.id.idPrecio);
            btn.setText(pizza.getPrecio() + "€");

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, pizza.getIngredientes());
            ListView lista = findViewById(R.id.miLista);
            lista.setAdapter(arrayAdapter);

            String[] tamanos = {"PEQUEÑA","MEDIANA","FAMILIAR"};

            Spinner spinner = findViewById(R.id.spinnerBase);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamanos);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    pizza.setTamano(tamanos[i]);
                    pizza.ajustarPrecio();
                    btn.setText(pizza.getPrecio() + "€");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

    }

    public void agregar(View v){
        pizza.ajustarPrecio();
        Servicio.getInstance().getPedido().add(pizza);

        //Guardo el última pizza
        SharedPreferences preferencias = getSharedPreferences("archivoGuardado", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        String cadena = "\n" + pizza.getName() + "  " + pizza.getPrecio() + "€" + " TAMAÑO " + pizza.getTamano() + "\n Ingredientes: \n";
        for(String ingrediente: pizza.getIngredientes()){
            cadena+=" -" + ingrediente + "\n";
        }
        editor.putString("ultimaPizza",cadena);
        editor.commit();

        Toast.makeText(this, "Pizza añadida a tu pedido.", Toast.LENGTH_LONG).show();
        finish();
    }

    public void salir(View v){
        finish();
    }
}