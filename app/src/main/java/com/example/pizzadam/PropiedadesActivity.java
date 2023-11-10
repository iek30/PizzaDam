package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        }

    }

    public void agregar(View v){
        Servicio.getInstance().getPedido().add(pizza);
        //Intent i = new Intent(PropiedadesActivity.this,PedidoActivity.class);
        //startActivity(i);
        finish();
    }
}