package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        Pizza[] listData = new Pizza[]{
                new Pizza(null,"Pizza 1",null,6),
                new Pizza(null,"Pizza 2",null,6),
                new Pizza(null,"Pizza 3",null,6),
                new Pizza(null,"Pizza 4",null,6),
                new Pizza(null,"Pizza 5",null,6),
                new Pizza(null,"Pizza 6",null,6),
                new Pizza(null,"Pizza 7",null,6)
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this, listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}