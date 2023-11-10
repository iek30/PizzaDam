package com.example.pizzadam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PedidoActivity extends AppCompatActivity {

    private Pizza[] listData = Servicio.getInstance().copiaListadoPizzas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        rellenarRecyclarView(listData);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas salir? Se perderá el progreso de tu pedido.");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario hace clic en "Sí", aquí puedes agregar la lógica para continuar
                Servicio.getInstance().crearPedido();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario hace clic en "No", aquí puedes agregar la lógica para cancelar
            }
        });

        // Crea y muestra el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public void showConfirmationDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas salir? Se perderá el progreso de tu pedido.");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario hace clic en "Sí", aquí puedes agregar la lógica para continuar
                Servicio.getInstance().crearPedido();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario hace clic en "No", aquí puedes agregar la lógica para cancelar
            }
        });

        // Crea y muestra el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void rellenarRecyclarView(Pizza[] lista){
        Pizza[] listData = lista;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(this, listData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void pagar(View v){
        double t= Servicio.getInstance().calcularTotal();
        startActivity(new Intent(PedidoActivity.this,FinalizarActivity.class));
    }

    public void onClick(View v){
        if (v.getTag() instanceof Pizza){
            Pizza pizza = (Pizza) v.getTag();
            if (pizza.getName()!="Pizza al gusto"){
                Intent i = new Intent(PedidoActivity.this, PropiedadesActivity.class);
                i.putExtra("pizza",pizza);
                startActivity(i);
            }
            else{
                Intent i = new Intent(PedidoActivity.this, PersonalizadaActivity.class);
                i.putExtra("pizza",pizza);
                startActivity(i);
            }
        }
    }

}