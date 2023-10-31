package com.example.pizzadam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        MyListData[] listData = new MyListData[]{
                new MyListData("Pizza Carbonara", R.drawable.cerrar_icono),
                new MyListData("Pizza Chicharruners", R.drawable.cerrar_icono),
                new MyListData("Pizza Mexicana", R.drawable.cerrar_icono),
                new MyListData("Pizza Ranchera", R.drawable.cerrar_icono),
                new MyListData("Pizza Ranchera", R.drawable.cerrar_icono),
                new MyListData("Pizza Ranchera", R.drawable.cerrar_icono),
                new MyListData("Pizza Ranchera", R.drawable.cerrar_icono)
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this, listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}