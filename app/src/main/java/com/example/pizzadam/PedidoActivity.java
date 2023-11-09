package com.example.pizzadam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PedidoActivity extends AppCompatActivity {

    Servicio servicio = new Servicio();
    private Pizza[] listData = servicio.copiaListadoPizzas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        rellenarRecyclarView(listData);
    }

    public void volver(View v){
        startActivity(new Intent(PedidoActivity.this,MenuActivity.class));
    }

    public void rellenarRecyclarView(Pizza[] lista){
        Pizza[] listData = lista;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this, listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onClick(View v){
        if (v.getTag() instanceof Pizza){
            Pizza pizza = (Pizza) v.getTag();
            Intent i = new Intent(PedidoActivity.this, PropiedadesActivity.class);
            i.putExtra("pizza",pizza);
            startActivity(i);
        }
    }

}